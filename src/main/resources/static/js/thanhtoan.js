function matHang(c){
    return `
     <div class="mat-hang">
                                <div>
                                    <span>
                                        <span>${c?.quantity}</span>
                                        <span>x</span>
                                        <span>${c.product?.name}</span> | <span>1lb</span>
                                    </span>
                                </div>
                                <span>₫${c?.quantity * c?.product?.price}</span>
                            </div>
                            `
}

async function loadCart(){
    const carts = await fetch("/api/cartItems").then(res=>res.json())
    console.log(carts)
    carts.map(c =>{
        $('.ds-mat-hang').append(matHang(c))
    })
}
loadCart();