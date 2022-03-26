package com.example.shopinglist.domain

import javax.inject.Inject

class CorrectingShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {

    suspend fun correctingUseCase(shopItem: ShopItem){
        shopListRepository.correctingUseCase(shopItem)
    }
}