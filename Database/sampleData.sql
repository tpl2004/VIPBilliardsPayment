USE [VIPBilliardsPaymentDB]
GO
SET IDENTITY_INSERT [dbo].[MatHang] ON 

INSERT [dbo].[MatHang] ([MaHang], [TenHang], [DonGia]) VALUES (1, N'Coca-Cola', 25000)
INSERT [dbo].[MatHang] ([MaHang], [TenHang], [DonGia]) VALUES (2, N'Pepsi', 30000)
INSERT [dbo].[MatHang] ([MaHang], [TenHang], [DonGia]) VALUES (3, N'RedBull', 40000)
INSERT [dbo].[MatHang] ([MaHang], [TenHang], [DonGia]) VALUES (4, N'Fanta', 30000)
INSERT [dbo].[MatHang] ([MaHang], [TenHang], [DonGia]) VALUES (5, N'Bún đậu nắm tôm', 50000)
INSERT [dbo].[MatHang] ([MaHang], [TenHang], [DonGia]) VALUES (6, N'Bạc xỉu', 50000)
INSERT [dbo].[MatHang] ([MaHang], [TenHang], [DonGia]) VALUES (7, N'Trà đá', 60000)
SET IDENTITY_INSERT [dbo].[MatHang] OFF
GO
SET IDENTITY_INSERT [dbo].[ThuNgan] ON 

INSERT [dbo].[ThuNgan] ([MaThuNgan], [HoTen], [NgaySinh], [GioiTinhNu], [Email], [SoDienThoai], [SoCCCD], [TenDangNhap], [MatKhau]) VALUES (1, N'Phạm Ngọc Mộng Phước', CAST(N'2004-03-15' AS Date), 1, N'phuocpham@gmail.com', N'0782730023', N'123456789012', N'btyDZ', N'$2a$10$6muio5QuRioIvsvSZVKZT.6P7EdpjX5CdBvEj9pCuQtD2bLds04s6')
INSERT [dbo].[ThuNgan] ([MaThuNgan], [HoTen], [NgaySinh], [GioiTinhNu], [Email], [SoDienThoai], [SoCCCD], [TenDangNhap], [MatKhau]) VALUES (2, N'Nguyễn Thị Thanh Nhi', CAST(N'2004-07-15' AS Date), 1, N'thanhei@gmail.com', N'0777195980', N'123456789013', N'thanhnhiNguyen', N'$2a$10$zV.O.lckMlEnJF9CnFajC.ckc0u1O2DLN6x.OcsmXYWExFQKvR92q')
INSERT [dbo].[ThuNgan] ([MaThuNgan], [HoTen], [NgaySinh], [GioiTinhNu], [Email], [SoDienThoai], [SoCCCD], [TenDangNhap], [MatKhau]) VALUES (3, N'Khánh Linh', CAST(N'2005-09-28' AS Date), 1, N'klynhh289@gmail.com', N'0777888111', N'197426789013', N'khanhlinh', N'$2a$10$mnkbOxSFkMo8wlbNTVwOVeRWMO7m8radwewKpHAItN9U6Yi.JPvNC')
SET IDENTITY_INSERT [dbo].[ThuNgan] OFF
GO
SET IDENTITY_INSERT [dbo].[LoaiBan] ON 

INSERT [dbo].[LoaiBan] ([LoaiBan], [TenLoai], [DonGia]) VALUES (1, N'Thường', 65000)
INSERT [dbo].[LoaiBan] ([LoaiBan], [TenLoai], [DonGia]) VALUES (2, N'VIP', 100000)
SET IDENTITY_INSERT [dbo].[LoaiBan] OFF
GO
SET IDENTITY_INSERT [dbo].[BanBida] ON 

INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (1, 1, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (2, 0, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (3, 1, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (4, 0, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (5, 1, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (6, 0, 2)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (7, 1, 2)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (8, 2, 2)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (9, 1, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (10, 1, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (11, 0, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (12, 0, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (13, 0, 2)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (14, 0, 2)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (15, 0, 1)
INSERT [dbo].[BanBida] ([SoBan], [TrangThai], [LoaiBan]) VALUES (16, 0, 1)
SET IDENTITY_INSERT [dbo].[BanBida] OFF
GO
SET IDENTITY_INSERT [dbo].[CapDoHoiVien] ON 

INSERT [dbo].[CapDoHoiVien] ([CapDo], [TenCapDo], [SoGioChoi], [UuDai]) VALUES (1, N'NEWBIE', 0, 0.05)
INSERT [dbo].[CapDoHoiVien] ([CapDo], [TenCapDo], [SoGioChoi], [UuDai]) VALUES (2, N'PRO', 100, 0.2)
INSERT [dbo].[CapDoHoiVien] ([CapDo], [TenCapDo], [SoGioChoi], [UuDai]) VALUES (3, N'VIP', 500, 0.4)
SET IDENTITY_INSERT [dbo].[CapDoHoiVien] OFF
GO
SET IDENTITY_INSERT [dbo].[HoiVien] ON 

INSERT [dbo].[HoiVien] ([MaHoiVien], [HoTen], [Email], [SoDienThoai], [SoCCCD], [SoGioChoi], [NgayDangKy], [CapDo]) VALUES (1, N'Võ Hoàng Minh', N'vohoangminh11021@gmail.com', N'0945612355', N'123456789129', 29.875499722222223, CAST(N'2025-04-10' AS Date), 1)
INSERT [dbo].[HoiVien] ([MaHoiVien], [HoTen], [Email], [SoDienThoai], [SoCCCD], [SoGioChoi], [NgayDangKy], [CapDo]) VALUES (2, N'Lê Phương Thảo', N'locdepzai@gmail.com', N'0945612455', N'123456789188', 57.86772944444445, CAST(N'2025-04-10' AS Date), 1)
INSERT [dbo].[HoiVien] ([MaHoiVien], [HoTen], [Email], [SoDienThoai], [SoCCCD], [SoGioChoi], [NgayDangKy], [CapDo]) VALUES (3, N'Ngô Diễm Khánh Quỳnh', N'quynh19@gmail.com', N'0777195980', N'123053789188', 0, CAST(N'2025-04-10' AS Date), 1)
INSERT [dbo].[HoiVien] ([MaHoiVien], [HoTen], [Email], [SoDienThoai], [SoCCCD], [SoGioChoi], [NgayDangKy], [CapDo]) VALUES (4, N'Võ Ngọc Châu', N'chauVo@gmail.com', N'0777295980', N'923053709188', 0, CAST(N'2025-04-10' AS Date), 1)
INSERT [dbo].[HoiVien] ([MaHoiVien], [HoTen], [Email], [SoDienThoai], [SoCCCD], [SoGioChoi], [NgayDangKy], [CapDo]) VALUES (5, N'Trần Thị Anh Thư', N'ThuTran@gmail.com', N'0768445464', N'983053709188', 0, CAST(N'2025-04-10' AS Date), 1)
INSERT [dbo].[HoiVien] ([MaHoiVien], [HoTen], [Email], [SoDienThoai], [SoCCCD], [SoGioChoi], [NgayDangKy], [CapDo]) VALUES (6, N'Dương Thị Ngọc Huyền', N'locdeptrai@gmail.com', N'0768485464', N'983053709988', 0, CAST(N'2025-04-13' AS Date), 1)
INSERT [dbo].[HoiVien] ([MaHoiVien], [HoTen], [Email], [SoDienThoai], [SoCCCD], [SoGioChoi], [NgayDangKy], [CapDo]) VALUES (7, N'Châu Thị Phước Huyền', N'locdeptrai@gmail.com', N'0782430012', N'123456789456', 0, CAST(N'2025-04-14' AS Date), 1)
INSERT [dbo].[HoiVien] ([MaHoiVien], [HoTen], [Email], [SoDienThoai], [SoCCCD], [SoGioChoi], [NgayDangKy], [CapDo]) VALUES (8, N'Nguyễn Hoài Minh', N'phuocdz@gmail.com', N'0785230045', N'896325417895', 0, CAST(N'2025-04-14' AS Date), 1)
SET IDENTITY_INSERT [dbo].[HoiVien] OFF
GO
SET IDENTITY_INSERT [dbo].[HoaDon] ON 

INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (9, CAST(N'2025-04-11T10:50:51.223' AS DateTime), CAST(N'2025-04-11T11:09:47.917' AS DateTime), 1, 0.31574861111111113, 128747.4767361111, 1, 2, 3)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (10, CAST(N'2025-04-11T10:51:26.040' AS DateTime), CAST(N'2025-04-11T11:15:31.610' AS DateTime), 1, 0.40154722222222222, 26100.569444444445, 2, NULL, 3)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (11, CAST(N'2025-04-11T10:51:45.793' AS DateTime), CAST(N'2025-04-11T11:16:24.910' AS DateTime), 1, 0.41086583333333332, 191032.25416666665, 14, 1, 2)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (12, CAST(N'2025-04-11T10:52:14.953' AS DateTime), CAST(N'2025-04-11T11:15:57.357' AS DateTime), 1, 0.39511194444444442, 175682.27638888889, 16, NULL, 1)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (13, CAST(N'2025-04-12T17:13:53.837' AS DateTime), CAST(N'2025-04-13T22:41:46.520' AS DateTime), 1, 29.464633888888891, 1952441.1426388889, 1, 1, 1)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (14, CAST(N'2025-04-12T17:20:21.807' AS DateTime), CAST(N'2025-04-13T23:27:27.547' AS DateTime), 1, 30.11826111111111, 1957686.9722222223, 2, NULL, 1)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (15, CAST(N'2025-04-12T17:21:51.707' AS DateTime), CAST(N'2025-04-13T23:27:47.437' AS DateTime), 1, 30.098814166666667, 1981422.9208333334, 3, NULL, 1)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (16, CAST(N'2025-04-12T17:24:51.817' AS DateTime), CAST(N'2025-04-13T23:28:06.400' AS DateTime), 1, 30.054050555555555, 1953513.2861111111, 4, NULL, 1)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (17, CAST(N'2025-04-12T17:27:57.747' AS DateTime), CAST(N'2025-04-13T23:31:47.047' AS DateTime), 1, 30.063693888888888, 1954140.1027777777, 5, NULL, 1)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (18, CAST(N'2025-04-12T17:32:15.053' AS DateTime), CAST(N'2025-04-14T01:45:05.367' AS DateTime), 1, 32.213975833333336, 3202827.7041666666, 6, 2, 1)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (19, CAST(N'2025-04-12T17:37:37.007' AS DateTime), NULL, 0, NULL, NULL, 7, 3, 1)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (20, CAST(N'2025-04-12T22:08:52.737' AS DateTime), CAST(N'2025-04-13T23:29:09.557' AS DateTime), 1, 25.338005, 1588371.8087499999, 12, 2, 3)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (21, CAST(N'2025-04-13T12:34:02.873' AS DateTime), NULL, 0, NULL, NULL, 9, 2, 2)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (22, CAST(N'2025-04-13T17:07:34.110' AS DateTime), NULL, 0, NULL, NULL, 10, 1, 3)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (23, CAST(N'2025-04-13T23:28:13.777' AS DateTime), NULL, 0, NULL, NULL, 1, 2, 2)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (24, CAST(N'2025-04-13T23:32:07.987' AS DateTime), NULL, 0, NULL, NULL, 3, 2, 2)
INSERT [dbo].[HoaDon] ([MaHoaDon], [ThoiDiemVao], [ThoiDiemRa], [TrangThai], [SoGioChoi], [TongTien], [SoBan], [MaHoiVien], [MaThuNgan]) VALUES (25, CAST(N'2025-04-14T01:45:34.330' AS DateTime), NULL, 0, NULL, NULL, 5, 2, 2)
SET IDENTITY_INSERT [dbo].[HoaDon] OFF
GO
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (1, 9, 3)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (1, 15, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (1, 20, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (1, 21, 2)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (1, 23, 3)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (1, 24, 2)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (1, 25, 3)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (2, 13, 2)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (3, 9, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (3, 21, 2)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (3, 25, 3)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (4, 12, 5)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (4, 13, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (4, 22, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (5, 11, 2)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (5, 13, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (5, 18, 3)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (5, 23, 2)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (6, 21, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (6, 23, 2)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (6, 24, 2)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (7, 11, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (7, 19, 1)
INSERT [dbo].[MatHangTrongHoaDon] ([MaHang], [MaHoaDon], [SoLuong]) VALUES (7, 24, 1)
GO
SET IDENTITY_INSERT [dbo].[ChuQuan] ON 

INSERT [dbo].[ChuQuan] ([MaChuQuan], [TenDangNhap], [MatKhau]) VALUES (1, N'admin', N'$2a$10$HhimQmPdvzqGADnpiyvXi.Lleav0HcnGeYaHNJQq5YTc.L/5T4d.m')
SET IDENTITY_INSERT [dbo].[ChuQuan] OFF
GO
