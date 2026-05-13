USE hotel;
GO

-------------------------------------------------------------------------------
-- 0. XÓA BẢNG CŨ (NẾU CÓ) ĐỂ TRÁNH LỖI TRÙNG LẶP (Xóa ngược từ con lên cha)
-------------------------------------------------------------------------------
IF OBJECT_ID('tblBookedRoom', 'U') IS NOT NULL DROP TABLE tblBookedRoom;
IF OBJECT_ID('tblBooking', 'U') IS NOT NULL DROP TABLE tblBooking;
IF OBJECT_ID('tblClient', 'U') IS NOT NULL DROP TABLE tblClient;
IF OBJECT_ID('tblRoom', 'U') IS NOT NULL DROP TABLE tblRoom;
IF OBJECT_ID('tblHotel', 'U') IS NOT NULL DROP TABLE tblHotel;
IF OBJECT_ID('tblUser', 'U') IS NOT NULL DROP TABLE tblUser;
GO

-------------------------------------------------------------------------------
-- 1. BẢNG tblUser (Người dùng hệ thống)
-------------------------------------------------------------------------------
CREATE TABLE tblUser (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100),
    username VARCHAR(50),
    password VARCHAR(255),
    position VARCHAR(50)
);
GO

INSERT INTO tblUser (name, username, password, position) VALUES 
(N'Quản lý Trần Minh', 'manager', 'manager', 'manager'),
(N'Quản trị hệ thống', 'admin', 'admin', 'administrator'),
(N'Nhân viên Sales 1', 'seller', 'seller', 'seller'),
(N'Nhân viên Sales 2', 'seller2', '123456', 'seller'),
(N'Lễ tân Ca sáng', 'recept', 'recept', 'receptionist'),
(N'Lễ tân Ca tối', 'recept2', '123456', 'receptionist'),
(N'Nguyễn Văn A', 'nva', '123', 'seller'),
(N'Trần Thị B', 'ttb', '123', 'seller'),
(N'Lê Văn C', 'lvc', '123', 'receptionist'),
(N'Phạm Thị D', 'ptd', '123', 'receptionist');
-- (Đã nạp 10 users đủ các role để test login)
GO

-------------------------------------------------------------------------------
-- 2. BẢNG tblHotel (Khách sạn)
-------------------------------------------------------------------------------
CREATE TABLE tblHotel (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
    address NVARCHAR(255),
    star INT,
    des NVARCHAR(MAX)
);
GO

INSERT INTO tblHotel (name, address, star, des) VALUES 
(N'Metropole', N'15 Ngô Quyền, Hà Nội', 5, N'Hạng sang, trung tâm HN'),
(N'Mường Thanh', N'Đà Nẵng', 4, N'Gần biển Mỹ Khê'),
(N'Vinpearl', N'Nha Trang', 5, N'Khu nghỉ dưỡng biển'),
(N'InterContinental', N'Phú Quốc', 5, N'Resort cao cấp'),
(N'Novotel', N'Hà Nội', 4, N'Khách sạn doanh nhân');
GO

-------------------------------------------------------------------------------
-- 3. BẢNG tblRoom (Phòng) - Phụ thuộc tblHotel
-------------------------------------------------------------------------------
CREATE TABLE tblRoom (
    id INT IDENTITY(1,1) PRIMARY KEY,
    idHotel INT,
    name NVARCHAR(100),
    type NVARCHAR(100),
    price FLOAT,
    des NVARCHAR(MAX),
    CONSTRAINT FK_Hotel FOREIGN KEY (idHotel) REFERENCES tblHotel(id)
);
GO

-- Nạp 20 phòng cho khách sạn Metropole (id=1) và Mường Thanh (id=2)
INSERT INTO tblRoom (idHotel, name, type, price, des) VALUES 
(1, '101', 'single', 800, 'garden view'),
(1, '102', 'double', 1000, 'garden view'),
(1, '103', 'twin', 1000, 'city view'),
(1, '201', 'single', 800, 'garden view'),
(1, '202', 'double', 1200, 'pool view'),
(1, '203', 'twin', 1200, 'pool view'),
(1, '301', 'vip', 3000, 'presidential suite'),
(1, '302', 'vip', 2500, 'ocean view'),
(1, '401', 'single', 900, 'high floor'),
(1, '402', 'double', 1100, 'high floor'),
(2, 'A101', 'single', 500, 'standard'),
(2, 'A102', 'double', 700, 'standard'),
(2, 'A201', 'twin', 750, 'balcony'),
(2, 'A202', 'vip', 1500, 'ocean view'),
(2, 'B101', 'single', 500, 'standard'),
(2, 'B102', 'double', 700, 'standard'),
(2, 'B201', 'twin', 750, 'balcony'),
(2, 'B202', 'vip', 1500, 'ocean view'),
(3, 'V101', 'double', 2000, 'resort view'),
(3, 'V102', 'vip', 5000, 'private pool');
GO

-------------------------------------------------------------------------------
-- 4. BẢNG tblClient (Khách hàng)
-------------------------------------------------------------------------------
CREATE TABLE tblClient (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100),
    idcard VARCHAR(50),
    address NVARCHAR(255),
    email VARCHAR(100),
    tel VARCHAR(20),
    des NVARCHAR(MAX)
);
GO

