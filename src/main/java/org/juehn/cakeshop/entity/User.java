package org.juehn.cakeshop.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

@JsonRootName("user")
public class User {

    private int id = -1;
    private String username;
    private String password;
    private String nick;
    private boolean locked;
    private String role;
    private String avatar;
    private String telephone;

    @JsonGetter
    public int getId() {
        return id;
    }

    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter
    public String getUsername() {
        return username;
    }

    @JsonSetter
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter
    public String getNick() {
        return nick;
    }

    @JsonSetter
    public void setNick(String nick) {
        this.nick = nick;
    }

    @JsonGetter
    public boolean isLocked() {
        return locked;
    }

    @JsonSetter
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @JsonGetter
    public String getRole() {
        return role;
    }

    @JsonSetter
    public void setRole(String role) {
        this.role = role;
    }

    @JsonGetter
    public String getAvatar() {
        return avatar;
    }

    @JsonSetter
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @JsonGetter
    public String getTelephone() {
        return telephone;
    }

    @JsonSetter
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nick='" + nick + '\'' +
                ", isLocked=" + locked +
                ", role='" + role + '\'' +
                '}';
    }
}
