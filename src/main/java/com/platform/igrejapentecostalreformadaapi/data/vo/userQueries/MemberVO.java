package com.platform.igrejapentecostalreformadaapi.data.vo.userQueries;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "name", "craft", "membership", "isRegisterFinished"})
public class MemberVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String craft;

    private String membership;

    private Boolean isRegisterFinished;

    public MemberVO(Long id, String name, String craft, String membership, Boolean isRegisterFinished) {
        this.id = id;
        this.name = name;
        this.craft = craft;
        this.membership = membership;
        this.isRegisterFinished = isRegisterFinished;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public Boolean getRegisterFinished() {
        return isRegisterFinished;
    }

    public void setRegisterFinished(Boolean registerFinished) {
        isRegisterFinished = registerFinished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberVO memberVO)) return false;
        return Objects.equals(getId(), memberVO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
