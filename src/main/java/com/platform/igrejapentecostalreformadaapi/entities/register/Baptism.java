package com.platform.igrejapentecostalreformadaapi.entities.register;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.igrejapentecostalreformadaapi.entities.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "baptism")
public class Baptism {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "baptized")
    private String baptized;

    @Column(name = "baptism_date")
    private Date baptismDate;

    @Column(name = "baptism_pastor")
    private String baptismPastor;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Baptism() {
    }

    public Baptism(Long id, String baptized, Date baptismDate, String baptismPastor, User user) {
        this.id = id;
        this.baptized = baptized;
        this.baptismDate = baptismDate;
        this.baptismPastor = baptismPastor;
        this.user = user;
    }

    public Baptism(String baptized, Date baptismDate, String baptismPastor, User user) {
        this.baptized = baptized;
        this.baptismDate = baptismDate;
        this.baptismPastor = baptismPastor;
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

    public Date getBaptismDate() {
        return baptismDate;
    }

    public void setBaptismDate(Date baptismDate) {
        this.baptismDate = baptismDate;
    }

    public String getBaptismPastor() {
        return baptismPastor;
    }

    public void setBaptismPastor(String baptismPastor) {
        this.baptismPastor = baptismPastor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Baptism baptism)) return false;
        return Objects.equals(getId(), baptism.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

