package com.canh.movie.ui.movie_detail

import android.os.Bundle
import com.canh.movie.BuildConfig
import com.canh.movie.utils.log
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment

class YoutubeFragment : YouTubePlayerFragment(), YouTubePlayer.OnInitializedListener, YouTubePlayer.OnFullscreenListener {

    private var youTubePlayer: YouTubePlayer? = null
    private var trailerKey: String? = null
    var isYtbFullScreen = false

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        initialize(BuildConfig.YOUTUBE_API_KEY, this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.let {
            youTubePlayer = it

            if (!p2 && !trailerKey.isNullOrBlank()) {
                youTubePlayer?.fullscreenControlFlags =
                    YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION
            }
            trailerKey?.let {
                youTubePlayer?.cueVideo(trailerKey)
                youTubePlayer?.setOnFullscreenListener(this)
            }
        }

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        youTubePlayer = null
    }

    override fun onFullscreen(p0: Boolean) {
        isYtbFullScreen = p0
    }

    override fun onDestroy() {
        if (youTubePlayer != null) {
            youTubePlayer?.release()
        }
        super.onDestroy()
    }

    fun setTrailerKey(movieTrailerKey: String) {
        trailerKey = movieTrailerKey
        if (trailerKey != null && youTubePlayer != null) {
            youTubePlayer?.cueVideo(trailerKey)
        }
    }

    fun playTrailer() {
        youTubePlayer?.play()
    }

    fun setFullScreen(isFullScreen: Boolean) {
        youTubePlayer?.setFullscreen(isFullScreen)
    }
}
