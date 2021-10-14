package com.alkemy.ong.domain.users;

import com.alkemy.ong.domain.exceptions.DomainException;
import com.alkemy.ong.domain.mail.EmailService;
import com.alkemy.ong.domain.utils.Jwt;
import com.alkemy.ong.web.security.DefaultUserDetails;
import com.alkemy.ong.web.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private EmailService emailService;
    @Autowired
    JwtUtils jwtUtils;

    public DefaultUserService(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
    }

    public List<UserModel> getUsers() {
        return userRepository.getUsers();
    }

    public UserModel updateUser(UserModel userModel) {
        userRepository.getUserById(userModel.getIdUser())
                .orElseThrow(() -> new DomainException("User not found with id: " + userModel.getIdUser()));
        return userRepository.updateUser(userModel);
    }

    public Jwt registerUserAccount(UserModel userModel) {
        Optional<UserModel> user = userRepository.findUserByEmail(userModel.getEmail());
        Jwt jwt = new Jwt();
        if (user.isPresent()) {
            throw new DomainException("There is an account with that email address: " + userModel.getEmail());
        } else {
            userRepository.registerUserAccount(userModel);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));
            jwt.setToken(jwtUtils.generateJwtToken(authentication));
            emailService.sendWelcomeEmail(userModel.getEmail());
        }
        return jwt;
    }

    public void deleteUser(long idUser) {
        userRepository.getUserById(idUser)
                .orElseThrow(() -> new DomainException("User not found with id: " + idUser));
        userRepository.deleteUser(idUser);
    }

    public UserModel loginUser(UserModel userModel) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
        return setUser(userDetails);
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new DomainException("User not found with id: " + id));
    }

    public UserModel profile() {
        DefaultUserDetails userDetails =
                (DefaultUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return setUser(userDetails);
    }

    public UserModel setUser(DefaultUserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        RoleModel roleModel = roleRepository.findByName(roles.get(0));
        UserModel user = new UserModel();
        user.setIdUser(userDetails.getIdUser());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setIdRole(roleModel);
        return user;
    }
}