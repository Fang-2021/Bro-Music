package com.yergbro.dao;

import com.yergbro.domain.Comment;
import com.yergbro.domain.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RankMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Rank record);

    Rank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Rank record);

    int updateByPrimaryKey(Rank record);

    int selectScoreSum(@Param("song_ListId") Long songListId);

    int selectRankNum(@Param("song_ListId") Long songListId);

    int addRank(Rank rank);

    List<Rank> haveRanked(@Param("consumerId")Long consumerId, @Param("song_ListId") Long songListId);
}
