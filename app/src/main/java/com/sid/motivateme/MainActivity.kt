package com.sid.motivateme

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.sid.motivateme.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: QuoteViewModel by viewModels()


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.btnNext.setOnClickListener {
            observeQuote()
        }
    }

    fun init() {

        viewModel.isFirstRun.observe(this) { isFirstRun ->
            if (isFirstRun) {
                observeInstalling()
                lifecycleScope.launch {
                    viewModel.insertAllQuotes()
                }
            }
        }
    }

    fun observeQuote() {
        /*   viewModel.getQuoteById(Random.nextInt(1, viewModel.getQuoteListSize())).observe(this) {
               if (it != null) {
                   binding.tvQuote.text = it.quote
               } else {
                   binding.tvQuote.text = "Please try again"
               }
           }*/

        viewModel.getRandomQuote().observe(this) { quoteList ->
            if (quoteList != null) {
                val quoteNetworkEntity = quoteList.get(0)

                binding.tvQuote.text = quoteNetworkEntity.content +"\n-"+quoteNetworkEntity.author
            } else {
                binding.tvQuote.text = "Please try again"
            }
        }

        viewModel.fetchRandomQuote()

    }

    fun observeInstalling() {
        viewModel.quotesInstall.observe(this) {
            if (it) {
                binding.btnNext.isEnabled = true
                binding.tvQuote.text = getString(R.string.motivate_string)
            } else {
                binding.btnNext.isEnabled = false
                binding.tvQuote.text = getString(R.string.inital_text)
            }
        }
    }

    /*fun setQuoteBackground() {


        // Get the URI of the video file from the raw folder
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.quotes_bg)

        // Set the URI to the VideoView
        binding.vvQuotebg.setVideoURI(videoUri)

        // Optionally, set a listener to handle when the video is ready to play
        binding.vvQuotebg.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true // Loop the video if needed
        }

        // Start the video playback
        binding.vvQuotebg.start()
    }*/


}


