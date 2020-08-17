package com.nikolam.menu.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.nikolam.core.model.MenuItem
import com.nikolam.menu.R
import com.nikolam.menu.databinding.ItemDetailFragmentBinding
import org.koin.android.ext.android.inject
import timber.log.Timber

class ItemDetailFragment : Fragment() {

    lateinit var binding : ItemDetailFragmentBinding

    lateinit var itemID : String

    private val detailViewModel: ItemDetailViewModel by inject()

    private lateinit var menuItem : MenuItem

    val args : ItemDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.item_detail_fragment,container, false)

        val view = binding.root

        binding.apply {
            this.viewModel = detailViewModel
            lifecycleOwner = this@ItemDetailFragment
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        detailViewModel.fetchMenuItem(args.itemID)
    }

    private fun observeData(){

        binding.addButtonDetail.setOnClickListener {

            detailViewModel.orderMenuItem(menuItem)

        }



        detailViewModel._itemLiveData.observe(viewLifecycleOwner, Observer {
            menuItem = it
            binding.item = it
            itemID = it.itemID
            Timber.d(it.toString())
        })
    }

}