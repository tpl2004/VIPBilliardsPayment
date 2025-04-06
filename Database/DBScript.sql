USE [VIPBilliardsPaymentDB]
GO

--MatHang
CREATE TABLE [dbo].[MatHang]
(
	[MaHang] [int] NOT NULL IDENTITY,
	[TenHang] [nvarchar](100) NOT NULL,
	[DonGia] [float] NOT NULL,

	CONSTRAINT [PK_MatHang] PRIMARY KEY CLUSTERED
	(
		[MaHang] ASC
	) ON [PRIMARY],
	CONSTRAINT [CHK_MatHang_DonGia] CHECK (DonGia >= 0)

) ON [PRIMARY]
GO

--ThuNgan
CREATE TABLE [dbo].[ThuNgan]
(
	[MaThuNgan] [int] NOT NULL IDENTITY,
	[HoTen] [nvarchar](100) NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[GioiTinhNu] [bit] NOT NULL,
	[Email] [nvarchar](100) NOT NULL,
	[SoDienThoai] [nvarchar](50) NOT NULL,
	[SoCCCD] [nvarchar](50) NOT NULL,
	[TenDangNhap] [nvarchar](100) NOT NULL,
	[MatKhau] [nvarchar](100) NOT NULL,

	CONSTRAINT [PK_ThuNgan] PRIMARY KEY CLUSTERED
	(
		[MaThuNgan] ASC
	) ON [PRIMARY],
	CONSTRAINT [UQ_ThuNgan_SoCCCD] UNIQUE (SoCCCD),
	CONSTRAINT [UQ_ThuNgan_TenDangNhap] UNIQUE (TenDangNhap),
	CONSTRAINT [CHK_ThuNgan_SoDienThoai] CHECK (LEN(SoDienThoai) = 10 AND SoDienThoai LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	CONSTRAINT [CHK_ThuNgan_SoCCCD] CHECK (LEN(SoCCCD) = 12 AND SoCCCD LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	CONSTRAINT [CHK_ThuNgan_TenDangNhap] CHECK (LEN(TenDangNhap) >= 3),
	CONSTRAINT [CHK_ThuNgan_MatKhau] CHECK (LEN(MatKhau) >= 3)
) ON [PRIMARY]
GO


--ChuQuan
CREATE TABLE [dbo].[ChuQuan]
(
	[MaChuQuan] [int] NOT NULL IDENTITY,
	[TenDangNhap] [nvarchar](100) NOT NULL,
	[MatKhau] [nvarchar](100) NOT NULL,

	CONSTRAINT [PK_ChuQuan] PRIMARY KEY CLUSTERED
	(
		[MaChuQuan] ASC
	) ON [PRIMARY],
	CONSTRAINT [UQ_ChuQuan_TenDangNhap] UNIQUE (TenDangNhap),
	CONSTRAINT [CHK_ChuQuan_TenDangNhap] CHECK (LEN(TenDangNhap) >= 3),
	CONSTRAINT [CHK_ChuQuan_MatKhau] CHECK (LEN(MatKhau) >= 3)
) ON [PRIMARY]
GO

--LoaiBan
CREATE TABLE [dbo].[LoaiBan]
(
	[LoaiBan] [int] NOT NULL IDENTITY,
	[TenLoai] [nvarchar](100) NOT NULL,
	[DonGia] [float] NOT NULL,

	CONSTRAINT [PK_LoaiBan] PRIMARY KEY CLUSTERED
	(
		[LoaiBan] ASC
	) ON [PRIMARY],
	CONSTRAINT [UQ_LoaiBan_TenLoai] UNIQUE (TenLoai),
	CONSTRAINT [CHK_LoaiBan_DonGia] CHECK (DonGia >= 0)
) ON [PRIMARY]
GO

--BanBida
CREATE TABLE [dbo].[BanBida]
(
	[SoBan] [int] NOT NULL IDENTITY,
	[TrangThai] [int] NOT NULL DEFAULT(0),
	[LoaiBan] [int] NOT NULL,

	CONSTRAINT [PK_BanBida] PRIMARY KEY CLUSTERED
	(
		[SoBan] ASC
	) ON [PRIMARY],
	CONSTRAINT [CHK_BanBida_TrangThai] CHECK (TrangThai IN (0, 1, 2))
) ON [PRIMARY]
GO

--CapDoHoiVien
CREATE TABLE [dbo].[CapDoHoiVien]
(
	[CapDo] [int] NOT NULL IDENTITY,
	[TenCapDo] [nvarchar](100) NOT NULL,
	[SoGioChoi] [float] NOT NULL,
	[UuDai] [float] NOT NULL,

	CONSTRAINT [PK_CapDoHoiVien] PRIMARY KEY CLUSTERED
	(
		[CapDo] ASC
	) ON [PRIMARY],
	CONSTRAINT [UQ_CapDoHoiVien_TenCapDo] UNIQUE (TenCapDo),
	CONSTRAINT [UQ_CapDoHoiVien_SoGioChoi] UNIQUE (SoGioChoi),
	CONSTRAINT [CHK_CapDoHoiVien_UuDai] CHECK (UuDai >= 0)
) ON [PRIMARY]
GO

--HoiVien
CREATE TABLE [dbo].[HoiVien]
(
	[MaHoiVien] [int] NOT NULL IDENTITY,
	[HoTen] [nvarchar](100) NOT NULL,
	[Email] [nvarchar](100) NOT NULL,
	[SoDienThoai] [nvarchar](50) NOT NULL,
	[SoCCCD] [nvarchar](50) NOT NULL,
	[SoGioChoi] [float] NOT NULL DEFAULT(0),
	[NgayDangKy] [date] NOT NULL DEFAULT(GETDATE()),
	[CapDo] [int] NOT NULL,

	CONSTRAINT [PK_HoiVien] PRIMARY KEY CLUSTERED
	(
		[MaHoiVien] ASC
	) ON [PRIMARY],
	CONSTRAINT [UQ_HoiVien_SoCCCD] UNIQUE (SoCCCD),
	CONSTRAINT [CHK_HoiVien_SoDienThoai] CHECK (LEN(SoDienThoai) = 10 AND SoDienThoai LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	CONSTRAINT [CHK_HoiVien_SoCCCD] CHECK (LEN(SoCCCD) = 12 AND SoCCCD LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
) ON [PRIMARY]
GO

--HoaDon
CREATE TABLE [dbo].[HoaDon]
(
	[MaHoaDon] [int] NOT NULL IDENTITY,
	[ThoiDiemVao] [datetime] NOT NULL DEFAULT(GETDATE()),
	[ThoiDiemRa] [datetime] NULL,
	[TrangThai] [bit] NOT NULL DEFAULT(0),
	[SoGioChoi] [float] NULL,
	[TongTien] [float] NULL,
	[SoBan] [int] NOT NULL,
	[MaHoiVien] [int] NULL,
	[MaThuNgan] [int] NOT NULL,

	CONSTRAINT [PK_HoaDon] PRIMARY KEY CLUSTERED
	(
		[MaHoaDon] ASC
	) ON [PRIMARY]
) ON [PRIMARY]
GO

--MatHangTrongHoaDon
CREATE TABLE [dbo].[MatHangTrongHoaDon]
(
	[MaHang] [int] NOT NULL,
	[MaHoaDon] [int] NOT NULL,
	[SoLuong] [int] NOT NULL,

	CONSTRAINT [PK_MatHangTrongHoaDon] PRIMARY KEY CLUSTERED
	(
		[MaHang] ASC, 
		[MaHoaDon] ASC
	) ON [PRIMARY],
	CONSTRAINT [CHK_MatHangTrongHoaDon_SoLuong] CHECK (SoLuong >= 0)
) ON [PRIMARY]
GO

-------------------------------------------------------------------------
-- CÀI ĐẶT KHÓA NGOÀI (LIÊN KẾT CÁC BẢNG)
-------------------------------------------------------------------------
ALTER TABLE [dbo].[MatHangTrongHoaDon] WITH CHECK ADD CONSTRAINT [FK_MatHangTrongHoaDon_MatHang] FOREIGN KEY ([MaHang])
REFERENCES [dbo].[MatHang] ([MaHang])
GO

ALTER TABLE [dbo].[MatHangTrongHoaDon] WITH CHECK ADD CONSTRAINT [FK_MatHangTrongHoaDon_HoaDon] FOREIGN KEY ([MaHoaDon])
REFERENCES [dbo].[HoaDon] ([MaHoaDon])
GO

ALTER TABLE [dbo].[HoaDon] WITH CHECK ADD CONSTRAINT [FK_HoaDon_ThuNgan] FOREIGN KEY ([MaThuNgan])
REFERENCES [dbo].[ThuNgan] ([MaThuNgan])
GO

ALTER TABLE [dbo].[BanBida] WITH CHECK ADD CONSTRAINT [FK_BanBida_LoaiBan] FOREIGN KEY ([LoaiBan])
REFERENCES [dbo].[LoaiBan] ([LoaiBan])
GO

ALTER TABLE [dbo].[HoaDon] WITH CHECK ADD CONSTRAINT [FK_HoaDon_BanBida] FOREIGN KEY ([SoBan])
REFERENCES [dbo].[BanBida] ([SoBan])
GO

ALTER TABLE [dbo].[HoaDon] WITH CHECK ADD CONSTRAINT [FK_HoaDon_HoiVien] FOREIGN KEY ([MaHoiVien])
REFERENCES [dbo].[HoiVien] ([MaHoiVien])
GO

ALTER TABLE [dbo].[HoiVien] WITH CHECK ADD CONSTRAINT [FK_HoiVien_CapDoHoiVien] FOREIGN KEY ([CapDo])
REFERENCES [dbo].[CapDoHoiVien] ([CapDo])