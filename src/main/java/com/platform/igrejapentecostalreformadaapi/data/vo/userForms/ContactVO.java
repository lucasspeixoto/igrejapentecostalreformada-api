package com.platform.igrejapentecostalreformadaapi.data.vo.userForms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.platform.igrejapentecostalreformadaapi.entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "birthday", "cellphone", "telephone", "sex",  "created_at", "cellphone"})
public class ContactVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date birthday;

    private String cellphone;

    private String telephone;

    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="America/Sao_Paulo")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="America/Sao_Paulo")
    private Date updatedAt;

    @JsonIgnore
    private User user;

    public ContactVO(Date birthday, String cellphone, String telephone, String sex) {
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.telephone = telephone;
        this.sex = sex;
    }

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
