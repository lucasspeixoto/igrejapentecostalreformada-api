package com.platform.igrejapentecostalreformadaapi.data.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.igrejapentecostalreformadaapi.utils.converters.ToCapitalizeCaseConverter;
import com.platform.igrejapentecostalreformadaapi.utils.converters.ToLowerCaseConverter;

import java.util.Objects;

public class UserVO {

    private Long id;

    @JsonSerialize(converter = ToCapitalizeCaseConverter.class)
    private String name;

    @JsonSerialize(converter = ToLowerCaseConverter.class)
    private String username;

    @JsonSerialize(converter = ToLowerCaseConverter.class)
    //@Email(message = "Insira um e-mail válido!")
    private String email;

    public UserVO() {
    }

    public UserVO(Long id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserVO userVO)) return false;
        return Objects.equals(getId(), userVO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

