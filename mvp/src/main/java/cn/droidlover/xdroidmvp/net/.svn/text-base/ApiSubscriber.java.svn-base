package cn.droidlover.xdroidmvp.net;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.mvp.IView;
import io.reactivex.subscribers.ResourceSubscriber;


/**
 * Created by wanglei on 2016/12/26.
 */

public abstract class ApiSubscriber<T extends IModel> extends ResourceSubscriber<T> {

    private static final String TAG = "ApiSubscriber";

    private IView view;
    boolean isShowErrorMsg;

    public ApiSubscriber() {

    }

    public ApiSubscriber(IView iView, boolean isShowErrorMsg) {
        this.view = iView;
        this.isShowErrorMsg = isShowErrorMsg;
    }

    @Override
    public void onError(Throwable e) {
        NetError error = null;
        if (e != null) {
            if (!(e instanceof NetError)) {
                if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
                    error = new NetError("网络超时，请求失败", NetError.NoConnectError);
                } else if (e instanceof JSONException
                        || e instanceof JsonParseException
                        || e instanceof JsonSyntaxException) {
                    error = new NetError(e, NetError.ParseError);
                } else {
                    error = new NetError(e, NetError.OtherError);
                }
            } else {
                error = (NetError) e;
            }
            if (view != null)
                view.dismissLoadingDialog();
            if (useCommonErrorHandler()
                    && XApi.getCommonProvider() != null) {
                if (XApi.getCommonProvider().handleError(error)) {        //使用通用异常处理
                    return;
                }
            }
            XLog.e("the error is" + error);
            onFail(error);
        }

    }

    protected void onFail(NetError error) {
        if (isShowErrorMsg) {
            view.showTs(error.getMessage());
        }
        XLog.e(TAG, error.getMessage());
    }

    @Override
    public void onComplete() {
        if (view != null)
            view.dismissLoadingDialog();
    }


    protected boolean useCommonErrorHandler() {
        return true;
    }


}
