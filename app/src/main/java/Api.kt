package com.graminsta.apifetch

import com.graminsta.apifetch.com.graminsta.apifetch.Comments
import retrofit2.Call
import retrofit2.http.GET

interface MyApi{

    @GET("comments")
    fun getComments(): Call<List<Comments>>

}