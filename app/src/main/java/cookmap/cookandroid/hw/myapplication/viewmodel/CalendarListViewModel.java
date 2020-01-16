package cookmap.cookandroid.hw.myapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import cookmap.cookandroid.hw.myapplication.CdLiveData;
import cookmap.cookandroid.hw.myapplication.DateFormat;
import cookmap.cookandroid.hw.myapplication.Keys;

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
        switch (month){
            case 0 :
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
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int date = cal.get(Calendar.DATE);
            int dayOfWeek1 = cal.get(Calendar.DAY_OF_WEEK);

            Log.d("year", String.valueOf(year));
            Log.d("month", String.valueOf(month));
            Log.d("date", String.valueOf(date));
            Log.d("dayOfWeek", String.valueOf(dayOfWeek1));

            GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
            Log.d("cyear", String.valueOf(calendar.get(Calendar.YEAR)));
            Log.d("cmonth", String.valueOf(calendar.get(Calendar.MONTH)));
            Log.d("cmax", String.valueOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));

            //calendarList.add(calendar.getTimeInMillis());
            Log.d("첫번째", String.valueOf(calendar.getTimeInMillis()));

            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) -1;
            int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int whatmonth = calendar.get(Calendar.MONTH);
            Log.d("두두", String.valueOf(dayOfWeek));
            Log.d("두두", String.valueOf(max));
            Log.d("두두", String.valueOf(whatmonth));
            Log.d("지금!: " , String.valueOf(calendar));
            if (dayOfWeek > 0){
                /*GregorianCalendar emptyCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)-1, 1, 0, 0, 0);
                int eyear = emptyCalendar.get(Calendar.YEAR);
                int emonth = emptyCalendar.get(Calendar.MONTH);
                int emax = emptyCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                int edayOfWeek = emptyCalendar.get(Calendar.DAY_OF_WEEK) -1;
                Log.d("eyear", String.valueOf(eyear));
                Log.d("emonth", String.valueOf(emonth));
                Log.d("emax", String.valueOf(emax));*/
                for (int i = 1; i <= dayOfWeek; i++){
                    calendarList.add(Keys.EMPTY);
                    //calendarList.add(new GregorianCalendar((emptyCalendar.get(Calendar.YEAR)), emptyCalendar.get(Calendar.MONTH), emax-dayOfWeek+i));

                }
            }


            for (int i = 1; i <= max; i++){
                Log.d("하이1", String.valueOf(calendar.get(Calendar.YEAR)));
                Log.d("하이2", String.valueOf(calendar.get(Calendar.MONTH)));
                Log.d("하이3", String.valueOf(i));
                calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i));
                Log.d("두번째 for문",i +": "+ String.valueOf(calendar.get(Calendar.YEAR))+"," +
                        String.valueOf(calendar.get(Calendar.MONTH)));
            }
            Log.d("사이즈", String.valueOf(calendarList.size()));
            /*if (calendarList.size() < 43){
                int addEmptysize = 42 - calendarList.size();
                for (int i = 0; i < addEmptysize; i++){
                    calendarList.add(Keys.EMPTY);
                }
            }*/
            Log.d("사이즈", String.valueOf(calendarList.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        mCalendarList.setValue(calendarList);

    }

}
