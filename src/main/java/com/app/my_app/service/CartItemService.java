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
    private ModelMapper mapper;

    public CartItemService(final CartItemRepository cartItemRepository,
            final UserRepository userRepository, final ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
//                .stream()
//                .map(cartItem -> mapper.map(cartItem, CartItemDTO.class))
//                .collect(Collectors.toList());
    }

    public List<CartItem> findAllByUserId(Long userId) {
        return cartItemRepository.findAllByUserId(userId);
//                .stream()
//                .map(cartItem -> mapper.map(cartItem, CartItemDTO.class))
//                .collect(Collectors.toList());
    }

    public CartItemDTO get(final Long id) {
        return cartItemRepository.findById(id)
                .map(cartItem -> mapToDTO(cartItem, new CartItemDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CreateCartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        mapToEntity(cartItemDTO, cartItem);
//        cartItem = mapper.map(cartItemDTO, CartItem.class);
        return cartItemRepository.save(cartItem).getId();
    }

    public void update(final Long id, final CreateCartItemDTO createCartItemDTO) {
        final CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(createCartItemDTO, cartItem);
        cartItemRepository.save(cartItem);
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

    private CartItem mapToEntity(final CreateCartItemDTO cartItemDTO, final CartItem cartItem) {
        cartItem.setQuantity(cartItemDTO.getQuantity());
        if (cartItemDTO.getUserId() != null) {
            final User user = userRepository.findById(cartItemDTO.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
            cartItem.setUser(user);
        }
        if (cartItemDTO.getProductId() != null) {
            final Product product = productRepository.findById(cartItemDTO.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
            cartItem.setProduct(product);
        }
        return cartItem;
    }

}
