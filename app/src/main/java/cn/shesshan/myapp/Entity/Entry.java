package cn.shesshan.myapp.Entity;

import android.graphics.Bitmap;

/**
 * 活动概要
 * logo:组织图标
 * publisher:活动发布者
 * content:活动内容(标题)
 */
public class Entry {
    private int id;
    private String logoURI;   // 组织logo网络地址
    private String publisher; // 组织名称
    private String content;   // 内容
    private Bitmap bitLogo;   //
    private Message message;
    private static final String rootURL="https://shesshan.cn/img/";

    public Entry(String publisher,String content,Bitmap bitLogo){
        this.publisher=publisher;
        this.content=content;
        this.bitLogo=bitLogo;
    }
    public Entry(String publisher,String s){
        this.publisher=publisher;
        this.logoURI=rootURL+s+".jpg";
    }
    public Entry(int id,String publisher,String logoURI,String content,Message message){
        this.id=id;
        this.publisher=publisher;
        this.logoURI=logoURI;
        this.content=content;
        this.message=message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entry(String publisher){
        this.publisher=publisher;
    }

    public String getLogoURI() {
        return logoURI;
    }

    public void setLogoURI(String logo) {
        this.logoURI = logo;
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

    public Bitmap getBitLogo() {
        return bitLogo;
    }

    public void setBitLogo(Bitmap bitLogo) {
        this.bitLogo = bitLogo;
    }
}
