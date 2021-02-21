package com.iki.tworecyclerviewonefragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iki.tworecyclerviewonefragment.databinding.RecyclerviewlayoutBinding

class TheRecyclerViewAdapter(private var clickListener: OnItemRV1ClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listitem: List<CardModel> = ArrayList()
    private var listquantity: List<Int> = ArrayList()
    private lateinit var _binding: RecyclerviewlayoutBinding
    private val binding get() = _binding
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        layoutInflater = LayoutInflater.from(parent.context)
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.recyclerviewlayout, parent,false)
        return RecyclerViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecyclerViewHolder -> {
                holder.bind(listitem[position], listquantity[position])
                holder.onItemClicklistener(listitem[position],clickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return listitem.size
    }

    fun setData(arrayItems: List<CardModel>, arrayQuantity: List<Int>, context: Context){
        this.listitem = arrayItems
        this.listquantity = arrayQuantity
        this.context = context
        notifyDataSetChanged()
    }

    class RecyclerViewHolder constructor(private val context: Context, private var binding: RecyclerviewlayoutBinding):RecyclerView.ViewHolder(binding.root){

        @SuppressLint("ClickableViewAccessibility")
        fun onItemClicklistener(item: CardModel, action: OnItemRV1ClickListener){
            binding.pluscardView.setOnTouchListener(){ view: View, motionEvent: MotionEvent ->
                when(motionEvent.action){
                    MotionEvent.ACTION_DOWN -> binding.pluscardView.alpha = 0.8f
                    MotionEvent.ACTION_UP -> {
                        action.onItemRV1PlusClick(item, adapterPosition)
                        binding.pluscardView.alpha = 1f
                    }
                }
                true
            }
            binding.minuscardView.setOnTouchListener(){ view: View, motionEvent: MotionEvent ->
                when(motionEvent.action){
                    MotionEvent.ACTION_DOWN -> binding.minuscardView.alpha = 0.8f
                    MotionEvent.ACTION_UP -> {
                        action.onItemRV1MinusClick(item, adapterPosition)
                        binding.minuscardView.alpha = 1f
                    }
                }
                true
            }

        }

        @SuppressLint("ResourceType", "SetTextI18n")
        fun bind(item: CardModel, quantity: Int){
            //binding data and UI
            binding.fruittextView.text = "$" + item.cardPrice.toString() + "/" + item.cardType
            binding.quantitytextView.text = quantity.toString()
        }
    }
}

interface OnItemRV1ClickListener{
    fun onItemRV1PlusClick(item: CardModel, position: Int)
    fun onItemRV1MinusClick(item: CardModel, position: Int)
}