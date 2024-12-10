package com.example.coursereview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "comments"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER; // Default role is USER

    private boolean verified = false;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Reply> replies;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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


