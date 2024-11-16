// Kiểm tra đăng nhập trước khi thêm món vào giỏ hàng
const addToCartButtons = document.querySelectorAll('.btn-outline-dark');
addToCartButtons.forEach(button => {
    button.addEventListener('click', function() {
        // Kiểm tra nếu người dùng đã đăng nhập
        const isLoggedIn = localStorage.getItem('isLoggedIn');
        
        if (isLoggedIn === 'true') {
            const productName = button.closest('.card').querySelector('h5').textContent;
            alert(`${productName} đã được thêm vào giỏ hàng.`);
            updateCart();  // Cập nhật giỏ hàng
        } else {
            alert('Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng.');
            window.location.href = '/dang_nhap.html';  // Chuyển hướng đến trang đăng nhập
        }
    });
});

// Cập nhật giỏ hàng
function updateCart() {
    const cartCount = document.querySelectorAll('.btn-outline-dark').length;
    document.querySelector('.badge').textContent = cartCount;
}



document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.querySelector('.search-bar'); // Tìm kiếm input
    const products = document.querySelectorAll('.card'); // Tất cả các sản phẩm

    searchInput.addEventListener('input', () => {
        const searchQuery = searchInput.value.toLowerCase(); // Lấy từ khóa tìm kiếm

        products.forEach(product => {
            const productName = product.querySelector('h5').textContent.toLowerCase(); // Lấy tên sản phẩm
            if (productName.includes(searchQuery)) {
                product.style.display = ''; // Hiển thị sản phẩm nếu tên chứa từ khóa tìm kiếm
            } else {
                product.style.display = 'none'; // Ẩn sản phẩm nếu không chứa từ khóa
            }
        });
    });
});
