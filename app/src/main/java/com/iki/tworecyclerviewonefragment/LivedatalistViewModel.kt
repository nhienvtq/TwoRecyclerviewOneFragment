package com.iki.tworecyclerviewonefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LivedatalistViewModel(): ViewModel()  {
    private var _myList1 = MutableLiveData<ArrayList<CardModel>>()
    val myList1: LiveData<ArrayList<CardModel>> get() = _myList1
    var quantityList1: ArrayList<Int>

    private var _myList2 = MutableLiveData<ArrayList<CardModel>>()
    val myList2: LiveData<ArrayList<CardModel>> get() = _myList2
    var quantityList2: ArrayList<Int>

    init{
        _myList1.value = ArrayList(0)
        quantityList1 = ArrayList(0)
        _myList2.value = ArrayList(0)
        quantityList2 = ArrayList(0)

        initdataList1(1,"apple", 3.5F, myList1.value!!.size,0)
        initdataList1(2, "lemon", 2.5F,myList1.value!!.size, 0)
        initdataList1(3,"orange", 1.0F,myList1.value!!.size,0)
        initdataList1(4, "banana", 10F,myList1.value!!.size,0)

    }

    fun initdataList1(cardId: Int, cardType: String, cardPrice: Float, position: Int, quantity: Int){
        val myCardModel = CardModel(cardId, cardType, cardPrice,"RV1")
        if (position == _myList1.value?.size!!){
            _myList1.value?.add(myCardModel)
            quantityList1.add(quantity)
        }
        _myList1.postValue(_myList1.value)
    }

    fun adddataListRV1(cardModel: CardModel, position: Int){
        var i = 0
        while (i != _myList2.value!!.size){
            if (_myList2.value!![i].cardType == cardModel.cardType){
                break
            } else {
                i++
            }
        }

        val myCardModel = CardModel(cardModel.cardId , cardModel.cardType, cardModel.cardPrice,"RV2")
        if (i == _myList2.value!!.size){
            _myList2.value?.add(myCardModel)
            quantityList2.add(0)
        }
        quantityList2[i]++
        quantityList1[position]++

        _myList1.postValue(_myList1.value)
        _myList2.postValue(_myList2.value)
    }
    fun adddataListRV2(cardModel: CardModel, position: Int){
        var i = 0
        while (i != _myList1.value!!.size){
            if (_myList1.value!![i].cardType == cardModel.cardType){
                break
            } else {
                i++
            }
        }

        quantityList1[i]++
        quantityList2[position]++

        _myList1.postValue(_myList1.value)
        _myList2.postValue(_myList2.value)
    }

    fun removedataListRV1(cardModel: CardModel, position: Int){
        if (quantityList1[position] != 0){
            var i = 0
            while (i != _myList2.value!!.size){
                if (_myList2.value!![i].cardType == cardModel.cardType){
                    break
                } else {
                    i++
                }
            }
            quantityList1[position]--
            quantityList2[i] = quantityList1[position]
            if (quantityList1[position] == 0){
                quantityList2.removeAt(i)
                _myList2.value!!.remove(_myList2.value!![i])
            }
            _myList1.postValue(_myList1.value)
            _myList2.postValue(_myList2.value)
        }
    }

    fun removedataListRV2(cardModel: CardModel, position: Int){
        var i = 0
        while (i != _myList1.value!!.size){
            if (_myList1.value!![i].cardType == cardModel.cardType){
                break
            } else {
                i++
            }
        }
        quantityList1[i]--
        quantityList2[position] = quantityList1[i]
        if (quantityList1[i] == 0){
            quantityList2.removeAt(position)
            _myList2.value!!.remove(_myList2.value!![position])
        }
        _myList1.postValue(_myList1.value)
        _myList2.postValue(_myList2.value)
    }
}