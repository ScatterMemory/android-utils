package com.lhg.sangong.utils.log;

import android.util.Log;

import com.lhg.sangong.utils.SGLog;
import com.lhg.sangong.utils.SGUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ethan on 2016/6/7.
 * 打印JSON日志
 */
public class JsonLog {

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(SGLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(SGLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        SGUtils.printLine(tag, true);
        message = headString + SGLog.LINE_SEPARATOR + message;
        String[] lines = message.split(SGLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        SGUtils.printLine(tag, false);
    }
}
