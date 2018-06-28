package com.example.user.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FeatureActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "FeatureActivity";
    private TextView mTvMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);

        initContentView();
        EventBus.getDefault().register(this);
    }

    private void initContentView() {
        Button btnStart = findViewById(R.id.bt_feature);
        mTvMessage = findViewById(R.id.tv_feature);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_feature) {
            SecondActivity.start(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.i(TAG, "message is " + event.getMessage());
        mTvMessage.setText(event.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
