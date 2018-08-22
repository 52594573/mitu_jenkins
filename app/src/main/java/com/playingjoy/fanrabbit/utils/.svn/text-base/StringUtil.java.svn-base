package com.playingjoy.fanrabbit.utils;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

/**
 * @author Morphine
 * @date 2018-04-18.
 */

public class StringUtil {
    /**
     * 获取搜索结果内容变色html
     *
     * @param content
     * @param keyword
     * @return
     */
    public static Spanned getSearchSpanString(String content, String keyword) {
        if (TextUtils.isEmpty(keyword)) return Html.fromHtml(content);
        if (content.contains(keyword)) {
            String[] split = content.split(keyword);
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < split.length; i++) {
                sb.append(split[i]);
                if (i != split.length - 1) {
                    sb.append("<font color='#F33738'>");
                    sb.append(keyword);
                    sb.append("</font>");
                }
            }

            if (content.lastIndexOf(keyword) == content.length() - keyword.length()) {
                sb.append("<font color='#F33738'>");
                sb.append(keyword);
                sb.append("</font>");
            }
            return Html.fromHtml(sb.toString());
        }
        return Html.fromHtml(content);
    }
}
