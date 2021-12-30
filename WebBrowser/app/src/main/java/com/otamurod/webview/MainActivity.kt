package com.otamurod.webview

import android.app.ProgressDialog
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val url = "https://wallpapercave.com/wp/wp7701518.jpg"

    lateinit var progressDialog: ProgressDialog
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                web_view.loadUrl("https://www.google.com/search?q=$query")
                img_view.visibility = SearchView.GONE

                search_view.visibility = SearchView.GONE
                Log.d(TAG, "onQueryTextSubmit: ")
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d(TAG, "onQueryTextChange: ")
                return true
            }

        })

        /*edit_view.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d(TAG, "beforeTextChanged: ")
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d(TAG, "onTextChanged: ")
            }

            override fun afterTextChanged(s: Editable?) {
                web_view.loadUrl("https://${s.toString()}")
                Log.d(TAG, "afterTextChanged: ")
            }
        })*/

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.loading))

        web_view.settings.javaScriptEnabled = true
        web_view.isVerticalScrollBarEnabled = false
        web_view.isHorizontalScrollBarEnabled = false

        web_view.webViewClient = object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress_circular.visibility = View.VISIBLE
//                progressDialog.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress_circular.visibility = View.INVISIBLE
//                progressDialog.hide()
            }
        }

//        web_view.loadUrl(url)
        web_view.loadData("<html><head><style type='text/css'>body{margin:auto auto;text-align:center;} img{width:100%25; height:100%25} </style></head><body><img src='$url'/></body></html>" ,"text/html",  "UTF-8")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (web_view.canGoBack()) {
                        web_view.goBack()

                        if (!web_view.canGoBack()){
                            img_view.visibility = SearchView.VISIBLE
                            search_view.visibility = SearchView.VISIBLE
                        }

                    } else {
                        finish()
                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}