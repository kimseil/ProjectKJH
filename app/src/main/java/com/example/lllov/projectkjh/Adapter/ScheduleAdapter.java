package com.example.lllov.projectkjh.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lllov.projectkjh.AddPlaceActivity;
import com.example.lllov.projectkjh.ApiClient;
import com.example.lllov.projectkjh.ApiService;
import com.example.lllov.projectkjh.BaseActivity;
import com.example.lllov.projectkjh.MemoActivity;
import com.example.lllov.projectkjh.DTO.ResponseScheduleInfoVO;
import com.example.lllov.projectkjh.R;
import com.example.lllov.projectkjh.ScheduleActivity;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 일정 추가 화면의 일정 목록 어댑터
 * 선택한 날짜만큼 리스트가 생성됨
 * 원하는 날짜에 장소, 메모를 추가하는 버튼 있음
 *=================================================================================================*/
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private ArrayList<Long> data;
    private BaseActivity context;

    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.KOREA);
    private SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.KOREA);

    private String[] dayOfWeek = {"일", "월", "화", "수", "목", "금", "토"};

    public ScheduleAdapter(ArrayList<Long> data, BaseActivity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_schedule_row, parent, false);

        return new ViewHolder(view);
    }

    public long getItem(int position) {
        if(data == null) {
            return 0;
        }
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final long data = getItem(position);

        //데이터로부터 각 아이템에 해당하는 날짜를 넣어줌.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data);

        //해당 일정이 여행 며칠째인지 ex) day1, day2, day3, ...
        viewHolder.tvDayNumber.setText("day" + (position + 1));
        //해당 일정의 실제 날짜
        viewHolder.tvDay.setText(monthFormat.format(calendar.getTime()) + "." + dayFormat.format(calendar.getTime()) + "/" + dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1]);

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<ResponseScheduleInfoVO>> call = service.getScheduleInfoList(((ScheduleActivity)context).schedule.getId(), data);

        call.enqueue(new Callback<ArrayList<ResponseScheduleInfoVO>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseScheduleInfoVO>> call, Response<ArrayList<ResponseScheduleInfoVO>> response) {
                ArrayList<ResponseScheduleInfoVO> data = response.body();

                ScheduleInfoAdapter adapter = new ScheduleInfoAdapter(data, context);
                LinearLayoutManager manager = new LinearLayoutManager(context);

                viewHolder.rvSchedule.setLayoutManager(manager);
                viewHolder.rvSchedule.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseScheduleInfoVO>> call, Throwable t) {

            }
        });

        //장소추가 버튼
        viewHolder.btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlace(data, viewHolder.rvSchedule.getAdapter().getItemCount());
            }
        });
        //메모추가 버튼
        viewHolder.btnAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmemo(data, viewHolder.rvSchedule.getAdapter().getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayNumber, tvDay;
        RecyclerView rvSchedule;
        Button btnAddPlace, btnAddMemo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayNumber = itemView.findViewById(R.id.tvDayNumber);
            tvDay = itemView.findViewById(R.id.tvDay);
            rvSchedule = itemView.findViewById(R.id.rvSchedule);
            btnAddPlace = itemView.findViewById(R.id.btnAddPlace);
            btnAddMemo = itemView.findViewById(R.id.btnAddMemo);
        }
    }

    private void addPlace(long data, int number) {
        Intent intent = new Intent(context, AddPlaceActivity.class);
        intent.putExtra("schedules", Parcels.wrap(((ScheduleActivity)context).schedules));
        intent.putExtra("day", data);
        intent.putExtra("number", number);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    private void addmemo(long data, int number) {
        Intent intent = new Intent(context, MemoActivity.class);
        intent.putExtra("schedules", Parcels.wrap(((ScheduleActivity)context).schedules));
        intent.putExtra("day", data);
        intent.putExtra("nubmer", number);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

}
