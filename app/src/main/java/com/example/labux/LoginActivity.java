package com.example.labux;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private TextView errorMessageTextView;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        errorMessageTextView = findViewById(R.id.error_message);
        loginButton = findViewById(R.id.login_button);

        // Kosongkan pesan error saat awal
        errorMessageTextView.setText("");

        // Event klik tombol login
        loginButton.setOnClickListener(v -> validateLogin());
    }

    private void validateLogin() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty()) {
            errorMessageTextView.setText("Username must be filled in.");
        } else if (password.isEmpty()) {
            errorMessageTextView.setText("Password must be filled in.");
        } else if (username.length() < 5 || username.length() > 10) {
            errorMessageTextView.setText("Username must be 5-10 characters.");
        } else {
            // Valid: simpan username ke global dan arahkan ke halaman home
            GlobalData.loggedInUsername = username;
            errorMessageTextView.setText("");

            // Ganti ini dengan Activity home milikmu
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
