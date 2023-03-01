package com.example.gettingstarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gettingstarted.adapters.CartAdapter

class cart : AppCompatActivity() {
    companion object {
        var deletedItems = ArrayList<Int>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val recyclerView = findViewById<RecyclerView>(R.id.cartRecyclerView)
        val backButton = findViewById<ImageButton>(R.id.backButton)

        backButton.setOnClickListener() {
            onBackPressed()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = CartAdapter(UserCart.itemPairArray, this)

        val cart_total = findViewById<TextView>(R.id.cart_total)
        var net_sum: Double = 0.0
        for (i in UserCart.itemPairArray) {
            net_sum = net_sum + i.first.itemCost * i.second
        }
//        net_sum = net_sum.toString().toDouble()
//        net_sum = String.format("%.2f", net_sum)
        Log.d("Net Sum ", net_sum.toString())
        cart_total.text = net_sum.toString()

        val intent = Intent()
        intent.putIntegerArrayListExtra("deletedItems", deletedItems)
        deletedItems.clear()
        this.setResult(1, intent)
    }
}

data class Product (
        var itemId:Int,
        var itemName: String?,
        var itemDesc: String?,
        var itemCost: Double,
        var itemThumbnail: Int
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(itemId)
        parcel.writeString(itemName)
        parcel.writeString(itemDesc)
        parcel.writeDouble(itemCost)
        parcel.writeInt(itemThumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}

class AllProducts() {
    fun ProductsList(): ArrayList<Product> {
        var ProductListArray = arrayListOf<Product>()

        ProductListArray.add(Product(1, "Baby Clothing", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.baby_clothing))
        ProductListArray.add(Product(2, "Hoodie", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.hoodie))
        ProductListArray.add(Product(3, "Lower Set", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.lower_set))
        ProductListArray.add(Product(4, "T Shirt", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.t_shirt))
        ProductListArray.add(Product(5, "Track Pants", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.track_pant))
        ProductListArray.add(Product(6, "Baby Clothing", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.baby_clothing))
        ProductListArray.add(Product(7, "Hoodie", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.hoodie))
        ProductListArray.add(Product(8, "Lower Set", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.lower_set))
        ProductListArray.add(Product(9, "T Shirt", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.t_shirt))
        ProductListArray.add(Product(10, "Track Pants", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", 1234.56, R.drawable.track_pant))

        return ProductListArray
    }
}

object Products {
    val products = AllProducts().ProductsList()
}

object UserCart {
    init {
        Log.d("Cart Object:", "Cart singleton invoked...")
    }

    var itemPairArray = arrayListOf<Pair<Product, Int>>()

    fun addItem(reqProduct : Product) {
        for ((cartProduct,) in itemPairArray) {
            if (reqProduct == cartProduct) {
                Log.d("addItem:", "Item already exists in cart!")
                return
            }
        }
        itemPairArray.add(Pair(reqProduct, 1))
        Log.d("addItem:", "Item added to cart...")
    }

    fun removeItem(reqProduct : Product) {
        for ((cartProduct, qty) in itemPairArray) {
            if (reqProduct == cartProduct) {
                itemPairArray.remove(Pair(reqProduct, qty))
                Log.d("removeItem:", "Item removed from cart...")
                return
            }
        }
        Log.d("removeItem:", "Item does not exist in cart!")
        return
    }

    fun changeQuantity(id:Int, newQuantity: Int) {
        Log.d("changeQty", "$id: $newQuantity")
        itemPairArray.forEachIndexed { index, (product,) ->
            if (product.itemId == id) {
                itemPairArray[index] = Pair(product, newQuantity)
            }
        }
    }
}