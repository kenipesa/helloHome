package com.kenipesa.helloHome.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  
  @Column(unique = true)
  String username;
  String password;

  @OneToMany
  Set<Expenses> expenses;

  @OneToMany
  Set<Searches> searches;
  public ApplicationUser() {}
  
  public ApplicationUser(String username, String password) {
    this.username = username;
    this.password = password;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }
  
  public Long getId() {
    return this.id;
  }
  
  @Override
  public String getPassword () {
    return this.password;
  }
  
  @Override
  public String getUsername () {
    return this.username;
  }
  
  @Override
  public boolean isAccountNonExpired () {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked () {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired () {
    return true;
  }
  
  @Override
  public boolean isEnabled () {
    return true;
  }
}
