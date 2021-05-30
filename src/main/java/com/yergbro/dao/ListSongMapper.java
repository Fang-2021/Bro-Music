package com.yergbro.dao;

import com.yergbro.domain.ListSong;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ListSongMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ListSong record);

    ListSong selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(ListSong record);

    int addListSong(ListSong listSong);

    int updateListSongMsg(ListSong listSong);

    int deleteListSongBySongID(Integer songId);

    List<ListSong> allListSong();

    List<ListSong> listSongOfSongListId(Integer songListId);
}