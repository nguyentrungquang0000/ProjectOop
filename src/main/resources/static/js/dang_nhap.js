




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

// Hàm chuyển navbar sang trạng thái "Đăng Xuất" và thay đổi "Đăng Kí" thành icon tài khoản
function updateNavToLogout() {
    const navLinks = document.querySelectorAll(".nav-link");

    // navLinks.forEach(link => {
    //     // Thay đổi nút "Đăng Nhập" thành "Đăng Xuất"
    //     if (link.textContent.trim() === "Đăng Nhập") {
    //         link.textContent = "Đăng Xuất";
    //         link.onclick = handleLogout; // Gắn sự kiện Đăng Xuất
    //     }

    //     // Thay đổi "Đăng Kí" thành icon tài khoản
    //     if (link.textContent.trim() === "Đăng Kí") {
    //         link.innerHTML = '<i class="fa-solid fa-user"></i>'; // Icon tài khoản
    //         link.onclick = null; // Không cần gắn sự kiện
    //     }
    // });


    const loginLinks = document.querySelectorAll(".navbar-login .nav-item");
    const cartElement = document.getElementById("cart");
    const userMenu = document.getElementById("navbar-user");

    // Ẩn nút "Đăng Nhập" và "Đăng Kí"
    loginLinks.forEach(link => link.style.display = "none");

    // Hiển thị giỏ hàng
    if (cartElement) {
        cartElement.style.display = "flex";
    }

    // Hiển thị menu người dùng
    if (userMenu) {
        userMenu.style.display = "flex";
    }

     // Thêm sự kiện xử lý "Đăng Xuất"
     const logoutLink = document.getElementById("logout-link");
     if (logoutLink) {
         logoutLink.addEventListener("click", handleLogout);
     }

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
        window.location.href = "/trang_chu.html"; // Chuyển hướng đến trang chủ sau khi đăng nhập
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
        // Đặt lại "Đăng Xuất" thành "Đăng Nhập"
        if (link.textContent.trim() === "Đăng Xuất") {
            link.textContent = "Đăng Nhập";
            link.onclick = null; // Gỡ bỏ sự kiện đăng xuất
        }

        // Đặt lại icon tài khoản thành "Đăng Kí"
        if (link.innerHTML.includes("fa-user")) {
            link.textContent = "Đăng Kí";
            link.onclick = null;
        }
    });

    // Thông báo thành công
    alert("Bạn đã đăng xuất!");
    window.location.href = "/trang_chu.html"; // Chuyển hướng về trang chủ sau khi đăng xuất
}


// kiểm tra xem đăng nhập chưa khi bấm vô giỏ hàng
document.addEventListener("DOMContentLoaded", () => {
    const cartIcon = document.getElementById("cart-icon");

    // Giả sử chúng ta lưu trạng thái đăng nhập trong `localStorage`
    const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";

    // cartIcon.addEventListener("click", (event) => {
    //     if (!isLoggedIn) {
    //         event.preventDefault(); // Ngăn chặn chuyển hướng
    //         alert("Bạn cần đăng nhập để truy cập giỏ hàng!");
    //         window.location.href = "/dang_nhap.html"; // Chuyển hướng đến trang đăng nhập
    //     }
    // });
});



document.addEventListener("DOMContentLoaded", () => {
    const searchBar = document.getElementById("search-bar");
    const searchButton = document.getElementById("search-btn");

    searchButton.addEventListener("click", () => {
        const query = searchBar.value.trim();
        if (query) {
            // Chuyển hướng tới trang đồ ăn với từ khóa tìm kiếm
            window.location.href = `/do_an.html?search=${encodeURIComponent(query)}`;
        } else {
            alert("Vui lòng nhập từ khóa để tìm kiếm.");
        }
    });
});



// Xử lý sự kiện click vào User để hiển thị dropdown
document.addEventListener("DOMContentLoaded", () => {
    const userMenu = document.getElementById("navbar-user");

    if (userMenu) {
        userMenu.addEventListener("click", () => {
            // Toggle class 'active' để hiện/ẩn menu
            userMenu.classList.toggle("active");
        });

        // Ẩn menu khi click ra ngoài
        document.addEventListener("click", (event) => {
            if (!userMenu.contains(event.target)) {
                userMenu.classList.remove("active");
            }
        });
    }
});


