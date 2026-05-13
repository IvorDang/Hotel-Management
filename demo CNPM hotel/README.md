Consumer Goods Distribution Agency Management System
Hệ thống Quản lý Đại lý Phân phối Hàng tiêu dùng
Dự án được thực hiện trong khuôn khổ môn học Nhập môn Công nghệ Phần mềm tại Học viện Công nghệ Bưu chính Viễn thông (PTIT).

🇻🇳 TIẾNG VIỆT
1. Giới thiệu tổng quan
Ứng dụng desktop (Java Swing) hỗ trợ quản lý quy trình vận hành của một cửa hàng/kho hàng phân phối, từ khâu nhập kho, xuất kho đến thống kê doanh thu. Hệ thống được thiết kế theo mô hình DAO (Data Access Object) để tách biệt logic xử lý dữ liệu và giao diện.
2. Các chức năng chính
Hệ thống phân quyền rõ ràng cho các Actor:
Quản lý (Manager):
Xem báo cáo thống kê doanh thu theo mặt hàng/loại phòng.
Xem chi tiết hóa đơn của từng mặt hàng.
Quản lý danh mục Khách sạn/Mặt hàng (Thêm, Sửa, Xóa).
Nhân viên (Seller/Warehouse Staff):
Tìm kiếm phòng trống/mặt hàng khả dụng theo thời gian thực.
Thực hiện quy trình đặt phòng (Booking) và tìm kiếm khách hàng.
Tạo hóa đơn nhập/xuất kho.
3. Công nghệ sử dụng
Ngôn ngữ: Java (JDK 17+)
Giao diện: Java Swing
Cơ sở dữ liệu: SQL Server
Thư viện: JDBC Driver (mssql-jdbc)
4. Cấu trúc dự án
dao: Chứa các lớp xử lý truy vấn cơ sở dữ liệu (RoomDAO, StatDAO, UserDAO,...).
model: Định nghĩa các đối tượng thực thể (User, Room, Hotel, Item,...).
view: Chứa các giao diện người dùng (LoginFrm, ManagerHomeFrm, SellerHomeFrm,...).

🇺🇸 ENGLISH
1. Project Overview
A Java Desktop application designed for internal store/warehouse management, covering importing, exporting, inventory control, and revenue statistics.
2. Core Features
Manager Module:
Revenue statistics by item type/room type with time-range filtering.
Detailed view of invoices and customer purchase history.
Category management (Hotels, Rooms, Items).
Seller/Staff Module:
Real-time search for available rooms/items between specific dates.
Customer search and booking flow execution.
Automated invoice generation for imports and exports.
3. Tech Stack
Language: Java
Framework: Java Swing (Desktop UI)
Database: Microsoft SQL Server
Design Pattern: DAO (Data Access Object)

🛠 Cài đặt & Sử dụng (Setup Instruction)
Cơ sở dữ liệu (Database):
Chạy script SQL Server để tạo các bảng tbl_user, tbl_item, tbl_export_bill,....
Cập nhật thông tin kết nối (URL, User, Password) trong file dao/DAO.java.
Chạy ứng dụng (Execution):
Mở dự án bằng VS Code hoặc Eclipse.
Chạy file view/user/LoginFrm.java để bắt đầu.
👤 Tác giả (Author)
Sinh viên: Đặng Trần Minh Quân
Trường: Học viện Công nghệ Bưu chính Viễn thông (PTIT)