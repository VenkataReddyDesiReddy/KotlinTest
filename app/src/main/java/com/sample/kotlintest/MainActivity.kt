package com.sample.kotlintest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sample.kotlintest.networkcalls.RetrofitHelper
import com.sample.kotlintest.networkcalls.WeatherApi
import com.sample.kotlintest.ui.theme.KotlinTestTheme
import kotlinx.android.synthetic.main.activity_main.button
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
                if (result != null)
                // Checking the results
                    Log.d("ayush: ", result.body().toString())
            }
        }
    }
}