package cn.shesshan.myapp;

import android.graphics.Bitmap;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.shesshan.myapp.Adapter.TimelineAdapter;
import cn.shesshan.myapp.Entity.DateContent;
import cn.shesshan.myapp.Entity.Entry;
import cn.shesshan.myapp.Entity.Message;

public class DataHelper {
    private List<DateContent> dateList;
    private List<Entry> entryList;
    private List<Bitmap> bitTest;
    private List<Message> messages;
    private Map<String,String> publisherNameMatch;
    private static final String rootURL="https://shesshan.cn/img/";

    public DataHelper(){
        nameMatch();
        initData();
    }

    public void nameMatch(){
        publisherNameMatch=new HashMap<>();
        publisherNameMatch.put("经济信息工程学院","swufe_info");
        publisherNameMatch.put("经济数学学院","swufe_math");
        publisherNameMatch.put("青春西财","swufe_youth");
        publisherNameMatch.put("SKC韩语社","swufe_skc");
        publisherNameMatch.put("创新创业俱乐部","swufe_entrepre");
        publisherNameMatch.put("TEDxSWUFE","swufe_tedx");
        publisherNameMatch.put("SWUFE志行者","swufe_volunteer");
        publisherNameMatch.put("SWUFE思享","swufe_idea");
        publisherNameMatch.put("西财图书馆","swufe_lib");
        publisherNameMatch.put("西财教务处","swufe_teach");
        publisherNameMatch.put("经信学生职业发展中心","swufe_zhifa");
        publisherNameMatch.put("学生实验超市","swufe_market");
        publisherNameMatch.put("金融投资协会","swufe_fia");
    }

    public void initData(){
        List<Entry> entries;
        dateList=new ArrayList<>();
        entryList=new ArrayList<>();
        entries=new ArrayList<>();
        Entry entry;
        entry=new Entry(1,"经济信息工程学院",
                rootURL+publisherNameMatch.get("经济信息工程学院")+".jpg",
                "\"成都80\"高校金融产品设计与研发大赛校内赛报名",new Message(1,"9.25","无"));
        entryList.add(entry);
        entries.add(entry);
        dateList.add(new DateContent("9.25",entries));

        entries=new ArrayList<>();
        entry=new Entry(2,"经济信息工程学院",
                rootURL+publisherNameMatch.get("经济信息工程学院")+".jpg",
                "奇点工作室招新",new Message(2,"10.14 12:00前","无"));
        entryList.add(entry);
        entries.add(entry);
        dateList.add(new DateContent("10.14",entries));

        entries=new ArrayList<>();
        entry=new Entry(3,"经济数学学院",
                rootURL+publisherNameMatch.get("经济数学学院")+".jpg",
                "文化素质讲座：浅谈数学之用",new Message(3,"10.20 13:00","经世楼C301"));
        entryList.add(entry);
        entries.add(entry);
        entry=new Entry(4,"创新创业俱乐部",
                rootURL+publisherNameMatch.get("创新创业俱乐部")+".jpg",
                "2020光华创业大赛决赛",new Message(4,"10.20","弘远楼510"));
        entryList.add(entry);
        entries.add(entry);
        dateList.add(new DateContent("10.20",entries));

        entries=new ArrayList<>();
        entry=new Entry(5,"青春西财",
                rootURL+publisherNameMatch.get("青春西财")+".jpg",
                "第十九届英语节之英语演讲大赛报名",new Message(5,"10.27 23:00前","无"));
        entryList.add(entry);
        entries.add(entry);
        dateList.add(new DateContent("10.27",entries));

        entries=new ArrayList<>();
        entry=new Entry(6,"经济信息工程学院",
                rootURL+publisherNameMatch.get("经济信息工程学院")+".jpg",
                "西南财经大学第三届国际金融科技论坛SWUFE&CDAR",new Message(6,"10.30-10.31","希尔顿酒店"));
        entryList.add(entry);
        entries.add(entry);
        dateList.add(new DateContent("10.30",entries));

        entries=new ArrayList<>();
        entry=new Entry(7,"SWUFE志行者",
                rootURL+publisherNameMatch.get("SWUFE志行者")+".jpg",
                "2021西财暖冬通知",new Message(7,"11.3发布","无"));
        entryList.add(entry);
        entries.add(entry);
        dateList.add(new DateContent("11.3",entries));

        entries=new ArrayList<>();
        entry=new Entry(8,"经济数学学院",
                rootURL+publisherNameMatch.get("经济数学学院")+".jpg",
                "研究生学术节系列讲座：A Brief Guide to Machine Learning",new Message(8,"11.6","通博楼B412"));
        entryList.add(entry);
        entries.add(entry);
        dateList.add(new DateContent("11.6",entries));
        entries=new ArrayList<>();

        entry=new Entry(9,"青春西财",
                rootURL+publisherNameMatch.get("青春西财")+".jpg",
                "第十届\"舞动西财\"舞蹈大赛报名",new Message(9,"11.10 12:00前","无"));
        entryList.add(entry);
        entries.add(entry);
        dateList.add(new DateContent("11.10",entries));
    }

    public List<Entry> findAllInfo(String publisherName){
        List<Entry> entries=new ArrayList<>();
        System.out.println("findAllInfo: sizesize!! "+entryList.size());
        for(int i=0;i<entryList.size();i++) {
            if (entryList.get(i).getPublisher().equals(publisherName))
                entries.add(entryList.get(i));
        }
        return entries;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<DateContent> getDateList() {
        return dateList;
    }

    public void setDateList(List<DateContent> dateList) {
        this.dateList = dateList;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public List<Bitmap> getBitTest() {
        return bitTest;
    }

    public void setBitTest(List<Bitmap> bitTest) {
        this.bitTest = bitTest;
    }

    public Map<String, String> getPublisherNameMatch() {
        return publisherNameMatch;
    }

    public void setPublisherNameMatch(Map<String, String> publisherNameMatch) {
        this.publisherNameMatch = publisherNameMatch;
    }

    public static String getRootURL() {
        return rootURL;
    }
}
