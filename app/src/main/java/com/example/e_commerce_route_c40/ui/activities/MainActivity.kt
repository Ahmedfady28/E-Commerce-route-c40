package com.example.e_commerce_route_c40.ui.activities

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseActivity
import com.example.e_commerce_route_c40.databinding.ActivityMainBinding
import com.example.e_commerce_route_c40.util.makeNavyBottomVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    override fun getLayoutId(): Int = R.layout.activity_main

    private fun initNavController() = findNavController(R.id.fragmentContainerView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.navController = initNavController()
        linkNavHostWithBottomNavigation()
        handleNavigationBottomVisibility()
    }

    private fun linkNavHostWithBottomNavigation() =
        binding.bottomNavigation.setupWithNavController(navController)


    private fun handleNavigationBottomVisibility() {
        // Listen for layout changes
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            binding.root.getWindowVisibleDisplayFrame(rect)

            // Get screen height and compare with visible window height
            val screenHeight = binding.root.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) {
                // If more than 15% of the screen height is covered, the keyboard is open
                binding.bottomNavigation.visibility = View.GONE
            } else {
                // Keyboard is closed
                this.onDestinationChange()
            }

        }
    }


    private fun onDestinationChange() {
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            when (nd.id) {
                R.id.loginScreen,
                R.id.splashScreen -> this.makeNavyBottomVisible(false)

                else -> this.makeNavyBottomVisible(true)
            }
        }
    }
}