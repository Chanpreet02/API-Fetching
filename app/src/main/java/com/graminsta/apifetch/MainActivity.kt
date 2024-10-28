package com.graminsta.apifetch

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.graminsta.apifetch.com.graminsta.apifetch.Adapter.VideoAdapter
import com.graminsta.apifetch.com.graminsta.apifetch.Comments
import com.graminsta.apifetch.com.graminsta.apifetch.Models.VideoItem
import com.graminsta.apifetch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
        private val Base_Url = "https://https://jsonplaceholder.typicode.com/" //Api link
    private val Tag:String = "Check_Response"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val videoPager = binding.viewPagerID
//        val videoList = listOf(
//            VideoItem("1", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"),
//            VideoItem("2", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"),
//            VideoItem("3", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"),
//            VideoItem("4", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"),
//            VideoItem("5", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"),
//            VideoItem("6", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"),
//            VideoItem("7", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"))
//        val videoAdapter = VideoAdapter()
//        videoAdapter.submitList(videoList)
//        binding.viewPagerID.adapter = videoAdapter
        getAllComments()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getAllComments() {
        val api =
            Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create())
                .build().create(MyApi::class.java)

        api.getComments().enqueue(object : Callback<List<Comments>> {
            override fun onResponse(
                call: Call<List<Comments>>,
                response: Response<List<Comments>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (comment in it) {
                            Log.w("Name", comment.email)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.w("fail", "${t.message}")
            }

        })
    }
}