package com.alkemy.ong.web.security;

import com.alkemy.ong.domain.users.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DefaultUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public DefaultUserDetails(Long idUser, String firstName, String lastName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static DefaultUserDetails build(UserModel user) {
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getIdRole().getName()));
        return new DefaultUserDetails(
                    user.getIdUser(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getIdUser() {
        return idUser;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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