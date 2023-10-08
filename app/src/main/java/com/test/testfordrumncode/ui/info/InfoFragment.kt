package com.test.testfordrumncode.ui.info

import android.text.method.LinkMovementMethod
import androidx.core.text.parseAsHtml
import androidx.core.view.isGone
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.test.common.base.BaseFragment
import com.test.common.flow.Resource
import com.test.common.intent.IntentExt.Companion.openUrl
import com.test.common.time.TimeExt.Companion.formatToHuman
import com.test.domain.model.InfoImageModel
import com.test.testfordrumncode.databinding.FragmentInfoBinding
import com.test.testfordrumncode.state.PositionState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {

    private val viewModel: InfoViewModel by viewModel()

    override fun initView() = with(binding) {
        initState(nestedScrollView, progress)
        applyNavigationBarInsets(llBottom)

        btnNext.setOnClickListener { viewModel.onNext() }
        btnPrev.setOnClickListener { viewModel.onPrev() }
    }

    override fun initObs(): Unit = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            status
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { updateUi(it) }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            position
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { updatePosition(it) }
        }
    }

    private fun updateUi(status: Resource<InfoImageModel>) {
        when (status) {
            is Resource.Error -> showErrorDialog(
                error = status.exception,
                onRetry = { viewModel.onRetry() },
                onCancel = { findNavController().popBackStack() }
            )

            is Resource.Loading -> state?.showLoading()
            is Resource.Success -> {
                updateInfo(status.data)
                state?.showContent()
            }
        }
    }

    private fun updatePosition(it: PositionState.Position) {
        binding.tvPage.text = "${it.position + 1}/${it.size}"
    }

    private fun updateInfo(data: InfoImageModel) = with(binding) {
        imageView.load(data.path)
        tvTitle.text = data.title
        tvDesc.apply {
            movementMethod = LinkMovementMethod.getInstance()
            text = data.description.parseAsHtml()
        }
        tvViews.text = data.views
        chipUsername.text = "@${data.ownerUsername}"
        tvAdditionals.text = "${data.dateUploaded.formatToHuman()} | ${data.ownerRealName}"

        btnOpenInBrowser.apply {
            isGone = data.urlOriginal.isEmpty()
            setOnClickListener { openUrl(data.urlOriginal) }
        }
    }

}