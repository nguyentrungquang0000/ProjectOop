document.addEventListener("DOMContentLoaded", function () {
    // Lấy trạng thái đăng nhập từ localStorage
    var isLoggedIn = localStorage.getItem("isLoggedIn") === "true";
    console.log("Trạng thái đăng nhập:", isLoggedIn);

    // Nếu người dùng chưa đăng nhập, chuyển hướng tới trang đăng nhập khi bấm vào mục Thực Đơn
    var menuLink = document.querySelector("a[href='/do_an.html']"); // Chọn liên kết với đường dẫn "/do_an.html"
    if (menuLink) {
        menuLink.addEventListener("click", function (event) {
            if (!isLoggedIn) {
                event.preventDefault();
                alert("Bạn cần đăng nhập để truy cập mục Thực Đơn.");
                window.location.href = "/dang_nhap.html"; // Đã thay trang đăng nhập đúng
            }
        });
    } else {
        console.error("Không tìm thấy liên kết 'Thực Đơn'");
    }

    // Kiểm tra xem người dùng có đang ở trang do_an.html không
    // const currentPage = window.location.pathname;
    // if (currentPage === "/do_an.html" && !isLoggedIn) {
    //     alert("Bạn cần đăng nhập để truy cập mục Thực Đơn.");
    //     window.location.href = "/dang_nhap.html"; // Đã thay trang đăng nhập đúng
    // }
});

// Khởi tạo bản đồ
function initMap() {
    const location = { lat: 21.00096, lng: 105.8148101 };
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 15,
        center: location,
    });
    const marker = new google.maps.Marker({
        position: location,
        map: map,
        title: "Cô Cô Shop - Địa Chỉ Của Chúng Tôi"
    });
}


document.addEventListener("DOMContentLoaded", function() {
    const navLinks = document.querySelectorAll('.nav-link');
    const currentPath = window.location.pathname;

    navLinks.forEach(link => {
        if (link.getAttribute('href') === currentPath) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });
});
