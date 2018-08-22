package com.playingjoy.fanrabbit.utils;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.playingjoy.fanrabbit.R;
import com.playingjoy.fanrabbit.ui.activity.login.LoginActivity;
import com.playingjoy.fanrabbit.ui.dialog.SimpleDialog;

/**
 * 提示Dialog
 *
 * @author Morphine
 * @date 2018-04-10.
 */

public class TipDialogUtil {

    private Activity context;
    final SimpleDialog giftsDetailDialog;

    public TipDialogUtil(Activity context) {
        this.context = context;
        giftsDetailDialog = new SimpleDialog(context);
    }

    public void showLoginTip(String dialogTitle, String dialogContent, String ButtonText) {
        show(dialogTitle, dialogContent, ButtonText, v -> LoginActivity.toLoginActivity(context));
    }


    public void show(String dialogTitle, String dialogContent, String buttonText, View.OnClickListener onClickListener) {
        View view = View.inflate(context, R.layout.view_gifts_detail_dialog_container_text, null);
        TextView textView = view.findViewById(R.id.tv_tip_content);
        textView.setText(dialogContent);
        giftsDetailDialog.setTitleText(dialogTitle);
        giftsDetailDialog.setBtnText(buttonText);
        giftsDetailDialog.addContentView(view);
        giftsDetailDialog.addBtnClickListener(v -> {
            onClickListener.onClick(giftsDetailDialog.findViewById(R.id.btn_confirm));
            giftsDetailDialog.dismiss();
        });
        giftsDetailDialog.show();
    }

}
