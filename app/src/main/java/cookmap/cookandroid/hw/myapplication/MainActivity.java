package cookmap.cookandroid.hw.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import cookmap.cookandroid.hw.myapplication.databinding.CalendarListBinding;
import cookmap.cookandroid.hw.myapplication.viewmodel.CalendarListViewModel;

public class MainActivity extends AppCompatActivity {

    private CalendarListViewModel model;
    private CalendarListBinding binding;
    private GregorianCalendar gregorianCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        model = ViewModelProviders.of(this).get(CalendarListViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);
        observe();
        binding.month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("햐햐햐햐햫햐햐햐햐", "monthmonth");
                Custom_Dialog custom_dialog = new Custom_Dialog(MainActivity.this);
                custom_dialog.callFunction();

            }
        });
        if (model != null) {
            model.initCalendarList(0);
        }


    }
    private void observe() {
        model.mCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.calendarRecycler;
                CalendarAdapter adapter = (CalendarAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setCalendarList(objects);
                } else {
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
                    adapter = new CalendarAdapter(objects);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }


            }
        });

    }
}
