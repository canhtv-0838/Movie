package com.canh.movie.ui.movie_detail.sharing

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import com.canh.movie.R
import com.canh.movie.databinding.DialogFragmentShareBinding
import com.canh.movie.ui.base.BaseDialogFragment
import com.canh.movie.utils.Constants
import com.canh.movie.utils.SharedPreference
import com.canh.movie.utils.StringUtils
import kotlinx.android.synthetic.main.dialog_fragment_share.*
import org.koin.android.viewmodel.ext.android.viewModel

class SharePostDialogFragment :
    BaseDialogFragment<DialogFragmentShareBinding, SharePostViewModel>() {
    override val viewModel: SharePostViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.dialog_fragment_share

    override fun initComponents() {
    }

    override fun initData() {
        super.initData()
        val idUser = SharedPreference(activity!!).getValueLong(Constants.PREF_ID_USER, 0).apply {
            viewModel.idUser.postValue(this)
        }

        val idMovie = arguments?.getInt(EXTRA_MOVIE_ID)?.apply {
            viewModel.idMovie.postValue(this)
        }
        val posterPathMovie = arguments?.getString(EXTRA_MOVIE_POSTER_PATH)?.apply {
            viewModel.posterPathMovie.postValue(this)
        }

        val overviewMovie = arguments?.getString(EXTRA_MOVIE_OVERVIEW)?.apply {
            viewModel.overviewMovie.postValue(this)
        }?.replace("'","")

        val titleMovie = arguments?.getString(EXTRA_MOVIE_TITLE)?.apply {
            viewModel.titleMovie.postValue(this)
        }?.replace("'","")

        if (idUser != 0.toLong() && idMovie != null && posterPathMovie != null && overviewMovie != null && titleMovie != null) {
            shareButtonShare.setOnClickListener {
                if (StringUtils.isMaxLength(shareTextContent, maxLength = 1000)) {
                    return@setOnClickListener
                }
                viewModel.sharePost(
                    idUser,
                    idMovie,
                    titleMovie,
                    overviewMovie,
                    posterPathMovie,
                    shareTextContent.text.toString().trim().replace("'","")
                )
                shareProgressBar?.visibility = View.VISIBLE
            }
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.shareStatus.observe(this, Observer {
            if (it == viewModel.SHARE_SUCCESS) {
                shareProgressBar?.visibility = View.GONE
                Toast.makeText(activity!!, "Shared", Toast.LENGTH_SHORT).show()
                this.dismiss()
            } else {
                shareProgressBar?.visibility = View.GONE
                Toast.makeText(
                    activity!!,
                    "Something went wrong. Try it later!",
                    Toast.LENGTH_SHORT
                ).show()
                this.dismiss()
            }

        })
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        const val TAG = "SharePostDialogFragment"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_POSTER_PATH = "EXTRA_MOVIE_POSTER_PATH"
        private const val EXTRA_MOVIE_OVERVIEW = "EXTRA_MOVIE_OVERVIEW"
        private const val EXTRA_MOVIE_TITLE = "EXTRA_MOVIE_TITLE"

        fun newInstance(
            idMovie: Int,
            titleMovie: String,
            posterPathMovie: String,
            movieOverview: String
        ) = SharePostDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_MOVIE_ID, idMovie)
                putString(EXTRA_MOVIE_POSTER_PATH, posterPathMovie)
                putString(EXTRA_MOVIE_OVERVIEW, movieOverview)
                putString(EXTRA_MOVIE_TITLE, titleMovie)
            }
        }
    }
}
