package com.github.panarik.learningenglishquiz.ui.downloading

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.panarik.learningenglishquiz.ui.home.model.QuizSession

private const val TAG = "DownloadingViewModel"

class DownloadingViewModel : ViewModel() {

    private lateinit var fragment: DownloadingFragment
    private val quiz = MutableLiveData<QuizSession?>()

    fun init(fragment: DownloadingFragment): DownloadingViewModel {
        this.fragment = fragment
        quiz.observe(fragment.viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(fragment.context, "Received empty Quiz", LENGTH_SHORT).show()
                downloadQuiz()
            } else {
                fragment.startQuizFragment(it)
            }
        }
        return this
    }

    fun downloadQuiz() {
        QuizDownloader(fragment.activity, quiz).downloadQuiz()
    }

}