package com.example.mainactivity.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mainactivity.R
import com.example.mainactivity.data.MovieName
import com.example.mainactivity.databinding.ActivityDetailBinding
import com.example.mainactivity.provider.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "MOVIE_ID"
    }
    lateinit var binding: ActivityDetailBinding

    lateinit var movie: MovieName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra(EXTRA_MOVIE_ID)!!

        getSuperhero(id)


    }

    private fun loadData() {

        println(" datos de detalle  ${movie.title}")
        supportActionBar?.title = movie.title
        binding.detailNameTextView.text = movie.title
        binding.anoTextView.text = movie.year
        binding.paisTextView.text  =
            Picasso.get().load(movie.image).into(binding.posterImageView).toString()
        binding.directorTextView.text = movie.director
        binding.paisTextView.text = movie.country
        binding.generoTextView.text = movie.genre
        binding.duracionTextView.text = movie.runtime
        binding.sinopsisTextView.text = movie.plot
    }

    private fun getSuperhero(id:String) {
        val service = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                movie = service.findMovieById(id)

                CoroutineScope(Dispatchers.Main).launch {
                    loadData()
                }
            } catch (e: Exception) {
                Log.e("API", e.stackTraceToString())
            }
        }
    }

}
