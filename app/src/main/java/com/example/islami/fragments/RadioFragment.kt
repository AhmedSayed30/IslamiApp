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
    private lateinit var binding: FragmentRadioBinding
    private lateinit var items: List<RadiosItem>
    var position = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getChannelsFromApi()
        binding.icControler.setOnClickListener { startRadio(position) }
        binding.icNext.setOnClickListener {
            getNextChannal()
        }
        binding.icPrevious.setOnClickListener {
            getPreviousChannal()
        }
    }

    private fun getPreviousChannal() {
        if (position <= 0) position = items.size - 1
        else position--
        startRadioService(items[position])
    }

    private fun getNextChannal() {
        if (position == items.size - 1) position = 0
        else position++

        startRadioService(items[position])
    }

    private fun getChannelsFromApi() {
        ApiManger.getWebService().getRadioChannels().enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                val channels = response.body()?.radios
                if (!channels.isNullOrEmpty()) {
                    items = channels as List<RadiosItem>
                }

            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun startRadio(position: Int) {
        if (bound) {
            startRadioService(items[position])
        } else {
            stopRadioService()
        }
        bound = !bound
    }

    override fun onStart() {
        super.onStart()
        startSevice()
        bindService()
    }

    private fun bindService() {
        val intent = Intent(activity, PlayerService::class.java)
        activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun startSevice() {
        val intent = Intent(requireActivity(), PlayerService::class.java)
        activity?.startService(intent)
    }

    override fun onStop() {
        super.onStop()
        activity?.unbindService(connection)
    }

    fun startRadioService(item: RadiosItem) {
        service.startMediaPlayer(item.url!!, item.name!!)
        binding.radioName.setText(item.name)
        binding.icControler.setImageResource(R.drawable.ic_pause)
    }

    fun stopRadioService() {
        service.stopMediaPlayer()
        binding.icControler.setImageResource(R.drawable.ic_play)

    }

    lateinit var service: PlayerService
    var bound = false
    private val connection = object : ServiceConnection {
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