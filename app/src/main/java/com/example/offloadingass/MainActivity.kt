package com.example.offloadingass

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var playWhenReady = true
    var crrentWidow = 0
    var playBackPostion = 0.0

    var videoURL ="https://drive.google.com/file/d/1BLvKz_czqyw-XDLZb5odSlrhEVyhFOTr/view?usp=share_link"
    lateinit var player: SimpleExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun initVideo(){
        player = ExoPlayerFactory.newSimpleInstance(this)
        playerview.player =player
        var uri = Uri.parse(videoURL)
        var dataSource = DefaultDataSourceFactory(this,"exoplayer-code lab")
        var media = ProgressiveMediaSource.Factory(dataSource).createMediaSource(uri)
        player.playWhenReady=playWhenReady
        player.seekTo(crrentWidow,playBackPostion.toLong())
        player.prepare(media,false,false)
    }
    fun releaseVideo(){
        if(player != null){
            playWhenReady =player.playWhenReady
            playBackPostion = player.currentPosition.toInt().toDouble()
            crrentWidow =player.currentWindowIndex
            player.release()

        }
    }

    override fun onStart() {
        super.onStart()
        initVideo()
    }

    override fun onResume() {
        super.onResume()
        if (player!=null ){
            initVideo()
        }
    }

    override fun onPause() {
        super.onPause()
        releaseVideo()

    }

    override fun onStop() {
        super.onStop()
        releaseVideo()
    }
}