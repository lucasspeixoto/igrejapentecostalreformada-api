package com.platform.igrejapentecostalreformadaapi.data.vo.register;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "birthday", "cellphone", "telephone", "sex"})
public class ContactVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "O aniversário é obrigatório!")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date birthday;

    @NotEmpty(message = "O celular é obrigatório!")
    @Size(min = 10, max = 11, message = "Digite um número válido de celular!")
    private String cellphone;

    @Size(min = 10, max = 11, message = "Digite um número válido de telefone!")
    private String telephone;

    @NotEmpty(message = "O sexo é obrigatório!")
    private String sex;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private Date updatedAt;

    @JsonIgnore
    private User user;

    public ContactVO(Date birthday, String cellphone, String telephone, String sex) {
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.telephone = telephone;
        this.sex = sex;
    }

    public ContactVO() {}

    public Long getUserId() {
        return this.user.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactVO contactVO)) return false;
        return Objects.equals(getId(), contactVO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
