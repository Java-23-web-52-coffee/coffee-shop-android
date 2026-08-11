package edu.cnm.deepdive.coffeeshop.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import edu.cnm.deepdive.coffeeshop.databinding.FragmentShopFeedBinding;

public class ShopFeedFragment extends Fragment {

  private FragmentShopFeedBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentShopFeedBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    binding.rvShopList.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.rvShopList.setAdapter(new ShopFeedAdapter((shop, isFavorite) -> {}));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }
}
