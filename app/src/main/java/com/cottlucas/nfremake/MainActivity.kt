package com.cottlucas.nfremake

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cottlucas.nfremake.model.Category
import com.cottlucas.nfremake.model.Movie
import com.cottlucas.nfremake.util.CategoryTask

class MainActivity : AppCompatActivity(),CategoryTask.Callback {

    private lateinit var progressBar: ProgressBar
    private val categories = mutableListOf<Category>()
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progress_main)



        adapter = CategoryAdapter(categories) { id ->
            val intent = Intent(this@MainActivity, MovieActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
        val rv: RecyclerView = findViewById(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        CategoryTask(this).execute("https://atway.tiagoaguiar.co/fenix/netflixapp/home?apiKey=0e731766-af7d-4eae-85cf-73766b053090")
    }

    override fun onPreExecute() {
        progressBar.visibility = View.VISIBLE
    }

    override fun onResult(categories: List<Category>) {
        Log.i("Teste MainAc", categories.toString())
        this.categories.clear()
        this.categories.addAll(categories)
        adapter.notifyDataSetChanged()

        progressBar.visibility = View.GONE

    }

    override fun onFailure(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        progressBar.visibility = View.GONE
    }
}