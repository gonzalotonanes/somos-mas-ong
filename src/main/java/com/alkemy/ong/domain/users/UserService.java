package com.alkemy.ong.domain.users;

import com.alkemy.ong.domain.utils.Jwt;
import java.util.List;

public interface UserService {
    List<UserModel> getUsers();
    Jwt registerUserAccount(UserModel userModel);
    UserModel updateUser(UserModel userModel);
    void deleteUser(long idUser);
    UserModel loginUser(UserModel userModel);
    UserModel profile();
    UserModel getUserById(Long id);
}