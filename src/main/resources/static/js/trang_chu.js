


// let list = document.querySelector('.slider .list');
// let items = document.querySelectorAll('.slider .list .item');
// let dots = document.querySelectorAll('.slider .dots li');
// let prev = document.getElementById('prev');
// let next = document.getElementById('next');

// // vị trí item đầu tiên =0
// let active = 0; 
// let lengthItems=items.length-1;

// next.onclick=function(){
//     if(active+1>lengthItems){
//         active=0;
//     }
//     else{
//         active=active+1;
//     }
//     reloadSlider();
// }

// prev.onclick = function(){
//     if(active-1<0){
//         active=lengthItems;
//     }
//     else{
//         active=active-1;
//     }
//     reloadSlider();
// }

// let refreshSlider = setInterval(()=> {next.click()},3000);
// function reloadSlider(){
//     let checkLeft = items[active].offsetLeft;
//     list.style.left = -checkLeft + 'px';

//     let lastActiveDot = document.querySelector('.slider .dots li.active');
//     lastActiveDot.classList.remove('active');
//     dots[active].classList.add('active');
//     clearInterval(refreshSlider);
//     refreshSlider = setInterval(()=> {next.click()},3000);
// }

// dots.forEach((li,key ) =>{
//     li.addEventListener('click',function(){
//         active=key;
//         reloadSlider();
//     })
// })





let list = document.querySelector('.slider .list');
let items = document.querySelectorAll('.slider .list .item');
let dots = document.querySelectorAll('.slider .dots li');
let prev = document.getElementById('prev');
let next = document.getElementById('next');

// vị trí item đầu tiên =0
let active = 0; 
let lengthItems = items.length - 1;

// Thêm class active cho item đầu tiên
items[active].classList.add('active');

next.onclick = function() {
    // Loại bỏ class active ở item cũ
    items[active].classList.remove('active');
    
    // Tính toán item mới
    if(active + 1 > lengthItems) {
        active = 0;
    } else {
        active = active + 1;
    }
    
    // Thêm class active cho item mới
    items[active].classList.add('active');
    
    reloadSlider();
}

prev.onclick = function() {
    // Loại bỏ class active ở item cũ
    items[active].classList.remove('active');
    
    // Tính toán item mới
    if(active - 1 < 0) {
        active = lengthItems;
    } else {
        active = active - 1;
    }
    
    // Thêm class active cho item mới
    items[active].classList.add('active');
    
    reloadSlider();
}

let refreshSlider = setInterval(() => {next.click()}, 3000);

function reloadSlider() {
    let checkLeft = items[active].offsetLeft;
    list.style.left = -checkLeft + 'px';

    let lastActiveDot = document.querySelector('.slider .dots li.active');
    lastActiveDot.classList.remove('active');
    dots[active].classList.add('active');
    
    clearInterval(refreshSlider);
    refreshSlider = setInterval(() => {next.click()}, 3000);
}

dots.forEach((li, key) => {
    li.addEventListener('click', function() {
        // Loại bỏ class active ở item cũ
        items[active].classList.remove('active');
        
        active = key;
        
        // Thêm class active cho item mới
        items[active].classList.add('active');
        
        reloadSlider();
    })
})


// Xử lý toggle menu
const toggle = document.getElementById('toggle');
const navbar = document.querySelector('.navbar');
const header = document.querySelector('.header');

toggle.addEventListener('click', () => {
  // Lấy chiều cao của header
  const headerHeight = header.offsetHeight;

  // Đặt vị trí top của menu bằng chiều cao header
  navbar.style.top = `${headerHeight}px`;

  // Bật/tắt menu
  navbar.classList.toggle('active');
});


