package com.example.resumegenerator.services.implementations;

import com.example.resumegenerator.entities.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUid = 1L;

    private Integer id;
    private String email;
    @JsonIgnore
    private String password;

    private Collection<? extends  GrantedAuthority> authorities;

    public UserDetailsImpl(Integer id, String email, String password, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailsImpl build(AppUser user){
        List<GrantedAuthority> authorityList = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(user.getId(), user.getUserEmail(), user.getUserPassword(),authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String userEmail){
        this.email = userEmail;
    }

    public Integer getId(){
        return id;
    };
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
