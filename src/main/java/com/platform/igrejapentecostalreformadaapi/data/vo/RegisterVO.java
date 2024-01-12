package com.platform.igrejapentecostalreformadaapi.data.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class RegisterVO {

    @NotEmpty(message = "O nome é obrigatório!")
    @Size(min = 3, message = "O nome precisa conter pelo menos 3 caracteres!")
    private String name;

    @NotEmpty(message = "O nome de usuário é obrigatório!")
    @Size(min = 3, message = "O nome de usuário precisa conter pelo menos 3 caracteres!")
    private String username;

    @NotEmpty(message = "O E-mail é obrigatório!")
    @Email(message = "E-mail incorreto!")
    private String email;

    @NotEmpty(message = "A senha é obrigatória!")
    @Size(min = 3, message = "A senha precisa conter no mínimo 3 caracteres!")
    private String password;

    public RegisterVO() {
    }

    public RegisterVO(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterVO that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUsername(), getEmail(), getPassword());
    }
}
