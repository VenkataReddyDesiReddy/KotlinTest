package com.sample.kotlintest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.kotlintest.networkcalls.RetrofitHelper
import com.sample.kotlintest.networkcalls.WeatherApi
import com.sample.kotlintest.responsmodels.WeatherResponse
import com.sample.kotlintest.ui.theme.KotlinTestTheme
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.recyclerview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener() {
            Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()

            val weatherApi = RetrofitHelper.getInstance().create(WeatherApi::class.java)
            // launching a new coroutine
            GlobalScope.launch {
                val result = weatherApi.getWeatherData()
                Log.d("ayush: ", result.body().toString())
            }

            recyclerview.layoutManager = LinearLayoutManager(this)
            val data = ArrayList<WeatherResponse>()
            // This will pass the ArrayList to our Adapter
            val adapter = WeatherAdapter(data)
            recyclerview.adapter = adapter
        }
    }
}