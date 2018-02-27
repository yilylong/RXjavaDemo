package com.zhl.rx.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zhl.rx.R;
import com.zhl.rx.bean.Movie;
import com.zhl.rx.bean.MovieList;
import com.zhl.rx.constans.Constant;
import com.zhl.rx.http.MovieWebService;
import com.zhl.rx.utils.MeasureUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private MovieAdapter adapter;
    private ArrayList<Movie> Data = new ArrayList<Movie>();
    private RequestOptions options = new RequestOptions()
            .placeholder(R.mipmap.movie_banner_default)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transform(new RoundedCornersTransformation(10,0, RoundedCornersTransformation.CornerType.TOP))
            ;
    private ConstraintLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels- MeasureUtil.dp2px(this,20);
        int height = (width*9)/16;
        params = new ConstraintLayout.LayoutParams(width,height);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new MovieAdapter(this,R.layout.item_movie_list,Data));
        initData();
    }

    private void initData() {
        getMovieListFromServer();
    }

    public void getMovieListFromServer() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MovieWebService service = retrofit.create(MovieWebService.class);
        // 1、Retrofit原生
//        Call<MovieList> call = service.listMovies();
//        call.enqueue(new Callback<MovieList>() {
//            @Override
//            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
//                Data.clear();
//                Data.addAll(response.body().getData());
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<MovieList> call, Throwable t) {
//                Log.e("mytag",t.getMessage());
//            }
//        });

        // 2、Retrofit 与 Rxjava 结合
        service.getMoviesByRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieList>() {
            @Override
            public void accept(MovieList movieList) {
                Data.clear();
                Data.addAll(movieList.getData());
                adapter.notifyDataSetChanged();
            }
        });
    }


    private class MovieAdapter extends CommonAdapter<Movie> {

        public MovieAdapter(Context context, int layoutId, List<Movie> datas) {
            super(context, layoutId, datas);
        }
        @Override
        protected void convert(ViewHolder viewHolder, Movie movie, int position) {
            ImageView movieBanner = viewHolder.getView(R.id.item_movie_img);
            movieBanner.setLayoutParams(params);
            viewHolder.setText(R.id.item_movie_moviename,movie.getMovieName());
            viewHolder.setText(R.id.item_movie_title,movie.getName());
            viewHolder.setText(R.id.item_movie_wish,movie.getWish()+"");
            Glide.with(MovieActivity.this)
                    .load(movie.getImg())
                    .apply(options)
                    .into(movieBanner);
        }
    }


}
