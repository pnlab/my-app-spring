package com.app.my_app.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/*
This is class is required for creating a response containing the JWT to be returned to the user.
 */
@Getter
@Setter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
