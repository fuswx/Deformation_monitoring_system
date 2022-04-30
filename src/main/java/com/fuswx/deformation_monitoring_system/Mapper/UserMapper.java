package com.fuswx.deformation_monitoring_system.Mapper;

import com.fuswx.deformation_monitoring_system.Bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

public interface UserMapper {

    @Select("select * from users where username=#{userName}")
    User getUserByUserName(String userName);

    @Update("update users set limits=#{limits} where username=#{userName}")
    void invokeUserByUserName(String userName, String limits);

    @Insert("insert into users (username,password,addtime,updatetime,limits) values(#{userName},#{password},#{addTime},#{updateTime},#{limits})")
    Integer saveUser(User user);

    @Update("delete from users where username=#{userName}")
    void deleteUserByName(String userName);

    @Update("update users set username=#{userName},password=#{password},updatetime=#{updateTime},limits=#{limits} where username=#{userName}")
    void updateUser(User user);

    @Select("select * from users")
    ArrayList<User> getAllUserByUserName();

    @Select("select id,username,addtime,updatetime,limits from users")
    ArrayList<User> getAllUser();

    @Update("update users set limits=#{limits} where id=#{id}")
    Integer invokeUserById(Integer id, String limits);
}
