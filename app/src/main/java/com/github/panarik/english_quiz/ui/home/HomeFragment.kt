package com.github.panarik.english_quiz.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.airbnb.lottie.LottieDrawable
import com.github.panarik.english_quiz.R
import com.github.panarik.english_quiz.databinding.FragmentHomeBinding
import com.github.panarik.english_quiz.services.AppFlags
import com.github.panarik.english_quiz.ui.home.model.GameStates
import com.github.panarik.english_quiz.ui.home.model.HomeViewModel
import com.github.panarik.english_quiz.ui.home.model.QuizSession
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

private const val TAG = "[HomeFragment]"

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var model: HomeViewModel
    var ad: InterstitialAd? = null

    override fun onCreateView(inf: LayoutInflater, cont: ViewGroup?, state: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inf, cont, false)
        model = ViewModelProvider(this)[HomeViewModel::class.java].init(this)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""
        setupAd()
        setUpListeners()
        model.startQuiz()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun startFragment(quizSession: QuizSession) {
        Log.d(TAG, "Start Home fragment with new Quiz.")
        val bundle = Bundle().apply { putSerializable("QuizSession", quizSession) }
        binding?.root?.let {
            Navigation.findNavController(it).navigate(R.id.toHomeFragment, bundle)
        }
    }

    fun startBuildingQuizFragment() {
        binding?.root?.let { Navigation.findNavController(it).navigate(R.id.toDownloadingFragment) }
    }

    private fun setUpListeners() {
        binding?.homeAnswer0Button?.setOnClickListener { checkQuiz(0) }
        binding?.homeAnswer1Button?.setOnClickListener { checkQuiz(1) }
        binding?.homeAnswer2Button?.setOnClickListener { checkQuiz(2) }
        binding?.homeAnswer3Button?.setOnClickListener { checkQuiz(3) }
        binding?.homeLikeIcon?.setOnClickListener { likeQuiz() }
        binding?.homeDislikeIcon?.setOnClickListener { dislikeQuiz() }
        binding?.homeNextButton?.setOnClickListener { showAd() }
    }

    private fun startNextQuiz() {
        AppFlags.newSession()
        val bundle = Bundle().apply { putSerializable("QuizSession", model.newQuiz.value) }
        binding?.root?.let {
            Navigation.findNavController(it).navigate(R.id.toHomeFragment, bundle)
        }
    }

    fun createScreen(session: QuizSession) {
        binding?.homeSummaryText?.text = session.quiz.summary
        binding?.homeQuestionText?.text = session.quiz.question
        binding?.homeAnswer0Button?.text = session.answers?.get(0)?.answer
        binding?.homeAnswer0ResultText?.text = session.answers?.get(0)?.answer
        binding?.homeAnswer1Button?.text = session.answers?.get(1)?.answer
        binding?.homeAnswer1ResultText?.text = session.answers?.get(1)?.answer
        binding?.homeAnswer2Button?.text = session.answers?.get(2)?.answer
        binding?.homeAnswer2ResultText?.text = session.answers?.get(2)?.answer
        binding?.homeAnswer3Button?.text = session.answers?.get(3)?.answer
        binding?.homeAnswer3ResultText?.text = session.answers?.get(3)?.answer

    }

    private fun checkQuiz(button: Int) {
        if (model.gameState.value == GameStates.WAITING_USER_ACTION) {
            model.checkQuiz(button)
        }
    }

    fun finishQuiz(session: QuizSession) {
        model.gameState.value = GameStates.QUIZ_FINISHED
        val green = resources.getColor(R.color.win_green)
        val red = resources.getColor(R.color.lose_red)
        binding?.homeAnswer0Button?.visibility = View.INVISIBLE
        binding?.homeAnswer0Button?.isEnabled = false
        binding?.homeAnswer1Button?.visibility = View.INVISIBLE
        binding?.homeAnswer1Button?.isEnabled = false
        binding?.homeAnswer2Button?.visibility = View.INVISIBLE
        binding?.homeAnswer2Button?.isEnabled = false
        binding?.homeAnswer3Button?.visibility = View.INVISIBLE
        binding?.homeAnswer3Button?.isEnabled = false
        binding?.homeAnswer0ResultText?.visibility = View.VISIBLE
        binding?.homeAnswer0ResultText?.setBackgroundColor(if (session.answers?.get(0)?.isRight == true) green else red)
        binding?.homeAnswer1ResultText?.setBackgroundColor(if (session.answers?.get(1)?.isRight == true) green else red)
        binding?.homeAnswer1ResultText?.visibility = View.VISIBLE
        binding?.homeAnswer2ResultText?.setBackgroundColor(if (session.answers?.get(2)?.isRight == true) green else red)
        binding?.homeAnswer2ResultText?.visibility = View.VISIBLE
        binding?.homeAnswer3ResultText?.setBackgroundColor(if (session.answers?.get(3)?.isRight == true) green else red)
        binding?.homeAnswer3ResultText?.visibility = View.VISIBLE
        binding?.homeLikeIcon?.visibility = View.VISIBLE
        binding?.homeDislikeIcon?.visibility = View.VISIBLE
        showNextButton()
    }

    private fun showNextButton() {
        binding?.homeNextButton?.visibility = View.VISIBLE
        binding?.homeNextAnimation?.visibility = View.VISIBLE
        binding?.homeNextAnimation?.playAnimation()
        binding?.homeNextAnimation?.repeatCount = LottieDrawable.INFINITE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding?.homeNextButton?.focusable = View.FOCUSABLE
        }
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
        binding?.homeDislikeIcon?.visibility = View.VISIBLE
        binding?.homeDislikeAnimation?.visibility = View.INVISIBLE
        binding?.homeLikeAnimation?.playAnimation()
        model.rateQuiz(1)
    }

    private fun dislikeQuiz() {
        binding?.homeDislikeIcon?.visibility = View.INVISIBLE
        binding?.homeDislikeAnimation?.visibility = View.VISIBLE
        binding?.homeLikeIcon?.visibility = View.VISIBLE
        binding?.homeLikeAnimation?.visibility = View.INVISIBLE
        binding?.homeDislikeAnimation?.playAnimation()
        model.rateQuiz(-1)
    }

    private fun setupAd() {
        context?.let { MobileAds.initialize(it) {} }
        var adRequest = AdRequest.Builder().build()
        context?.let {
            InterstitialAd.load(
                it,
                "ca-app-pub-3940256099942544/1033173712", // test Ad
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        ad = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        ad = interstitialAd
                    }
                })
        }
    }

    private fun showAd() {
        if (ad != null && AppFlags.isAdTime(model.gameState.value)) {
            Log.d(TAG, "Add is loaded. Start showing it...")
            AppFlags.resetSession()
            activity?.let { ad?.show(it) }
            ad?.fullScreenContentCallback = object : FullScreenContentCallback() {

                /**
                 * Called when a click is recorded for an ad.
                 */
                override fun onAdClicked() {
                    Log.d(TAG, "Ad was clicked.")
                }

                /**
                 * Called when ad is dismissed.
                 */
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad dismissed fullscreen content.")
                    ad = null
                    startNextQuiz()
                }

                /**
                 * Called when ad fails to show.
                 */
                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    Log.e(TAG, "Ad failed to show fullscreen content.")
                    ad = null
                    startNextQuiz()
                }

                /**
                 * Called when an impression is recorded for an ad.
                 */
                override fun onAdImpression() {
                    Log.d(TAG, "Ad recorded an impression.")
                    startNextQuiz()
                }

                /**
                 * Called when ad is shown.
                 */
                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    startNextQuiz()
                }
            }
        } else {
            Log.d(TAG, "Add is not ready. Start new Quiz.")
            startNextQuiz()
        }
    }
}