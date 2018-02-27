package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhl.rx.R;
import com.zhl.rx.bean.Movie;

import java.util.ArrayList;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RxJava2XActivity extends AppCompatActivity {
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2_x);
        result = (TextView) findViewById(R.id.textView);
        Button doA = (Button) findViewById(R.id.doA);
        doA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doA();
            }
        });
        Button doB = (Button) findViewById(R.id.doB);
        doB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doB();
            }
        });
        Button doC = (Button) findViewById(R.id.doC);
        doC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doC();
            }
        });
        Button doD = (Button) findViewById(R.id.doD);
        doD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doD();
            }
        });
        Button doE = (Button) findViewById(R.id.doE);
        doE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doE();
            }
        });
    }




    private void doA() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("subscribe Observer");
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String string) {
                result.setText("使用Observer接收事件==" + string);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void doB() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(20);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
           .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        result.setText("接收事件==" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Toast.makeText(RxJava2XActivity.this, "事件接收完成", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void doC() {
        ArrayList<Movie> movies = new ArrayList<>();
        for(int i=0;i<3;i++){
            Movie movie = new Movie();
            movie.setName("《无问西东》"+i);
            movies.add(movie);
        }
//        Flowable.fromArray(movies)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ArrayList<Movie>>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//
//            }
//
//            @Override
//            public void onNext(ArrayList<Movie> movies) {
//                StringBuilder builder = new StringBuilder();
//                if(movies!=null){
//                    for(Movie movie:movies){
//                        builder.append(movie.getName()+"-");
//                    }
//                }
//                result.setText("电影列表==" + builder.toString());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                t.getMessage();
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        Flowable.fromArray(movies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Movie>>() {
                    @Override
                    public void accept(ArrayList<Movie> movies) throws Exception {
                        StringBuilder builder = new StringBuilder();
                        if (movies != null) {
                            for (Movie movie : movies) {
                                builder.append(movie.getName() + "-");
                            }
                        }
                        result.setText("电影列表==" + builder.toString());
                    }
                });

    }

    private void doD() {
        ArrayList<Movie> movies = new ArrayList<>();
        for(int i=0;i<3;i++){
            Movie movie = new Movie();
            movie.setName("《无问西东》"+i);
            movies.add(movie);
        }
        Flowable.just(movies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Movie>>() {
                    @Override
                    public void accept(ArrayList<Movie> movies) throws Exception {
                        StringBuilder builder = new StringBuilder();
                        if (movies != null) {
                            for (Movie movie : movies) {
                                builder.append(movie.getName() + "-");
                            }
                        }
                        result.setText("JUST电影列表==" + builder.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    private void doE() {
        ArrayList<Movie> movies = new ArrayList<>();
        for(int i=0;i<3;i++){
            Movie movie = new Movie();
            movie.setName("《无问西东》"+i);
            movies.add(movie);
        }
        Flowable.fromIterable(movies).subscribe(new Consumer<Movie>() {
            @Override
            public void accept(Movie movie) throws Exception {
                result.setText("fromIterable==" + movie.getName());
            }
        });



    }

}
