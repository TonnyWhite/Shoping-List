package com.example.shopinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopinglist.data.ShopListRepositoryImpl
import com.example.shopinglist.domain.CorrectingShopItemUseCase
import com.example.shopinglist.domain.DeleteShopItemUseCase
import com.example.shopinglist.domain.GetShopListUseCase
import com.example.shopinglist.domain.ShopItem

class MainViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val correctingShopItemUseCase =CorrectingShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()


    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        correctingShopItemUseCase.correctingUseCase(newItem)
        getShopList()
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }
}