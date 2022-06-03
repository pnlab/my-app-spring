const mangSanPham = [
  {
    name: "Cà phê uống liền Nescafé Latte lon 180ml",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162428162813310234294-LON-C%C3%A0-pho-sua-do-uong-lien-Nescafe-lon-170ml.jpg",
    unit: '""',
    price: "7.000",
  },
  {
    name: "Cà phê đen uống liền Café Việt Nescafé lon 170ml",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162428161592610314423-LON-Thuc-uong-dinh-duong-lya-mach-Milo-Nestlo-thung-24-lon-x-240ml.jpg",
    unit: '""',
    price: "7.400",
  },
  {
    name: "Sữa tươi nguyên kem tiệt trùng Lemontree Dairy hộ...",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162428240833410056267-HOP-Khan-giay-1-lop-Lency-33cm-gui-1kg.jpg",
    unit: "LON",
    price: "29.900",
  },
  {
    name: "Sữa tắm Elizzer Goat's Milk 1000ml",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162428440283210017624-CHA-Phu-mai-ELLE&VIRE-kem-cream-150g.jpg",
    unit: '""',
    price: "88.000",
  },
  {
    name: "Bánh đa cua Vifon bát 125g",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162427283214610007953-G1-Bonh-da-cua-an-lien-Vifon-gui-60g.jpg",
    unit: "LON",
    price: "17.300",
  },
  {
    name: "Phở thịt bò Vifon bát 120g",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162427279536210007964-G1-Pho-thit-bu-Vifon-gui-65g.jpg",
    unit: '""',
    price: "17.600",
  },
  {
    name: "Phở thịt gà Vifon bát 120g",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162427278551310007963-G1-Pho-thit-g%C3%A0-Vifon-gui-65g.jpg",
    unit: "HỘP",
    price: "16.800",
  },
  {
    name: "Nước tăng lực Predato lon 330ml",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/10195616_ff1f8567-756d-494c-b72e-a264d4d52705.jpg",
    unit: '""',
    price: "8.500",
  },
  {
    name: "Cá trứng Đôi Đũa Vàng khay 400g",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/10006535_199c80ef-4eb6-40f0-a5a3-b1848ce76058.jpg",
    unit: "CHAI",
    price: "94.600",
  },
  {
    name: "Lốc 4 hộp sữa uống dinh dưỡng Colos Gold 110ml",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/sudd%20vinamilk%20colosgold%204*110mlt12%20sudd%20vinamilk%20colosgold%204*110mlt12_fa548c20-cb8d-4b29-bd51-e38a3b733933.png",
    unit: '""',
    price: "31.600",
  },
  {
    name: "Táo Queen New Zealand PG size 90-120",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162427182365910055070-kg-too-queen-pg-size-110(vmnk)_5ae3fcb5-57b1-4a5e-8764-09f0fb10d5bc.jpg",
    unit: "GÓI",
    price: "99.900",
  },
  {
    name: "Cam ruột vàng úc",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162985899484210628789.jpg",
    unit: '""',
    price: "47.940",
  },
  {
    name: "Rau muống WinEco",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162427541254510054693-KG-Rau-ngut.jpg",
    unit: "GÓI",
    price: "13.150",
  },
  {
    name: "Cải chíp",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/1629651311173product4342.jpg",
    unit: '""',
    price: "9.870",
  },
  {
    name: "Cải xanh",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162428528384910053865-KG-Cam-s%C3%A0nh-loai-lon.jpg",
    unit: "GÓI",
    price: "10.500",
  },
  {
    name: "Cải ngọt WinEco",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162428528744910054696-KG-Cai-thao-L1-MT.jpg",
    unit: '""',
    price: "14.950",
  },
  {
    name: "Lê đỏ Nam phi",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162428246526910056225-KG-Ba-roi-xung-khui-xat-lot-Le-Gourmet-gui-200g.jpg",
    unit: "LON",
    price: "44.950",
  },
  {
    name: "Rau muống",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162427542940610053880-KG-Rau-ngo.jpg",
    unit: '""',
    price: "21.000",
  },
  {
    name: "Súp lơ (Bông cải) xanh",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162427337001710053922-KG-Syp-lo-(bung-cai)-xanh-L1-MT.jpg",
    unit: "KHAY",
    price: "37.450",
  },
  {
    name: "Khoai lang giống Nhật",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/10053951_1d0fad92-e349-49e5-81bf-3de48186ce36.jpg",
    unit: '""',
    price: "36.900",
  },
  {
    name: "Thịt vai heo Meat Deli (S)",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162428206102210617956-KG-Giu-Lua-MEATDeli-lo-chuoi-thuong-hang-250g.jpg",
    unit: "GÓI 4",
    price: "51.960",
  },
  {
    name: "Nộm sứa Tĩnh Gia Nhật Minh gói 350g",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/162427611046910005597-G1-Nho-den-KYOHO-H%C3%A0n-Quoc.jpg",
    unit: '""',
    price: "27.500",
  },
  {
    name: "Tôm nõn khô hộp 200g",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/888cfb3c-d711-43c1-b91a-c78469ba2620_20210916025751.jpg",
    unit: "1 KG",
    price: "190.500",
  },
  {
    name: "Trứng gà sạch O'LALA hộp 10 quả",
    image:
      "https://cdn-crownx.winmart.vn/images/prod/tr%E1%BB%A9ng%20g%C3%A0%20s%E1%BA%A1ch%20o'lala%20h%E1%BB%99p%2010_tc%20tr%E1%BB%A9ng%20g%C3%A0%20s%E1%BA%A1ch%20o'lala%20h%E1%BB%99p%2010_tc_905612e1-b40b-4002-9996-45829206e477.jpg",
    unit: '""',
    price: "30.000",
  },
];
