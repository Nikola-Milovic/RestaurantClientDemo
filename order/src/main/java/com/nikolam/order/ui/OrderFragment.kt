package com.nikolam.order.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolam.order.R
import com.nikolam.order.databinding.OrderFragmentBinding
import com.nikolam.order.di.dataModule
import com.nikolam.order.di.viewmodelModule
import com.nikolam.order.ui.adapter.OrderAdapter
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class OrderFragment : Fragment() {

    val moduleList = arrayListOf(viewmodelModule, dataModule)

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

        binding.orderButton.setOnClickListener {
            orderConfirmation()
        }

        return binding.root
    }


    private fun orderConfirmation(){
        val builder = AlertDialog.Builder(context).apply {
            setTitle("Da li ste sigurni da zelite da porucite?")

            val brojStola = EditText(context)
            brojStola.hint = "Uneti broj stola"
            brojStola.inputType = InputType.TYPE_CLASS_TEXT
            setView(brojStola)


            //TODO(Dodati napomene da mogu musteriju da pisu) i dodati neki Toast za uspesno porucivanje
            val napomena = EditText(context)
            napomena.hint = "Unesite napomenu neku za konobara ako je imate, ili ostavite prazno"
            napomena.inputType = InputType.TYPE_CLASS_TEXT

            setPositiveButton("Poruci", DialogInterface.OnClickListener { dialog, id ->
                viewModel.orderPlaced(brojStola.text.toString())
            }).show()
        }
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