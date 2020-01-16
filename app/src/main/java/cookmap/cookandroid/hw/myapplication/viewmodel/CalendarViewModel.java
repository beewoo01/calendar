package cookmap.cookandroid.hw.myapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.Calendar;

import cookmap.cookandroid.hw.myapplication.CdLiveData;

public class CalendarViewModel extends ViewModel {

    public CdLiveData<Calendar> mCalendar = new CdLiveData<>();

    public void setCalendar(Calendar calendar){
        this.mCalendar.setValue(calendar);
    }
}
