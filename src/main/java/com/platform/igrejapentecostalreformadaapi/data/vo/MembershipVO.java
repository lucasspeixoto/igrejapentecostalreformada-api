package com.platform.igrejapentecostalreformadaapi.data.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "membership", "craft", "community", "interest", "created_at", "updated_at"})
public class MembershipVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "A membresia é obrigatória!")
    private String membership;

    @NotEmpty(message = "O ofício é obrigatório!")
    private String craft;

    @NotEmpty(message = "A última igreja que frequentou é obrigatório!")
    private String community;

    @NotEmpty(message = "O principal interesse é obrigatório!")
    private String interest;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date updatedAt;

    @JsonIgnore
    private User user;

    public MembershipVO(String membership, String craft, String community, String interest) {
        this.membership = membership;
        this.craft = craft;
        this.community = community;
        this.interest = interest;
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

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
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
        if (!(o instanceof MembershipVO contactVO)) return false;
        return Objects.equals(getId(), contactVO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
