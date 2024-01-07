package com.platform.igrejapentecostalreformadaapi.data.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class MemberVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String craft;

    private String membership;

    private String birthday;

    private Boolean isRegisterFinished;

    public MemberVO() {
    }

    public MemberVO(Long id, String name, String craft, String membership, String birthday, Boolean isRegisterFinished) {
        this.id = id;
        this.name = name;
        this.craft = craft;
        this.membership = membership;
        this.birthday = birthday;
        this.isRegisterFinished = isRegisterFinished;
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

    public String getCraft() {
        return this.craft;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getIsRegisterFinished() {
        return isRegisterFinished;
    }

    public void setIsRegisterFinished(Boolean isRegisterFinished) {
        this.isRegisterFinished = isRegisterFinished;
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
