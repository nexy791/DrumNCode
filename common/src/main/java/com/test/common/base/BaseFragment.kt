package com.test.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.redmadrobot.lib.sd.LoadingStateDelegate
import com.test.common.alias.Inflate
import dev.chrisbanes.insetter.applyInsetter

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected var state: LoadingStateDelegate? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clear()
    }

    abstract fun initView()

    protected open fun initObs() {}

    protected fun applyStatusBarInsets(vararg views: View) =
        views.forEach { it.applyInsetter { type(statusBars = true) { margin() } } }

    protected fun applyNavigationBarInsets(vararg views: View) =
        views.forEach { it.applyInsetter { type(navigationBars = true) { margin() } } }

    protected fun initState(
        contentView: View,
        loadingView: View? = null,
        errorView: View? = null,
    ) {
        state = LoadingStateDelegate(contentView, loadingView, errorView)
    }

    protected fun showErrorDialog(error: Throwable, onRetry: () -> Unit, onCancel: () -> Unit) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(error.message ?: "Unknown error")
            .setPositiveButton("Retry") { _, _ -> onRetry() }
            .setNegativeButton("Cancel") { _, _ -> onCancel() }
            .setCancelable(false)
            .show()
    }

    protected fun initRecycler(
        recycler: RecyclerView,
        adapter: RecyclerView.Adapter<*>,
        layoutManager: RecyclerView.LayoutManager,
        hasFixedSize: Boolean = false,
    ) {
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
        recycler.setHasFixedSize(hasFixedSize)
    }

    @CallSuper
    protected fun clear() {
        state = null
        _binding = null
    }
}