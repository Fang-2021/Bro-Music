package com.yergbro;

import com.yergbro.domain.*;
import com.yergbro.service.imp.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Ignore
class BroMusicApplicationTests {
@Autowired
    private ConsumerServiceImpl consumerService;

@Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private CollectServiceImpl collectService;

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private ListSongServiceImpl listSongService;

    @Autowired
    private RankServiceImpl rankService;

    @Autowired
    private SingerServiceImpl singerService;

    @Autowired
    private SongServiceImpl songService;

    @Autowired
    private SongListServiceImpl songListService;
    @Test
public void ConsumerTest(){
//    Consumer consumer = new Consumer();
//    consumer.setUsername("你的汉王");
//    consumer.setPassword("123456");
//    consumer.setSex(new Byte("1"));
//    consumer.setPhoneNum("11111111");
//    consumer.setEmail("1111111@qq.com");
//    consumer.setBirth(new Date());
//    consumer.setIntroduction("woshishei");
//    consumer.setLocation("hangzhou");
//    consumer.setAvatar("/img/user.jpg");
//    consumer.setCreateTime(new Date());
//    consumer.setUpdateTime(new Date());
   // consumerService.updateUserMsg(consumer);
//    List<Consumer> list=consumerService.allUser();
//    System.out.println("=====================");
//    for (int i = 0; i <list.size() ; i++) {
//        System.out.println(list.get(i));
//    }
//    System.out.println(    consumerService.getUserByUsername("yoona"));
//    System.out.println("=====================");
        List<Consumer> userByID = consumerService.getUserByID(59);
        System.out.println(userByID.get(0));
    }

    @Test
    public void AdminTest(){
        System.out.println("=====================");
        System.out.println(  adminService.veritypasswd("admin","123"));
        System.out.println("=====================");
}

    @Test
    public void CollectTest(){
        Collect collect = new Collect();
        collect.setId(1);
        collect.setCreateTime(new Date());
        collect.setSongId(2);
        collect.setSongListId(2);
        collect.setType(new Byte("0"));
        collect.setUserId(3);


        System.out.println("=====================");
//        System.out.println(  collectService.addCollection(collect));
        List<Collect> list=collectService.collectionOfUser(94);
        for (int i = 0; i <list.size() ; i++) {
        System.out.println(list.get(i));
        }
        System.out.println("=====================");
    }

    @Test
    public void CommentTest(){
        Comment comment=new Comment();
        comment.setContent("ohhhhhhhhhhhhh");
        comment.setCreateTime(new Date());
        comment.setId(2);
        comment.setSongId(2);
        comment.setSongListId(1);
        comment.setType(new Byte("1"));
        comment.setUp(1);
        comment.setUserId(1);

        System.out.println("=====================");
        List<Comment> list=commentService.commentOfSongListId(5);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
        System.out.println("=====================");
    }


    @Test
    public void ListSongTest(){
        ListSong listSong = new ListSong();
        listSong.setId(2);
        listSong.setSongId(3);
        listSong.setSongListId(1);

        System.out.println("=====================");
        List<ListSong> list=listSongService.listSongOfSongListId(22);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
//        System.out.println(listSongService.deleteListSongBySongID(3));
        System.out.println("=====================");
    }

    @Test
    public void RankTest(){
        Rank rank = new Rank();
        rank.setConsumerid(3L);
        rank.setScore(10);
        rank.setSonglistid(3L);

        System.out.println("=====================");
//        List<ListSong> list=listSongService.listSongOfSongId(22);
//        for (int i = 0; i <list.size() ; i++) {
//            System.out.println(list.get(i));
//        }
//        System.out.println(listSongService.deleteListSongBySongID(3));
//        System.out.println("歌单平均分"+  rankService.rankOfSongListId(2L));
        System.out.println(rankService.addRank(rank));
        System.out.println("=====================");
    }

    @Test
    public void SingerTest(){
//        Singer singer = new Singer();
//        singer.setBirth(new Date());
//        singer.setIntroduction("wo shi zcq");
//        singer.setLocation("hangzhou");
//        singer.setName("zcq");
//        singer.setPic("/img/tubiao.jpg");
//        singer.setSex(new Byte("1"));
//        singer.setId(44);
        System.out.println("=====================");
        List<Singer> list=singerService.singerOfId(1);
        System.out.println(list);
        System.out.println("=====================");
    }

    @Test
    public void SongTest(){
        Song song = new Song();
        song.setCreateTime(new Date());
        song.setIntroduction("123");
        song.setLyric("zcq");
        song.setName("zcq");
        song.setPic("/img/user.jpg");
        song.setSingerId(2);
        song.setUrl("123456");
        song.setUpdateTime(new Date());
        song.setId(114);
        System.out.println("=====================");
        System.out.println( songService.songOfSingerName("夜曲"));

        System.out.println("=====================");
    }

    @Test
    public void SongListTest(){
        SongList songList = new SongList();
        songList.setIntroduction("123");
        songList.setPic("/img/tubiao.jpg");
        songList.setStyle("12");
        songList.setTitle("123456");
        songList.setId(85);
        System.out.println("=====================");
        System.out.println( songListService.songListOfTitle("年轻之歌 有关爱与挑衅"));

        System.out.println("=====================");
    }

    @Test
    public void test() {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getProperty("user.dir")+System.getProperty("file.separator") +"avatarImages");
      }

}
