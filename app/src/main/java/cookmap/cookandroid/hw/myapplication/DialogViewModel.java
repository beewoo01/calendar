package cookmap.cookandroid.hw.myapplication;

import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class DialogViewModel extends ViewModel {
    public CdLiveData<Calendar> mCalendar = new CdLiveData<>();

    public void setCalendar(Calendar calendar){
        this.mCalendar.setValue(calendar);
    }
}
