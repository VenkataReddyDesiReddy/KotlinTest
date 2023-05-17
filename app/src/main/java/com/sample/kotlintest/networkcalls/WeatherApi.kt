package com.sample.kotlintest.networkcalls

import com.sample.kotlintest.responsmodels.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather?lat=17.4486&lon=78.3908&appid=29cfde0bb60c763d75fbb5501f4ed4c3")
    suspend fun getWeatherData() : Response<WeatherResponse>
}