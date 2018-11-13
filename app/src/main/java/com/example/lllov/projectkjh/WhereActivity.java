package com.example.lllov.projectkjh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lllov.projectkjh.Adapter.WhereAdapter;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*==================================================================================================
 * 메인에서 여행 검색 버튼을 누르면 나타나는 화면
 * 데이터베이스에 저장된 location 정보를 가져와 그리드 리사이클러뷰에 띄워줌
 * 각 버튼 선택시 해당 지역의 정보와 일정추가 화면등을 할 수 있음
 *=================================================================================================*/
public class WhereActivity extends BaseActivity {

    Toolbar toolbar;
    SearchView searchView;
    RecyclerView rvContent;
    WhereAdapter adapter;
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //스테이터스바 제거
        deleteStatusBar();
        setContentView(R.layout.activity_where);
        toolbar = new ToolBar(this).setBack().setToolbar();

        //지역 리스트를 요청
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ArrayList<LocationVO>> call = service.getLocationList();

        call.enqueue(new Callback<ArrayList<LocationVO>>() {
            @Override
            public void onResponse(Call<ArrayList<LocationVO>> call, Response<ArrayList<LocationVO>> response) {
                ArrayList<LocationVO> data = response.body();

                rvContent = findViewById(R.id.rvContent);
                layoutManager = new GridLayoutManager(WhereActivity.this, 2);

                adapter = new WhereAdapter(data, WhereActivity.this);
                rvContent.setAdapter(adapter);
                rvContent.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ArrayList<LocationVO>> call, Throwable t) {

            }
        });
    }

    //상단에 여행을 검색할 수 있도록 함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setQueryHint("여행할 도시를 검색하세요");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }
}
