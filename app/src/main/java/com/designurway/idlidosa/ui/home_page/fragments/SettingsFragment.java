package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.MobileVerificationActivity;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentSettingsBinding;
import com.designurway.idlidosa.ui.auth.AuthActivity;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.designurway.idlidosa.BuildConfig.VERSION_NAME;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    NavDirections action;

    private static final String TAG = "SettingsFragment";
    ConstraintLayout consHistory, consTrack, constPickReward, consRefer, consEdtAd, consSupport, consSigout;
    String orderId = "none";
    String currentAppVersion, latestAppVersion;
    CardView CardUpdate;
    Context context;

    /* @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

     }
 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        CardUpdate = binding.CardUpdate;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        consHistory = binding.consHistory;
        consTrack = binding.consTrack;
        constPickReward = binding.constPickReward;
        consRefer = binding.consRefer;
        consEdtAd = binding.consEdtAd;
        consSupport = binding.consSupport;
        consSigout = binding.consSigout;

        context = view.getContext();

        consSigout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logout();
            }
        });

        consHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToOrderHistoryFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        consEdtAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToAddressBookFragment("00", "setting", orderId);
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        consTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToTrackOrderListFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        consSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToSupportFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        constPickReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToReceiveComboFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        consRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToReferFriendFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });


        getLatestAppUpdate();

    }

    public void logout() {

        PreferenceManager.checkUserLoggedIn(false);
        PreferenceManager.clearCustomer();
        Intent intent = new Intent(getContext(), AuthActivity.class);
        startActivity(intent);
        getActivity().finish();


    }

    private void getLatestAppUpdate() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // do In Background
                try {
                    latestAppVersion = Jsoup
                            .connect("https://play.google.com/store/apps/details?id=com.designurway.idlidosa")
                            .timeout(30000)
                            .get()
                            .select("div.hAyfc:nth-child(4)>" + "span:nth-child(2) > div:nth-child(1)" + "> span:nth-child(1)")
                            .first()
                            .ownText();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (AndroidUtils.isNetworkAvailable(context)) {


                    if (getActivity() != null) {

                        //postExecute
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                // do onPostExecute stuff

                                //Getting Current Version
                                currentAppVersion = VERSION_NAME;

                                if (currentAppVersion != null && latestAppVersion != null) {
                                    //Version convert to float
                                    float cVersion = Float.parseFloat(currentAppVersion);
                                    float lVersion = Float.parseFloat(latestAppVersion);

                                    if (lVersion > cVersion) {

//                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,
//                                                R.style.AppTheme));
                                        CardUpdate.setVisibility(View.VISIBLE);
//                                        alertDialogBuilder.setTitle(context.getString(R.string.youAreNotUpdatedTitle));
//                                        alertDialogBuilder.setMessage(context.getString(R.string.youAreNotUpdatedMessage) + " " + latestAppVersion + context.getString(R.string.youAreNotUpdatedMessage1));
//                                        alertDialogBuilder.setCancelable(false);
//                                        alertDialogBuilder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int id) {
                                        CardUpdate.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));

                                            }
                                        });
//                                                dialog.cancel();

//                                            }
//                                        });
//                                        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.cancel();
//                                            }
//                                        });
//                                        alertDialogBuilder.show();
                                    } else {

                                    }
                                }
                            }
                        });
                    }
                } else {
                    Log.d(TAG, "No internet");
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        context=getActivity().getApplicationContext();
    }
}