package cookmap.cookandroid.hw.myapplication;

import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class EmptyViewModel extends ViewModel {

    public CdLiveData<Calendar> setEmptyText = new CdLiveData<>();

    public void setEmptyText(Calendar calendar){
        this.setEmptyText.setValue(calendar);
    }
}
