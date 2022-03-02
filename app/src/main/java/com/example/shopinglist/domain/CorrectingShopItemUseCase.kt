package com.example.shopinglist.domain

class CorrectingShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun correctingUseCase(shopItem: ShopItem){
        shopListRepository.correctingUseCase(shopItem)
    }
}