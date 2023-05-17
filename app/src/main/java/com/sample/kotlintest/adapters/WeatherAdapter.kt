package com.sample.kotlintest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.kotlintest.R
import com.sample.kotlintest.responsmodels.WeatherResponse
import com.sample.kotlintest.viewmodels.WeatherViewModel

public class WeatherAdapter (private val mList: List<WeatherResponse>, val context: Context)  : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherResponse = mList[position]
        //holder.imageView.setImageResource(weatherResponse.id)
        holder.nameTV.text = "Area: "+ weatherResponse.name
        holder.tempTV.text = "temp in F: "+ weatherResponse.main.temp.toString()
        holder.descTV.text = "Weather description: "+ weatherResponse.weather[0].description
        holder.speedTV.text = "Wind speed: " +weatherResponse.wind.speed.toString()
    }

    override fun getItemCount(): Int {
        return  mList.size;
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val tempTV: TextView = itemView.findViewById(R.id.tempTV)
        val descTV: TextView = itemView.findViewById(R.id.descTV)
        val speedTV: TextView = itemView.findViewById(R.id.speedTV)
    }

}