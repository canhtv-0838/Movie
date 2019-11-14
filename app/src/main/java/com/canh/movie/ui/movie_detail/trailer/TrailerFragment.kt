package com.canh.movie.ui.movie_detail.trailer

import android.content.Context
import android.os.Bundle
import com.canh.movie.R
import com.canh.movie.data.model.Video
import com.canh.movie.data.model.result.VideoResult
import com.canh.movie.databinding.FragmentTrailerBinding
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.movie_detail.MovieTrailerListener
import org.koin.android.viewmodel.ext.android.viewModel

class TrailerFragment : BaseFragment<FragmentTrailerBinding, TrailerViewModel>(),
    BaseAdapterItemClickListener<Video> {

    override val viewModel: TrailerViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.fragment_trailer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        trailerListener = context as MovieTrailerListener
    }

    override fun initComponents() {
        listener = this
    }

    override fun initData() {
        super.initData()
        arguments?.getParcelable<VideoResult>(ARGUMENT_VIDEO_RESULT)?.let {
            viewModel.postVideoResult(it)
        }
    }

    override fun onItemClick(item: Video) {
       trailerListener.playTrailer(item.key)
    }

    companion object {
        private const val ARGUMENT_VIDEO_RESULT = "ARGUMENT_VIDEO_RESULT"
        @JvmStatic
        lateinit var listener: BaseAdapterItemClickListener<Video>

        lateinit var trailerListener : MovieTrailerListener

        fun newInstance(videoResult: VideoResult) = TrailerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_VIDEO_RESULT, videoResult)
            }
        }
    }
}
