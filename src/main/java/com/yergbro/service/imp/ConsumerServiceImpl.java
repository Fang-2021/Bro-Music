package com.yergbro.service.imp;

import com.yergbro.dao.ConsumerMapper;
import com.yergbro.domain.Consumer;
import com.yergbro.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl  implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;
    @Override
    public boolean addUser(Consumer consumer) {
        return consumerMapper.addUser(consumer) >0 ?true:false;
    }

    @Override
    public boolean updateUserMsg(Consumer consumer) {
        return consumerMapper.updateUserMsg(consumer) > 0;
    }

    @Override
    public boolean updateUserAvatar(Consumer consumer) {
        return consumerMapper.updateUserAvatar(consumer) > 0;
    }

    @Override
    public boolean existUser(String username) {
        return consumerMapper.existUser(username) > 0;
    }

    @Override
    public boolean veritypasswd(String username, String password) {
        return consumerMapper.veritypasswd(username, password) > 0;
    }

    @Override
    public boolean deleteUserByID(Integer id) {
        return consumerMapper.deleteUserByID(id) > 0;
    }

    @Override
    public List<Consumer> allUser() {
        return consumerMapper.allUser();
    }

    @Override
    public List<Consumer> getUserByID(Integer id) {
        return consumerMapper.getUserByID(id);
    }

    @Override
    public List<Consumer> getUserByUsername(String username) {
        return consumerMapper.getUserByUsername(username);
    }

}
