document.addEventListener("DOMContentLoaded", () => {
    const navLinks = document.querySelectorAll(".nav-link");
    const currentPath = window.location.pathname;

    // Đánh dấu trang hiện tại
    navLinks.forEach(link => {
        if (link.getAttribute("href") === currentPath) {
            link.classList.add("active");
        } else {
            link.classList.remove("active");
        }
    });

    // Kiểm tra trạng thái đăng nhập
    const isLoggedIn = localStorage.getItem("isLoggedIn");

    if (isLoggedIn === "true") {
        updateNavToLogout();
    }
});

// Hàm chuyển navbar sang trạng thái "Đăng Xuất"
function updateNavToLogout() {
    const navLinks = document.querySelectorAll(".nav-link");

    navLinks.forEach(link => {
        if (link.textContent.trim() === "Đăng Nhập") {
            link.textContent = "Đăng Xuất";
            link.onclick = handleLogout; // Gắn sự kiện Đăng Xuất
        }
    });
}

// Hàm xử lý đăng nhập
function handleLogin() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (username === "admin" && password === "123456") {
        // Lưu trạng thái đăng nhập
        localStorage.setItem("isLoggedIn", "true");

        // Cập nhật navbar
        updateNavToLogout();

        // Thông báo thành công
        alert("Đăng nhập thành công!");
        window.location.href = '/trang_chu.html'; // Chuyển hướng đến trang chủ sau khi đăng nhập
    } else {
        // Hiển thị lỗi
        const errorMessage = document.getElementById("error-message");
        if (errorMessage) {
            errorMessage.style.display = "block";
            errorMessage.textContent = "Tên đăng nhập hoặc mật khẩu không đúng!";
        }
    }
}

// Hàm xử lý đăng xuất
function handleLogout(event) {
    event.preventDefault(); // Ngăn không chuyển hướng

    // Xóa trạng thái đăng nhập
    localStorage.setItem("isLoggedIn", "false");

    // Đặt lại navbar
    const navLinks = document.querySelectorAll(".nav-link");

    navLinks.forEach(link => {
        if (link.textContent.trim() === "Đăng Xuất") {
            link.textContent = "Đăng Nhập";
            link.onclick = null; // Gỡ bỏ sự kiện đăng xuất
        }
    });

    // Thông báo thành công
    alert("Bạn đã đăng xuất!");
}
