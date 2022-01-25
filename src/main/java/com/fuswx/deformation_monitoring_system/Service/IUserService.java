package com.fuswx.deformation_monitoring_system.Service;

import com.fuswx.deformation_monitoring_system.Bean.User;

import java.util.ArrayList;

public interface IUserService {
    User getUserByUserName(String userName);

    void invokeUserByUserName(String userName,String limits);

    void saveUser(User user);

    void deleteUserByName(String userName);

    void updateUser(User user);

    ArrayList<User> getAllUserByUserName();
}
