package com.canh.movie.ui.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
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
import com.canh.movie.utils.log
import com.canh.movie.utils.replaceFragment
import com.canh.movie.utils.widget.BackDropView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val TIME_DELAY_CHANGE_MODE = 3000L

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    BaseAdapterItemClickListener<Genres> {

    override val viewModel: MainViewModel by viewModel()

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun initComponents() {
        genresListener = this
        setToolbar()
        startFrag(HomeFragment.newInstance())
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
        if (mainDrawer.isDrawerOpen(GravityCompat.END)) {
            mainDrawer.closeDrawer(GravityCompat.END)
        }
    }

    override fun onItemClick(item: Genres) {
        log("${item.name}")
    }

    private fun setNightMode(toggleTheme: MenuItem?) {
        (toggleTheme?.actionView as CheckBox?)?.apply {
            setButtonDrawable(R.drawable.ic_dark_theme)
            isChecked = isNightMode()
            jumpDrawablesToCurrentState()
            setOnCheckedChangeListener { _, isChecked ->
                postDelayed({
                    AppCompatDelegate.setDefaultNightMode(
                        if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                        else AppCompatDelegate.MODE_NIGHT_NO
                    )
                    delegate.applyDayNight()
                }, TIME_DELAY_CHANGE_MODE)
            }
        }
    }

    private fun isNightMode(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    private fun setToolbar() {
        setSupportActionBar(mainToolBar)
        mainToolBar.setNavigationOnClickListener(
            BackDropView(
                this, mainContainerId, AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(this, R.drawable.ic_menu),
                ContextCompat.getDrawable(this, R.drawable.ic_close_menu)
            )
        )
    }

    private fun startFrag(fragment: Fragment) {
        replaceFragment(R.id.mainContainerId, fragment, false)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)

        @JvmStatic
        lateinit var genresListener: BaseAdapterItemClickListener<Genres>
    }
}
