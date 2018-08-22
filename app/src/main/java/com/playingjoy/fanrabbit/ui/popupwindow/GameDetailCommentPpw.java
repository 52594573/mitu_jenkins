package com.playingjoy.fanrabbit.ui.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.playingjoy.fanrabbit.R;

/**
 * 游戏详情页面评论PopupWindow
 *
 * @author Morphine
 * @date 2018-03-29.
 */

public class GameDetailCommentPpw extends PopupWindow {

    private Context context;
    final EditText etComment;

    public GameDetailCommentPpw(Context context, View.OnClickListener onSendClickListener) {
        this.context = context;
        View inflate = View.inflate(context, R.layout.ppw_game_detail_comment, null);
        etComment = inflate.findViewById(R.id.et_comment);
        setContentView(inflate);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
//        setAnimationStyle(R.style.ActionSheetDialogAnimation);
        setFocusable(true);
        setOnDismissListener(() -> setBackgroundAlpha(1f));
        //监听发送按钮点击
        etComment.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                if (TextUtils.isEmpty(etComment.getText().toString())) {
                    Toast.makeText(context, R.string.text_please_input_comment, Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (onSendClickListener != null) onSendClickListener.onClick(etComment);
                etComment.setText("");
                dismiss();
                return true;
            }
            return false;
        });
    }


    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        setBackgroundAlpha(0.4f);
        super.showAtLocation(parent, gravity, x, y);
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //修改背景颜色
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }
}
