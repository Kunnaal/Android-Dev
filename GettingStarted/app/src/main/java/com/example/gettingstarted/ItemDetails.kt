package com.example.gettingstarted

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class ItemDetails : AppCompatActivity() {

    companion object {
        lateinit var product : Product
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        val title = findViewById<TextView>(R.id.title)
        val fullImage = findViewById<ImageView>(R.id.full_image)
        val thumbnailImage = findViewById<ImageView>(R.id.thumbnail_image)
        val cost = findViewById<TextView>(R.id.cost)
        val description = findViewById<TextView>(R.id.description)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val addToCart = findViewById<Button>(R.id.add_to_cart)

        product = intent.extras?.getParcelable<Product>("product") as Product

        for (item in UserCart.itemPairArray) {
            if (item.first == product) {
                addToCart.text = "Goto Cart"
            }
        }

        if (addToCart.text == "Goto Cart") {
            addToCart.setOnClickListener() {
                val intent = Intent(this, cart::class.java)
                startActivityForResult(intent, 2)
            }
        } else {
            addToCart.setOnClickListener() {
                addToCart.text = "Goto Cart"
                addToCart.setOnClickListener() {
                    UserCart.itemPairArray.add(Pair(product, 1))
                    val intent = Intent(this, cart::class.java)
                    startActivityForResult(intent, 2)
                }
            }
        }

        title.text = product.itemName
        fullImage.setImageResource(product.itemThumbnail)
        thumbnailImage.setImageResource(product.itemThumbnail)
        cost.text = "$ "+product.itemCost.toString()
        description.text = product.itemDesc

        backButton.setOnClickListener() {
            onBackPressed()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("onActivityResult Code", resultCode.toString())
        Log.d("Product", product.toString())

        var deletedItems = data?.getIntegerArrayListExtra("deletedItems")

        for (item in deletedItems!!) {
            if (item == product.itemId) {
                findViewById<Button>(R.id.add_to_cart).text = "Add to Cart"
            }
        }

        if (findViewById<Button>(R.id.add_to_cart).text == "Add to Cart") {
            findViewById<Button>(R.id.add_to_cart).setOnClickListener {
                findViewById<Button>(R.id.add_to_cart).text = "Add to Cart"
                UserCart.itemPairArray.add(Pair(product, 1))
                val intent = Intent(this, cart::class.java)
                startActivityForResult(intent, 2)
            }
        }

        val intent = Intent()
        intent.putIntegerArrayListExtra("deletedItems", deletedItems)
        this.setResult(1, intent)
    }
}