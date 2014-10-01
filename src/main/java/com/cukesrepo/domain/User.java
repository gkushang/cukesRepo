package com.cukesrepo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.Generated;

/**
 * Created by maduraisamy on 12/23/13.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
        "username",
        "password",
        "role"
})
@Document(collection = "user")
public class User {

    public static final String NAME = "username";
    public static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";

    @Field(NAME)
    private String userName;

    @Field(PASSWORD)

    private String password;

    @Field(ROLE)
    private String role;


    @Field(EMAIL)
    private String email;


    public String getEmail() {
          return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
