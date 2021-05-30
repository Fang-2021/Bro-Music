package com.yergbro.service;

import com.yergbro.domain.Singer;

import java.util.List;

public interface SingerService {

    boolean addSinger (Singer singer);

    boolean updateSingerMsg(Singer singer);

    boolean updateSingerPic(Singer singer);

    boolean deleteSingerByID(Integer id);

    List<Singer> allSinger();

    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Integer sex);

    List<Singer> singerOfId(Integer id);
}
