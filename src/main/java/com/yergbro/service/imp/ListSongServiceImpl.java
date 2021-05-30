package com.yergbro.service.imp;

import com.yergbro.dao.ListSongMapper;
import com.yergbro.domain.ListSong;
import com.yergbro.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    ListSongMapper listSongMapper;

    @Override
    public boolean addListSong(ListSong listSong) {
        return listSongMapper.addListSong(listSong)>0;
    }

    @Override
    public boolean updateListSongMsg(ListSong listSong) {
        return listSongMapper.updateListSongMsg(listSong)>0;
    }

    @Override
    public boolean deleteListSongBySongID(Integer songId) {
        return listSongMapper.deleteListSongBySongID(songId)>0;
    }

    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }

    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        return listSongMapper.listSongOfSongListId(songListId);
    }
}
