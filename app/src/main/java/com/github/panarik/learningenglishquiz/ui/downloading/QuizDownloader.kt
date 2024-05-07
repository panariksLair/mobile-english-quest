package com.github.panarik.learningenglishquiz.ui.downloading

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

private const val TAG = "QuizDownloader"

class QuizDownloader(val fragment: FragmentActivity?, val liveData: MutableLiveData<String?>) {

    fun downloadQuiz() {
        Log.d(TAG, "Downloading Quiz...")
        val request = Request.Builder()
            .url("https://mxkrc6qenp.eu-central-1.awsapprunner.com/quiz")
            .build()
        OkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Failed to request quiz. Original exception: ${e.message}")
                fragment?.runOnUiThread { liveData.value = null }
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "Response from Quiz server received.")
                val code = response.code
                val body = response.body?.string() ?: ""
                if (code == 200) {
                    Log.d(TAG, "Quiz downloaded successfully. Quiz body: $body")
                    fragment?.runOnUiThread { liveData.value = body }
                } else {
                    Log.e(TAG, "Failed to request. Response: code=$code body=${body}")
                    fragment?.runOnUiThread { liveData.value = null }
                }
            }
        })
    }

}