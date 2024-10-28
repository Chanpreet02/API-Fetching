package com.graminsta.apifetch.com.graminsta.apifetch.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.graminsta.apifetch.R
import com.graminsta.apifetch.com.graminsta.apifetch.Models.VideoItem

class VideoAdapter: ListAdapter<VideoItem, VideoAdapter.ViewHolder>(DiffCallback()) {
    class DiffCallback : DiffUtil.ItemCallback<VideoItem>(){
        override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem == newItem
        }

    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val videoView=itemView.findViewById<VideoView>(R.id.video)
        private val loadingPB = itemView.findViewById<ProgressBar>(R.id.progressBar)

        fun bindData(videoItem: VideoItem){
            videoView.setVideoURI(Uri.parse(videoItem.VideoURL))
            videoView.setOnPreparedListener { mp->
                loadingPB.visibility = View.GONE
                mp.start()
                val videoWidth = mp.videoWidth.toFloat()
                val videoHeight = mp.videoHeight.toFloat()
                val videoRatio = videoWidth/videoHeight
                val screenRatio = videoView.width.toFloat() / videoView.height.toFloat()

                if(videoRatio>screenRatio){
                    val scale = screenRatio / videoRatio
                    videoView.scaleX = 1f
                    videoView.scaleY = scale
                }
                else{
                    val scale =  videoRatio / screenRatio
                    videoView.scaleY = 1f
                    videoView.scaleX = scale
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.video_item_view,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoItem = getItem(position)
        holder.bindData(videoItem)
    }
}