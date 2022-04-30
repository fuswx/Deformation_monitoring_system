package com.fuswx.deformation_monitoring_system.Controller;

import com.fuswx.deformation_monitoring_system.Bean.User;
import com.fuswx.deformation_monitoring_system.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/account/getUserByUserName.do")
    @ResponseBody
    public User getUserByUserName(@RequestParam String userName){
        return userService.getUserByUserName(userName);
    }

    @GetMapping("/account/getUserByUserNameIsExist.do")
    @ResponseBody
    public Boolean getUserByUserNameIsExist(@RequestParam String userName){
        User user=userService.getUserByUserName(userName);
        return user!=null;
    }

    @GetMapping("/getAllUser.do")
    @ResponseBody
    public ModelAndView getAllUser(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                     @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("users",userService.getAllUser(pageNum,pageSize));
        modelAndView.setViewName("/pages/account/user");
        return modelAndView;
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

    @GetMapping("/account/invokeUserByUserId.do")
    @ResponseBody
    public ModelAndView invokeUserByUserId(@RequestParam(name = "id",defaultValue = "1")Integer id,
                                   @RequestParam(name = "limits",defaultValue = "1")String limits,
                                   @RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum,
                                   @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
        ModelAndView modelAndView=new ModelAndView();
        userService.invokeUserById(id,limits);
        modelAndView.addObject("users",userService.getAllUser(pageNum,pageSize));
        modelAndView.setViewName("/pages/account/user");
        return modelAndView;
    }

    @RequestMapping("/account/updateUser.do")
    @ResponseBody
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @PostMapping("/saveUser.do")
    @ResponseBody
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PostMapping("/saveCommonUser.do")
    public String saveCommonUser(@RequestParam String userName, @RequestParam String password){
        userService.saveCommonUser(userName,password);
        return "redirect:/toLogin";
    }

    @GetMapping("/account/deleteUserByName.do")
    @ResponseBody
    public void deleteUserByName(String userName){
        userService.deleteUserByName(userName);
    }

}
