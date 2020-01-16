package cookmap.cookandroid.hw.myapplication;

import androidx.lifecycle.MutableLiveData;

public class CdLiveData<T> extends MutableLiveData<T> {

    public CdLiveData(){

    }

    public CdLiveData(T value){
        setValue(value);
    }
}
