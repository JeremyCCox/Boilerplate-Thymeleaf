package com.example.resumegenerator.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user", schema = "public")
public class AppUser {

    @Id
    @Column(name = "user_id", nullable = false)
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Integer id;

    @Column(name = "user_password", length = Integer.MAX_VALUE)
    private String userPassword;

    @Column(name = "user_email", length = Integer.MAX_VALUE)
    private String userEmail;

    @Column(name = "user_password_salt", length = Integer.MAX_VALUE)
    private String userPasswordSalt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public AppUser(){

    }
    public AppUser(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
    public AppUser(Integer id, String userEmail, String userPassword) {
        this.id=id;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }


    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPasswordSalt() {
        return userPasswordSalt;
    }

    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", roles=" + roles +
                '}';
    }
}