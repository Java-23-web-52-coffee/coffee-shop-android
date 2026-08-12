package edu.cnm.deepdive.coffeeshop.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.coffeeshop.adapter.ShopFeedAdapter;
import edu.cnm.deepdive.coffeeshop.viewmodel.ShopViewModel;
import edu.cnm.deepdive.coffeeshop.databinding.FragmentShopFeedBinding;

@AndroidEntryPoint
public class ShopFeedFragment extends Fragment {

  private FragmentShopFeedBinding binding;
  private ShopViewModel shopViewModel;
  private ShopFeedAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentShopFeedBinding.inflate(inflater, container, false);
    adapter = new ShopFeedAdapter((shop, isFavorite) -> shopViewModel.setFavorite(shop, isFavorite));
    binding.rvShopList.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
    shopViewModel.getShops()
        .observe(getViewLifecycleOwner(), adapter::setShops);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

}
