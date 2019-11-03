package com.canh.movie.ui.main

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.canh.movie.R
import com.canh.movie.data.model.Genres
import com.canh.movie.databinding.ActivityMainBinding
import com.canh.movie.ui.base.BaseActivity
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.main.home.HomeFragment
import com.canh.movie.ui.main.movies.MoviesFragment
import com.canh.movie.utils.Constants.NIGH_MODE
import com.canh.movie.utils.SharedPreference
import com.canh.movie.utils.widget.BackDropView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


private const val TIME_DELAY_CHANGE_MODE = 1000L

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    BaseAdapterItemClickListener<Genres> {
    private var fragmentName: String = HomeFragment.javaClass.name

    override fun getContainerId(): Int = R.id.mainContainerId

    override val viewModel: MainViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun initComponents() {
        genresListener = this
        setToolbar()
        startFrag(HomeFragment.newInstance())
        initBackStackListener()
        initBackDropView()
    }

    override fun observeData() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val toggleTheme = menu?.findItem(R.id.menu_theme)
        setNightMode(toggleTheme)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_search -> {
            //todo
            true
        }
        R.id.menu_filter -> {
            mainDrawer.openDrawer(GravityCompat.END)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (fragmentName == HomeFragment.javaClass.name) {
            return
        } else {
            super.onBackPressed()
        }
        if (mainDrawer.isDrawerOpen(GravityCompat.END)) {
            mainDrawer.closeDrawer(GravityCompat.END)
        }
    }

    override fun onItemClick(item: Genres) {
        addFragment(MoviesFragment.newInstance(item), true)
        mainDrawer.closeDrawer(GravityCompat.END)
    }

    private fun setNightMode(toggleTheme: MenuItem?) {
        (toggleTheme?.actionView as CheckBox?)?.apply {
            setButtonDrawable(R.drawable.ic_dark_theme)
            isChecked = SharedPreference(context).getValueBoolean(NIGH_MODE, false)
            jumpDrawablesToCurrentState()
            setOnCheckedChangeListener { _, isChecked ->
                postDelayed({
                    AppCompatDelegate.setDefaultNightMode(
                        if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                        else AppCompatDelegate.MODE_NIGHT_NO
                    )
                    delegate.applyDayNight()
                    SharedPreference(context).saveValueBoolean(NIGH_MODE, isChecked)
                }, TIME_DELAY_CHANGE_MODE)
            }
        }
    }

    private fun setToolbar() {
        setSupportActionBar(mainToolBar)
    }

    private fun setToolbarTitle() {
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun initBackStackListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.mainContainerId)
            if (fragment != null) {
                fragmentName = fragment.javaClass.name
                changeToolBarStatus()
            }
        }
    }

    private fun changeToolBarStatus() {
        val backStackEntryCount = supportFragmentManager.backStackEntryCount
        if (backStackEntryCount == 0) {
            setToolbarTitle()
            initBackDropView()
        } else {
            mainToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            mainToolBar.setNavigationOnClickListener { supportFragmentManager.popBackStack() }
        }
    }

    private fun initBackDropView() {
        mainToolBar.setNavigationIcon(R.drawable.ic_menu)
        mainToolBar.setNavigationOnClickListener(
            BackDropView(
                this, mainContainerId, AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(this, R.drawable.ic_menu),
                ContextCompat.getDrawable(this, R.drawable.ic_close_menu)
            )
        )
    }

    private fun startFrag(fragment: Fragment) {
        replaceFragment(fragment, false)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)

        @JvmStatic
        lateinit var genresListener: BaseAdapterItemClickListener<Genres>
    }
}
