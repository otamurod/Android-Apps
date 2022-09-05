package com.otamurod.intentservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.intentservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var myServiceExample: MyServiceExample? = null
    private var serviceIntent: Intent? = null
    private var isServiceBound: Boolean? = null
    private var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serviceIntent = Intent(applicationContext, MyServiceExample::class.java)

        binding.startServiceBtn.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
                startService(it)
                binding.serviceInfo.text = "Service is running..."
            }

            Intent(this, MyService::class.java).also {
                startService(it)
                binding.serviceInfo.text = "Service is running..."
            }
            Intent(this, MyServiceExample::class.java).also {
                startService(it)
                binding.serviceInfo.text = "Service is running..."
            }
        }

        binding.stopServiceBtn.setOnClickListener {
            MyIntentService.stopService()
            binding.serviceInfo.text = "Service is stopped"

            Intent(this, MyService::class.java).also {
                stopService(it)
                binding.serviceInfo.text = "Service is stopped"
            }
            Intent(this, MyServiceExample::class.java).also {
                stopService(it)
                binding.serviceInfo.text = "Service is stopped"
            }
        }

        binding.sendDataBtn.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val dataString = binding.editText.text.toString()
                it.putExtra("EXTRA_DATA", dataString)

                startService(it)
                binding.serviceInfo.text = "Send Data: $dataString"
                binding.editText.setText("")
            }
        }

        binding.bindBtn.setOnClickListener {
            if (serviceConnection == null) {
                serviceConnection = object : ServiceConnection {
                    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                        val myServiceBinder = service as MyServiceExample.MyServiceBinder
                        myServiceExample = myServiceBinder.service
                        isServiceBound = true
                    }

                    override fun onServiceDisconnected(name: ComponentName?) {
                        isServiceBound = false
                    }
                }

                bindService(serviceIntent, serviceConnection!!, Context.BIND_AUTO_CREATE)
                binding.serviceInfo.text = "Binded"
            }
        }

        binding.unbindBtn.setOnClickListener {
            if (isServiceBound!!) {
                unbindService(serviceConnection!!)
                isServiceBound = false
                binding.serviceInfo.text = "Unbinded"
            }
        }

        binding.getRandomNumberBtn.setOnClickListener {
            setRandomNumber()
        }
    }

    private fun setRandomNumber() {
        if (isServiceBound == true) {
            binding.serviceInfo.text = "Random number: ${myServiceExample?.getRandomNumber()}"
        } else {
            binding.serviceInfo.text = "Service not bound"
        }
    }
}