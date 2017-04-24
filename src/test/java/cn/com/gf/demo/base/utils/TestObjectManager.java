package cn.com.gf.demo.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/4/22.
 */
public class TestObjectManager {
    private static Map<String, List<String>> logMap = new ConcurrentHashMap<String, List<String>>();

    public static void addLog(String testCaseId, String msg) {
        // logger.info("[TestObjectManager--addLog]--testCaseId:" + testCaseId + "--msg:" + msg);
        if (msg != null) {
            List<String> list = null;
            if (logMap.containsKey(testCaseId)) {
                list = logMap.get(testCaseId);
            } else {
                list = new ArrayList<String>();
            }
            list.add(msg);
            logMap.put(testCaseId, list);
        }
    }
}
