package com.programers.githubapi;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    UserVO userVO;
    ArrayList<ReposVO> items;

    private String url = "https://api.github.com/users/";
    private String user;
    private HttpConnection connection = HttpConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = getIntent().getStringExtra("user");
        Toast.makeText(getApplicationContext(), user, Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        link1();
        link2();

    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (userVO != null && items != null) {
                        MyAdapter adapter = new MyAdapter(userVO, items);
                        recyclerView.setAdapter(adapter);
                    }

                    break;
            }
        }
    };

    private void link1() {
        connection.requestServer(url + user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("HttpConnection", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.d("onResponse", body);

                // Gson 변경
                Gson gson = new Gson();
                Type type = new TypeToken<UserVO>(){}.getType();
                userVO = gson.fromJson(body, type);
            }
        });
    }

    private void link2() {
        connection.requestServer(url + user + "/repos", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("HttpConnection", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.d("onResponse", body);

                // Gson 변경
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<ReposVO>>(){}.getType();
                items = gson.fromJson(body, listType);

                // 정렬
                MiniComparator comp = new MiniComparator();
                Collections.sort(items, comp);

                items.add(0, null);

                Message msg = handler.obtainMessage();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
    }

}
