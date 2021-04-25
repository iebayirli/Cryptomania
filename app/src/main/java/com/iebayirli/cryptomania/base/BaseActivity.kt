package com.iebayirli.cryptomania.base

import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iebayirli.cryptomania.BR
import com.iebayirli.cryptomania.utils.ProgressDialog
import com.iebayirli.cryptomania.utils.observeNotNull

abstract class BaseActivity<BINDING : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), BaseView {

    abstract val layoutId: Int

    abstract val fragmentContainerId: Int

    abstract val viewModel: VM

    lateinit var binding: BINDING

    private lateinit var progressDialog: ProgressDialog

    protected val navController: NavController by lazy {
        findNavController(fragmentContainerId)
    }

    protected val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(fragmentContainerId)
    }


    private val appBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }


    abstract fun onReady(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.viewModel, viewModel)
        progressDialog = ProgressDialog(this)
        observe()
        onReady(savedInstanceState)
    }

    private fun observe() {
        viewModel.shouldShowProgressDialog.observeNotNull(this, ::progressStateChanged)
    }

    fun progressStateChanged(shouldShowProgress: Boolean) {
        if (shouldShowProgress) showProgressDialog() else dismissProgressDialog()
    }


    protected fun withToolbar(
            container: FragmentContainerView,
            toolbar: androidx.appcompat.widget.Toolbar
    ) {
        container.post {
            toolbar.setupWithNavController(navController, appBarConfiguration)
        }
    }

    protected fun withBottomNavView(
            bottomNavigationView: BottomNavigationView
    ) {
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment!!.findNavController())
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    protected fun navigate(id: Int) = navController.navigate(id)


    override fun showProgressDialog() {
        progressDialog.showProgress()
    }

    override fun dismissProgressDialog() {
        progressDialog.dismissProgress()
    }
}