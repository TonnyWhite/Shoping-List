package com.example.shopinglist.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.R
import com.example.shopinglist.ShoppingApp
import com.example.shopinglist.databinding.ActivityMainBinding
import com.example.shopinglist.domain.ShopItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.RuntimeException
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {
    private lateinit var viewModel: MainViewModel

    private lateinit var shopListAdapter: ShopListAdapter

    @Inject
    lateinit var  viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as ShoppingApp).component
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)

        }

        binding.buttonAddShopItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = Intent(ShopItemActivity.newIntentAddItem(this))
                startActivity(intent)
            } else{
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }
    }

    fun onEditingFinished() {
        Toast.makeText(this, "Datasaved", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }


    private fun  isOnePaneMode(): Boolean{
        return binding.shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container,fragment)
            .addToBackStack(null)
            .commit()
    }


    private fun setUpRecyclerView() {
        with(binding.rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }

        setUpLongClickListener()
        setUpClickListener()
        setUpSwipeListener(binding.rvShopList)

    }

    private fun setUpSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }

        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setUpClickListener() {
        shopListAdapter.onShopItemClickListener = {
            if (isOnePaneMode()) {
                val intent = Intent(ShopItemActivity.newIntentEditItem(this, it.id))
                startActivity(intent)
            } else{
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setUpLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    override fun onEditingFinish() {
        Toast.makeText(this@MainActivity, "Data saved", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }


}