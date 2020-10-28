package cn.shesshan.myapp.Entity;

/**
 * 活动概要
 * logo:组织图标
 * publisher:活动发布者
 * content:活动内容(标题)
 */
public class Entry {
    private String logo;
    private String publisher;
    private String content;

    public Entry(String publisher,String content){
        this.publisher=publisher;
        this.content=content;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
