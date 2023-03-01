package com.example.retrofit_gettingstarted.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_gettingstarted.R
import com.example.retrofit_gettingstarted.models.User
import kotlin.math.min

class CustomAdapter(private val data: ArrayList<User>, private val context: Context): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameField = view.findViewById<TextView>(R.id.name_field)
        var emailField = view.findViewById<TextView>(R.id.email_field)
        var imageField = view.findViewById<ImageView>(R.id.image_view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycle_viewer_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameField.text = data[position].first_name+" "+data[position].last_name
        holder.emailField.text = data[position].email

        Glide.with(context)
            .load(data[position].avatar)
            .into(holder.imageField)
    }

}