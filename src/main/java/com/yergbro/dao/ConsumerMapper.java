package com.yergbro.dao;

import com.yergbro.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ConsumerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Consumer record);

    Consumer selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Consumer record);

    int addUser(Consumer consumer);

    int updateUserMsg(Consumer consumer);

    int updateUserAvatar(Consumer consumer);

    int existUser(String username);

    int veritypasswd(@Param("Username")String username, @Param("pwd")String password);

    int deleteUserByID(Integer id);

    List<Consumer> allUser();

    List<Consumer> getUserByID(Integer id);

    List<Consumer> getUserByUsername(String username);
}