package com.example.isd.videoss;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.isd.videoss.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat")
    public void BtnCheck() {

        bi.date1.getText().toString();
        bi.date2.getText().toString();

        long differ = getDateDiff(new SimpleDateFormat("dd-MM-yyyy"), bi.date1.getText().toString(), bi.date2.getText().toString());

        if (differ < 0) {
            Toast.makeText(this, " Date is invalid " + differ, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, " Date1 " + bi.date1.getText().toString() + ",\nDate2 " + bi.date2.getText().toString() + ",\nDiffer " + differ + " Days", Toast.LENGTH_LONG).show();

            long years = differ / 360 >= 1 ? differ / 360 : 0;
            long months = (differ % 360) / 30 >= 1 ? (differ % 360) / 30 : 0;
            //long days = months%1;
            long days = (differ % 360) % 30 >= 1 ? (differ % 360) % 30 : 0;


            //bi.differ.setTextColor(ContextCompat.getColor(this, R.color.white));
            //bi.differ.setBackgroundColor(ContextCompat.getColor(this, R.color.green4));

            bi.differ.setText(differ + " Total Days");
            bi.year.setText(years + " Years");
            bi.month.setText(months + " Months");
            bi.days.setText(days + " Days");

        }


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
