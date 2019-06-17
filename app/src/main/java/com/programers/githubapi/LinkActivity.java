package com.programers.githubapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LinkActivity extends AppCompatActivity implements View.OnClickListener {

    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        link = findViewById(R.id.link);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.link:
                Intent launchIntent = new Intent(this, MainActivity.class);
                String user = link.getText().toString().substring(link.getText().toString().lastIndexOf("/") + 1);
                launchIntent.putExtra("user", user);
                startActivity(launchIntent);
                break;
        }
    }
}
