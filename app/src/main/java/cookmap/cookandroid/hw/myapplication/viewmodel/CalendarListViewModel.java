package cookmap.cookandroid.hw.myapplication.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.DatabaseUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import cookmap.cookandroid.hw.myapplication.CdLiveData;
import cookmap.cookandroid.hw.myapplication.Custom_Dialog;
import cookmap.cookandroid.hw.myapplication.DateFormat;
import cookmap.cookandroid.hw.myapplication.Keys;
import cookmap.cookandroid.hw.myapplication.MainActivity;

public class CalendarListViewModel extends ViewModel {

    //private long mCurrentTime;
    public CdLiveData<String> month = new CdLiveData<>();
    public CdLiveData<ArrayList<Object>> mCalendarList = new CdLiveData<>(new ArrayList<>());
    public int mCenterPosition;
    public int now_position = 0;

    public void setTitle(long time) {
        //mCurrentTime = time;
        month.setValue(DateFormat.getDate(time, DateFormat.CALENDAR_HEADER_FORMAT));
    }


    public void initCalendarList(int month) {
        GregorianCalendar cal = new GregorianCalendar();
        GregorianCalendar calendar;
        Log.d(" position?? : ", String.valueOf(now_position));

        switch (month) {
            case 0:
                //오늘
                setCalendarList(cal);
                Log.d(" 0 날짜는?? : ", String.valueOf(cal.get(Calendar.MONTH)));
                break;
            case 1:
                //다음 달
                now_position++;
                calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + now_position, 1, 0, 0);
                Log.d(" 1 날짜는?? : ", String.valueOf(calendar.get(Calendar.MONTH)));
                setCalendarList(calendar);
                break;
            case 2:
                //이전 달
                now_position--;
                calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + now_position, 1, 0, 0);
                Log.d(" 2 날짜는?? : ", String.valueOf(calendar.get(Calendar.MONTH)));
                setCalendarList(calendar);
                break;
        }
    }


    public void setCalendarList(GregorianCalendar cal) {
        setTitle(cal.getTimeInMillis());
        ArrayList<Object> calendarList = new ArrayList<>();

        try {

            GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (dayOfWeek > 0) {
                for (int i = 1; i <= dayOfWeek; i++) {
                    calendarList.add(Keys.EMPTY);

                }
            }

            for (int i = 1; i <= max; i++) {
                calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i));

            }
            if (calendarList.size() < 43) {
                int addEmptysize = 42 - calendarList.size();
                for (int i = 0; i < addEmptysize; i++) {
                    calendarList.add(Keys.EMPTY);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        mCalendarList.setValue(calendarList);
    }

}
