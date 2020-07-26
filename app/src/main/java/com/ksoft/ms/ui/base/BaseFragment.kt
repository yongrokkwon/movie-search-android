package com.ksoft.ms.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ksoft.ms.BR
import com.ksoft.ms.ui.widget.ProgressLoadingDialog
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel<BasePresenter>, VB : ViewDataBinding> : Fragment(),
    BasePresenter {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    lateinit var binding: VB
    protected val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(viewModelStore, viewModelProviderFactory).get(viewModelClass.java)
    }
    private val progress: ProgressLoadingDialog by lazy(LazyThreadSafetyMode.NONE) {
        ProgressLoadingDialog(context = requireContext())
    }

    protected abstract val layoutRes: Int
    protected abstract val viewModelClass: KClass<VM>
    protected abstract fun initPresenter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        binding.lifecycleOwner = this

        binding.setVariable(BR.viewModel, viewModel)

        initPresenter()

        return binding.root
    }

    override fun hideLoading() {
        progress.hide()
    }

    override fun showLoading() {
        progress.safeShow()
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

}