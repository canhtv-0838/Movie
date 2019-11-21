package com.canh.movie.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.canh.movie.R
import com.canh.movie.data.model.Genres
import com.canh.movie.data.model.User
import com.canh.movie.databinding.ActivityMainBinding
import com.canh.movie.ui.base.BaseActivity
import com.canh.movie.ui.base.BaseAdapterItemClickListener
import com.canh.movie.ui.main.home.HomeFragment
import com.canh.movie.ui.main.login.LoginDialogFragment
import com.canh.movie.ui.main.movies.MoviesFragment
import com.canh.movie.ui.main.profile.ProfileFragment
import com.canh.movie.ui.main.timeline.TimelineFragment
import com.canh.movie.ui.search.SearchActivity
import com.canh.movie.utils.Constants.PREF_ID_USER
import com.canh.movie.utils.Constants.PREF_LOGGED_IN
import com.canh.movie.utils.Constants.PREF_NIGH_MODE
import com.canh.movie.utils.SharedPreference
import com.canh.movie.utils.widget.BackDropView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_menu.*
import org.koin.android.viewmodel.ext.android.viewModel


private const val TIME_DELAY_CHANGE_MODE = 1000L

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    BaseAdapterItemClickListener<Genres>,
    LoginDialogFragment.LoginListener {
    private var fragmentName: String = HomeFragment.javaClass.name

    private val backDropView by lazy {
        BackDropView(
            this, mainContainerId, AccelerateDecelerateInterpolator(),
            ContextCompat.getDrawable(this, R.drawable.ic_menu),
            ContextCompat.getDrawable(this, R.drawable.ic_close_menu)
        )
    }

    override fun getContainerId(): Int = R.id.mainContainerId

    override val viewModel: MainViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun initComponents() {
        genresListener = this
        setToolbar()
        startFrag(HomeFragment.newInstance())
        initBackStackListener()
        initBackDropView()
        setDropViewListener()
    }

    override fun observeData() {
        if (SharedPreference(this).getValueBoolean(PREF_LOGGED_IN, false)) {
            setViewOnLoggedIn()
        }
    }

    override fun onResume() {
        super.onResume()
        if (SharedPreference(this).getValueBoolean(PREF_LOGGED_IN, false)) {
            setViewOnLoggedIn()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val toggleTheme = menu?.findItem(R.id.menu_theme)
        setNightMode(toggleTheme)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_search -> {
            startActivity(Intent(this, SearchActivity::class.java))
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

    override fun onLoginSuccess(user: User) {
        setViewOnLoggedIn()
        backDropView.onLoggedIn()
        mainToolBar.setNavigationIcon(R.drawable.ic_menu)
    }

    private fun setViewOnLoggedIn() {
        menuButtonLogin.visibility = View.GONE
        menuButtonProfile.visibility = View.VISIBLE
        menuButtonLogout.visibility = View.VISIBLE
        menuButtonHome.visibility = View.VISIBLE
        menuButtonTimeline.visibility = View.VISIBLE
    }

    private fun setNightMode(toggleTheme: MenuItem?) {
        (toggleTheme?.actionView as CheckBox?)?.apply {
            setButtonDrawable(R.drawable.ic_dark_theme)
            isChecked = SharedPreference(context).getValueBoolean(PREF_NIGH_MODE, false)
            jumpDrawablesToCurrentState()
            setOnCheckedChangeListener { _, isChecked ->
                postDelayed({
                    AppCompatDelegate.setDefaultNightMode(
                        if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                        else AppCompatDelegate.MODE_NIGHT_NO
                    )
                    delegate.applyDayNight()
                    SharedPreference(context).saveValueBoolean(PREF_NIGH_MODE, isChecked)
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
        mainToolBar.setNavigationOnClickListener(backDropView)
    }

    private fun startFrag(fragment: Fragment) {
        replaceFragment(fragment, false)
    }


    @SuppressLint("ResourceType")
    private fun setDropViewListener() {
        menuButtonLogin.setOnClickListener {
            LoginDialogFragment.newInstance().show(supportFragmentManager, LoginDialogFragment.TAG)
        }

        menuButtonProfile.setOnClickListener {
            backDropView.close()
            mainToolBar.setNavigationIcon(R.drawable.ic_menu)
            startFrag(
                ProfileFragment.newInstance(
                    SharedPreference(this).getValueLong(
                        PREF_ID_USER,
                        0
                    )
                )
            )
        }

        menuButtonHome.setOnClickListener {
            backDropView.close()
            mainToolBar.setNavigationIcon(R.drawable.ic_menu)
            startFrag(HomeFragment.newInstance())
        }

        menuButtonTimeline.setOnClickListener {
            backDropView.close()
            mainToolBar.setNavigationIcon(R.drawable.ic_menu)
            startFrag(TimelineFragment.newInstance())
        }

        menuButtonLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this).apply{
                setMessage(getString(R.string.main_alert_logout))
                setPositiveButton(getString(R.string.main_alert_logout_positive_button)){ dialog, which ->
                    SharedPreference(this@MainActivity).removeValue(PREF_LOGGED_IN)
                    SharedPreference(this@MainActivity).removeValue(PREF_ID_USER)
                    backDropView.onLoggedOut()
                    mainToolBar.setNavigationIcon(R.drawable.ic_menu)
                    menuButtonProfile.visibility = View.GONE
                    menuButtonHome.visibility = View.GONE
                    menuButtonTimeline.visibility = View.GONE
                    menuButtonLogin.visibility = View.VISIBLE
                    startFrag(HomeFragment.newInstance())
                    dialog.dismiss()
                }
                setNegativeButton(getString(R.string.main_alert_logout_negative_button)){ dialog, which ->
                    dialog.dismiss()
                }
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)

        @JvmStatic
        lateinit var genresListener: BaseAdapterItemClickListener<Genres>
    }
}
