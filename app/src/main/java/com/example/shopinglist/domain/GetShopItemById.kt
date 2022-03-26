package com.example.shopinglist.domain

import javax.inject.Inject

class GetShopItemById @Inject constructor(private val shopListRepository: ShopListRepository) {
   suspend fun getItemById(id: Int): ShopItem {
        return shopListRepository.getItemById(id)
    }
}