package com.pocketshiksha.pocketshiksha.Students.homeScreen.homeFragment.chat.ext;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ais on 28/10/17.
 */

public interface ApiInterface {
    @GET("inbox.json")
    Call<List<Message>> getInbox();
}