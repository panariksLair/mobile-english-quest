package com.github.panarik.english_quiz.ui.downloading

import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.github.panarik.english_quiz.MainActivity
import com.github.panarik.english_quiz.ui.home.model.QuizSession
import kotlinx.coroutines.launch

private const val TAG = "[DownloadingViewModel]"

class DownloadingViewModel : ViewModel() {

    private lateinit var fragment: DownloadingFragment
    private val quiz = MutableLiveData<QuizSession?>()

    fun init(fragment: DownloadingFragment): DownloadingViewModel {
        this.fragment = fragment
        quiz.observe(fragment.viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(fragment.context, "Received empty Quiz", LENGTH_SHORT).show()
                buildQuiz()
            } else {
                Log.d(TAG, "New Quiz has been created. Id=${it.sessionId} Quiz=${it.quiz}")
                fragment.startQuizFragment(it)
                Log.d(TAG, "New Quiz Fragment has started.")
                val activity = fragment.activity as MainActivity
                activity.lifecycleScope.launch {
                    Log.d(TAG, "Mark current Quiz as read.")
                    activity.db.dao.markAsReadQuiz(it.sessionId)
                }
            }
        }
        return this
    }

    fun buildQuiz() {
        QuizBuilder(fragment.activity, quiz).buildQuiz()
    }

}