package com.zhl.rx.http;

import com.zhl.rx.bean.MovieList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 描述：
 * Created by zhaohl on 2018-1-8.
 */

public interface MovieWebService {

    @GET("mmdb/movie/lp/list.json?movieBundleVersion=192")
    Call<MovieList> listMovies();

    @GET("mmdb/movie/lp/list.json?movieBundleVersion=192")
    Observable<MovieList> getMoviesByRx();
}
