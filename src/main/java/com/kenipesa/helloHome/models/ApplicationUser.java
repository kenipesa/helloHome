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
  String firstName;
  String lastName;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "expense_id", referencedColumnName = "id")
  Expenses expense;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "buyer")
  Set<Searches> searches;
  
  public ApplicationUser() {}
  
  public ApplicationUser(String username, String password, String firstName, String lastName) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }
  
  public Long getId() {
    return this.id;
  }
  
  @Override
  public String getPassword() {
    return this.password;
  }
  
  @Override
  public String getUsername() {
    return this.username;
  }
  
  public String getFirstName() {
    return this.firstName;
  }
  
  public String getLastName() {
    return this.lastName;
  }
  
  public void setExpense(Expenses expense) {
    this.expense = expense;
  }
  
  public Expenses getExpense() {
    return this.expense;
  }
  
  public Set<Searches> getSearches() {
    return this.searches;
  }
  
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
