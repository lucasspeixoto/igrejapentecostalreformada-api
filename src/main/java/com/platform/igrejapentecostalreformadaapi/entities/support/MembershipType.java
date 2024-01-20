package com.platform.igrejapentecostalreformadaapi.entities.support;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "membership_type")
public class MembershipType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "membership_type")
    private String membershipType;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name="updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;


    public MembershipType() {
    }

    public MembershipType(Long id, String membershipType) {
        this.id = id;
        this.membershipType = membershipType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
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
        if (!(o instanceof MembershipType that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

