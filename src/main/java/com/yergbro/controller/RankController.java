package com.yergbro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yergbro.domain.Rank;
import com.yergbro.service.imp.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Controller
@RequestMapping("/client")
public class RankController {
    @Autowired
    private RankServiceImpl rankService;

    //提交评分
    @ResponseBody
    @RequestMapping(value = "/rank/add",method = RequestMethod.POST)
    public Object addRank(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String consumerId = req.getParameter("consumerId");
        String songListId = req.getParameter("songListId");
        String score = req.getParameter("score");
        List<Rank> ranks = rankService.haveRanked(Long.parseLong(consumerId), Long.parseLong(songListId));
        if(ranks.size()>0){
            jsonObject.put("code",2);
            jsonObject.put("msg","已评价");
            jsonObject.put("score",ranks.get(0).getScore());
            return jsonObject;
        }else{
            Rank rank = new Rank();
            rank.setConsumerid(Long.parseLong(consumerId));
            rank.setSonglistid(Long.parseLong(songListId));
            rank.setScore(Integer.parseInt(score));

            boolean res = rankService.addRank(rank);
            if(res){
                jsonObject.put("code",1);
                jsonObject.put("msg","评价成功");
                return jsonObject;
            }else {
                jsonObject.put("code",0);
                jsonObject.put("msg","评价失败");
                return jsonObject;
            }
        }

    }


    //获取指定歌单的评分
    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public Object rankOfSongListId(HttpServletRequest req){
        String songListId = req.getParameter("songListId");
        return rankService.rankOfSongListId(Long.parseLong(songListId));
    }
}
