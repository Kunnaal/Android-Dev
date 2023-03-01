package com.example.gettingstarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gettingstarted.adapters.CustomAdapter

class MainActivity : AppCompatActivity(), CustomAdapter.HomeProductsListener {
    lateinit var recyclerView : RecyclerView
    lateinit var cartQty : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // supportActionBar?.hide()
        // want action bar hidden on evey activity so hid it from theme xml

        val userIcon = findViewById<ImageButton>(R.id.personIcon)
        val cartIcon = findViewById<ImageButton>(R.id.btnCart)

        recyclerView = findViewById(R.id.gridRecyclerView)
        cartQty = findViewById(R.id.cart_number)

        setItems()

        cartIcon.setOnClickListener {
            val intent = Intent(this, cart::class.java)
            startActivityForResult(intent, 0)
        }

        userIcon.setOnClickListener {
            val intent = Intent(this, ProfileUser::class.java)
            startActivity(intent)
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("onActivityResult Code", resultCode.toString())
        var deletedItems = data?.getIntegerArrayListExtra("deletedItems")
        Log.d("deletedItems", deletedItems.toString())

        deletedItems?.forEach {
            recyclerView.adapter?.notifyItemChanged(it-1)
        }

        // Update cart quantity if returned
        updateCartQty()
    }

    fun setItems(){
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = CustomAdapter(Products.products, this)
    }

    override fun updateCartQty(){
        var net_qty = 0
        for (i in UserCart.itemPairArray) {
            net_qty = net_qty + i.second
        }
        cartQty.text = net_qty.toString()
    }
}