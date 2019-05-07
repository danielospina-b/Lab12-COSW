package edu.eci.cosw.APIApp.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import edu.eci.cosw.APIApp.R;
import edu.eci.cosw.APIApp.storage.Storage;

public class LaunchActivity
        extends AppCompatActivity
{

    public static final String TOKEN_KEY = "TOKEN_KEY";

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        Storage storage = new Storage( this );
        if ( storage.containsToken() ) {
            Intent intentMain = new Intent(this, MainActivity.class);
            startActivity(intentMain);
        }else{
            Intent intentMain = new Intent(this, LoginActivity.class);
            startActivity(intentMain);
        }
    }
}
