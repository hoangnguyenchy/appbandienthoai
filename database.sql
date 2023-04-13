use master
go
create database CuaHangDienThoai
go
use CuaHangDienThoai
go
create table TaiKhoan(
	TenTK varchar(20) primary key,
	MatKhau varchar(30) not null,
	HoTen nvarchar(50) not null,
	DiaChi nvarchar(100),
	SDT char(10),
)
go
create table Hang(
	MaHang int identity primary key,
	TenHang nvarchar(50) not null,
	HinhAnh nvarchar(max) not null
)
go
create table SanPham(
	MaSP int identity primary key,
	TenSP nvarchar(50) not null,
	SoLuong int not null,
	GiaBan int not null,
	HinhAnh nvarchar(max),
	MoTa nvarchar(max),
	MaHang int not null,
	MaLoai int not null
)
go
create table HinhAnh(
	MaHinhAnh int identity primary key,
	HinhAnh nvarchar(max),
	MaSP int not null
)
select * from HinhAnh
go
create table HoaDon(
	MaHD int identity primary key,
	TenTK varchar(20) not null,
	NgayBan nvarchar(100),
	DiaChi nvarchar(max),
	SDT char(10),
	TrangThai int
)
go
create table CTHoaDon(
	MaCTHD int identity primary key,
	MaHD int not null,
	MaSP int not null,
	SoLuong int not null,
)
go
create proc GetSanPhams
as
begin
	select MaSP, TenSP,SoLuong,GiaBan,SanPham.HinhAnh,MoTa,TenHang from SanPham,Hang where SanPham.MaHang =Hang.MaHang
end
go
create proc GetSanPhamID @MaSP int
as
begin
	select MaSP, TenSP,SoLuong,GiaBan,SanPham.HinhAnh,MoTa,TenHang from SanPham,Hang where SanPham.MaHang =Hang.MaHang and SanPham.MaSP = @MaSP
end
go
create proc GetSanPhamTheoHang @MaHang int
as
begin
	select MaSP, TenSP,SoLuong,GiaBan,SanPham.HinhAnh,MoTa,TenHang from SanPham,Hang where SanPham.MaHang =Hang.MaHang and  SanPham.MaHang = @MaHang
end
go
create proc GetHoaDons
as
begin
	select HoaDon.MaHD,HoaDon.DiaChi,HoaDon.NgayBan,HoaDon.SDT,HoaDon.TenTK,TaiKhoan.HoTen,SUM(CTHoaDon.SoLuong*SanPham.GiaBan)[TongTien],HoaDon.TrangThai 
	from HoaDon,CTHoaDon,SanPham,TaiKhoan 
	where HoaDon.MaHD = CTHoaDon.MaHD and CTHoaDon.MaSP = SanPham.MaSP and TaiKhoan.TenTK = HoaDon.TenTK
	group by HoaDon.MaHD,HoaDon.DiaChi,HoaDon.NgayBan,HoaDon.SDT,HoaDon.TenTK,TaiKhoan.HoTen,HoaDon.TrangThai
end



go
create proc GetHoaDonsTaiKhoan @TenTK varchar(20)
as
begin
	select HoaDon.MaHD,HoaDon.DiaChi,HoaDon.NgayBan,HoaDon.SDT,HoaDon.TenTK,TaiKhoan.HoTen,SUM(CTHoaDon.SoLuong*SanPham.GiaBan)[TongTien],HoaDon.TrangThai from HoaDon,CTHoaDon,SanPham,TaiKhoan 
	where HoaDon.MaHD = CTHoaDon.MaHD and CTHoaDon.MaSP = SanPham.MaSP and TaiKhoan.TenTK = HoaDon.TenTK and HoaDon.TenTK = @TenTK
	group by HoaDon.MaHD,HoaDon.DiaChi,HoaDon.NgayBan,HoaDon.SDT,HoaDon.TenTK,TaiKhoan.HoTen,HoaDon.TrangThai
end
go
create proc GetCTHoaDon @MaHD int
as
begin
	select MaCTHD,SanPham.TenSP,SanPham.HinhAnh,SanPham.GiaBan,CTHoaDon.SoLuong, SanPham.GiaBan*CTHoaDon.SoLuong [ThanhTien] from HoaDon,SanPham,CTHoaDon where HoaDon.MaHD = CTHoaDon.MaHD and CTHoaDon.MaSP = SanPham.MaSP and HoaDon.MaHD=@MaHD
end

INSERT INTO SanPham
VALUES ('sanpham01','doramin','1','100.000','abc','hang dung tot','mahang01','maloai01'),
       ('sanpham02','gido','1','200.000','cba','hang dung tot','mahang02','maloai02'),
	   ('sanpham03','conan','1','300.000','abc','hang dung tot','mahang03','maloai03')

