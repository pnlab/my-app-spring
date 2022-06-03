// let user;
// async function fetchUser(){
//     const u  = await fetch('/api/users/me').then(res=>res.json())
//     user = u;
//     console.log("User",u)
// }
//fetchUser()
  function cartItem(cart){
    return `
      <div class="ds-san-pham">
        <div class="tt-san-pham">
          <img src="${cart?.product?.image}" width="60">
          <span class="ten-san-pham-gio-hang">${cart?.product?.name}</span>
        </div>
  
        <div class="tt-so-luong">
          <div class="so-luong">
            <button onclick="updateSL(${cart.id},${cart.quantity-1})">-</button>
            <input type="number" value="${cart.quantity}">
            <button onclick="updateSL(${cart.id},${cart.quantity+1})">+</button>
          </div>
  
          <div class="tong">
            <span class="text-xs font-medium">₫${cart?.quantity * cart?.product?.price}</span>
          </div>
          <div class="xoa">
            <button onclick="xoa(${cart.id})">xóa</button>
          </div>
        </div>              
      </div>
    `
  }

 async function loadCart(){
    const carts = await fetch("/api/cartItems").then(res=>res.json())
    console.log(carts)
    let total = 0
   console.log(total)
    carts.map(c =>{
      $('.san-phams').append(cartItem(c))
      total+= parseInt(c?.quantity || 0) * parseInt(c?.product?.price || 0);
    })
   console.log(total)
   document.querySelector("body > div.than > div.noi_dung > div > div > div > div.gia-don-hang > div:nth-child(2) > p").textContent = total
 }
 loadCart();





async function updateSL(id,quantity) {
  const res = await fetch(`/api/cartItems/${id}`,{
    method: "PUT",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      "quantity": quantity
    })
  }).then(res =>{
    console.log(res)
    window.location.reload()
  })
}

async function xoa(id){
    const res = await fetch(`/api/cartItems/${id}`,{
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        },

    }).then(res =>{
        window.location.reload()
    })
}


