package edu.eci.cosw.APIApp;

import androidx.appcompat.app.AppCompatActivity;
import edu.eci.cosw.APIApp.utils.APIClient;
import edu.eci.cosw.APIApp.utils.LoginService;
import edu.eci.cosw.APIApp.utils.LoginWrapper;
import edu.eci.cosw.APIApp.utils.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /** Called when the user taps the Login button */
    public void startMainActivity(View view) {
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        boolean validFields = checkFields(editTextEmail, editTextPassword);
        if (validFields) {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            LoginService apiService = APIClient.getRetrofitInstance().create(LoginService.class);
            Call<Token> tokenCall = apiService.attemptLogin(new LoginWrapper(email, password));
            tokenCall.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    if (response.isSuccessful()) {
                        Token token = response.body();
                        setAuthToken(token);
                        loginSuccessfulStartIntent();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login Failed...", Toast.LENGTH_SHORT).show();
                        try {
                            //TODO Use actual logging x2
                            System.out.println(response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    //TODO Use actual logging
                    System.out.println("Callback Error: " + t.getLocalizedMessage());
                    Toast.makeText(getApplicationContext(), "Something went wrong in our end...", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void loginSuccessfulStartIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setAuthToken(Token token) {
        SharedPreferences sharedPref =
                getSharedPreferences( getString( R.string.preference_file_key ), Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(LaunchActivity.TOKEN_KEY, token.getAccessToken());
        editor.commit();
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
