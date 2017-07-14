package com.coc.basemodule.arch.mvv;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coc.basemodule.R;
import com.coc.basemodule.arch.bean.BizBean;
import com.coc.basemodule.arch.bean.StatusBean;

public class DemoArchActivity extends LifecycleActivity implements View.OnClickListener {

    private DemoViewModel demoViewModel;

    private TextView textView;
    private TextView textView2;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_activity_demo_arch);
        initView();

        inidViewModel();
    }

    private void inidViewModel() {
        demoViewModel = ViewModelProviders.of(this).get(DemoViewModel.class);
        demoViewModel.init(this.getClass().getSimpleName());

        demoViewModel.getViewStatus_LiveData().observe(this, new Observer<StatusBean>() {
            @Override
            public void onChanged(@Nullable StatusBean statusBean) {
                if (statusBean == null) return;
            }
        });

        demoViewModel.getBizBean_LiveData().observe(this, new Observer<BizBean>() {
            @Override
            public void onChanged(@Nullable BizBean bizBean) {
                if (bizBean == null) return;
                updateUIByBean(bizBean);
            }
        });

    }


    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                demoViewModel.pullData();
                break;
        }
    }


    //通过bean来更新图片
    private void updateUIByBean(@NonNull BizBean bizBean) {
        textView2.setText(TextUtils.isEmpty(bizBean.getDescription()) ? "图片描述" : bizBean.getDescription());
        Glide.with(this).load(bizBean.getDescription()).into(imageView);
    }
}
