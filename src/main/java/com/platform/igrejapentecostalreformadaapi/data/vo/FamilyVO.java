package com.platform.igrejapentecostalreformadaapi.data.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "civil_status", "spouse_name", "wedding_date", "father_name", "mother_name", "created_at", "updated_at"})
public class FamilyVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "O estado civil é obrigatório!")
    private String civilStatus;

    @NotEmpty(message = "O nome do cônjuge é obrigatório!")
    @Size(min = 3, message = "O nome do cônjuge deve conter ao menos 3 caracteres!")
    private String spouseName;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date weddingDate;

    @NotEmpty(message = "O nome do pai é obrigatório!")
    @Size(min = 3, message = "O nome do pai deve conter ao menos 3 caracteres!")
    private String fatherName;

    @NotEmpty(message = "O nome da mãe é obrigatório!")
    @Size(min = 3, message = "O nome da mãe deve conter ao menos 3 caracteres!")
    private String motherName;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date updatedAt;

    @JsonIgnore
    private User user;

    public FamilyVO(Long id, String civilStatus, String spouseName, Date weddingDate, String fatherName, String motherName, User user) {
        this.id = id;
        this.civilStatus = civilStatus;
        this.spouseName = spouseName;
        this.weddingDate = weddingDate;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public Date getWeddingDate() {
        return weddingDate;
    }

    public void setWeddingDate(Date weddingDate) {
        this.weddingDate = weddingDate;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
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

    public Long getUserId() {
        return this.user.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyVO familyVO)) return false;
        return Objects.equals(getId(), familyVO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
