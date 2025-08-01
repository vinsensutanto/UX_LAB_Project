package com.example.labux.ui.detail;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.labux.R;
import com.example.labux.model.Anime;
import android.os.Handler;
public class DetailFragment extends Fragment {

    private static final String ARG_ANIME = "arg_anime";

    public static DetailFragment newInstance(Anime anime) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ANIME, anime);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Anime anime = (Anime) getArguments().getSerializable(ARG_ANIME);

        ImageView image = view.findViewById(R.id.detailImage);
        TextView title = view.findViewById(R.id.detailTitle);
        TextView titleBottom = view.findViewById(R.id.detailTitleBottom);
        TextView genre = view.findViewById(R.id.detailGenre);
        TextView description = view.findViewById(R.id.detailDescription);

        if (anime != null) {
            image.setImageResource(anime.getImageResource());
            title.setText(anime.getTitle());
            titleBottom.setText(anime.getTitle());
            genre.setText(anime.getGenre());
            description.setText(anime.getDescription());
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView reviewSuccessMessage = view.findViewById(R.id.reviewSuccessMessage);

        final boolean[] isPopupVisible = {false}; // simple 1-element array to allow final reference


        View topSpacer = view.findViewById(R.id.topSpacer);
        ScrollView detailScroll = view.findViewById(R.id.detailScroll);
        View backButton = view.findViewById(R.id.backButton);

        TextView successMessage = view.findViewById(R.id.reviewSuccessMessage);

        // Set top spacer to 3/4 of screen height
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        ViewGroup.LayoutParams spacerParams = topSpacer.getLayoutParams();
        spacerParams.height = (int) (screenHeight * 0.75); // 3/4 of screen
        topSpacer.setLayoutParams(spacerParams);

        // Scroll automatically to bottom of spacer
        detailScroll.post(() -> detailScroll.scrollTo(0, spacerParams.height));
        backButton.setOnClickListener(v -> requireActivity().finish());

        // REVIEW BUTTON BEHAVIOR
        ImageButton fabReview = view.findViewById(R.id.fabReview);
        FrameLayout reviewOverlay = view.findViewById(R.id.reviewOverlay);
        View popup = view.findViewById(R.id.reviewPopup);
        EditText reviewInput = view.findViewById(R.id.reviewInput);
        View submitBtn = view.findViewById(R.id.submitBtn);

        fabReview.setOnClickListener(v -> {
            reviewSuccessMessage.setVisibility(View.GONE);
            if (!isPopupVisible[0]) {
                fabReview.setImageResource(R.drawable.ic_write); // pencil icon
                reviewOverlay.setVisibility(View.VISIBLE);
                isPopupVisible[0] = true;
            } else {
                fabReview.setImageResource(R.drawable.ic_post); // back to post icon
                reviewOverlay.setVisibility(View.GONE);
                isPopupVisible[0] = false;
            }
        });

// Prevent clicking through the popup (no need to duplicate this)
        popup.setOnClickListener(v -> {});

// Clicking on dark background = close popup and reset icon
        reviewOverlay.setOnClickListener(v -> {
            reviewOverlay.setVisibility(View.GONE);
            fabReview.setImageResource(R.drawable.ic_post);
            isPopupVisible[0] = false;

            successMessage.setVisibility(View.GONE);
        });
        TextView reviewMessage = view.findViewById(R.id.reviewMessage);

        reviewInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reviewMessage.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        submitBtn.setOnClickListener(v -> {
            String review = reviewInput.getText().toString().trim();
            if (review.isEmpty()) {
                // Show error inside popup
                reviewMessage.setText("Review must be filled in!");
                reviewMessage.setTextColor(Color.parseColor("#F5C34A"));
                reviewMessage.setVisibility(View.VISIBLE);
            } else {
                // Show outside message (yellow), hide popup
                reviewOverlay.setVisibility(View.GONE);
                reviewInput.setText("");
                isPopupVisible[0] = false;
                fabReview.setImageResource(R.drawable.ic_post); // Reset icon

                reviewSuccessMessage.setText("Review submitted!");
                reviewSuccessMessage.setTextColor(Color.parseColor("#F5C34A")); // Ensure correct color
                reviewSuccessMessage.setVisibility(View.VISIBLE);

                // Auto-hide after 3s on UI thread
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    reviewSuccessMessage.setVisibility(View.GONE);
                }, 3000);
            }
        });

    }
}
