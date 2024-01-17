package com.platform.igrejapentecostalreformadaapi.data.vo.register;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "cep", "state", "city", "district", "street", "number", "created_at", "updated_at"})
public class AddressVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "O cep é obrigatório!")
    @Pattern(regexp = "^[0-9]{8}$", message = "O cep precisa conter 8 dígitos numéricos!")
    private String cep;

    @NotEmpty(message = "O Estado é obrigatório!")
    @Pattern(regexp = "^([^0-9]{2})$", message = "O estado deve conter 2 caracteres!")
    private String state;

    @NotEmpty(message = "A Cidade é obrigatório!")
    private String city;

    @NotEmpty(message = "O Bairro é obrigatório!")
    private String district;

    @NotEmpty(message = "A Rua é obrigatório!")
    private String street;

    @NotNull(message = "O número da casa é obrigatório!")
    @Positive(message = "Insira um número válido!")
    private Integer number;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="America/Sao_Paulo")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone="America/Sao_Paulo")
    private Date updatedAt;

    @JsonIgnore
    private User user;

    public AddressVO() {
    }

    public AddressVO(String cep, String state, String city, String district, Integer number, String street) {
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.district = district;
        this.number = number;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressVO addressVO)) return false;
        return Objects.equals(getId(), addressVO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
