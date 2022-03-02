package com.example.shopinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun correctingUseCase(shopItem: ShopItem)

    suspend fun getItemById(id: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}