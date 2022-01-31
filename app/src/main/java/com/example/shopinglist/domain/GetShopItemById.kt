package com.example.shopinglist.domain

class GetShopItemById(private val shopListRepository: ShopListRepository) {
    fun getItemById(id: Int): ShopItem {
        return shopListRepository.getItemById(id)
    }
}