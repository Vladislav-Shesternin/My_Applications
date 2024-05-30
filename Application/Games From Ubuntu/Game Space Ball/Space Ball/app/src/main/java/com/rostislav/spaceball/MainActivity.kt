package com.rostislav.spaceball

import android.content.Intent
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.gms.games.AuthenticationResult
import com.google.android.gms.games.GamesSignInClient
import com.google.android.gms.games.PlayGames
import com.google.android.gms.tasks.Task
import com.rostislav.spaceball.databinding.ActivityMainBinding
import com.rostislav.spaceball.util.Lottie
import com.rostislav.spaceball.util.Once
import com.rostislav.spaceball.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        private const val RC_LEADERBOARD_UI = 9004
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

    private lateinit var binding : ActivityMainBinding
    lateinit var lottie          : Lottie

    var isGPGAuthenticated = false
    var gamesSignInClient: GamesSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        lottie.showLoader()

        gamesSignInClient = PlayGames.getGamesSignInClient(this)

        gamesSignInClient?.let { gsc ->
            gsc.isAuthenticated().addOnCompleteListener { isAuthenticatedTask: Task<AuthenticationResult> ->
                isGPGAuthenticated = (isAuthenticatedTask.isSuccessful && isAuthenticatedTask.result.isAuthenticated)
                log("PlayGames isAuthenticated = $isGPGAuthenticated")

                if (isGPGAuthenticated) {
                    PlayGames.getPlayersClient(this).currentPlayer.addOnCompleteListener { mTask ->
                        log("PlayGames playerId = ${mTask.result.playerId}")
                    }
                }
            }
        }
    }

    override fun exit() {
        onceExit.once {
            log("exit")
            coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                delay(100)
                exitProcess(0)
            }
        }
    }

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lottie       = Lottie(binding)
    }

    fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@MainActivity, colorId)
        }
    }

    fun showLeaderboard() {
        PlayGames.getLeaderboardsClient(this)
            .getLeaderboardIntent(getString(R.string.leaderboard_number_of_stars))
            .addOnSuccessListener { intent -> startActivityForResult(intent, RC_LEADERBOARD_UI) }
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        log("Hello: $resultCode")
    }

}