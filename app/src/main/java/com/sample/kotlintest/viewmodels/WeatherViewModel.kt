package com.sample.kotlintest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.kotlintest.networkcalls.RetrofitHelper
import com.sample.kotlintest.networkcalls.WeatherApi
import com.sample.kotlintest.responsmodels.WeatherResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

public class WeatherViewModel : ViewModel() {
    var list = MutableLiveData<List<WeatherResponse>>()
    private var newList = arrayListOf<WeatherResponse>()

    fun add(blog: WeatherResponse){
        newList.add(blog)
        list.value=newList
    }

    fun remove(blog: WeatherResponse){
        newList.remove(blog)
        list.value=newList
    }
}