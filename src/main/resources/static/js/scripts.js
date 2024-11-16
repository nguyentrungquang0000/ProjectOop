/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project



document.addEventListener("DOMContentLoaded", function () {
    var isLoggedIn = localStorage.getItem("isLoggedIn");
    isLoggedIn = false;
    console.log(isLoggedIn);

    // Nếu người dùng chưa đăng nhập, chuyển hướng tới trang đăng nhập khi bấm vào mục Thực Đơn
    document.getElementById("menuLink").addEventListener("click", function (event) {
        if (!isLoggedIn) {
            event.preventDefault();
            alert("Bạn cần đăng nhập để truy cập mục Thực Đơn.");
            window.location.href = "/dang_nhap.html";
        }
    });

    // Kiểm tra xem người dùng có đang ở trang do_an.html không
    const currentPage = window.location.pathname;
    if (currentPage === "/do_an.html" && !isLoggedIn) {
        alert("Bạn cần đăng nhập để truy cập mục Thực Đơn.");
        window.location.href = "/dang_nhap.html";
    }
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
