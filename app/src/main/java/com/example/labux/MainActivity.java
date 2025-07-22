package com.example.labux;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main.xml pakai NavHostFragment

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment); // ID NavHostFragment di layout
        navController = navHostFragment.getNavController();

        String navigateTo = getIntent().getStringExtra("navigateTo");
        if ("list".equals(navigateTo)) {
            navController.navigate(R.id.listFragment);
        }
    }
}
