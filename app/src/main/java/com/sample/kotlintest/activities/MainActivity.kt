package com.sample.kotlintest.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.kotlintest.R
import com.sample.kotlintest.adapters.WeatherAdapter
import com.sample.kotlintest.networkcalls.RetrofitHelper
import com.sample.kotlintest.networkcalls.WeatherApi
import com.sample.kotlintest.responsmodels.WeatherResponse
import com.sample.kotlintest.viewmodels.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.recyclerview
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: WeatherViewModel
    private lateinit var data: List<WeatherResponse>
    private lateinit var result: Response<WeatherResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        //initialiseAdapter();
        val weatherApi = RetrofitHelper.getInstance().create(WeatherApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            result = weatherApi.getWeatherData()
            Log.d("result: ", result.body().toString())
        }

        button.setOnClickListener() {
            Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
            populateRecyclerView()
        }
    }

    private fun populateRecyclerView() {
        recyclerview.layoutManager = viewManager
        data = listOf(result.body()!!)
        //data.add(WeatherResponse(name = "first"));
        // This will pass the ArrayList to our Adapter
        val adapter = WeatherAdapter(data, this)
        recyclerview.adapter = adapter
    }

    private fun initialiseAdapter(){
        recyclerview.layoutManager = viewManager
        observeData()
    }

    private fun observeData() {
        viewModel.list.observe(this){
            recyclerview.adapter= WeatherAdapter(it, this)
        }
    }
}