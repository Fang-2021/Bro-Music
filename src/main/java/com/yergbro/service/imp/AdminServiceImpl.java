package com.yergbro.service.imp;

import com.yergbro.dao.AdminMapper;
import com.yergbro.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean veritypasswd(String name, String password) {
        return adminMapper.veritypasswd(name, password) > 0;
    }
}
