package com.app.my_app.service;

import com.app.my_app.domain.CartItem;
import com.app.my_app.domain.Product;
import com.app.my_app.domain.User;
import com.app.my_app.model.CartItemDTO;
import com.app.my_app.model.CreateCartItemDTO;
import com.app.my_app.model.UpdateCartItemDto;
import com.app.my_app.repos.CartItemRepository;
import com.app.my_app.repos.ProductRepository;
import com.app.my_app.repos.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;


@Service
@Transactional
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    private AuthService authService;

  

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper mapper;

    public CartItemService(final CartItemRepository cartItemRepository,
                           final UserRepository userRepository, final ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public List<CartItem> addProduct(Long productId){
        Product product = productService.get(productId);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setUser(authService.getCurrentUser());
        cartItemRepository.save(cartItem);
        return findAllByUserId();
    }

    public List<CartItem> findAll() {
        return cartItemRepository.findAllByUserId(authService.getCurrentUserId());
    }

    public List<CartItem> findAllByUserId() {        
        return cartItemRepository.findAllByUserId(authService.getCurrentUserId());
    }

    public CartItemDTO get(final Long id) {
        return cartItemRepository.findById(id)
                .map(cartItem -> mapToDTO(cartItem, new CartItemDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Them san pham vao gio hang
    @Transactional
    public CartItem create(final CreateCartItemDTO cartItemDTO) {
        // Kiem tra san pham co trong gio hang hay chua
        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndUserId(cartItemDTO.getProductId(), authService.getCurrentUserId());
        if(cartItem!=null){
            // Neu ton tai thi cong so luong len 1
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }else{
            cartItem = new CartItem();
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItem.setProduct(productService.get(cartItemDTO.getProductId()));
            cartItem.setUser(authService.getCurrentUser());
        }                   
        return cartItemRepository.save(cartItem);
    }


    // Xóa giỏ hàng của 1 user
    public void deleteAll(){
        cartItemRepository.deleteAllByUserId(authService.getCurrentUserId());
    }

    // Cập nhật giỏ hàng
    public CartItem update(Long id, final CreateCartItemDTO cartItemDTO) {
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        //cartItem.setProduct(productService.get(cartItemDTO.getProductId()));
        cartItem.setQuantity(cartItemDTO.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    public void delete(final Long id) {
        cartItemRepository.deleteById(id);
    }

    private CartItemDTO mapToDTO(final CartItem cartItem, final CartItemDTO cartItemDTO) {
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        System.out.println(cartItem.getUser());

//        cartItemDTO.setUser(cartItem.getUser() == null ? null : cartItem.getUser());
        cartItemDTO.setProduct(cartItem.getProduct() == null ? null : cartItem.getProduct());
        return cartItemDTO;
    }



}
