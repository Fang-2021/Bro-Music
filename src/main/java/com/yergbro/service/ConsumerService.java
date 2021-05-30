package com.yergbro.service;

import com.yergbro.domain.Consumer;

import java.util.List;

public interface ConsumerService {

    boolean addUser(Consumer consumer);

    boolean updateUserMsg(Consumer consumer);

    boolean updateUserAvatar(Consumer consumer);

    boolean existUser(String username);

    boolean veritypasswd(String username, String password);

    boolean deleteUserByID(Integer id);

    List<Consumer> allUser();

    List<Consumer> getUserByID(Integer id);

    List<Consumer> getUserByUsername(String username);
}
