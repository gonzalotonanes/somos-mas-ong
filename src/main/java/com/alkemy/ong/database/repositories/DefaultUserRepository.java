package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.RoleEntity;
import com.alkemy.ong.database.entities.UserEntity;
import com.alkemy.ong.database.jparepositories.RoleJpaRepository;
import com.alkemy.ong.database.jparepositories.UserJpaRepository;
import com.alkemy.ong.domain.users.RoleModel;
import com.alkemy.ong.domain.users.UserModel;
import com.alkemy.ong.domain.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

@Repository
public class DefaultUserRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final PasswordEncoder encoder;

    public DefaultUserRepository(UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository, PasswordEncoder encoder) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.encoder = encoder;
    }

    public List<UserModel> getUsers() {
        return userJpaRepository.findAll().stream()
                .map(this::toModel)
                .collect(toList());
    }

    public void deleteUser(long idUser){
        Optional<UserEntity> user = userJpaRepository.findById(idUser);
        user.get().setDeleted(true);
        userJpaRepository.save(user.get());
    }

    public Optional<UserModel> findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(this::toModel);
    }

    public Optional<UserModel> getUserById(long idUser) {
        return userJpaRepository.findById(idUser)
                .map(this::toModel);
    }

    private UserModel toModel(UserEntity userEntity){
        UserModel userModel = new UserModel();
        userModel.setIdUser(userEntity.getIdUser());
        userModel.setDeleted(userEntity.isDeleted());
        userModel.setCreatedAt(userEntity.getCreatedAt());
        userModel.setEmail(userEntity.getEmail());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setIdRole(toRoleModel(userEntity.getRoleId()));
        userModel.setLastName(userEntity.getLastName());
        userModel.setPassword(userEntity.getPassword());
        userModel.setPhoto(userEntity.getPhoto());
        userModel.setUpdatedAt(userEntity.getUpdatedAt());
        return userModel;
    }

    public UserModel registerUserAccount (UserModel userModel){
        UserEntity userEntity = new UserEntity();
        RoleEntity role = roleJpaRepository.findById(1);
        userEntity.setFirstName(userModel.getFirstName());
        userEntity.setLastName(userModel.getLastName());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(encoder.encode(userModel.getPassword()));
        userEntity.setRoleId(role);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userJpaRepository.save(userEntity);
        return toModel(userEntity);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        return toModel(userJpaRepository.save(toEntity(userModel)));
    }

    private RoleModel toRoleModel(RoleEntity roleEntity){
        RoleModel roleModel = new RoleModel();
        roleModel.setIdRole(roleEntity.getIdRole());
        roleModel.setName(roleEntity.getName());
        roleModel.setDescription(roleEntity.getDescription());
        roleModel.setCreatedAt(roleEntity.getCreatedAt());
        roleModel.setUpdatedAt(roleEntity.getUpdatedAt());
        return roleModel;
    }

    private UserEntity toEntity(UserModel userModel){
        UserEntity userEntity  = userJpaRepository.findById(userModel.getIdUser()).get();
        userEntity.setEmail(userModel.getEmail());
        userEntity.setFirstName(userModel.getFirstName());
        userEntity.setLastName(userModel.getLastName());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setPhoto(userModel.getPhoto());
        userEntity.setUpdatedAt(userModel.getUpdatedAt());
        return userEntity;
    }
}