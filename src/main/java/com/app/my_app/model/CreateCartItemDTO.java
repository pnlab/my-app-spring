package com.app.my_app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartItemDTO {
    private Long userId;
    private Long productId;
    private Integer quantity;

}
