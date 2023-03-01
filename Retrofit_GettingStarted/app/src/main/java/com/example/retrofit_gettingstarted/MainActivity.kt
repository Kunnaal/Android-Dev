package com.example.retrofit_gettingstarted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_gettingstarted.adapters.CustomAdapter
import com.example.retrofit_gettingstarted.api.ApiInterface
import com.example.retrofit_gettingstarted.api.RetrofitClient
import com.example.retrofit_gettingstarted.helper.AppHelper
import com.example.retrofit_gettingstarted.models.UserArray
import com.google.gson.Gson
import retrofit2.*

class MainActivity : AppCompatActivity() {

    var progressBar: ProgressBar? = null
    var apiInterace: ApiInterface? = null
    var retrofit: Retrofit? = null
    var textPlace: TextView? = null
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        var submitButton = findViewById<Button>(R.id.submit_btn)
        var textValue = findViewById<EditText>(R.id.textValue)

        submitButton.setOnClickListener {
            getList(textValue.text.toString().toInt())
        }

        recyclerView = findViewById(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun init() {

        retrofit = RetrofitClient.getInstance()
        apiInterace = retrofit!!.create(ApiInterface::class.java)
    }

    fun getList(pageNo : Int) {
        if(AppHelper.isConnected(this)) {
            progressBar?.visibility = View.VISIBLE

            apiInterace?.getUsersList(pageNo)?.enqueue(object : Callback<UserArray> {

                override fun onResponse(call: Call<UserArray>, response: Response<UserArray>) {

                    progressBar?.visibility = View.INVISIBLE

                    Log.d(this.toString(), Gson().toJson(response.body()))
                    textPlace?.text = Gson().toJson(response.body())
                    recyclerView.adapter = response.body()
                        ?.let { CustomAdapter(it.data, this@MainActivity) }
                }

                override fun onFailure(call: Call<UserArray>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        } else {
            Log.d(this.toString(), "Device not connected to the internet")
        }
    }
}