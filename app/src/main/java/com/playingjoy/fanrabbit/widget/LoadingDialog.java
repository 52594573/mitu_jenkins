package com.playingjoy.fanrabbit.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;

/**
 * @author Morphine
 * @date 2018-04-02.
 */

public class LoadingDialog extends Dialog {

    TextView tvLoadingText;

    public LoadingDialog(@NonNull Context context) {
        this(context, R.style.CustomDialog);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.dialog_loading, null);
        tvLoadingText = view.findViewById(R.id.tv_loading_text);
        setContentView(view);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.width = (int) (defaultDisplay.getWidth() * 0.45);
        getWindow().setAttributes(attributes);
    }

    public void show(boolean cancelable) {
        setCancelable(cancelable);
        this.show();
    }

    public LoadingDialog show(String loadingText, boolean cancelable) {
        setCancelable(cancelable);
        if (TextUtils.isEmpty(loadingText)) {
            tvLoadingText.setVisibility(View.GONE);
        } else {
            tvLoadingText.setVisibility(View.VISIBLE);
            tvLoadingText.setText(loadingText);
        }
        this.show();
        return this;
    }
}
