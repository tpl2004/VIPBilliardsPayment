-- Viết các thủ tục sau:
/*
1.Viết procedure proc_ThongKeDoanhThuTheoNgay
			@NgayBatDau date,
			@NgayKetThuc date
thực hiện thống kê doanh thu theo từng ngày từ ngày bắt đầu đến ngày kết thúc, thông tin bao gồm ngày, doanh thu, nếu ngày không có doanh thu thì hiển thị doanh thu là 0
*/
IF EXISTS (SELECT * FROM sys.objects WHERE name = 'proc_ThongKeDoanhThuTheoNgay')
    DROP PROCEDURE proc_ThongKeDoanhThuTheoNgay
GO
CREATE PROCEDURE proc_ThongKeDoanhThuTheoNgay
    @NgayBatDau DATE,
    @NgayKetThuc DATE
AS
BEGIN
    SET NOCOUNT ON;

	DECLARE @date date = @NgayBatDau
	DECLARE @temptable table 
	(
		[Ngày] date
	)
	WHILE (@date <= @NgayKetThuc)
		BEGIN
			INSERT INTO @temptable([Ngày]) VALUES (@date)
			
			SET @date = DATEADD(day, 1, @date)	
		END
	
	SELECT		t1.Ngày,
				ISNULL(t2.DoanhThu, 0) AS [Doanh thu]
	FROM		@temptable AS t1
		LEFT JOIN (
				SELECT		CAST(h.ThoiDiemVao AS DATE) AS [Ngày],
							ISNULL(SUM(h.TongTien), 0) AS DoanhThu
				FROM		HoaDon AS h
				WHERE		CAST(h.ThoiDiemVao AS DATE)0 BETWEEN @NgayBatDau AND @NgayKetThuc
				GROUP BY	CAST(h.ThoiDiemVao AS DATE)
				) as t2 ON t1.Ngày = t2.Ngày
END
GO
/*
2.Viết procedure proc_UpdateCapDoHoiVien
			@MaHoiVien INT
thực hiện công việc kiểm tra và update cấp độ của hội viên như sau:
	- Nếu số giờ chơi >= số giờ chơi cao nhất:
		+ Nếu cấp độ hiện tại của hội viên là cấp độ ứng với số giờ chơi cao nhất thì không cập nhật
		+ Ngược lại thì cập nhật sang cấp độ mới là cấp độ ứng với số giờ chơi cao nhất
	-> Break
	- Nếu số giờ chơi >= số giờ chơi cao thứ 2:
		+ Nếu cấp độ hiện tại của hội viên là cấp độ ứng với số giờ chơi cap thứ 2 thì không cập nhật
		+ Ngược lại thì cập nhật sang cấp độ mới là cấp độ ứng với số giờ chơi cao thứ 2
	-> Break
	- Ngược lại thì thực hiện tương tự cho các cấp độ tiếp theo...
*/
IF EXISTS (SELECT * FROM sys.objects WHERE name = 'proc_UpdateCapDoHoiVien')
    DROP PROCEDURE proc_UpdateCapDoHoiVien
GO
CREATE PROCEDURE proc_UpdateCapDoHoiVien
    @MaHoiVien INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @SoGioChoi FLOAT, @CapDoHienTai INT, @CapDoMoi INT;

    SELECT @SoGioChoi = SoGioChoi, @CapDoHienTai = CapDo
    FROM HoiVien
    WHERE MaHoiVien = @MaHoiVien;

    SELECT TOP 1 @CapDoMoi = CapDo
    FROM CapDoHoiVien
    WHERE SoGioChoi <= @SoGioChoi
    ORDER BY SoGioChoi DESC;

    IF @CapDoMoi IS NOT NULL AND @CapDoMoi <> @CapDoHienTai
    BEGIN
        UPDATE HoiVien
        SET CapDo = @CapDoMoi
        WHERE MaHoiVien = @MaHoiVien;
    END
END
GO
--II.	Viết các trigger sau:
/*
1. Viết trigger trg_Insert_HoaDon bắt lệnh insert trên bảng HoaDon, thực hiện update TrangThai của bàn bida trong hóa đơn vừa được thêm vào bảng HoaDon thành giá trị 1.
*/
IF EXISTS (SELECT * FROM sys.objects WHERE name = 'trg_Insert_HoaDon')
    DROP TRIGGER trg_Insert_HoaDon
GO
CREATE TRIGGER trg_Insert_HoaDon
ON HoaDon
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE	BanBida
    SET		TrangThai = 1
    FROM	BanBida B
     JOIN	inserted I ON B.SoBan = I.SoBan;
