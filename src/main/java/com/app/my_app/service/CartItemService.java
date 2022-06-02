package com.app.my_app.service;

import com.app.my_app.domain.CartItem;
import com.app.my_app.domain.Product;
import com.app.my_app.domain.User;
import com.app.my_app.model.CartItemDTO;
import com.app.my_app.repos.CartItemRepository;
import com.app.my_app.repos.ProductRepository;
import com.app.my_app.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartItemService(final CartItemRepository cartItemRepository,
            final UserRepository userRepository, final ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<CartItemDTO> findAll() {
        return cartItemRepository.findAll()
                .stream()
                .map(cartItem -> mapToDTO(cartItem, new CartItemDTO()))
                .collect(Collectors.toList());
    }

    public CartItemDTO get(final Long id) {
        return cartItemRepository.findById(id)
                .map(cartItem -> mapToDTO(cartItem, new CartItemDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CartItemDTO cartItemDTO) {
        final CartItem cartItem = new CartItem();
        mapToEntity(cartItemDTO, cartItem);
        return cartItemRepository.save(cartItem).getId();
    }

    public void update(final Long id, final CartItemDTO cartItemDTO) {
        final CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(cartItemDTO, cartItem);
        cartItemRepository.save(cartItem);
    }

    public void delete(final Long id) {
        cartItemRepository.deleteById(id);
    }

    private CartItemDTO mapToDTO(final CartItem cartItem, final CartItemDTO cartItemDTO) {
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setUser(cartItem.getUser() == null ? null : cartItem.getUser().getId());
        cartItemDTO.setProduct(cartItem.getProduct() == null ? null : cartItem.getProduct().getId());
        return cartItemDTO;
    }

    private CartItem mapToEntity(final CartItemDTO cartItemDTO, final CartItem cartItem) {
        cartItem.setQuantity(cartItemDTO.getQuantity());
        if (cartItemDTO.getUser() != null && (cartItem.getUser() == null || !cartItem.getUser().getId().equals(cartItemDTO.getUser()))) {
            final User user = userRepository.findById(cartItemDTO.getUser())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
            cartItem.setUser(user);
        }
        if (cartItemDTO.getProduct() != null && (cartItem.getProduct() == null || !cartItem.getProduct().getId().equals(cartItemDTO.getProduct()))) {
            final Product product = productRepository.findById(cartItemDTO.getProduct())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
            cartItem.setProduct(product);
        }
        return cartItem;
    }

}
