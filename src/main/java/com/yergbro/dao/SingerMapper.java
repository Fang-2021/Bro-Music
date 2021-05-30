package com.yergbro.dao;

import com.yergbro.domain.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SingerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Singer record);

    Singer selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Singer record);

    int addSinger(Singer singer);

    int updateSingerMsg(Singer singer);

    int updateSingerPic(Singer singer);

    int deleteSingerByID(Integer id);

    List<Singer> allSinger();

    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Integer sex);
//根据ID查询歌手
    List<Singer> singerOfId(Integer id);
}