package com.alkemy.ong.web.security;

import com.alkemy.ong.domain.exceptions.DomainException;
import com.alkemy.ong.domain.users.RoleRepository;
import com.alkemy.ong.domain.users.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DefaultUserDetailsService(@Lazy UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return DefaultUserDetails.build(userRepository.findUserByEmail(email)
                                        .orElseThrow(() -> new DomainException("User not found with email: " + email)));
    }
}