# Thuc Hanh 01 - Android Jetpack Compose App

## Giới thiệu
Ứng dụng Android đơn giản sử dụng Jetpack Compose để nhập họ tên và tuổi của người dùng, sau đó phân loại độ tuổi dựa trên giá trị nhập vào.

## Chức năng chính
- Nhập họ tên và tuổi.
- Phân loại độ tuổi:
  - 0 - 2 tuổi: "Em bé"
  - 3 - 6 tuổi: "Trẻ em"
  - 7 - 65 tuổi: "Người lớn"
  - Trên 65 tuổi: "Người già"
- Hiển thị kết quả phân loại khi người dùng nhấn nút "Kiểm tra".

## Công nghệ sử dụng
- **Ngôn ngữ lập trình**: Kotlin
- **Giao diện UI**: Jetpack Compose
- **Thư viện Material3**: Dùng để tạo giao diện đẹp và hiện đại

## Cấu trúc mã nguồn
- **MainActivity.kt**: Chứa toàn bộ logic giao diện và xử lý sự kiện của ứng dụng.
- **AppUI()**: Hàm chính chứa giao diện của ứng dụng.
- **State Management**: Sử dụng `remember` để quản lý trạng thái của biến `name`, `age` và `result`.
- **Composable Components**:
  - `TextField`: Nhập thông tin người dùng.
  - `Button`: Xử lý logic kiểm tra độ tuổi.
  - `Text`: Hiển thị kết quả.


## Mở rộng và nâng cấp
- Cải thiện giao diện với nhiều tùy chỉnh về màu sắc và bố cục.
- Thêm chức năng lưu trữ thông tin người dùng.
- Hỗ trợ nhập tuổi bằng cách sử dụng `NumberKeyboard`.

##DEMO
![Screenshot 2025-03-15 214116](https://github.com/user-attachments/assets/646624a3-5a6f-436f-85ce-42b66ecff486)


# Ứng dụng Quản Lý Thư Viện - Android Jetpack Compose - Bai_tap_ve_nha

## Giới thiệu
Ứng dụng quản lý thư viện sử dụng Jetpack Compose giúp theo dõi danh sách sách và nhân viên, đồng thời cho phép quản lý việc mượn sách.

## Chức năng chính
- Quản lý danh sách sách.
- Quản lý danh sách nhân viên.
- Chọn nhân viên và mượn sách.
- Hiển thị danh sách sách đã mượn theo nhân viên.
- Điều hướng giữa các màn hình bằng **Jetpack Navigation**.

## Công nghệ sử dụng
- **Ngôn ngữ lập trình**: Kotlin
- **Giao diện UI**: Jetpack Compose
- **Thư viện Material3**: Tạo giao diện hiện đại
- **Navigation Component**: Điều hướng giữa các màn hình

## Cấu trúc mã nguồn
- **MainActivity.kt**: Chứa logic điều hướng và khởi tạo giao diện.
- **MainScreen()**: Xử lý điều hướng và quản lý danh sách dữ liệu.

- **LibraryManagementScreen()**: Màn hình chính để quản lý sách và nhân viên.
- **BookListScreen()**: Hiển thị danh sách sách và cho phép thêm sách mới.
- **EmployeeListScreen()**: Hiển thị danh sách nhân viên và cho phép thêm nhân viên mới.
- **BottomNavBar()**: Thanh điều hướng giữa các màn hình.

## Mở rộng và nâng cấp
- Thêm tính năng trả sách.
- Cải thiện giao diện người dùng.
- Thêm lưu trữ dữ liệu bằng Room Database.
- Hỗ trợ quét mã vạch để quản lý sách dễ dàng hơn.

##DEMO
![Screenshot 2025-03-15 214346](https://github.com/user-attachments/assets/22febfbe-9506-47e0-8995-5e641864f27d)
![Screenshot 2025-03-15 214346](https://github.com/user-attachments/assets/6f42fe0c-d205-434b-842a-b5f3ba3d628c)

