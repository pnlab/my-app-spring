let user;
async function fetchUser(){
    const u  = await fetch('/api/users/me').then(res=>res.json())
    user = u;
    console.log("User",u)
}
fetchUser()


function refreshPage () {
    var page_y = $(document).scrollTop();
    window.location.href = window.location.href + '?page_y=' + page_y;
}

const dsSanPham = document.getElementById("ds-san-pham");

console.log(dsSanPham);

let sp = document.getElementsByClassName("san_pham")

async function addCart(productId, userId) {
    await fetch('/api/cartItems', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "userId": user.userId,
            "productId": productId,
            "quantity": 1
        })
    }).then(() => {
        refreshPage()
    })
}

function mauSanPham(ten, anh, mota, gia, id) {
    return `<div class="san_pham">
          <div class="noi_dung_san_pham">
            <p class="ghi_chu">
              Thực phẩm
            </p>
            <img class="anh-minh_hoa_san_pham"
              src="${anh}" />
            <div class="can_le"></div>
            <h4 class="ten_san_pham">${ten}</h4>

            <p class="mo_ta_san_pham">${""}</p>
            <div class="gia_san_pham">
              <p>đ${gia}</p>
            </div>
            <button onClick="addCart(${id},1)" class="nut_them_vao_gio_hang ">
              Thêm vào giỏ hàng
            </button>
          </div>
        </div>`;
}


async function loadProduct() {
    const products = await fetch('/api/products').then(res => res.json())
    console.log(products)
    products.map(product => {
        dsSanPham.innerHTML += mauSanPham(product.name, product.image, "", product.price, product.id);
    })

}

loadProduct();


// Danh muc
function hienThiDanhMuc() {
    document.getElementById("danhmuc-nd").style.display = "block"
}


window.onclick = function (e) {
    if (!e.target.matches(".danhmuc-nut")) {
        document.getElementById("danhmuc-nd").style.display = "none";
    }
};

let soLuong = 0;

function tinhGia() {
    let tongTien = 0;
    const dsSanPham = document.querySelectorAll(".sanpham");
    dsSanPham.forEach((e) => {
        const gia = e.querySelector(".gia").innerText;
        const soLuong = e.querySelector(".soluong").value;
        const tong = Number(gia) * Number(soLuong);
        e.querySelector(".tong").innerText = Math.round(tong * 100) / 100;
        tongTien += tong;
    });
    document.querySelector("#tong-tien").innerText =
        Math.round(tongTien * 100) / 100;
}

tinhGia();

function tangSL(id) {
    soLuong++;
    $("#" + id).val(soLuong);
    tinhGia();
}

function giamSL(id) {
    if (soLuong > 0) {
        soLuong--;
        $("#" + id).val(soLuong);
        tinhGia();
    }
}


