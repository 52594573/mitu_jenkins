package com.playingjoy.fanrabbit.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

/**
 * @author Morphine
 * @date 2018-03-28.
 */

public class SimpleNoTitleDialog extends Dialog {

    TextView tvTipContent;
    Button cancelBtn;
    Button confirmBtn;

    public SimpleNoTitleDialog(@NonNull Context context) {
        this(context, R.style.CustomDialog);
    }


    public SimpleNoTitleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_simple_no_title, null);
        tvTipContent = view.findViewById(R.id.tv_tip_content);
        cancelBtn = view.findViewById(R.id.btn_cancel);
        confirmBtn = view.findViewById(R.id.btn_confirm);
        this.setContentView(view);
    }

    protected SimpleNoTitleDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public SimpleNoTitleDialog setTipText(String text) {
        if (TextUtils.isEmpty(text)) return this;
        tvTipContent.setText(text);
        return this;
    }

    public SimpleNoTitleDialog setCancelBtnClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null) return this;
        cancelBtn.setOnClickListener(onClickListener);
        return this;
    }

    public SimpleNoTitleDialog setConfirmBtnClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null) return this;
        confirmBtn.setOnClickListener(onClickListener);
        return this;
    }

}
