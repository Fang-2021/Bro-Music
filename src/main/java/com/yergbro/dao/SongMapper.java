package com.yergbro.dao;

import com.yergbro.domain.Singer;
import com.yergbro.domain.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SongMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Song record);

    Song selectByPrimaryKey(Integer id);


    int updateByPrimaryKeyWithBLOBs(Song record);

    int updateByPrimaryKey(Song record);

    int addSong(Song song);

    int updateSongMsg(Song song);

    int updateSongUrl(Song song);

    int updateSongPic(Song song);

    int deleteSong(Integer id);

    List<Song> allSong();

    List<Song> songOfSingerId(Integer singerId);

    List<Song> songOfId(Integer id);

    List<Song> songOfSingerName(@Param("SingerName") String name);

    List<Song> songOfName(String name);
}