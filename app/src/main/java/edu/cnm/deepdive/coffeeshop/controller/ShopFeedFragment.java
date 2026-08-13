package edu.cnm.deepdive.coffeeshop.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.coffeeshop.R;
import edu.cnm.deepdive.coffeeshop.adapter.ShopFeedAdapter;
import edu.cnm.deepdive.coffeeshop.viewmodel.ShopViewModel;
import edu.cnm.deepdive.coffeeshop.databinding.FragmentShopFeedBinding;
import java.util.List;
import java.util.Map;

@AndroidEntryPoint
public class ShopFeedFragment extends Fragment {

  private static final String TAG = ShopFeedFragment.class.getSimpleName();

  private FragmentShopFeedBinding binding;
  private ShopViewModel shopViewModel;
  private ShopFeedAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentShopFeedBinding.inflate(inflater, container, false);
    adapter = new ShopFeedAdapter((shop, isFavorite) -> {
      // TODO: 8/11/26 Invoke method in view model to change favorite status.
      Log.d(TAG, "%1$s clicked; favorite = %2$b".formatted(shop, isFavorite));
    });
    binding.rvShopList.setAdapter(adapter);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
    shopViewModel.getShops()
        .observe(getViewLifecycleOwner(), adapter::setShops);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  // =========================================================================
  // Inner Adapter Class (nested safely inside ShopFeedFragment)
  // =========================================================================
  private static class QuickShopAdapter extends RecyclerView.Adapter<QuickShopAdapter.ViewHolder> {

    private final List<Map<String, String>> shops;

    public QuickShopAdapter(List<Map<String, String>> shops) {
      this.shops = shops;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_shop_card, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Map<String, String> shop = shops.get(position);

      if (holder.tvName != null && shop.containsKey("name")) {
        holder.tvName.setText(shop.get("name"));
      }
      if (holder.tvDescription != null && shop.containsKey("description")) {
        holder.tvDescription.setText(shop.get("description"));

        if (holder.imgShop != null) {
          holder.imgShop.setImageResource(R.drawable.coffee);
        }
      }
    }

    @Override
    public int getItemCount() {
      return shops != null ? shops.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

      TextView tvName;
      TextView tvDescription;
      ImageView imgShop;

      @SuppressLint("WrongViewCast")
      public ViewHolder(@NonNull View itemView) {
        super(itemView);
        // Make sure these IDs match what is inside item_shop_card.xml
        tvName = itemView.findViewById(R.id.ivShopImage);
        tvDescription = itemView.findViewById(R.id.tvShopDescription);
        imgShop = itemView.findViewById(R.id.image_shop);
      }
    }
  }


}
