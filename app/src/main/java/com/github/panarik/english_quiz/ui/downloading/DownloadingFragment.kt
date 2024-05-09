package com.github.panarik.english_quiz.ui.downloading

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.github.panarik.english_quiz.R
import com.github.panarik.english_quiz.databinding.FragmentDownloadingBinding
import com.github.panarik.english_quiz.ui.home.model.QuizSession

private const val UI_ANIMATION_DELAY = 500
private const val TAG = "DownloadingFragment"

class DownloadingFragment : Fragment() {

    private var binding: FragmentDownloadingBinding? = null
    private lateinit var model: DownloadingViewModel
    private val hideHandler = Handler(Looper.myLooper()!!)
    private val fullScreenRunnable = Runnable {
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    override fun onCreateView(infl: LayoutInflater, cont: ViewGroup?, state: Bundle?): View? {
        binding = FragmentDownloadingBinding.inflate(infl, cont, false)
        model = ViewModelProvider(this)[DownloadingViewModel::class.java].init(this)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""
        fullScreenMode()
        model.downloadQuiz()
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        delayedHide(0)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        activity?.window?.decorView?.systemUiVisibility = 0
        show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun fullScreenMode() {
        hideHandler.postDelayed(fullScreenRunnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        hideHandler.removeCallbacks(fullScreenRunnable)
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks { fullScreenMode() }
        hideHandler.postDelayed({ fullScreenMode() }, delayMillis.toLong())
    }

    fun startQuizFragment(quiz: QuizSession) {
        Log.d(TAG, "Start Home fragment with new Quiz.")
        val bundle = Bundle().apply { putSerializable("QuizSession", quiz) }
        binding?.root?.let {
            Navigation.findNavController(it).navigate(R.id.toHomeFragment, bundle)
        }
    }
}

