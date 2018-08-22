package com.playingjoy.fanrabbit.ui.receiver;

import android.content.Context;
import android.util.Log;

import com.hyphenate.chat.EMMipushReceiver;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;

/**
 * 小米广播接收
 *
 * @author Morphine
 * @date 2018-04-13.
 */

public class MIPushReceiver extends EMMipushReceiver {

    private static final String TAG = "MIPushReceiver";

    @Override
    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
        super.onReceiveMessage(context, miPushMessage);
        Log.e("miPush", miPushMessage.getContent());
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        Log.e("miPush", miPushMessage.getContent());
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        super.onReceiveRegisterResult(context, miPushCommandMessage);
        Log.e(TAG, "onReceiveRegisterResult: " );
    }
}
