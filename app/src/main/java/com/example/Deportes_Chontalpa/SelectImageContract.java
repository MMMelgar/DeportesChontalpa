package com.example.Deportes_Chontalpa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SelectImageContract extends ActivityResultContract<Void, Uri> {
    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Void input) {
        return new Intent(Intent.ACTION_GET_CONTENT)
                .setType("image/*");
    }

    @Override
    public Uri parseResult(int resultCode, @Nullable Intent intent) {
        if (resultCode == Activity.RESULT_OK && intent != null) {
            return intent.getData();
        }
        return null;
    }
}
