package com.nikolam.menu.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolam.menu.R
import com.nikolam.menu.databinding.MenuFragmentBinding
import com.nikolam.menu.ui.menu.adapter.MenuAdapter
import org.koin.android.ext.android.inject

class MenuFragment : Fragment() {

    lateinit var binding : MenuFragmentBinding

    lateinit var menuAdapter : MenuAdapter

    private val viewModel: MenuViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.menu_fragment, container, false)
        binding.apply {

            val layoutMana=
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            menuAdapter = MenuAdapter()
            recyclerViews.apply{
                layoutManager = layoutMana
            }
            this.adapter = menuAdapter
            viewModel = viewModel
            lifecycleOwner = this@MenuFragment
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchMenuItems()
        observeData()
    }

    private fun observeData(){
        viewModel._menuItemsLiveData.observe(viewLifecycleOwner, Observer {
            menuAdapter.addMenuItems(it)
        })
    }

}