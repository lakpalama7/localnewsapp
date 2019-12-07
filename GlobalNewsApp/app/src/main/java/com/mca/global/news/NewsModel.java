package com.mca.global.news;

public class NewsModel {
    private String title;
    private String imageUrl;
    private String publishdate;
    private String summary;
    private String weblink;


    public  NewsModel(){

    }
   /* public NewsModel(String title, String imageUrl, String publishdate, String summary,String weblink) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.publishdate = publishdate;
        this.summary = summary;
        this.weblink=weblink;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

}
