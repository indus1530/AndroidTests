package com.example.isd.videoss;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.isd.videoss.databinding.ActivityMainBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;

    /*DateTimeUtils obj = new DateTimeUtils();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

    try
    ParseException e catch(

    {
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse("10/10/2013 11:30:10");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse("13/10/2013 20:35:55");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        obj.printDifference(date1, date2);

    })

    {
        e.printStackTrace();
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);


    }


    //1 minute = 60 seconds
//1 hour = 60 x 60 = 3600
//1 day = 3600 x 24 = 86400
    public void printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        Toast.makeText(this,
                "%d days, %d hours, %d minutes, %d seconds%n" +
                        elapsedDays + " " + " " + elapsedHours + " " + " " + elapsedMinutes + " " + " " + elapsedSeconds, Toast.LENGTH_LONG).show();
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
            Toast.makeText(this, " OldDate " + bi.date1.getText().toString() + "\nNewDate " + bi.date2.getText().toString() + "\nDiffer " + differ + " Days", Toast.LENGTH_LONG).show();

            long years = differ / 365 >= 1 ? differ / 365 : differ / 365 >= 1 && (differ % 365) / 30 == 12 ? differ / 365 + 1 : 0;
            long months = (differ % 365) / 30 >= 1 && (differ % 365) / 30 <= 11 ? (differ % 365) / 30 : 0;
            //long days = months%1;
            long days = (differ % 365) % 30 >= 1 ? (differ % 365) % 30 : 0;


            //bi.differ.setTextColor(ContextCompat.getColor(this, R.color.white));
            //bi.differ.setBackgroundColor(ContextCompat.getColor(this, R.color.green4));


            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            try {
                printDifference(Objects.requireNonNull(format.parse(Objects.requireNonNull(bi.date1.getText()).toString())), Objects.requireNonNull(format.parse(Objects.requireNonNull(bi.date2.getText()).toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }

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
