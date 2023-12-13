package com.platform.igrejapentecostalreformadaapi.data.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class UserDetailVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email;

    private String sex;

    private Date birthday;

    public UserDetailVO() {
    }

    public UserDetailVO(Long id, String name, String email, String sex, Date birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

