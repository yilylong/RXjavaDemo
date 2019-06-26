package com.zhl.rx.bean;

import com.zhl.rx.annotation.MyAnnotation;

import java.io.Serializable;

/**
 * 描述：
 * Created by zhaohl on 2018-1-5.
 */
@MyAnnotation(ID = 11,name = "Movie")
public class Movie implements Serializable {
    private String img;
    private int movieId;
    private String movieName;
    private String name;
    private String originName;
    private String url;
    private int videoId;
    private int wish;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getWish() {
        return wish;
    }

    public void setWish(int wish) {
        this.wish = wish;
    }
}
