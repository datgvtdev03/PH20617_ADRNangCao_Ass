package com.example.ph20617_mob201_assignment.DTO;

public class News {
    private  String title,img,gioiThieu,ngay,link ;

    public News() {
    }
    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", gioiThieu='" + gioiThieu + '\'' +
                ", ngay='" + ngay + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public News(String title, String img, String gioiThieu, String ngay, String link) {
        this.title = title;
        this.img = img;
        this.gioiThieu = gioiThieu;
        this.ngay = ngay;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
