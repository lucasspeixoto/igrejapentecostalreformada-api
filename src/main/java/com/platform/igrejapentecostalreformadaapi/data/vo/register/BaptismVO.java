package com.platform.igrejapentecostalreformadaapi.data.vo.register;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({"id", "baptized", "baptism_date", "baptism_pastor", "created_at", "updated_at"})
public class BaptismVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Ser batizado ou não é obrigatório!")
    @Size(min = 2, max = 3, message = "Ser batizado dever ser respondido como Não ou Sim!")
    private String baptized;

    private final String baptismPastor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date baptismDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date updatedAt;

    @JsonIgnore
    private User user;

    public BaptismVO(Long id, String baptized, String baptismPastor, Date baptismDate, User user) {
        this.id = id;
        this.baptized = baptized;
        this.baptismPastor = baptismPastor;
        this.baptismDate = baptismDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaptized() {
        return baptized;
    }

    public void setBaptized(String baptized) {
        this.baptized = baptized;
    }

    public String getBaptismPastor() {
        return baptismPastor;
    }

    public Date getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(Date baptismDate) {
        this.baptismDate = baptismDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
