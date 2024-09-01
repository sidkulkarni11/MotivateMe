package com.sid.motivateme.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sid.motivateme.R
import com.sid.motivateme.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        init()

        binding.btnNext.setOnClickListener {
            observeQuote()
        }

        return root
    }

    fun init() {

        viewModel.isFirstRun.observe(viewLifecycleOwner) { isFirstRun ->
            if (isFirstRun) {
                observeInstalling()
                lifecycleScope.launch(Dispatchers.IO) {
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun observeQuote() {
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
}