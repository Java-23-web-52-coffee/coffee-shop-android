package edu.cnm.deepdive.coffeeshop.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.coffeeshop.R;
import edu.cnm.deepdive.coffeeshop.databinding.FragmentShopFeedBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    List<Map<String, String>> shopList = new ArrayList<>();

    Map<String, String> shop1 = new HashMap<>();
    shop1.put("name", "Espresso Express");
    shop1.put("description", "Local brews, cozy seating, and fresh pastries.");
    shopList.add(shop1);

    Map<String, String> shop2 = new HashMap<>();
    shop2.put("name", "Bean & Brew");
    shop2.put("description", "Artisanal roasts and organic teas.");
    shopList.add(shop2);

    Map<String, String> shop3 = new HashMap<>();
    shop3.put("name", "Roast & Co.");
    shop3.put("description", "Specialty espresso drinks and quiet study spots.");
    shopList.add(shop3);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

  class QuickShopAdapter extends RecyclerView.Adapter<QuickShopAdapter.ViewHolder> {

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
      if (holder.tvName != null) {
        holder.tvName.setText(shop.get("name"));
      }
      if (holder.tvDescription != null) {
        holder.tvDescription.setText(shop.get("description"));
      }
    }

    @Override
    public int getItemCount() {
      return shops.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

      TextView tvName;
      TextView tvDescription;

      public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.nav_graph);
        tvDescription = itemView.findViewById(R.id.nav_graph);
      }
    }
  }

}

