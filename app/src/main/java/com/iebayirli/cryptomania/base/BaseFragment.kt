package com.iebayirli.cryptomania.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.iebayirli.cryptomania.BR
import com.iebayirli.cryptomania.utils.observeNotNull

abstract class BaseFragment<BINDING: ViewDataBinding, VM: BaseViewModel>: Fragment(){

    abstract val layoutId: Int

    lateinit var binding: BINDING

    abstract val viewModel: VM

    abstract fun onReady(savedInstanceState: Bundle?)

    protected val baseActivity by lazy { requireActivity() as BaseActivity<*,*>? }

    protected val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (::binding.isInitialized){
            return binding.root
        }

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        onReady(savedInstanceState)

    }

    private fun observe() {
        viewModel.shouldShowProgressDialog.observeNotNull(this) {
            baseActivity?.progressStateChanged(it)
        }
    }

    fun navigate(actionId: Int){
        navController.navigate(actionId)
    }
}