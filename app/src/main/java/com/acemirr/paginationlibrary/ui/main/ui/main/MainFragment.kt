package com.acemirr.paginationlibrary.ui.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.paginationlibrary.R
import com.acemirr.paginationlibrary.ui.adapter.PagingRVAdapter

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val adapter = PagingRVAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel(activity?.application!!)::class.java)

        setupRecyclerView()
        listenData()
    }
    private fun setupRecyclerView(){
        val rv = view?.findViewById<RecyclerView>(R.id.rvMain)
        rv?.layoutManager = LinearLayoutManager(context)
        rv?.adapter = adapter
    }
    private fun listenData(){
        viewModel.pagingList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}
