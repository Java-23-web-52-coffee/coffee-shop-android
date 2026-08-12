package edu.cnm.deepdive.coffeeshop.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.coffeeshop.adapter.ShopFeedAdapter;
import edu.cnm.deepdive.coffeeshop.databinding.FragmentProfilePageBinding;
import edu.cnm.deepdive.coffeeshop.model.domain.Profile;
import edu.cnm.deepdive.coffeeshop.model.domain.Visit;
import edu.cnm.deepdive.coffeeshop.viewmodel.ProfileViewModel;

@AndroidEntryPoint
public class ProfilePage extends Fragment {

  private FragmentProfilePageBinding binding;
  private ShopFeedAdapter favoritesAdapter;
  private ShopFeedAdapter visitedAdapter;
  private ProfileViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentProfilePageBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    binding.rvFavorites.setLayoutManager(
        new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    );    binding.rvVisited.setLayoutManager(new LinearLayoutManager(requireContext()));
    favoritesAdapter = new ShopFeedAdapter((shop, isFavorite) -> {
      viewModel.setFavorite(shop, isFavorite);
    });
    visitedAdapter = new ShopFeedAdapter((shop, isFavorite) -> {
      viewModel.setFavorite(shop, isFavorite);
    });

    binding.rvFavorites.setAdapter(favoritesAdapter);
    binding.rvVisited.setAdapter(visitedAdapter);
    viewModel.getProfile().observe(getViewLifecycleOwner(), (profile) -> {
      binding.textName.setText(profile.getName());
      favoritesAdapter.setShops(profile.getFavorites());
      visitedAdapter.setShops(
          profile.getVisits().stream().map(Visit::getShop).toList());
      viewModel.getProfile().observe(getViewLifecycleOwner(), this::adapter);
    });};

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  private void adapter(Profile profile) {
    if (profile != null) {
      profile.getName();
      binding.textName.setText(profile.getName());
      profile.getFavorites();
      favoritesAdapter.setShops(profile.getFavorites());
      profile.getVisits();
      visitedAdapter.setShops(
          profile.getVisits().stream()
              .map(Visit::getShop)
              .toList()
      );
    }
  }
}
