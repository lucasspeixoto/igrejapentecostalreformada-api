package com.platform.igrejapentecostalreformadaapi.data.vo;

import java.util.Objects;
import java.util.UUID;

public class UserVO {

    private UUID id;

    private String name;

    private String username;

    private String email;

    public UserVO() {
    }

    public UserVO(UUID id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

