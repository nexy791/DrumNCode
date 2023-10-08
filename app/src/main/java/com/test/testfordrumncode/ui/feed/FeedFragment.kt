package com.test.testfordrumncode.ui.feed

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.test.common.base.BaseFragment
import com.test.common.flow.Resource
import com.test.domain.model.FeedImageModel
import com.test.testfordrumncode.databinding.FragmentFeedBinding
import com.test.testfordrumncode.nav.NavExt.Companion.navigateInfo
import com.test.testfordrumncode.ui.feed.adapter.FeedAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    private val viewModel: FeedViewModel by viewModel()

    private var mAdapter: FeedAdapter? = null

    override fun initView() = with(binding) {
        initState(rvFeed, progress)

        mAdapter = FeedAdapter {
            viewModel.onItemClicked(it)
            findNavController().navigateInfo(it)
        }

        initRecycler(
            rvFeed,
            mAdapter!!,
            GridLayoutManager(requireContext(), GRID_SPAN_COUNT),
        )
    }

    override fun initObs(): Unit = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            status
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { updateUi(it) }
        }
    }

    private fun updateUi(status: Resource<List<FeedImageModel>>) {
        when (status) {
            is Resource.Error -> showErrorDialog(
                error = status.exception,
                onRetry = { viewModel.onRetry() },
                onCancel = { findNavController().popBackStack() }
            )

            is Resource.Loading -> state?.showLoading()
            is Resource.Success -> {
                mAdapter?.submitList(status.data) {
                    state?.showContent()
                }
            }
        }
    }

    companion object {
        private const val GRID_SPAN_COUNT = 3
    }

}