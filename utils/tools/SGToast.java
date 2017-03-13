package com.lhg.sangong.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lhg.sangong.R;

/**
 * Created by ethan on 2016/6/14.
 * 自定义效果Toast
 */
public class SGToast {


    public static void show(String content, Context context) {
        Toast result = new Toast(context);

        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.custome_toast, null);
        TextView tv = (TextView) v.findViewById(R.id.tv_toastcontent);
        tv.setText(content);

        result.setView(v);
        result.setDuration(Toast.LENGTH_SHORT);
        result.show();
    }


}
