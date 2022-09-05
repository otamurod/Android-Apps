package com.otamurod.rxkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.otamurod.rxkotlin.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val TAG = "MainActivity"

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchTextObservable = createButtonObservable()
//        searchTextObservable.subscribe {
//            binding.tv.text = it.toString()
//            Log.d(TAG, "onCreate: $it")
//        }

        searchTextObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter {
                it.length >= 5
            }
//            used to wait
//            .debounce(
//                3L, TimeUnit.SECONDS
//            )
            .map {
                try {
                    (it.toInt() * 2).toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            .subscribe {
                binding.tv.text = it.toString()
            }
    }

    private fun createButtonObservable(): Observable<String> {
        return Observable.create { emitter ->

            binding.edit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    emitter.onNext(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
            binding.button.setOnClickListener {
                emitter.onNext(binding.edit.text.toString())
            }
            emitter.setCancellable(null)
        }
    }

}