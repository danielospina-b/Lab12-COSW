package edu.eci.cosw.APIApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /** Called when the user taps the Send button */
    public void startMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        boolean validFields = checkFields(editTextEmail, editTextPassword);
        if (validFields) {
            startActivity(intent);
        }
    }

    /**
     * Returns true if editTextEmail contains @ and editTextPassword is not ""
     * @param editTextEmail email to check
     * @param editTextPassword password to check
     * @return true if both are valid
     */
    private boolean checkFields(EditText editTextEmail, EditText editTextPassword) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (email.contains("@")) {
            if (!password.isEmpty()) {
                return true;
            }
            else {
                editTextPassword.setError("Password cannot be empty");
                return false;
            }
        }
        else {
            editTextEmail.setError("Not a valid email");
            return false;
        }
    }
}
