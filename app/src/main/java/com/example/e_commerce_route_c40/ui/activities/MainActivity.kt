package com.example.e_commerce_route_c40.ui.activities

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseActivity
import com.example.e_commerce_route_c40.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linkNavHostWithBottomNavigation()
        binding.bottomNavigation.visibility = View.GONE

        linkNavHostWithBottomNavigation()
        handleKeyboardVisibility()

    }

    private fun linkNavHostWithBottomNavigation() {
        val navController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun handleKeyboardVisibility() {
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
                binding.bottomNavigation.visibility = View.VISIBLE
            }

        }
    }
}