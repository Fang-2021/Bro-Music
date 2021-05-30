package com.yergbro.dao;

import com.yergbro.domain.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int addCollection(Collect collect);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    int updateCollectMsg(Collect collect);

    int deleteCollect(@Param("userId")Integer userId, @Param("songId")Integer songId);

    List<Collect> allCollect();

    List<Collect> collectionOfUser(Integer userId) ;
}
