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
import com.github.panarik.learningenglishquiz.ui.home.model.QuizSession

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
        binding?.root?.let { Navigation.findNavController(it).navigate(R.id.toDownloadingFragment) }
    }

    fun createScreen(session: QuizSession) {
        binding?.homeSummaryText?.text = session.quiz.summary
        binding?.homeQuestionText?.text = session.quiz.question
        binding?.homeAnswer1Text?.text = session.quiz.right_answer
        binding?.homeAnswer2Text?.text = session.quiz.wrong_answers[0]
        binding?.homeAnswer3Text?.text = session.quiz.wrong_answers[1]
        binding?.homeAnswer4Text?.text = session.quiz.wrong_answers[2]
    }
}