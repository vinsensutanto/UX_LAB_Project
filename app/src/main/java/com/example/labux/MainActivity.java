package com.example.labux;

import static com.example.labux.GlobalData.loggedInUsername;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username;
        if (loggedInUsername == null || loggedInUsername.isEmpty()) {
            username = "Vincent";
        } else {
            username = loggedInUsername;
        }

        ImageButton menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialog);
            View dialogView = getLayoutInflater().inflate(R.layout.popup_logout, null);
            builder.setView(dialogView);

            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.show();

            // Move to top right
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.gravity = Gravity.TOP | Gravity.END; // top right corner
                params.x = 100; // margin from right (pixels)
                params.y = 100; // margin from top (pixels)
                window.setAttributes(params);
            }

            ImageButton logoutButton = dialogView.findViewById(R.id.logoutButton);
            logoutButton.setOnClickListener(view -> {
                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            });
        });


        // 1. Get NavHostFragment AFTER setContentView
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            // 2. Setup BottomNavigationView
            BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            if (bottomNav != null) {
                NavigationUI.setupWithNavController(bottomNav, navController);
                bottomNav.setItemBackground(null);
            }

            // 3. Handle navigation intent
            String navigateTo = getIntent().getStringExtra("navigateTo");
            if ("list".equals(navigateTo)) {
                navController.navigate(R.id.listFragment);
                if (bottomNav != null) {
                    bottomNav.setSelectedItemId(R.id.listFragment);
                }
            }

            TextView welcomeText = findViewById(R.id.welcomeText);
            String styledText = "<font color='#F5C34A'><small>Welcome,</small></font><br>" +
                    "<font color='#9C1126'><big><big><i>" + username + "</i></big></big></font>";
            welcomeText.setText(Html.fromHtml(styledText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            Log.e("MainActivity", "NavHostFragment not found in layout");
        }
    }

}
