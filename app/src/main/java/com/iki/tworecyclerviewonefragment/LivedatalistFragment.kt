package com.iki.tworecyclerviewonefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iki.tworecyclerviewonefragment.databinding.FragmentLivedatalistBinding

class LivedatalistFragment : Fragment(), OnItemRV1ClickListener, OnItemRV2ClickListener {

    private lateinit var _binding: FragmentLivedatalistBinding
    private val binding get()  = _binding

    private lateinit var myViewmodel: LivedatalistViewModel

    private lateinit var myRVAdapter1: TheRecyclerViewAdapter

    private lateinit var myRVAdapter2: TheRecyclerViewAdapter2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_livedatalist, container, false)
        binding.lifecycleOwner = this


        myViewmodel = ViewModelProvider(this).get(LivedatalistViewModel::class.java)

        myRVAdapter1 = TheRecyclerViewAdapter(this)
        initRecyclerView(binding.RecyclerView1,myRVAdapter1, myViewmodel.myList1.value!!, myViewmodel.quantityList1)

        myRVAdapter2 = TheRecyclerViewAdapter2(this)
        initRecyclerView2(binding.RecyclerView2,myRVAdapter2,myViewmodel.myList2.value!!, myViewmodel.quantityList2)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myViewmodel.myList1.observe(viewLifecycleOwner, Observer {
            myRVAdapter1.notifyDataSetChanged()
        })

        myViewmodel.myList2.observe(viewLifecycleOwner, Observer {
            myRVAdapter2.notifyDataSetChanged()
        })

        myViewmodel.initdataList1(1,"apple", 3.5F, myViewmodel.myList1.value!!.size,0)
        myViewmodel.initdataList1(2, "lemon", 2.5F, myViewmodel.myList1.value!!.size, 0)
        myViewmodel.initdataList1(3,"orange", 1.0F, myViewmodel.myList1.value!!.size,0)
        myViewmodel.initdataList1(4, "banana", 10F, myViewmodel.myList1.value!!.size,0)
    }

    private fun initRecyclerView(view: RecyclerView, adapter: TheRecyclerViewAdapter, dataList: ArrayList<CardModel>, quantityList: ArrayList<Int> ){

        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(requireContext())
        adapter.setData(dataList, quantityList, requireContext())
    }

    private fun initRecyclerView2(view: RecyclerView, adapter: TheRecyclerViewAdapter2, dataList: ArrayList<CardModel>, quantityList: ArrayList<Int> ){
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(requireContext())
        adapter.setData(dataList, quantityList,  requireContext())
    }

    override fun onItemRV1PlusClick(item: CardModel, position: Int) {
        myViewmodel.adddataListRV1(item, position)
    }

    override fun onItemRV1MinusClick(item: CardModel, position: Int) {
        myViewmodel.removedataListRV1(item, position)
    }

    override fun onItemRV2PlusClick(item: CardModel, position: Int) {
        myViewmodel.adddataListRV2(item, position)
    }

    override fun onItemRV2MinusClick(item: CardModel, position: Int) {
        myViewmodel.removedataListRV2(item, position)
    }
}