package com.example.farming.entity;

import java.util.Objects;

public class UserInfo {
    private Long id;

    private String uName;

    private String uPassword;

    private Byte identity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public Byte getIdentity() {
        return identity;
    }

    public void setIdentity(Byte identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo info = (UserInfo) o;
        return Objects.equals(uName, info.uName) &&
                Objects.equals(uPassword, info.uPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uName, uPassword);
    }
}