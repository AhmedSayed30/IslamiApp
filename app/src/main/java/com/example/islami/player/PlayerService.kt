package com.example.islami.player

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log

class PlayerService:Service() {
    private val TAG = "PlayerService"
    var mp = MediaPlayer()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val urlToPlay = intent?.getStringExtra("url")
        val name = intent?.getStringExtra("name")
        val action = intent?.getStringExtra("action")
        if (urlToPlay!=null && name != null)
            startMediaPlayer(urlToPlay,name)
        if (action != null){
            if (action.equals("play")){
                playPauseMediaPlayer()
            }else if (action.equals("pause")){
                stopMediaPlayer()
            }
        }
        return START_NOT_STICKY
    }

    fun stopMediaPlayer() {
        if (mp.isPlaying){
            mp.stop()
            mp.reset()
        }
    }

    var name : String = ""
    private fun playPauseMediaPlayer() {
        if (mp.isPlaying){
            mp.pause()
        }else if(!mp.isPlaying){
            mp.start()
        }
        stopForeground(true)
        stopSelf()
    }

    public fun startMediaPlayer(urlToPlay: String, name: String) {
        pauseMediaPlayer()
        this.name=name
        Log.e(TAG,urlToPlay)
        try {
            mp.setDataSource(this, Uri.parse(urlToPlay))
            mp.prepareAsync()
            mp.setOnPreparedListener {
                it.start()
            }
        }catch (ex:Exception){
            Log.e(TAG,ex.message.toString())
        }


    }

     fun pauseMediaPlayer() {
        if (mp.isPlaying){
            mp.pause()
        }
    }

    val binder = MyBinder()
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
    inner class MyBinder: Binder(){
        public fun getService():PlayerService{
            return this@PlayerService
        }
    }
}