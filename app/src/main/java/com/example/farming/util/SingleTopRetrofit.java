/*史长顺*/
package com.example.farming.util;

import android.provider.SyncStateContract;

import com.example.farming.constants.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleTopRetrofit {

    private static volatile Retrofit retrofit;

    private SingleTopRetrofit(){}

    public static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (SingleTopRetrofit.class) {
                if (retrofit == null) {

                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }


}
