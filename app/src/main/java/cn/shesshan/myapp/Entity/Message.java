package cn.shesshan.myapp.Entity;

/**
 * 活动详请
 */
public class Message {
    private int id;
    private String time;
    private String place;
    private String details;

    public Message(int id,String time,String place){
        this.id=id;
        this.time=time;
        this.place=place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
