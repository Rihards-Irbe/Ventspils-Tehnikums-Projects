var cpu_price = 297.00;
var gpu_price = 612.00;
var mobo_price = 198.00;
var psu_price = 122.00;
var pccase_price = 51.60;
var ssd_price = 101.00;
var total_price = 0;
var old_price = 0;

function Animation(){
var image = document.getElementById("cpu");
var square = document.getElementById("my-square");
var closeButton = document.getElementById("Close");

image.addEventListener("click", function() {
  square.style.display = "block";
  square.classList.remove("fade-out");
  square.classList.add("fade-in");
});
closeButton.addEventListener("click", function() {
  square.classList.remove("fade-in");
  square.classList.add("fade-out");
  setTimeout(function() {
    square.style.display = "none";
  }, 1000);
});
    cpu_price = 297;
    return cpu_price;
}

function gpu_animation(){
var image = document.getElementById("gpu");
var square = document.getElementById("my-square_2");
var closeButton = document.getElementById("Close_2");

image.addEventListener("click", function() {
  square.style.display = "block";
  square.classList.remove("fade-out");
  square.classList.add("fade-in");
});
closeButton.addEventListener("click", function() {
  square.classList.remove("fade-in");
  square.classList.add("fade-out");
  setTimeout(function() {
    square.style.display = "none";
  }, 1000);
});
}


function mobo_animation(){
var image = document.getElementById("mobo");
var square = document.getElementById("my-square_3");
var closeButton = document.getElementById("Close_3");

image.addEventListener("click", function() {
  square.style.display = "block";
  square.classList.remove("fade-out");
  square.classList.add("fade-in");
});
closeButton.addEventListener("click", function() {
  square.classList.remove("fade-in");
  square.classList.add("fade-out");
  setTimeout(function() {
    square.style.display = "none";
  }, 1000);
});
}

function psu_animation(){
var image = document.getElementById("psu");
var square = document.getElementById("my-square_4");
var closeButton = document.getElementById("Close_4");

image.addEventListener("click", function() {
  square.style.display = "block";
  square.classList.remove("fade-out");
  square.classList.add("fade-in");
});
closeButton.addEventListener("click", function() {
  square.classList.remove("fade-in");
  square.classList.add("fade-out");
  setTimeout(function() {
    square.style.display = "none";
  }, 1000);
});
}

function pccase_animation(){
var image = document.getElementById("pccase");
var square = document.getElementById("my-square_5");
var closeButton = document.getElementById("Close_5");

image.addEventListener("click", function() {
  square.style.display = "block";
  square.classList.remove("fade-out");
  square.classList.add("fade-in");
});
closeButton.addEventListener("click", function() {
  square.classList.remove("fade-in");
  square.classList.add("fade-out");
  setTimeout(function() {
    square.style.display = "none";
  }, 1000);
});
}

function ssd_animation(){
var image = document.getElementById("ssd");
var square = document.getElementById("my-square_6");
var closeButton = document.getElementById("Close_6");

image.addEventListener("click", function() {
  square.style.display = "block";
  square.classList.remove("fade-out");
  square.classList.add("fade-in");
});
closeButton.addEventListener("click", function() {
  square.classList.remove("fade-in");
  square.classList.add("fade-out");
  setTimeout(function() {
    square.style.display = "none";
  }, 1000);
});
}


function preview(){

let images = document.querySelectorAll("#image-container img");
let prevArrow = document.getElementById("prev-arrow");
let nextArrow = document.getElementById("next-arrow");
let currentImageIndex = 0;

prevArrow.addEventListener("click", function(){
  images[currentImageIndex].classList.remove("active");
  currentImageIndex--;
  if (currentImageIndex < 0) {
    currentImageIndex = images.length - 1;
  }
  images[currentImageIndex].classList.add("active");
});

nextArrow.addEventListener("click", function(){
  images[currentImageIndex].classList.remove("active");
  currentImageIndex++;
  if (currentImageIndex >= images.length) {
    currentImageIndex = 0;
  }
  images[currentImageIndex].classList.add("active");
});
}

