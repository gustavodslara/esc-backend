package com.github.gustavodslara.esc_service.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User  {

    @Id
    @GeneratedValue(strategy
            = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING) // Store the authority as a string
    @Column(name = "authority", nullable = false) // Use "authority" column name
    private UserAuthority authority; // Use an enum for authorities

    public UserAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(UserAuthority authority) {
        this.authority = authority;

    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;

    }
    public String getPassword() {
       return  this.password ;
    }
    public String getUsername() {
       return  this.username ;
    }

}