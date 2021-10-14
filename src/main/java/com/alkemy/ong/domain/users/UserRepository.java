package com.alkemy.ong.domain.users;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserModel> getUsers();
    UserModel registerUserAccount(UserModel userModel);
    UserModel updateUser(UserModel userModel);
    void deleteUser(long idUser);
    Optional<UserModel> findUserByEmail(String email);
    Optional<UserModel> getUserById(long idUser);
}