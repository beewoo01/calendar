package cookmap.cookandroid.hw.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cookmap.cookandroid.hw.myapplication.databinding.DayItemBinding;
import cookmap.cookandroid.hw.myapplication.databinding.EmptyDayBinding;
import cookmap.cookandroid.hw.myapplication.viewmodel.CalendarViewModel;

public class CalendarAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int EMPTY_TYPE = 1;
    private final int DAY_TYPE = 2;

    private List<Object> mCalendarList;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    public CalendarAdapter(List<Object> calendarList) {
        mCalendarList = calendarList;
    }


    public void setCalendarList(List<Object> calendarList) {
        mCalendarList = calendarList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mCalendarList.get(position);
        if (item instanceof Long) {
            return HEADER_TYPE;
        } else if (item instanceof String) {
            return EMPTY_TYPE;
        } else {
            Log.d("클래스는?", String.valueOf(item.getClass()));
            return DAY_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*if (viewType == HEADER_TYPE){

        }*/
        if (viewType == EMPTY_TYPE) {
            EmptyDayBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day_empty, parent, false);
            return new EmptyViewHolder(binding);
        }

        DayItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day, parent, false); // 일짜 타입
        return new DayViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        viewHolder.itemView.setSelected(isItemSelected(position));

        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", String.valueOf(position));
                if (mSelectedItems.get(position,false))
                {
                    mSelectedItems.put(position,false);
                    v.setBackgroundColor(Color.WHITE);
                }
                else{
                    mSelectedItems.put(position,true);
                    v.setBackgroundColor(Color.BLUE);
                }
            }
        });*/

        int viewType = getItemViewType(position);
        if (viewType == EMPTY_TYPE) {
            EmptyViewHolder holder = (EmptyViewHolder) viewHolder;
            EmptyViewModel model = new EmptyViewModel();
            Object item = mCalendarList.get(position);
            if (item instanceof Calendar){
                model.setEmptyText((Calendar) item);
            }
            holder.setViewModel(model);
        } else if (viewType == DAY_TYPE) {
            DayViewHolder holder = (DayViewHolder) viewHolder;
            Object item = mCalendarList.get(position);
            CalendarViewModel model = new CalendarViewModel();
            if (item instanceof Calendar) {
                model.setCalendar((Calendar) item);
            }

            holder.setViewModel(model);
        }

    }

    private boolean isItemSelected(int position){
     return mSelectedItems.get(position, false);
    }

    @Override
    public int getItemCount() {
        if (mCalendarList != null) {
            return mCalendarList.size();
        }
        return 0;
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        private EmptyDayBinding binding;

        private EmptyViewHolder(@NonNull EmptyDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setViewModel(EmptyViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();

        }
    }

    private class DayViewHolder extends RecyclerView.ViewHolder {
        private DayItemBinding binding;

        private DayViewHolder(@NonNull DayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setViewModel(CalendarViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
}
