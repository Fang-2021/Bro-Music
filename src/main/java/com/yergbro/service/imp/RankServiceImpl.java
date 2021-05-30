package com.yergbro.service.imp;

import com.yergbro.dao.RankMapper;
import com.yergbro.domain.Rank;
import com.yergbro.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankMapper rankMapper;

    //计算排名需取平均分
    @Override
    public int rankOfSongListId(Long songListId) {
        return rankMapper.selectScoreSum(songListId) / rankMapper.selectRankNum(songListId);
    }

    @Override
    public boolean addRank(Rank rank) {
        return rankMapper.addRank(rank)>0;
    }

    @Override
    public List<Rank> haveRanked(Long consumerId, Long songListId){
        return rankMapper.haveRanked(consumerId,songListId);
    }
}
