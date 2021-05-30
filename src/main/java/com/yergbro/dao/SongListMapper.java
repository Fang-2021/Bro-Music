package com.yergbro.dao;

import com.yergbro.domain.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SongListMapper {


    int insert(SongList record);

    SongList selectByPrimaryKey(Integer id);


    int updateByPrimaryKeyWithBLOBs(SongList record);

    int updateByPrimaryKey(SongList record);

    int addSongList(SongList songList);

    int updateSongListMsg(SongList songList);

    int updateSongListImg(SongList songList);

    int deleteSongList(Integer id);

    List<SongList> allSongList();

    List<SongList> likeTitle(String title);

    List<SongList> likeStyle(String style);

    List<SongList> songListOfTitle(String title);
}