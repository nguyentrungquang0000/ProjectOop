const btnAddItems = document.querySelectorAll(".btn-add-item");
btnAddItems.forEach(btn => {
    btn.addEventListener('click', () => {
        const id = parseInt(btn.dataset.id);
        $.ajax({
            url: `/api/cartitem/${id}`,
            method: "GET",
            success: (res) => {
                if (!res) {
                    $.ajax({
                        url: "/api/cartitem/add",
                        method: "POST",
                        data: JSON.stringify({
                            productId: id,
                            quantity: 1
                        }),
                        contentType: "application/json",
                        success: (res) => {
                            alert(`Thêm sản phẩm thành công!`);
                        },
                        error: (err) => {
                            alert(`Thêm sản phẩm thất bại!`);
                        }
                    });
                } else {
                    $.ajax({
                        url: "/api/cartitem/update",
                        method: "PUT",
                        data: JSON.stringify({
                            id: res['cartItemId'],
                            productId: id,
                            quantity: 1
                        }),
                        contentType: "application/json",
                        success: (res) => {
                            alert(`Sửa sản phẩm thành công!`)
                        },
                        error: (err) => {
                            alert(`Sửa sản phẩm thất bại!`);
                        }
                    });
                }
            },
            error: (err) => {
                window.location.href = "/login";
                alert("Bạn chưa đănh nhập!");
            }
        });
    });
})
