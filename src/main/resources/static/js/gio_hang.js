document.addEventListener("DOMContentLoaded", function () {
    const productItems = document.querySelectorAll(".product-item");
    const totalContainer = document.querySelector(".total-container");

    // Hàm tính tổng tiền
    function updateTotal() {
        let total = 0;

        productItems.forEach(item => {
            const price = parseInt(item.dataset.price, 10);
            const quantity = parseInt(item.querySelector(".quantity-input").value, 10);
            total += price * quantity;
        });

        totalContainer.textContent = `Tổng cộng: ${total.toLocaleString()} VND`;
    }

    // Gắn sự kiện cho các nút tăng/giảm số lượng
    productItems.forEach(item => {
        const decreaseBtn = item.querySelector(".decrease-btn");
        const increaseBtn = item.querySelector(".increase-btn");
        const quantityInput = item.querySelector(".quantity-input");

        decreaseBtn.addEventListener("click", function () {
            let currentQuantity = parseInt(quantityInput.value, 10);
            if (currentQuantity > 1) {
                quantityInput.value = currentQuantity - 1;
                updateTotal();
            }
        });

        increaseBtn.addEventListener("click", function () {
            let currentQuantity = parseInt(quantityInput.value, 10);
            quantityInput.value = currentQuantity + 1;
            updateTotal();
        });

        // Cập nhật tổng tiền khi người dùng nhập số lượng trực tiếp
        quantityInput.addEventListener("input", function () {
            let currentQuantity = parseInt(quantityInput.value, 10);
            if (isNaN(currentQuantity) || currentQuantity < 1) {
                quantityInput.value = 1;
            }
            updateTotal();
        });
    });

    // Tính tổng tiền ban đầu
    updateTotal();
});
