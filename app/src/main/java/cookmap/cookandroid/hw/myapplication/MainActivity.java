package cookmap.cookandroid.hw.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import cookmap.cookandroid.hw.myapplication.databinding.CalendarListBinding;
import cookmap.cookandroid.hw.myapplication.viewmodel.CalendarListViewModel;

public class MainActivity extends AppCompatActivity {

    private CalendarListViewModel model;
    private CalendarListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = ViewModelProviders.of(this).get(CalendarListViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        observe();
        if (model != null){
            model.initCalendarList(0);
        }

    }

    private void observe(){
        model.mCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.calendarRecycler;
                CalendarAdapter adapter = (CalendarAdapter) view.getAdapter();
                if (adapter != null){
                    adapter.setCalendarList(objects);
                }else{
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
                    adapter = new CalendarAdapter(objects);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }


            }
        });
    }
}
