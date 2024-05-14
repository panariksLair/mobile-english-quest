package com.github.panarik.english_quiz.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieDrawable
import com.github.panarik.english_quiz.R
import com.github.panarik.english_quiz.databinding.FragmentHomeBinding
import com.github.panarik.english_quiz.ui.home.model.GameStates
import com.github.panarik.english_quiz.ui.home.model.HomeViewModel
import com.github.panarik.english_quiz.ui.home.model.QuizSession

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
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""

        // Wait users answers
        binding?.homeAnswer0Text?.setOnClickListener { checkQuiz(0) }
        binding?.homeAnswer1Text?.setOnClickListener { checkQuiz(1) }
        binding?.homeAnswer2Text?.setOnClickListener { checkQuiz(2) }
        binding?.homeAnswer3Text?.setOnClickListener { checkQuiz(3) }
        binding?.homeLikeIcon?.setOnClickListener { likeQuiz() }
        binding?.homeDislikeIcon?.setOnClickListener { dislikeQuiz() }
        binding?.homeNextIcon?.setOnClickListener {
            startNextQuiz()
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

    private fun startNextQuiz() {
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

    private fun checkQuiz(button: Int) {
        if (model.gameState.value == GameStates.WAITING_USER_ACTION) {
            model.checkQuiz(button)
        }
    }

    fun finishQuiz(session: QuizSession) {
        binding?.homeAnswer0Text?.setBackgroundColor(if (session.answers?.get(0)?.isRight == true) Color.GREEN else Color.RED)
        binding?.homeAnswer1Text?.setBackgroundColor(if (session.answers?.get(1)?.isRight == true) Color.GREEN else Color.RED)
        binding?.homeAnswer2Text?.setBackgroundColor(if (session.answers?.get(2)?.isRight == true) Color.GREEN else Color.RED)
        binding?.homeAnswer3Text?.setBackgroundColor(if (session.answers?.get(3)?.isRight == true) Color.GREEN else Color.RED)
        binding?.homeLikeIcon?.visibility = View.VISIBLE
        binding?.homeDislikeIcon?.visibility = View.VISIBLE
        binding?.homeNextButton?.visibility = View.VISIBLE
        binding?.homeNextIcon?.visibility = View.VISIBLE
        binding?.homeNextIcon?.playAnimation()
        binding?.homeNextIcon?.repeatCount = LottieDrawable.INFINITE
    }

    fun showWinIcon() {
        binding?.homeShadowLayer?.visibility = View.VISIBLE
        binding?.homeWinMedalAnimation?.visibility = View.VISIBLE
        binding?.homeWinMedalAnimation?.playAnimation()
        binding?.homeWinLeavesAnimation?.visibility = View.VISIBLE
        binding?.homeWinLeavesAnimation?.playAnimation()
    }

    private fun likeQuiz() {
        binding?.homeLikeIcon?.visibility = View.INVISIBLE
        binding?.homeLikeAnimation?.visibility = View.VISIBLE
        binding?.homeLikeAnimation?.playAnimation()
    }

    private fun dislikeQuiz() {
        binding?.homeDislikeIcon?.visibility = View.INVISIBLE
        binding?.homeDislikeAnimation?.visibility = View.VISIBLE
        binding?.homeDislikeAnimation?.playAnimation()
    }
}