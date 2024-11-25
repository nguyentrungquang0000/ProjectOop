const products = {
    data: [
        {
            productName: "Khô Bò",
            price: "40.000đ - 80.000đ",
            image: "img/Screenshot 2024-11-01 101958.png"
        },
        {
            productName: "Nem Nướng",
            price: "30.000đ",
            image: "img/ảnh nem nướng nha trang.png"
        },
        {
            productName: "Bò Bít-Tết",
            price: "250.000đ",
            image: "img/Screenshot 2024-11-01 101547.png"
        },
        {
            productName: "Khoai Tây Chiên",
            price: "30.000đ",
            image: "img/Screenshot 2024-11-01 101958.png"
        },
        {
            productName: "Cơm gà",
            price: "30.000đ",
            image: "img/Screenshot 2024-11-01 101958.png"
        },
        {
            productName: "Gà tây",
            price: "30.000đ",
            image: "img/Screenshot 2024-11-01 101958.png"
        },
        {
            productName: "Cơm trộn",
            price: "30.000đ",
            image: "img/Screenshot 2024-11-01 101958.png"
        },
        {
            productName: "Khoai Tây Chiên",
            price: "30.000đ",
            image: "img/Screenshot 2024-11-01 101958.png"
        },
        // Thêm các sản phẩm khác
    ]
};

document.addEventListener('DOMContentLoaded', () => {
    const productsContainer = document.querySelector('.row');
    const searchInput = document.querySelector('.search-input');
    const searchBtn = document.querySelector('.search-btn');
    const cartBadge = document.querySelector('.badge');

    // Function to display products
    const displayProducts = (productsList) => {
        productsContainer.innerHTML = ''; // Clear current products
        productsList.forEach(product => {
            const productCol = document.createElement('div');
            productCol.classList.add('col', 'mb-5');

            const productCard = document.createElement('div');
            productCard.classList.add('card', 'h-100');

            const productImg = document.createElement('img');
            productImg.classList.add('card-img-top');
            productImg.src = product.image;
            productImg.alt = product.productName;

            const cardBody = document.createElement('div');
            cardBody.classList.add('card-body', 'p-4');
            const cardBodyContent = document.createElement('div');
            cardBodyContent.classList.add('text-center');

            const productName = document.createElement('h5');
            productName.classList.add('fw-bolder');
            productName.textContent = product.productName;

            const productPrice = document.createElement('p');
            productPrice.classList.add('price');
            productPrice.textContent = product.price;

            cardBodyContent.appendChild(productName);
            cardBodyContent.appendChild(productPrice);
            cardBody.appendChild(cardBodyContent);

            const cardFooter = document.createElement('div');
            cardFooter.classList.add('card-footer', 'p-4', 'pt-0', 'border-top-0', 'bg-transparent');
            const footerContent = document.createElement('div');
            footerContent.classList.add('text-center');
            const addToCartBtn = document.createElement('a');
            addToCartBtn.classList.add('btn', 'btn-outline-dark', 'mt-auto');
            addToCartBtn.href = '#';
            addToCartBtn.textContent = 'Thêm vào giỏ hàng';

            addToCartBtn.addEventListener('click', function () {
                if (isUserLoggedIn()) {
                    const productName = product.productName;
                    alert(`${productName} đã được thêm vào giỏ hàng.`);
                    updateCartCount();
                } else {
                    alert('Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng.');
                    window.location.href = '/dang_nhap.html';
                }
            });

            footerContent.appendChild(addToCartBtn);
            cardFooter.appendChild(footerContent);

            productCard.appendChild(productImg);
            productCard.appendChild(cardBody);
            productCard.appendChild(cardFooter);

            productCol.appendChild(productCard);
            productsContainer.appendChild(productCol);
        });
    };

    // Initial display of all products
    displayProducts(products.data);

    // Function to filter products based on search input
    const filterProducts = (searchTerm) => {
        const filteredProducts = products.data.filter(product =>
            product.productName.toLowerCase().includes(searchTerm.toLowerCase())
        );
        displayProducts(filteredProducts);
    };

    // Event listener for the search button
    searchBtn.addEventListener('click', () => {
        const searchTerm = searchInput.value.trim();
        if (searchTerm) {
            filterProducts(searchTerm);
        } else {
            // Display all products if search input is empty
            displayProducts(products.data);
        }
    });

    // Event listener for the search input (filter as you type)
    searchInput.addEventListener('input', () => {
        const searchTerm = searchInput.value.trim();
        if (searchTerm) {
            filterProducts(searchTerm);
        } else {
            // Display all products if search input is empty
            displayProducts(products.data);
        }
    });

    // Check if there's a search query in the URL
    const urlParams = new URLSearchParams(window.location.search);
    const searchQuery = urlParams.get('search');
    if (searchQuery) {
        searchInput.value = searchQuery; // Update search input
        filterProducts(searchQuery); // Perform search
    }

    // Hàm kiểm tra trạng thái đăng nhập
    function isUserLoggedIn() {
        return localStorage.getItem('isLoggedIn') === 'true';
    }

    // Hàm cập nhật giỏ hàng
    function updateCartCount() {
        let cartCount = parseInt(cartBadge.textContent) || 0;
        cartBadge.textContent = cartCount + 1; // Tăng số lượng sản phẩm trong giỏ
    }
});




document.addEventListener("DOMContentLoaded", () => {
    const cartIcon = document.getElementById("cart-icon");

    // Giả sử chúng ta lưu trạng thái đăng nhập trong `localStorage`
    const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";

    cartIcon.addEventListener("click", (event) => {
        if (!isLoggedIn) {
            event.preventDefault(); // Ngăn chặn chuyển hướng
            alert("Bạn cần đăng nhập để truy cập giỏ hàng!");
            window.location.href = "/dang_nhap.html"; // Chuyển hướng đến trang đăng nhập
        }
    });
});


