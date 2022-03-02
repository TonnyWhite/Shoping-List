package com.example.shopinglist.domain

class GetShopItemById(private val shopListRepository: ShopListRepository) {
   suspend fun getItemById(id: Int): ShopItem {
        return shopListRepository.getItemById(id)
    }
}