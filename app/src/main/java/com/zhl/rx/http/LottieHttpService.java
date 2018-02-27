package com.zhl.rx.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 描述：
 * Created by zhaohl on 2018-1-24.
 */

public interface LottieHttpService {
    @GET("{id}")
    Observable<ResponseBody> getLottieJsonByUrl(@Path("id") String id);

    @GET("{id}")
    Call<ResponseBody> getLottieJsonByUrlCall(@Path("id") String id);
}
