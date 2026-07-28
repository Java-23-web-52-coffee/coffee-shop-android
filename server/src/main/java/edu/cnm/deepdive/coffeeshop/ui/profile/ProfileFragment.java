package edu.cnm.deepdive.coffeeshop.ui.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import org.springframework.web.servlet.View;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

private FragmentProfileBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull android.view.LayoutInflater inflater,
      @Nullable android.view.ViewGroup container, @Nullable android.os.Bundle savedInstanceState) {
    binding = FragmentProfileBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  public void updateProfileView(Profile profile) {
    if (profile != null) {
      binding.displayName.setText(profile.getDisplayName());
      binding.emailAddress.setText(profile.getEmail());
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    binding = null;
  }

}
