package com.example.shopinglist.domain

class CorrectingShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun correctingUseCase(shopItem: ShopItem){
        shopListRepository.correctingUseCase(shopItem)
    }
}