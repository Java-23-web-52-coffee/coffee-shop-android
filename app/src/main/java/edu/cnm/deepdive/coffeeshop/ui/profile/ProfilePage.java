package edu.cnm.deepdive.coffeeshop.ui.profile;

import android.os.Bundle;
import android.util.Log;
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
import edu.cnm.deepdive.coffeeshop.viewmodel.ProfileViewModel;

@AndroidEntryPoint
public class ProfilePage extends Fragment {

  private static final String TAG = ProfilePage.class.getSimpleName();
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
    binding.rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.rvVisited.setLayoutManager(new LinearLayoutManager(requireContext()));
    favoritesAdapter = new ShopFeedAdapter((shop, isFavorite) -> {
      // TODO: 8/11/26 Invoke method in view model to change favorite status.
      Log.d(TAG, "%1$s clicked; favorite = %2$b".formatted(shop, isFavorite));
    });
    visitedAdapter = new ShopFeedAdapter((shop, isFavorite) -> {
      // TODO: 8/11/26 Invoke method in view model to change favorite status.
      Log.d(TAG, "%1$s clicked; favorite = %2$b".formatted(shop, isFavorite));
    });

    binding.rvFavorites.setAdapter(favoritesAdapter);
    binding.rvVisited.setAdapter(visitedAdapter);


  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

}