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

    // Xử lí các nút tăng/giảm số lượng
    productItems.forEach(item => {
        const decreaseBtn = item.querySelector(".decrease-btn");
        const increaseBtn = item.querySelector(".increase-btn");
        const quantityInput = item.querySelector(".quantity-input");
        const productId = parseInt(item.dataset.productid, 10);
        const id = parseInt(item.dataset.id, 10);
        console.log(productId, id);
        decreaseBtn.addEventListener("click", function () {
            let currentQuantity = parseInt(quantityInput.value, 10);
            if (currentQuantity > 1) {
                quantityInput.value = currentQuantity - 1;
                $.ajax({
                    url: "/api/cartitem/update",
                    method: "PUT",
                    data: JSON.stringify({
                        id: id,
                        productId: productId,
                        quantity: currentQuantity - 1
                    }),
                    contentType: "application/json",
                    success: (res) => {
                        console.log(res);
                    },
                    error: (err) => {
                        console.log(err);
                    }
                });
                updateTotal();
            }
        });

        increaseBtn.addEventListener("click", function () {
            let currentQuantity = parseInt(quantityInput.value, 10);
            quantityInput.value = currentQuantity + 1;
            $.ajax({
                url: "/api/cartitem/update",
                method: "PUT",
                data: JSON.stringify({
                    id: id,
                    productId: productId,
                    quantity: currentQuantity + 1
                }),
                contentType: "application/json",
                success: (res) => {
                    console.log(res);
                },
                error: (err) => {
                    console.log(err);
                }
            });
            updateTotal();
        });
    });

    // Xử lí nút xóa
    const deleteButtons = document.querySelectorAll('.delete-btn');

    deleteButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            const deleteBtn = event.target;
            const item = deleteBtn.closest(".product-item");
            const id = parseInt(item.dataset.id, 10);
            if(confirm("Bạn chắc chắn muốn xoá không?")) {
                $.ajax({
                    url: `/api/cartitem/delete-${id}`,
                    method: "DELETE",
                    success: (res) => {
                        window.location.reload();
                    },
                    error: (err) => {
                        console.log(err);
                    }
                })
                updateTotal();
            }
        });
    });

    // Hàm cập nhật tổng tiền
    function updateTotal() {
        let total = 0;
        const productItems = document.querySelectorAll('.product-item');

        productItems.forEach(item => {
            const price = parseFloat(item.getAttribute('data-price'));
            const quantity = item.querySelector('.quantity-input').value;
            total += price * quantity;
        });

        // Cập nhật tổng cộng
        document.querySelector('.total-container').textContent = `Tổng cộng: ${total.toLocaleString()} VND`;
    }


    // Tính tổng tiền ban đầu
    updateTotal();
});
