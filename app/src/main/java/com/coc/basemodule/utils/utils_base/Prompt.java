package com.coc.basemodule.utils.utils_base;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 提示 需要改进
 */
public class Prompt {

    private static Toast toast;

    /**
     * @param tipStr 提示内容
     */
    public static void showToast(String tipStr) {
        if (!TextUtils.isEmpty(tipStr)) {
            if (toast == null) {
                toast = Toast.makeText(Utils.getContext(), tipStr, Toast.LENGTH_SHORT);
                ViewGroup viewGroup = (ViewGroup) toast.getView();
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof TextView) {
                        TextView tv = (TextView) viewGroup.getChildAt(i);
                        tv.setMaxLines(100);
                        break;
                    }
                }
            } else {
                toast.setText(tipStr);
            }
            toast.show();
        }
    }

    public static void showToastSubthread(Context context, final String tipStr) {
        Handler handler = new Handler(Utils.getContext().getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                if (!TextUtils.isEmpty(tipStr)) {
                    if (toast == null) {
                        toast = Toast.makeText(Utils.getContext(), tipStr, Toast.LENGTH_SHORT);
                    } else {
                        toast.setText(tipStr);
                    }
                    toast.show();
                }
            }
        });
    }

    /**
     * 自定义文本内入，例如：
     */
    public static void showToast(View view) {
        if (toast == null) {
            toast = Toast.makeText(Utils.getContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, toast.getXOffset(), toast.getYOffset());
        LinearLayout toastView = (LinearLayout) toast.getView();
        toastView.addView(view, 0);
        toast.show();
    }

    /**
     * @param tip
     */
    public static void showToast(int tip) {
        String string = Utils.getContext().getString(tip);
        if (toast == null && string != null) {
            toast = Toast.makeText(Utils.getContext(), string, Toast.LENGTH_SHORT);
            ViewGroup viewGroup = (ViewGroup) toast.getView();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (viewGroup.getChildAt(i) instanceof TextView) {
                    TextView tv = (TextView) viewGroup.getChildAt(i);
                    tv.setMaxLines(100);
                    break;
                }
            }
        } else {
            toast.setText(tip);
        }
        toast.show();
    }

    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
