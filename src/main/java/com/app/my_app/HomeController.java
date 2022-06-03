package com.app.my_app;

import com.app.my_app.domain.User;
import com.app.my_app.model.ProductDTO;
import com.app.my_app.service.ProductService;
import com.app.my_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {

        List<ProductDTO> products = productService.findAll();

        CustomUserDetails u = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.get(1L);

        model.addAttribute("products", products);

        model.addAttribute("username", "Ngueyn");

        model.addAttribute("cardCount", user.getUserCartItems().size());


        return "index";

    }

    @GetMapping("/dangnhap")
    public String login(Model model) {
        model.addAttribute("name", "Nguyen");
        return "dangnhap";

    }

    @GetMapping("/giohang")
    public String gioHang(Model model) {
        model.addAttribute("name", "Nguyen");
        return "gioHang";

    }

    @GetMapping("/thanhtoan")
    public String thanhToan(Model model) {
        model.addAttribute("name", "Nguyen");
        return "thanhtoan";

    }
    @GetMapping("/donhang")
    public String donHang(Model model) {
        model.addAttribute("name", "Nguyen");
        return "donhang";

    }

    @GetMapping("/lienhe")
    public String lienHe(Model model) {
        model.addAttribute("name", "Nguyen");
        return "lienhe";

    }
}
