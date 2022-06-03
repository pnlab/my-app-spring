package com.app.my_app.rest;

import com.app.my_app.CustomUserDetails;
import com.app.my_app.domain.CartItem;
import com.app.my_app.model.CartItemDTO;
import com.app.my_app.model.CreateCartItemDTO;
import com.app.my_app.model.UpdateCartItemDto;
import com.app.my_app.service.CartItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/cartItems", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartItemResource {

    private final CartItemService cartItemService;

    public CartItemResource(final CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        CustomUserDetails u = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(cartItemService.findAllByUserId(u.getUserId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> getCartItem(@PathVariable final Long id) {
        return ResponseEntity.ok(cartItemService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCartItem(@RequestBody @Valid final CreateCartItemDTO cartItemDTO) {
        return new ResponseEntity<>(cartItemService.create(cartItemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCartItem(@PathVariable final Long id,
            @RequestBody @Valid final CreateCartItemDTO createCartItemDTO) {
        cartItemService.update(id, createCartItemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCartItem(@PathVariable final Long id) {
        cartItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
