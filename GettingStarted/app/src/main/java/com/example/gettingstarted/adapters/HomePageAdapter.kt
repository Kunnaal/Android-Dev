package com.example.gettingstarted.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.example.gettingstarted.*
import kotlin.math.min


class CustomAdapter(private val dataSet: ArrayList<Product>, private val context: Context): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    var homeProductsListener : HomeProductsListener?=null
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemNameView = view.findViewById<TextView>(R.id.itemName)
        var itemDescView = view.findViewById<TextView>(R.id.itemDesc)
        var itemCostView = view.findViewById<TextView>(R.id.itemCost)
        var addToCartButton = view.findViewById<TextView>(R.id.cartStatus)
        var itemThumbnail = view.findViewById<ImageView>(R.id.imgThumbnail)
        init {
            homeProductsListener = context as HomeProductsListener
        }
    }

    interface HomeProductsListener{
        fun updateCartQty()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_fragment, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemNameView.text = dataSet[position].itemName
        holder.itemDescView.text = dataSet[position].itemDesc?.substring(0, min(dataSet[position].itemDesc!!.length, 40))+"...";
        holder.itemCostView.text = "$ "+dataSet[position].itemCost.toString()
        holder.itemThumbnail.setImageResource(dataSet[position].itemThumbnail)
        holder.addToCartButton.text = "Add to cart"
        holder.itemView.setOnClickListener() {
            val intent = Intent(context, ItemDetails::class.java)
            intent.putExtra("product", dataSet[position])
            (context as MainActivity).startActivityForResult(intent, 0)
        }
        holder.addToCartButton.setOnClickListener {

            if (holder.addToCartButton.text == "Goto Cart") {

                var intent = Intent(context, cart::class.java)
                (context as MainActivity).startActivityForResult(intent, 0)

                for (item in UserCart.itemPairArray) {
                    if (item.first.itemId == dataSet[position].itemId) {
                        Log.d("Item exists in DS", "id: ${item.first.itemId}")
                    }
                }
            } else {
                holder.addToCartButton.text = "Goto Cart"
                UserCart.addItem(dataSet[position])
                Log.d(
                    "onClick add to cart:",
                    "Item added to cart:" + UserCart.itemPairArray.toString()
                )
                if (homeProductsListener != null) {
                    homeProductsListener!!.updateCartQty()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}