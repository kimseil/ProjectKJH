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
import com.example.lllov.projectkjh.DTO.DTOWhere;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        rvContent = findViewById(R.id.rvContent);
        layoutManager = new GridLayoutManager(this, 2);

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<DTOWhere> call = service.getLocation();

        call.enqueue(new Callback<DTOWhere>() {
            @Override
            public void onResponse(Call<DTOWhere> call, Response<DTOWhere> response) {
                DTOWhere where = response.body();


                Log.e("@@@@@@@@", new GsonBuilder().setPrettyPrinting().create().toJson(where));

                //adapter = new WhereAdapter(data, WhereActivity.this);
                //rvContent.setAdapter(adapter);
                //rvContent.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<DTOWhere> call, Throwable t) {

            }
        });
    }

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
