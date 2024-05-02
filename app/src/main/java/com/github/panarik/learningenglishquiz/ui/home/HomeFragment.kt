package com.github.panarik.learningenglishquiz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.github.panarik.learningenglishquiz.R
import com.github.panarik.learningenglishquiz.databinding.FragmentHomeBinding
import com.github.panarik.learningenglishquiz.ui.home.model.HomeViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var model: HomeViewModel

    override fun onCreateView(inf: LayoutInflater, cont: ViewGroup?, state: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inf, cont, false)
        model = ViewModelProvider(this)[HomeViewModel::class.java].init(this)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.createQuiz()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun startLoadingFragment() {
        binding?.root?.let {
            val action =
                HomeFragmentDirections.actionNavHomeToDownloadingFragment("test argument")
            Navigation.findNavController(it).navigate(action)
        }
    }
}