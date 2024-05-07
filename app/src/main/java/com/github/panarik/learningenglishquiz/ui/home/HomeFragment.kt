package com.github.panarik.learningenglishquiz.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.github.panarik.learningenglishquiz.R
import com.github.panarik.learningenglishquiz.databinding.FragmentHomeBinding
import com.github.panarik.learningenglishquiz.ui.home.model.GameStates
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

        // Wait users answers
        binding?.homeAnswer0Text?.setOnClickListener {
            if (model.gameState == GameStates.WAITING_USER_ACTION) {
                model.checkQuiz(0)
            }
        }
        binding?.homeAnswer1Text?.setOnClickListener {
            if (model.gameState == GameStates.WAITING_USER_ACTION) {
                model.checkQuiz(1)
            }
        }
        binding?.homeAnswer2Text?.setOnClickListener {
            if (model.gameState == GameStates.WAITING_USER_ACTION) {
                model.checkQuiz(2)
            }
        }
        binding?.homeAnswer3Text?.setOnClickListener {
            if (model.gameState == GameStates.WAITING_USER_ACTION) {
                model.checkQuiz(3)
            }
        }
        model.startQuiz()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun startLoadingFragment() {
        binding?.root?.let { Navigation.findNavController(it).navigate(R.id.toDownloadingFragment) }
    }

    fun startNextQuiz() {
        val bundle = Bundle().apply { putSerializable("QuizSession", model.newQuiz.value) }
        binding?.root?.let {
            Navigation.findNavController(it).navigate(R.id.toHomeFragment, bundle)
        }
    }

    fun createScreen(session: QuizSession) {
        binding?.homeSummaryText?.text = session.quiz.summary
        binding?.homeQuestionText?.text = session.quiz.question
        binding?.homeAnswer0Text?.text = session.answers?.get(0)?.answer
        binding?.homeAnswer1Text?.text = session.answers?.get(1)?.answer
        binding?.homeAnswer2Text?.text = session.answers?.get(2)?.answer
        binding?.homeAnswer3Text?.text = session.answers?.get(3)?.answer
    }

    fun finishQuiz(session: QuizSession) {
        binding?.homeAnswer0Text?.setBackgroundColor(if (session?.answers?.get(0)?.isRight == true) Color.GREEN else Color.RED)
        binding?.homeAnswer1Text?.setBackgroundColor(if (session?.answers?.get(1)?.isRight == true) Color.GREEN else Color.RED)
        binding?.homeAnswer2Text?.setBackgroundColor(if (session?.answers?.get(2)?.isRight == true) Color.GREEN else Color.RED)
        binding?.homeAnswer3Text?.setBackgroundColor(if (session?.answers?.get(3)?.isRight == true) Color.GREEN else Color.RED)
    }
}