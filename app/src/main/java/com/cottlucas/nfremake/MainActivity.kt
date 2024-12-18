package com.cottlucas.nfremake

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cottlucas.nfremake.model.Category
import com.cottlucas.nfremake.model.Movie
import com.cottlucas.nfremake.util.CategoryTask

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val categories = mutableListOf<Category>()

        val adapter = CategoryAdapter(categories)
        val rv: RecyclerView = findViewById(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        CategoryTask().execute("https://atway.tiagoaguiar.co/fenix/netflixapp/home?apiKey=0e731766-af7d-4eae-85cf-73766b053090")
    }
}