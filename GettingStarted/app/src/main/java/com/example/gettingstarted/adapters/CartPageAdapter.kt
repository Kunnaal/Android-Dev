package com.example.gettingstarted.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gettingstarted.*
import kotlin.math.min

class CartAdapter(private val dataSet: ArrayList<Pair<Product, Int>>, private val context: Context): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    val ddAdapter =  ArrayAdapter(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
        arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    )

    var tvTotal  : TextView?=null
    init {
        tvTotal = (context as Activity).findViewById(R.id.cart_total)
    }

    fun updateCartTotal() {
        var net_sum = 0.0
        for (i in UserCart.itemPairArray) {
            net_sum = net_sum + i.first.itemCost*i.second
        }
        Log.d("Net Sum in Adapter ", net_sum.toString())
        tvTotal?.text = "$ $net_sum"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemNameView = view.findViewById<TextView>(R.id.itemName)
        var itemDescView = view.findViewById<TextView>(R.id.itemDesc)
        var itemCostView = view.findViewById<TextView>(R.id.itemCost)
        var itemThumbnail = view.findViewById<ImageView>(R.id.imageView)
        var deleteButton = view.findViewById<TextView>(R.id.deleteButton)
        val spinner = view.findViewById<Spinner>(R.id.qtySpinner)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cart_item_fragment, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val position = position
        holder.itemNameView.text = dataSet[position].first.itemName
        holder.itemDescView.text = dataSet[position].first.itemDesc?.substring(0, min(dataSet[position].first.itemDesc!!.length, 20))+"...";
        holder.itemCostView.text = "$ ${dataSet[position].first.itemCost}"
        holder.itemThumbnail.setImageResource(dataSet[position].first.itemThumbnail)

        holder.deleteButton.setOnClickListener {
            cart.deletedItems.add(dataSet[position].first.itemId)
            UserCart.removeItem(dataSet[position].first)
            notifyItemRemoved(position)
            updateCartTotal()
            Log.d("onClick delete:", "Item removed from cart:"+ UserCart.itemPairArray.toString())
            Log.d("Change array ", cart.deletedItems.toString())
        }
        holder.spinner.adapter = ddAdapter
        holder.spinner.setSelection(dataSet[position].second-1)
        holder.spinner.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                pos: Int,
                id: Long
            ) {
                Log.d("Spinner value", parent?.getItemAtPosition(pos).toString())
                Log.d("Spinner position: ", position.toString())
                UserCart.changeQuantity(dataSet[position].first.itemId, parent?.getItemAtPosition(pos).toString().toInt())
                updateCartTotal()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}