package edu.cnm.deepdive.coffeeshop.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.coffeeshop.databinding.ItemShopCardBinding;
import edu.cnm.deepdive.coffeeshop.model.domain.Shop;
import java.util.ArrayList;
import java.util.List;

public class ShopFeedAdapter extends RecyclerView.Adapter<ShopFeedAdapter.ShopViewHolder> {

  public interface OnFavoriteClickListener {
    void onFavoriteClick(Shop shop, boolean isFavorite);
  }

  private List<Shop> shops = new ArrayList<>();
  private final OnFavoriteClickListener favoriteClickListener;

  public ShopFeedAdapter(OnFavoriteClickListener listener) {
    this.favoriteClickListener = listener;
  }

  public void setShops(List<Shop> shops) {
    this.shops = shops;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemShopCardBinding binding = ItemShopCardBinding.inflate(
        LayoutInflater.from(parent.getContext()), parent, false
    );
    return new ShopViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
    holder.bind(shops.get(position));
  }

  @Override
  public int getItemCount() {
    return shops != null ? shops.size() : 0;
  }

  class ShopViewHolder extends RecyclerView.ViewHolder {
    private final ItemShopCardBinding binding;
    private boolean isFavorited = false;

    public ShopViewHolder(@NonNull ItemShopCardBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    public void bind(Shop shop) {
      binding.tvShopTitle.setText(shop.getName() != null ? shop.getName() : "Coffee Shop");

      // Favorite button toggle
      binding.btnFavorite.setOnClickListener(v -> {
        isFavorited = !isFavorited;
        binding.btnFavorite.setImageResource(
            isFavorited ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off
        );
        if (favoriteClickListener != null) {
          favoriteClickListener.onFavoriteClick(shop, isFavorited);
        }
      });
    }
  }
}