END
GO
/*
2.	Viết trigger trg_Update_HoaDon bắt lệnh update trên bảng HoaDon với cột TrangThai (IF UPDATE(TrangThai)), thực hiện update TrangThai của bàn bida có hóa đơn vừa mới được thanh toán thành 0, update số giờ chơi của hội viên (nếu có) của hóa đơn vừa thanh toán = số giờ chơi cũ + giờ chơi trong hóa đơn.
*/
IF EXISTS (SELECT * FROM sys.objects WHERE name=N'trg_Update_HoaDon')
	DROP TRIGGER trg_Update_HoaDon
GO
CREATE TRIGGER trg_Update_HoaDon
ON HoaDon
AFTER UPDATE
AS
BEGIN
	SET NOCOUNT ON;

	IF (UPDATE(TrangThai))
		BEGIN
			UPDATE		BanBida
			SET			TrangThai = 0
			FROM		inserted AS i
				JOIN	BanBida AS b ON i.SoBan=b.SoBan
			WHERE		i.TrangThai = 1

			UPDATE		HoiVien
			SET			SoGioChoi =hv.SoGioChoi + ISNULL(i.SoGioChoi, 0)
			FROM		inserted AS i
				JOIN	HoiVien AS hv ON i.MaHoiVien=hv.MaHoiVien
			WHERE		i.TrangThai = 1 AND i.MaHoiVien IS NOT NULL
		END
END
GO
/*
3.	Viết trigger trg_Update_HoiVien bắt lệnh update trên bảng HoiVien với cột SoGioChoi (IF UPDATE(SoGioChoi)) thực hiện gọi thủ tục proc_UpdateCapDoHoiVien với tham số của proc là mã hội viên vừa mới được update.
*/
IF EXISTS (SELECT * FROM sys.objects WHERE name=N'trg_Update_HoiVien')
	DROP TRIGGER trg_Update_HoiVien
GO
CREATE TRIGGER trg_Update_HoiVien
ON HoiVien
AFTER UPDATE
AS
BEGIN
	SET NOCOUNT ON;

	IF (UPDATE(SoGioChoi))
		BEGIN
			DECLARE @MaHoiVien INT

			DECLARE		Cur CURSOR FOR
			SELECT		MaHoiVien
			FROM		inserted

			OPEN Cur
			FETCH NEXT FROM Cur INTO @MaHoiVien

			WHILE(@@FETCH_STATUS = 0)
				BEGIN
					EXEC proc_UpdateCapDoHoiVien @MaHoiVien
					FETCH NEXT FROM Cur INTO @MaHoiVien
				END
			CLOSE Cur
			DEALLOCATE Cur

		END
END
GO
/*
4.	Viết trigger trg_Insert_CapDoHoiVien bắt lệnh insert trên bảng CapDoHoiVien thực hiện update cấp độ hội viên cho tất cả các hội viên hiện có (duyệt qua tất cả hội viên và sử dụng thủ tục proc_UpdateCapDoHoiVien đã tạo).
*/
IF EXISTS (SELECT * FROM sys.objects WHERE name=N'trg_Insert_CapDoHoiVien')
	DROP TRIGGER trg_Insert_CapDoHoiVien
GO
CREATE TRIGGER trg_Insert_CapDoHoiVien
ON CapDoHoiVien
AFTER INSERT
AS
BEGIN
	SET NOCOUNT ON;
	
	DECLARE @MaHoiVien INT

	DECLARE	Cur CURSOR FOR
	SELECT	MaHoiVien FROM	HoiVien

	OPEN Cur
	FETCH NEXT FROM Cur INTO @MaHoiVien

	WHILE(@@FETCH_STATUS = 0)
		BEGIN
			EXEC proc_UpdateCapDoHoiVien @MaHoiVien
			FETCH NEXT FROM Cur INTO @MaHoiVien
		END

	CLOSE Cur
	DEALLOCATE Cur
END 
GO
/*
5.	Viết trigger trg_Update_CapDoHoiVien bắt lệnh update trên bảng CapDoHoiVien thực hiện update cấp độ hội viên cho tất cả các hội viên hiện có (duyệt qua tất cả hội viên và sử dụng thủ tục proc_UpdateCapDoHoiVien đã tạo).
*/
IF EXISTS (SELECT * FROM sys.objects WHERE name = N'trg_Update_CapDoHoiVien')
	DROP TRIGGER trg_Update_CapDoHoiVien
GO
CREATE TRIGGER trg_Update_CapDoHoiVien
ON CapDoHoiVien
AFTER UPDATE 
AS
BEGIN
	DECLARE @MaHoiVien INT

	DECLARE Cur CURSOR FOR
	SELECT MaHoiVien FROM HoiVien

	OPEN Cur
	FETCH NEXT FROM Cur INTO @MaHoiVien

	WHILE(@@FETCH_STATUS = 0)
		BEGIN
			EXEC proc_UpdateCapDoHoiVien @MaHoiVien
			FETCH NEXT FROM Cur INTO @MaHoiVien
		END

	CLOSE Cur
	DEALLOCATE Cur
END