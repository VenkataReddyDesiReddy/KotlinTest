package com.sample.kotlintest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherViewModelFactory():ViewModelProvider.Factory {

//    fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(WeatherViewModel::class.java)){
//            return WeatherViewModel() as T
//        }
//        throw IllegalArgumentException ("UnknownViewModel")
//    }
}
