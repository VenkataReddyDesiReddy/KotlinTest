package com.sample.kotlintest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.kotlintest.responsmodels.WeatherResponse

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