function cart(){
    
    let cart = [];
    let price = [];
    
document.querySelector('#button-68').addEventListener('click', function(){ 
    
    price.push(cpu_price);
    
});

let buttons = document.querySelectorAll('.add-to-cart');
  buttons.forEach(function (button) {
    button.addEventListener('click', function () {
      let item = this.dataset.item;
      let cart = JSON.parse(localStorage.getItem('cart')) || {};
      if (cart[item]) {
        cart[item]++;
      } else {
        cart[item] = 1;
      }
      localStorage.setItem('cart', JSON.stringify(cart));
    });
  });

const addToCart = (event) => {
    const item = event.target.dataset.item;
    var item_price = event.target.dataset.price;
    cart.push(item);
    console.log(cart);
}

const cartBtn = document.getElementById("cart-btn");
cartBtn.addEventListener("click", () => {
    console.log(cart);
    console.log(price);
});

const addCartBtns = document.querySelectorAll(".add-to-cart");
addCartBtns.forEach(button => {
    button.addEventListener("click", (e) => {
        addToCart(e);
        
    });
});

const cartDropdown = document.getElementById("cart-dropdown");
const cartItems = document.getElementById("cart-items");

cartBtn.addEventListener("click", () => {
    
        total_price = total_price;
    
    if (cartDropdown.classList.contains("hidden")) {
        cartDropdown.classList.remove("hidden");
        
        cartItems.innerHTML = "";
    
        total_price = total_price;
        
        cart.forEach((item) => {
            const li = document.createElement("li");
            
            
                if(item == "CPU: i5 12600K"){
                    
                li.innerHTML = item + ", Items price: " + cpu_price + "€";
                cartItems.appendChild(li);
                total_price = total_price;
                total_price = total_price + cpu_price;
                
                }else if(item =="GPU: RTX 3070"){
                    
                li.innerHTML = item + ", Items price: " + gpu_price + "€";
                cartItems.appendChild(li);
                total_price = total_price;
                total_price = total_price + gpu_price;
                    
                }else if(item =="Motherboard: MSI PRO Z690-A"){
                    
                li.innerHTML = item + ", Items price: " + mobo_price + "€";
                cartItems.appendChild(li);
                total_price = total_price;
                total_price = total_price + mobo_price;
                    
                }else if(item =="PSU: EVGA SuperNova GT 750"){
                    
                li.innerHTML = item + ", Items price: " + psu_price + "€";
                cartItems.appendChild(li);
                total_price = total_price;
                total_price = total_price + psu_price;
                    
                }else if(item =="Case: AeroCool Cylon"){
                    
                li.innerHTML = item + ", Items price: " + pccase_price + "€";
                cartItems.appendChild(li);
                total_price = total_price;
                total_price = total_price + pccase_price;
                    
                }else if(item =="SSD: Samsung 980 PCIe 3.0 NVMe™"){
                    
                li.innerHTML = item + ", Items price: " + ssd_price + "€";
                cartItems.appendChild(li);
                total_price = total_price;
                total_price = total_price + ssd_price;
                
                    
                }else{
            li.innerHTML = item;
            cartItems.appendChild(li);
                }
            
            //old_price = total_price;
            
        });
        
    } else {
        cartDropdown.classList.add("hidden");
    }
    
    if(old_price < total_price){
    
    const sentence = document.createElement("p1");
            sentence.innerHTML = "Carts total price: " + total_price + "€";
            cartItems.appendChild(sentence);
            console.log(old_price);
            total_price = old_price;
    }else if(old_price == total_price){
        
        const sentence = document.createElement("p1");
        sentence.innerHTML = "Carts total price: " + total_price + "€";
        cartItems.appendChild(sentence);
        
    }
    
});
    
    old_price = total_price;
    console.log(old_price);
}