package edu.cnm.deepdive.coffeeshop.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.coffeeshop.R;
import edu.cnm.deepdive.coffeeshop.databinding.ActivityMainBinding;
import java.util.Objects;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    SharedPreferences prefs = getSharedPreferences("coffee_shop_prefs", MODE_PRIVATE);
    boolean isDarkMode = prefs.getBoolean("key_dark_mode", false);

    if (isDarkMode) {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    } else {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    super.onCreate(savedInstanceState);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment);

    if (navHostFragment != null) {
      NavController navController = navHostFragment.getNavController();
      NavigationUI.setupWithNavController(binding.bottomNav, navController);
      navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
        int destinationId = destination.getId();
        boolean isTopLevelDestination = destinationId == R.id.loggedInFragment
            || destinationId == R.id.profilePageFragment
            || destinationId == R.id.settingsFragment;
        binding.bottomNav.setVisibility(isTopLevelDestination ? View.VISIBLE : View.GONE);
      });
    }
  }

}
