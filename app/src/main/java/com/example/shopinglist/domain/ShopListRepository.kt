package com.example.shopinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun correctingUseCase(shopItem: ShopItem)

    fun getItemById(id: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}