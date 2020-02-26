package cookmap.cookandroid.hw.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

import cookmap.cookandroid.hw.myapplication.databinding.DialogBinding;
import cookmap.cookandroid.hw.myapplication.viewmodel.CalendarListViewModel;

public class Custom_Dialog {

    private Context context;
    private int picked_Y, picked_M, picked_D;
    private NumberPicker picker_year, picker_month, picker_date;
    GregorianCalendar gregorianCalendar;

    public Custom_Dialog(Context context){
        this.context = context;
    }

    public void callFunction(){

        final Dialog dlg = new Dialog(context);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dlg.setContentView(R.layout.dialog);


        TextView okbutton = (TextView) dlg.findViewById(R.id.btn_ok);
        TextView clbutton = (TextView) dlg.findViewById(R.id.btn_cancle);
        picker_year = (NumberPicker) dlg.findViewById(R.id.picker_year);
        picker_month = (NumberPicker) dlg.findViewById(R.id.picker_month);
        picker_date = (NumberPicker) dlg.findViewById(R.id.picker_date);

        setPicker();
        dlg.show();
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picked_Y = picker_year.getValue();
                picked_M = picker_month.getValue();
                picked_D = picker_date.getValue();
                gregorianCalendar = new GregorianCalendar(picked_Y, picked_M, picked_D);


                Toast.makeText(context, "ok: " + picked_Y+"," + picked_M+ ", " + picked_D, Toast.LENGTH_SHORT).show();
                gregorianCalendar = new GregorianCalendar(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), 1, 0, 0);
                CalendarListViewModel clv = new CalendarListViewModel();
                gregorianCalendar = new GregorianCalendar(gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), 1, 0, 0);
                clv.setCalendarList(gregorianCalendar);
                dlg.dismiss();

            }
        });
        clbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "cancle Button", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
    }

    private void setPicker(){
        picker_year.setMaxValue(9999);
        picker_year.setMinValue(1900);
        picker_month.setMaxValue(12);
        picker_month.setMinValue(01);
        picker_date.setMaxValue(31);
        picker_date.setMinValue(01);
        picker_year.setValue(2020);
        picker_month.setValue(02);
        picker_date.setValue(26);
    }

}
