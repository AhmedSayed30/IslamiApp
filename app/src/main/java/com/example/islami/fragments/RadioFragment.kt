package com.example.islami.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.islami.ApiManger.ApiManger
import com.example.islami.ApiManger.Model.RadiosItem
import com.example.islami.ApiManger.Model.Response
import com.example.islami.R
import com.example.islami.databinding.FragmentRadioBinding
import com.example.islami.player.PlayerService
import retrofit2.Call
import retrofit2.Callback

class RadioFragment : Fragment() {
    private lateinit var binding : FragmentRadioBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRadioBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getChannelFromApi()
    }

    private fun getChannelFromApi() {
        ApiManger.getWebService().getRadioChannels().enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                val channel = response.body()?.radios
                controlarRadio(channel)
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(activity,t.localizedMessage,Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun controlarRadio(channel: List<RadiosItem?>?) {
        binding.radioName.setText(channel?.get(0)?.name ?: "")
        binding.icPlay.setOnClickListener(View.OnClickListener {
            channel?.get(0)?.let { it1 -> startRadioService(it1) }
        })

    }

    override fun onStart() {
        super.onStart()
        startSevice()
        bindService()
    }

    private fun bindService() {
        val intent = Intent(activity,PlayerService::class.java)
        activity?.bindService(intent,connection,Context.BIND_AUTO_CREATE)
    }

    private fun startSevice() {
        val intent = Intent(requireActivity(),PlayerService::class.java)
        activity?.startService(intent)
    }

    override fun onStop() {
        super.onStop()
        activity?.unbindService(connection)
    }
    fun startRadioService(item:RadiosItem){
        if (bound)
            service.startMediaPlayer(item.url!!,item.name!!)
    }

    fun stopRadioService(){
        if (bound)
            service.pauseMediaPlayer()
    }
    lateinit var service: PlayerService
    var bound = false
    private val connection = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, mbinder: IBinder?) {
            val binder = mbinder as PlayerService.MyBinder
            service = binder.getService()
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }
    }
}