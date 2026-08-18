package edu.cnm.deepdive.coffeeshop.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.coffeeshop.R;

public class PreferenceBottomSheetDialogFragment extends BottomSheetDialogFragment {

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    // Inflate your preference dialog layout here (e.g., fragment_settings.xml or a custom preferences' layout)
    return inflater.inflate(R.layout.dialog_preferences, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // Wire up buttons, check boxes, or sliders inside the popup here!
  }
}
