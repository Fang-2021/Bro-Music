package com.yergbro.service;

import com.yergbro.domain.Rank;

import java.util.List;

public interface RankService {
    int rankOfSongListId(Long songListId);

    boolean addRank(Rank rank);

    List<Rank> haveRanked(Long consumerId, Long songListId);
}
