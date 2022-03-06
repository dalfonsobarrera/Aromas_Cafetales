package com.fmauriciors.projectaromascafetales.ui.listproducts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmauriciors.projectaromascafetales.databinding.FragmentListProductsBinding
import com.fmauriciors.projectaromascafetales.ui.local.Product

class ListProductsFragment : Fragment() {


    private lateinit var listProductsBinding: FragmentListProductsBinding
    private lateinit var listProductsViewModel: ListProductsViewModel
    private lateinit var productsAdapter: ProductsAdapter
    private var productsList: ArrayList<Product> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listProductsBinding = FragmentListProductsBinding.inflate(inflater, container, false)
        listProductsViewModel = ViewModelProvider(this)[ListProductsViewModel::class.java]
        return listProductsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsList.add(Product(
            1,"cafe","50","caferico"
        ))



       listProductsViewModel.loadProducts()

        productsAdapter = ProductsAdapter(productsList)

        listProductsBinding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListProductsFragment.requireContext())
            adapter = productsAdapter
            setHasFixedSize(false)
        }
        listProductsBinding.newProductButton.setOnClickListener {
            findNavController().navigate(ListProductsFragmentDirections.actionListProductsFragmentToNewProductFragment())
        }
    }

    private fun onLoadProductsDoneSubscribe(productsListLoaded: ArrayList<Product>) {
        productsList = productsListLoaded
        productsAdapter.appendItems(productsList)
        Log.d("Hello","data")

    }

}

