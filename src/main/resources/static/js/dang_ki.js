function handleRegister(event) {
    event.preventDefault(); // Ngăn reload trang

   // Lấy giá trị từ các input
   const fullName = document.getElementById("register-fullname").value.trim();
   const username = document.getElementById("register-username").value.trim();
   const phoneNumber = document.getElementById("register-phonenumber").value.trim();
   const password = document.getElementById("register-password").value;
   const confirmPassword = document.getElementById("register-confirm-password").value;

    // Kiểm tra các trường có trống không
    if (!fullName || !username || !phoneNumber || !password || !confirmPassword) {
        alert("Vui lòng điền đầy đủ thông tin!");
        return;
    }

    // Kiểm tra số điện thoại (chỉ cho phép số và độ dài từ 10-11 ký tự)
    const phoneRegex = /^[0-9]{10,11}$/;
    if (!phoneRegex.test(phoneNumber)) {
        alert("Số điện thoại không hợp lệ! Vui lòng nhập 10-11 chữ số.");
        return;
    }

    // Kiểm tra độ dài mật khẩu (ít nhất 6 ký tự)
    if (password.length < 6) {
        alert("Mật khẩu phải có ít nhất 6 ký tự!");
        return;
    }
    // Kiểm tra mật khẩu và xác nhận mật khẩu
    if (password !== confirmPassword) {
        alert("Mật khẩu và xác nhận mật khẩu không khớp!");
        return;
    }

    $.ajax({
        url: "/api/register",
        method: "POST",
        data: JSON.stringify({
            fullName: fullName,
            phoneNumber: phoneNumber,
            password: password,
            username: username
        }),
        contentType: "application/json",
        success: (res) => {
            alert("Login success!");
        },
        error: (err) => {
            alert("Login fail!");
        }
    })
}


