package com.otamurod.nbu

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.otamurod.nbu.adapters.CurrencyAdapter
import com.otamurod.nbu.databinding.ActivityMainBinding
import com.otamurod.nbu.databinding.BottomSheetDialogBinding
import com.otamurod.nbu.models.Currency
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var requestQueue: RequestQueue
    private val url = "http://cbu.uz/uz/arkhiv-kursov-valyut/json/"
    lateinit var currencyAdapter: CurrencyAdapter

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)

        val jsonArrayRequest =
            JsonArrayRequest(
                Request.Method.GET, url, null, object : Response.Listener<JSONArray> {
                    override fun onResponse(response: JSONArray?) {

                        val type = object : TypeToken<List<Currency>>() {}.type
                        val list: List<Currency> = Gson().fromJson(response.toString(), type)
                        currencyAdapter =
                            CurrencyAdapter(list, object : CurrencyAdapter.OnItemClickListener {
                                override fun onItemClick(currency: Currency, position: Int) {
                                    showBottomSheetDialog(currency)
                                }

                            })
                        binding.rv.adapter = currencyAdapter

                        for (user in list) {
                            Log.d(TAG, "onResponse: ${response.toString()}")
                        }
                    }

                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {
                        Log.d(TAG, "onErrorResponse: ${error?.message}")
                    }

                })

        requestQueue.add(jsonArrayRequest)

    }

    private fun showBottomSheetDialog(currency: Currency) {

        val bottomSheetDialogBinding = BottomSheetDialogBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetDialogBinding.root)

        bottomSheetDialogBinding.Ccy.text = "Currency: ${currency.Ccy}"
        bottomSheetDialogBinding.CcyNmEN.text = "Eng: ${currency.CcyNm_EN}"
        bottomSheetDialogBinding.CcyNmRU.text = "RU: ${currency.CcyNm_RU}"
        bottomSheetDialogBinding.CcyNmUZ.text = "UZ: ${currency.CcyNm_UZ}"
        bottomSheetDialogBinding.CcyNmUZC.text = "УЗ: ${currency.CcyNm_UZC}"
        bottomSheetDialogBinding.Code.text = "Code: ${currency.Code}"
        bottomSheetDialogBinding.Date.text = "Date: ${currency.Date}"
        bottomSheetDialogBinding.Diff.text = "Diff: ${currency.Diff}"
        bottomSheetDialogBinding.Nominal.text = "Nominal: ${currency.Nominal}"
        bottomSheetDialogBinding.Rate.text = "Rate: ${currency.Rate}"
        bottomSheetDialogBinding.id.text = "ID: ${currency.id.toString()}"


        bottomSheetDialog.show()
    }
}