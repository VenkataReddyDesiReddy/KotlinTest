package com.sample.kotlintest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.kotlintest.networkcalls.RetrofitHelper
import com.sample.kotlintest.networkcalls.WeatherApi
import com.sample.kotlintest.responsmodels.WeatherResponse
import com.sample.kotlintest.viewmodels.WeatherViewModel
import com.sample.kotlintest.viewmodels.WeatherViewModelFactory
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.recyclerview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: WeatherViewModel
    private lateinit var mainrecycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialiseAdapter();

        button.setOnClickListener() {
            Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()

            val weatherApi = RetrofitHelper.getInstance().create(WeatherApi::class.java)
            // launching a new coroutine
            GlobalScope.launch {
                val result = weatherApi.getWeatherData()
                Log.d("ayush: ", result.body().toString())
            }

            //val factory = WeatherViewModelFactory()
            viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

            mainrecycler.layoutManager = viewManager
            val data = ArrayList<WeatherResponse>()
            // This will pass the ArrayList to our Adapter
            val adapter = WeatherAdapter(viewModel, data, this)
            mainrecycler.adapter = adapter
        }
    }

    private fun initialiseAdapter(){
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData() {
        viewModel.list.observe(this){
            mainrecycler.adapter= WeatherAdapter(viewModel, it, this)
        }
    }
}