INSERT INTO tblClient (name, idcard, address, email, tel, des) VALUES 
(N'Lê Văn An', '1111', N'Hà Nội', 'an@gmail.com', '0123456781', ''),
(N'Trần Tiến Anh', '2222', N'Đà Nẵng', 'anh@gmail.com', '0123456782', ''),
(N'Cao Thanh Thanh', '3333', N'Sài Gòn', 'ctt@gmail.com', '0123456783', ''),
(N'Hoàng Thị Luyến', '4444', N'Cần Thơ', 'htl@gmail.com', '0123456784', ''),
(N'Bùi Minh', '5555', N'Hải Phòng', 'minh@gmail.com', '0123456785', 'VIP client'),
(N'Đặng Trần', '6666', N'Nam Định', 'tran@gmail.com', '0123456786', ''),
(N'Vũ Khắc', '7777', N'Hà Nội', 'khac@gmail.com', '0123456787', ''),
(N'Ngô Bảo', '8888', N'Bắc Ninh', 'bao@gmail.com', '0123456788', ''),
(N'Đoàn Dự', '9999', N'Huế', 'du@gmail.com', '0123456789', ''),
(N'Kiều Phong', '1010', N'Hà Nội', 'phong@gmail.com', '0123456710', '');
-- (Nạp sẵn 10 khách hàng để test Add/Search Client)
GO

-------------------------------------------------------------------------------
-- 5. BẢNG tblBooking (Hóa đơn Đặt phòng) - Phụ thuộc User và Client
-------------------------------------------------------------------------------
CREATE TABLE tblBooking (
    id INT IDENTITY(1,1) PRIMARY KEY,
    idCreator INT,
    idClient INT,
    bookingdate DATETIME,
    saleoff FLOAT,
    note NVARCHAR(MAX),
    CONSTRAINT FK_BookingCreator FOREIGN KEY (idCreator) REFERENCES tblUser(id),
    CONSTRAINT FK_BookingClient FOREIGN KEY (idClient) REFERENCES tblClient(id)
);
GO

-- Tạo các hóa đơn booking mẫu do Seller (id=3) và Receptionist (id=5) lập
INSERT INTO tblBooking (idCreator, idClient, bookingdate, saleoff, note) VALUES 
(3, 1, '2026-04-10', 0, N'Khách đặt trước 1 tháng'),
(3, 2, '2026-04-15', 50, N'Giảm giá khách quen'),
(3, 3, '2026-04-20', 0, ''),
(5, 4, '2026-04-25', 100, N'Khách vãng lai'),
(3, 5, '2026-05-01', 0, N'Đoàn công tác'),
(5, 6, '2026-05-05', 0, '');
GO

-------------------------------------------------------------------------------
-- 6. BẢNG tblBookedRoom (Chi tiết phòng đã đặt) - Phụ thuộc Booking và Room
-------------------------------------------------------------------------------
CREATE TABLE tblBookedRoom (
    id INT IDENTITY(1,1) PRIMARY KEY,
    idBooking INT,
    idRoom INT,
    checkin DATETIME,
    checkout DATETIME,
    price FLOAT,
    saleoff FLOAT,
    isCheckin BIT DEFAULT 0,
    CONSTRAINT FK_BookedRoom_Booking FOREIGN KEY (idBooking) REFERENCES tblBooking(id),
    CONSTRAINT FK_BookedRoom_Room FOREIGN KEY (idRoom) REFERENCES tblRoom(id)
);
GO

-- GIẢ LẬP TEST CASE TÌM PHÒNG TRỐNG (Từ 10/05/2026 đến 15/05/2026)
-- sd = '2026-05-10', ed = '2026-05-15'

INSERT INTO tblBookedRoom (idBooking, idRoom, checkin, checkout, price, saleoff, isCheckin) VALUES 
-- TC1 (Phòng 102): Đã trả phòng trước khoảng sd-ed (co < sd) -> Trạng thái: TRỐNG
(1, 2, '2026-05-01', '2026-05-05', 1000, 0, 1), 

-- TC2 (Phòng 103): Giao đoạn đầu (sd < ci < ed < co) -> Trạng thái: BẬN
(2, 3, '2026-05-12', '2026-05-20', 1000, 0, 0),

-- TC3 (Phòng 201): Bao trùm toàn bộ (ci <= sd < ed <= co) -> Trạng thái: BẬN
(3, 4, '2026-05-01', '2026-05-30', 800, 0, 1),

-- TC4 (Phòng 202): Giao đoạn sau (ci < sd < co < ed) -> Trạng thái: BẬN
(4, 5, '2026-05-05', '2026-05-12', 1200, 0, 1),

-- TC5 (Phòng 203): Đặt sau khoảng tìm kiếm (ed < ci) -> Trạng thái: TRỐNG
(5, 6, '2026-05-20', '2026-05-25', 1200, 0, 0),

-- TC6 (Phòng 301): Nằm khít đúng biên (ci = sd và co = ed) -> Trạng thái: BẬN
(6, 7, '2026-05-10', '2026-05-15', 3000, 0, 0);

-- Các phòng còn lại (101, 302, 401...) chưa bị đặt lần nào -> Trạng thái: TRỐNG
GO