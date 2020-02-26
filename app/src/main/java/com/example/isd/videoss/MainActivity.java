package com.example.isd.videoss;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.isd.videoss.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    ActivityMainBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);


    }


    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void BtnCheck() {

        bi.date1.getText().toString();
        bi.date2.getText().toString();

        bi.check.setText((int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), bi.date1.getText().toString(), bi.date2.getText().toString()));


        /*if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
//                if (bi.RS16a.isChecked()) {
//                    finish();
//                    startActivity(new Intent(this, EndingActivity.class));
//                } else {
//                    Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
//                }
            }
        }*/
    }


}
