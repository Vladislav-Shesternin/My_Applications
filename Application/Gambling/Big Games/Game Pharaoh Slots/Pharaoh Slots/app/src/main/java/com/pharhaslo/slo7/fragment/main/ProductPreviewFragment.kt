package com.pharhaslo.slo7.fragment.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pharhaslo.slo7.MainActivity
import com.pharhaslo.slo7.MainViewModel
import com.pharhaslo.slo7.R
import com.pharhaslo.slo7.databinding.FragmentProductPreviewBinding
import com.pharhaslo.slo7.util.Resource
import com.pharhaslo.slo7.util.toast
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ProductPreviewFragment : Fragment(R.layout.fragment_product_preview) {
    private lateinit var binding: FragmentProductPreviewBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var previewAdapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        disableOnBackPressed()
        val visitorId =
            requireActivity().getSharedPreferences("save", 0).getString("visitor_id", "") ?: ""
        viewModel.getCasinos(visitorId)


        viewModel.products.observe(viewLifecycleOwner) { productList ->
            when (productList) {
                is Resource.Error -> {
                    if (productList.message == "We don't have offers for you") {
                        findNavController().navigate(R.id.navigation_game)
                    }
                    activity?.toast(productList.message)
                }
                is Resource.Loading -> {
                }


                is Resource.Success -> {
                    if (productList.data?.size == 1) {
                        val offer = productList.data[0]
                        openUrl(offer.productUrl)
                    } else {
                        previewAdapter = ProductAdapter()
                        previewAdapter.differ.submitList(productList.data)
                        binding.recyclerView.apply {
                            adapter = previewAdapter
                            changeRecyclerViewOrientation(requireActivity().resources.configuration.orientation)
                        }

                        previewAdapter.onItemClickListener = {
                            openUrl(it.productUrl)
                        }
                        previewAdapter.onPlayClickListener = {
                            openUrl(it.productUrl)
                        }
                    }
                }
            }
        }
    }

    private fun changeRecyclerViewOrientation(orientation: Int){
        when(orientation){
            Configuration.ORIENTATION_LANDSCAPE ->
                binding.recyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            else ->
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        changeRecyclerViewOrientation(newConfig.orientation)

    }
    private fun disableOnBackPressed() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            })
    }

    private fun openUrl(url : String?) = viewModel.siteUrl.postValue(url)

}