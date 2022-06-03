package com.app.my_app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdateCartItemDto  {
    private Integer quantity;
}
