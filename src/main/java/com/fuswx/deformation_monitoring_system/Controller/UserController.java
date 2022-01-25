package com.fuswx.deformation_monitoring_system.Controller;

import com.fuswx.deformation_monitoring_system.Bean.User;
import com.fuswx.deformation_monitoring_system.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/account/getUserByUserName.do")
    @ResponseBody
    public User getUserByUserName(String userName){
        return userService.getUserByUserName(userName);
    }

    @GetMapping("/account/getAllUserByUserName.do")
    @ResponseBody
    public ArrayList<User> getAllUserByUserName(){
        return userService.getAllUserByUserName();
    }

    @GetMapping("/account/invokeUserByUserName.do")
    @ResponseBody
    public void invokeUserByUserName(String userName,String limits){
        userService.invokeUserByUserName(userName,limits);
    }

    @RequestMapping("/account/updateUser.do")
    @ResponseBody
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @PostMapping("/account/saveUser.do")
    @ResponseBody
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping("/account/deleteUserByName.do")
    @ResponseBody
    public void deleteUserByName(String userName){
        userService.deleteUserByName(userName);
    }

}
