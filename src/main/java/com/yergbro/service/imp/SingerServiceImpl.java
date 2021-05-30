package com.yergbro.service.imp;

import com.yergbro.dao.SingerMapper;
import com.yergbro.domain.Singer;
import com.yergbro.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    SingerMapper singerMapper;

    @Override
    public boolean addSinger(Singer singer) {
        return singerMapper.addSinger(singer)>0;
    }

    @Override
    public boolean updateSingerMsg(Singer singer) {
        return singerMapper.updateSingerMsg(singer)>0;
    }

    @Override
    public boolean updateSingerPic(Singer singer) {
        return singerMapper.updateSingerPic(singer)>0;
    }

    @Override
    public boolean deleteSingerByID(Integer id) {
        return singerMapper.deleteSingerByID(id)>0;
    }

    @Override
    public List<Singer> allSinger() {
        return singerMapper.allSinger();
    }

    @Override
    public List<Singer> singerOfName(String name)
    {
        return singerMapper.singerOfName(name);
    }

    @Override
    public List<Singer> singerOfSex(Integer sex) {
        return singerMapper.singerOfSex(sex);
    }

    @Override
    public List<Singer> singerOfId(Integer id) {
        return singerMapper.singerOfId(id);
    }
}
