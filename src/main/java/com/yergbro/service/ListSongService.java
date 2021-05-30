package com.yergbro.service;

import com.yergbro.domain.ListSong;

import java.util.List;

public interface ListSongService {
    boolean addListSong(ListSong listSong);

    boolean updateListSongMsg(ListSong listSong);

    boolean deleteListSongBySongID(Integer songId);

    List<ListSong> allListSong();

    List<ListSong> listSongOfSongListId(Integer songListId);
}
