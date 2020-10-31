package cn.shesshan.myapp.Entity;

import java.util.List;

public class DateContent {
    private String date;
    private List<Entry> entryList;

    public DateContent(String date,List<Entry> entryList){
        this.date=date;
        this.entryList=entryList;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
