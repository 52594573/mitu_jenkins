package com.playingjoy.fanrabbit.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

/**
 * @author Morphine
 * @date 2018-03-31.
 */

public class SimpleDialog extends Dialog {

    FrameLayout containerLayout;
    TextView tvTitle;
    Button btnConfirm;
    ImageView ivClose;

    public SimpleDialog(@NonNull Context context) {
        this(context, R.style.CustomDialog);
    }

    public SimpleDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected SimpleDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.dialog_gifts_detail, null);
        containerLayout = view.findViewById(R.id.container);
        tvTitle = view.findViewById(R.id.tv_title);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        ivClose = view.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(v -> dismiss());
        setContentView(view);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.width = (int) (defaultDisplay.getWidth() * 0.832);
        getWindow().setAttributes(attributes);
    }

    public SimpleDialog addContentView(View view) {
        containerLayout.addView(view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        return this;
    }

    public SimpleDialog setTitleText(String titleText) {
        tvTitle.setText(titleText);
        return this;
    }

    public SimpleDialog addBtnClickListener(View.OnClickListener onClickListener) {
        btnConfirm.setOnClickListener(onClickListener);
        return this;
    }

    public SimpleDialog setBtnText(String text) {
        btnConfirm.setText(text);
        return this;
    }
}
