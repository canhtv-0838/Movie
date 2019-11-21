package com.canh.movie.ui.main.profile

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.canh.movie.R
import com.canh.movie.data.model.SharedPost
import com.canh.movie.databinding.FragmentProfileBinding
import com.canh.movie.ui.base.BaseFragment
import com.canh.movie.ui.movie_detail.MovieDetailActivity
import com.canh.movie.utils.log
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding,ProfileViewModel>(), ProfileListener {

    override val viewModel: ProfileViewModel by viewModel()

    override fun getLayoutResource(): Int  = R.layout.fragment_profile

    override fun initComponents(){
        listener = this
    }

    override fun initData() {
        super.initData()

        arguments?.getLong(ARGUMENT_USER_ID)?.let {
            viewModel.getUserDetail(it)
            viewModel.getAllSharedPost(it)
        }
    }

    override fun onMovieClick(idMovie: Int, titleMovie: String) {
        startActivity(MovieDetailActivity.getIntent(activity!!,idMovie, titleMovie))
    }

    override fun deleteSharedPost(item: SharedPost) {
        val builder = AlertDialog.Builder(activity!!).apply{
            setMessage(getString(R.string.profile_alert_delete_post))
            setPositiveButton(getString(R.string.profile_alert_positive_button)){ dialog, which ->
                viewModel.deleteSharedPost(item)
                dialog.dismiss()
            }
            setNegativeButton(getString(R.string.profile_alert_negative_button)){ dialog, which ->
                dialog.dismiss()
            }
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onItemClick(item: Any) {
        //todo
    }


    companion object {
        private const val ARGUMENT_USER_ID = "ARGUMENT_USER_ID"

        lateinit var listener: ProfileListener

        fun newInstance(userId : Long) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putLong(ARGUMENT_USER_ID,userId)
            }
        }
    }
}
