package edu.cnm.deepdive.coffeeshop.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.coffeeshop.databinding.FragmentProfileBinding;

@AndroidEntryPoint
public class ProfilePage extends Fragment {

  private FragmentProfileBinding binding;
  private CoffeeShopAdapter adapter;
  private ProfileViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentProfileBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    setupFavoritesList();
  }

  private void setupFavoritesList() {
    adapter = new CoffeeShopAdapter();
    binding.email.setAdapter(adapter);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  private class ProfileViewModel {

  }

  private class CoffeeShopAdapter {

  }
}
