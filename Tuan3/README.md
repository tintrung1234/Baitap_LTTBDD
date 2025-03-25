# Bai1 - Jetpack Compose Navigation Example

## Giới thiệu

Dự án này là một ứng dụng Android đơn giản sử dụng **Jetpack Compose** để xây dựng giao diện người dùng. Ứng dụng bao gồm ba màn hình chính và sử dụng **Navigation Component** để điều hướng giữa các màn hình.

## Cấu trúc dự án

- `MainActivity.kt`: Điểm vào chính của ứng dụng, nơi thiết lập `NavHost` để điều hướng giữa các màn hình.
- `Controller()`: Thành phần Compose chính quản lý điều hướng.
- `MainScreen()`: Màn hình chính hiển thị thông tin giới thiệu về Jetpack Compose.
- `SecondPage()`: Danh sách các thành phần giao diện người dùng phổ biến trong Jetpack Compose.
- `ThirdPage()`: Hiển thị chi tiết về một thành phần UI cụ thể.

## Các tính năng chính

- **Navigation Component**: Sử dụng `NavController` để điều hướng giữa các màn hình.
- **Material Design 3**: Áp dụng giao diện hiện đại theo tiêu chuẩn của Google.
- **Hiển thị hình ảnh**: Sử dụng `Image` với `painterResource` để hiển thị hình ảnh.
- **Nút bấm (Button)**: Điều hướng giữa các màn hình thông qua các `Button`.
- **Bố cục linh hoạt**: Sử dụng `Column`, `Row`, `Box` để sắp xếp giao diện người dùng.

## Công nghệ sử dụng

- **Kotlin** với Jetpack Compose
- **AndroidX Navigation Compose** (`androidx.navigation.compose`)
- **Material3** (`androidx.compose.material3`)

## Màn hình ứng dụng

1. **Màn hình chính (MainScreen)**: Hiển thị logo, mô tả về Jetpack Compose và nút "I'm ready" để điều hướng.
2. **Danh sách UI Components (SecondPage)**: Hiển thị danh sách các thành phần giao diện.
3. **Chi tiết UI Component (ThirdPage)**: Hiển thị chi tiết về một thành phần UI cụ thể.

## DEMO
![Screenshot 2025-03-15 214834](https://github.com/user-attachments/assets/7aa3cd30-c536-4641-a836-e1505fe6afd4)
![Screenshot 2025-03-15 214842](https://github.com/user-attachments/assets/cd1a3653-ce43-4731-a63f-8439b8eb9206)

# Bai2 - UTH SmartTasks 

## Giới thiệu
Ứng dụng **UTH SmartTasks** là một ứng dụng quản lý thời gian và công việc, giúp người dùng tổ chức, ưu tiên và hoàn thành công việc một cách hiệu quả. Ứng dụng được xây dựng bằng **Jetpack Compose** với giao diện trực quan và dễ sử dụng.

## Tính năng chính
- **Màn hình Splash Screen**: Hiển thị logo và tên ứng dụng trong 2 giây trước khi chuyển sang màn hình chính.
- **Hệ thống hướng dẫn** (Onboarding Screens): Gồm 3 trang giới thiệu tính năng chính của ứng dụng:
  - **FirstPage**: Quản lý thời gian dễ dàng.
  - **SecondPage**: Tăng hiệu quả công việc.
  - **ThirdPage**: Nhắc nhở thông minh.
- **Chuyển đổi giữa các màn hình**: Sử dụng `NavController` để điều hướng giữa các trang.

## Công nghệ sử dụng
- **Ngôn ngữ**: Kotlin
- **Giao diện**: Jetpack Compose
- **Điều hướng**: `androidx.navigation.compose`
- **Material Design 3**: `androidx.compose.material3`

## DEMO
![Screenshot 2025-03-15 215044](https://github.com/user-attachments/assets/aad906de-1fe5-4b3a-b1dd-79f3e66bec70)
![Screenshot 2025-03-15 215104](https://github.com/user-attachments/assets/73ca6a5d-046e-4f14-acc5-af1b88dbcca1)


