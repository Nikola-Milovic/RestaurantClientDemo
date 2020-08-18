package com.nikolam.order.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolam.order.R
import com.nikolam.order.databinding.OrderFragmentBinding
import com.nikolam.order.di.viewmodelModule
import com.nikolam.order.ui.adapter.OrderAdapter
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class OrderFragment : Fragment() {

    val moduleList = arrayListOf(viewmodelModule)

    private val loadModules by lazy {
        loadKoinModules(moduleList)
    }

    private fun injectFeatures() = loadModules


    lateinit var binding : OrderFragmentBinding

    lateinit var orderAdapter : OrderAdapter

    private val viewModel: OrderViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.order_fragment, container, false)
        binding.apply {

            val layoutMana=
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            orderAdapter = OrderAdapter()
            recyclerViews.apply{
                layoutManager = layoutMana
            }
            this.adapter = orderAdapter
            viewModel = viewModel
            lifecycleOwner = this@OrderFragment
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getOrderItems()
        observeData()
    }

    private fun observeData(){
        viewModel._orderItemsLiveData.observe(viewLifecycleOwner, Observer {
            orderAdapter.addOrderItems(it)
        })
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        injectFeatures()

    }

    override fun onDetach() {
        unloadKoinModules(moduleList)

        super.onDetach()
    }


}