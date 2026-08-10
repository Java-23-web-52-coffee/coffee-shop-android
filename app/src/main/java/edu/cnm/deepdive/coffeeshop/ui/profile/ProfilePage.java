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
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.coffeeshop.databinding.FragmentProfilePageBinding;
import edu.cnm.deepdive.coffeeshop.databinding.ItemFavoriteShopBinding;
import edu.cnm.deepdive.coffeeshop.model.domain.Shop;
import edu.cnm.deepdive.coffeeshop.viewmodel.ProfileViewModel;
import java.util.ArrayList;
import java.util.List;

@AndroidEntryPoint
public class ProfilePage extends Fragment {

  private FragmentProfilePageBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentProfilePageBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    CoffeeShopAdapter adapter = new CoffeeShopAdapter();
    ProfileViewModel viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    binding.rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
    binding.rvFavorites.setAdapter(adapter);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  private static class CoffeeShopAdapter extends RecyclerView.Adapter<CoffeeShopAdapter.ShopViewHolder> {

    private List<Shop> favoriteShops = new ArrayList<>();

    public void setFavoriteShops(List<Shop> shops) {
      this.favoriteShops = shops;
      notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      ItemFavoriteShopBinding itemBinding = ItemFavoriteShopBinding.inflate(
          LayoutInflater.from(parent.getContext()), parent, false
      );
      return new ShopViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
      Shop shop = favoriteShops.get(position);
      holder.bind(shop);
    }

    @Override
    public int getItemCount() {
      return favoriteShops != null ? favoriteShops.size() : 0;
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
      private final ItemFavoriteShopBinding itemBinding;

      public ShopViewHolder(@NonNull ItemFavoriteShopBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
      }

      public void bind(Shop shop) {
        itemBinding.textShopName.setText(shop.getName());
      }
    }
  }

}
