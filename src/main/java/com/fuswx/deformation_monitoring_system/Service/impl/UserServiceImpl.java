package com.fuswx.deformation_monitoring_system.Service.impl;

import com.fuswx.deformation_monitoring_system.Bean.User;
import com.fuswx.deformation_monitoring_system.Mapper.UserMapper;
import com.fuswx.deformation_monitoring_system.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public void invokeUserByUserName(String userName,String limits) {
        userMapper.invokeUserByUserName(userName,limits);
    }

    @Override
    public void saveUser(User user) {
        user.setAddTime(new Date());
        userMapper.saveUser(user);
    }

    @Override
    public void deleteUserByName(String userName) {
        userMapper.deleteUserByName(userName);
    }

    @Override
    public void updateUser(User user) {
        user.setUpdateTime(new Date());
        userMapper.updateUser(user);
    }

    @Override
    public ArrayList<User> getAllUserByUserName() {
        return userMapper.getAllUserByUserName();
    }
}
