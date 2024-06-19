package com.webmanagement.dev.webmanagement_dev.entities;

import java.util.Collection;
import java.util.List;

// import java.util.Collection;
// import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties(value = {"authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
public class User implements UserDetails {

  @Schema(title = "Identificador del usuario", nullable = false)
  @Id
  private Long id;

  //varchar 50 not null unique
  @Schema(description = "Nombre de usuario (unico)", maxLength = 50, minLength = 4, nullable = false)
  @Column("username")
  private String username;

  //varchar 100 not null
  @Schema(description = "Contraseña", maxLength = 100, minLength = 4, nullable = false)
  @Column("password")
  private String password;

  //varchar 30 not null default 'USER'
  @Schema(description = "Rol del usuario", nullable = true)
  @Column("role")
  private Role role;

  //boolean not null default true
  @Schema(description = "Habilitado", nullable = false, defaultValue = "true")
  @Column("enabled")
  private Boolean enabled;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (role == null) {
      return List.of();
    }
    return List.of(new SimpleGrantedAuthority(role.name()));
  }
  
}
