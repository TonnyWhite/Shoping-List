package com.example.shopinglist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun correctingUseCase(shopItem: ShopItem)

    fun getItemById(id: Int): ShopItem

    fun getShopList(): List<ShopItem>

}