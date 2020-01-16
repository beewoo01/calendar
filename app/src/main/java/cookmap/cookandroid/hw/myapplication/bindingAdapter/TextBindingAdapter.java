package cookmap.cookandroid.hw.myapplication.bindingAdapter;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.util.Calendar;
import java.util.GregorianCalendar;

import cookmap.cookandroid.hw.myapplication.DateFormat;

public class TextBindingAdapter {

    @BindingAdapter({"setCalendarHeaderText"})
    public static void setCalendarHeaderText(TextView view, Long date){
        try{
            if (date != null){
                view.setText(DateFormat.getDate(date, DateFormat.CALENDAR_HEADER_FORMAT));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @BindingAdapter({"setDayText"})
    public static void setDayText(TextView view, Calendar calendar){
        try {
            if (calendar != null){
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                String color;
                if (gregorianCalendar.get(Calendar.DAY_OF_WEEK) == 1){ color = "#FF0000"; }// 일요일
                else if (gregorianCalendar.get(Calendar.DAY_OF_WEEK) == 7){ color = "#008FFF"; } // 토요일
                else { color = "#000000"; } // 평일
                //

                //
                Log.d("textbinding",DateFormat.getDate(calendar.getTimeInMillis(),DateFormat.CALENDAR_HEADER_FORMAT));
                view.setText(DateFormat.getDate(gregorianCalendar.getTimeInMillis(), DateFormat.DAY_FORMAT));
                view.setTextColor(Color.parseColor(color));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setEmptyText"})
    public static void setEmptyText(TextView view, String date){
        try {
            if (date != null){
                view.setText(DateFormat.getDate(Long.parseLong(date), DateFormat.EMPTY_FORMAT));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
