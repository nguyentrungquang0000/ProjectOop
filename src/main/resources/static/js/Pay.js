// Xử lí khi chọn thanh toán bằng qr
const paymentMethodSelect = document.getElementById('payment-method');
const qrCodeSection = document.getElementById('qr-code');

// lắng nghe khi thay đổi phương thức thanh toán
paymentMethodSelect.addEventListener('change', function () {
    if (paymentMethodSelect.value === 'qr') {
        // Hiển thị mã QR
        qrCodeSection.style.display = 'block'; 
    } else {
        // Ẩn mã QR nếu không phải QR Code
        qrCodeSection.style.display = 'none'; 
    }
});
