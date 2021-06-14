package com.cst2335.exercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent fromPrevious = getIntent();
        TextView text = findViewById(R.id.textView);
        Button callNumber = findViewById(R.id.button2);
         TextView phone = findViewById(R.id.editTextPhone);
        Button photo1 = findViewById(R.id.button3);
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        text.setText(emailAddress);
        int age = fromPrevious.getIntExtra("Age",0);
        String name = fromPrevious.getStringExtra("Name");
        String pCode = fromPrevious.getStringExtra("PostalCode");
        SharedPreferences prefs = getSharedPreferences("Second", Context.MODE_PRIVATE);
        callNumber.setOnClickListener(clk->{

            String number1 =phone.getText().toString();
            Intent call  = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel: "+number1));
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("Number",phone.getText().toString());
            editor.apply();
            startActivityForResult(call,5432);
        });
        prefs.getString("call","");
        String number = prefs.getString("Number","");
        phone.setText(number);

      photo1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick (View v){
              Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              startActivityForResult(cameraIntent,3456);
          }

      });

        File file = new File("filename");
      if (file.exists()){

      }


    }   @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageButton image = findViewById(R.id.imageButton);
        if (requestCode == 3456){
            if(resultCode == RESULT_OK){
                Bitmap thumbnail =data.getParcelableExtra("data");
                image.setImageBitmap(thumbnail);
            }
            FileOutputStream fOut = null;
            try{
                fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                Bitmap mBitmap = null;
                mBitmap.compress(Bitmap.CompressFormat.PNG,100,fOut);
                fOut.flush();
                fOut.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}