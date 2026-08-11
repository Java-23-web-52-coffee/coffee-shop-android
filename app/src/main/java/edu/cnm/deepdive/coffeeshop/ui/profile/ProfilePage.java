package edu.cnm.deepdive.coffeeshop.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat.Type;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.coffeeshop.databinding.FragmentProfilePageBinding;
import edu.cnm.deepdive.coffeeshop.databinding.ItemFavoriteShopBinding;
import edu.cnm.deepdive.coffeeshop.model.domain.Shop;
import edu.cnm.deepdive.coffeeshop.ui.profile.ProfilePage.CoffeeShopAdapter.ShopViewHolder;
import edu.cnm.deepdive.coffeeshop.viewmodel.ProfileViewModel;
import java.util.ArrayList;
import java.util.List;

@AndroidEntryPoint
public class ProfilePage extends Fragment {

  private FragmentProfilePageBinding binding;
  private CoffeeShopAdapter favoritesAdapter;
  private CoffeeShopAdapter visitedAdapter;
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
    favoritesAdapter = new CoffeeShopAdapter();
    visitedAdapter = new CoffeeShopAdapter();

    binding.rvFavorites.setAdapter(favoritesAdapter);
    binding.rvVisited.setAdapter(visitedAdapter);


  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  class CoffeeShopAdapter extends Adapter<ShopViewHolder> {

    private List<Shop> shops = new ArrayList<>();

    public void setShops(List<Shop> shops) {
      this.shops = shops;
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
      Shop shop = shops.get(position);
    }

    @Override
    public int getItemCount() {
      return shops != null ? shops.size() : 0;
    }

    class ShopViewHolder extends ViewHolder {

      private final ItemFavoriteShopBinding itemBinding;

      public ShopViewHolder(@NonNull ItemFavoriteShopBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
      }

      public String getName() {
        return "";
      }
    }
  }
}