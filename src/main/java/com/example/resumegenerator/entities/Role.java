package com.example.resumegenerator.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "roles",schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id",nullable = false)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, length = Integer.MAX_VALUE)
    private ERole roleName;

    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName.name();
    }

    public void setRoleName(ERole roleName) {
        this.roleName = roleName;
    }

    public Role(){

    }
    public Role(ERole name){
        this.roleName = name;
    }
    public ERole getName(){
        return roleName;
    }

    //TODO [JPA Buddy] generate columns from DB
}