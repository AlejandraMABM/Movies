package com.example.mainactivity.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainactivity.R
import com.example.mainactivity.adapters.MovieAdapter
import com.example.mainactivity.data.MovieName
import com.example.mainactivity.databinding.ActivityMainBinding
import com.example.mainactivity.provider.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter: MovieAdapter

    // La referencia del RecyclerView
    lateinit var recyclerView: RecyclerView


    var movieList: List<MovieName> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = MovieAdapter(movieList) { position ->
            val movie = movieList[position]
            println(" verificando la movie $movie")
            navigateToDetail(movie)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        searchMovie("matrix")





        //cargarDatos()
    }

    private fun navigateToDetail(movie: MovieName) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE_ID,movie.imdbID)
        startActivity(intent)

    }

    fun searchMovie(query: String) {

        val service = RetrofitProvider.getRetrofit()
        println(" verificando datos $service $query")


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = service.findAllMovies(query)
                println("entrando por aqui $result" + "query" + "$query")


                CoroutineScope(Dispatchers.Main).launch {
                  if (result.movies.isNotEmpty()) {
                      movieList = result.movies
                      println(" movies en general $movieList")
                      adapter.updateItems(movieList)
                        println("resultado ${result}")
                    } else {
                        println(" valor erroneo")
                    }
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())

            }
        }
    }


}