package com.fuswx.deformation_monitoring_system.Service;

import com.fuswx.deformation_monitoring_system.Bean.User;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;

public interface IUserService {
    User getUserByUserName(String userName);

    void invokeUserByUserName(String userName,String limits);

    void saveUser(User user);

    void deleteUserByName(String userName);

    void updateUser(User user);

    ArrayList<User> getAllUserByUserName();

    Boolean saveCommonUser(String userName,String password);

    PageInfo<User> getAllUser(Integer pageNum, Integer pageSize);

    Boolean invokeUserById(Integer id, String limits);
}
