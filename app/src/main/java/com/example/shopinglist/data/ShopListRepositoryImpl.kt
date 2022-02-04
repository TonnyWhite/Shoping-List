package com.example.shopinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglist.domain.ShopItem
import com.example.shopinglist.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {
    private var shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id)})

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0
    init {
        for (i in 0 until 10){
            val item = ShopItem("name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
       shopList.remove(shopItem)
        updateList()
    }

    override fun correctingUseCase(shopItem: ShopItem) {
        val oldElement = getItemById(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)

    }

    override fun getItemById(id: Int): ShopItem {
        return shopList.find {it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}