package cn.com.gf.demo.base.utils;


import cn.com.gf.demo.tests.BookManageTest;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import cn.com.gf.demo.base.utils.Logger;

/**
 * Created by Administrator on 2017/4/22.
 */

public class TestObjectManager {
    private static final Logger logger = Logger.getLogger(BookManageTest.class);
    private static Map<String, List<String>> logMap = new ConcurrentHashMap<String, List<String>>();
    private static Map<String, WebDriver> driverMap = new ConcurrentHashMap<String, WebDriver>();
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
        public static WebDriver getDriver(String testCaseId) {
            if (driverMap.containsKey(testCaseId)) {
                logger.info("[TestObjectManager--getDriver]--testCaseId:" + testCaseId);
                return driverMap.get(testCaseId);
            }
            logger.info("[TestObjectManager--getDriver]--testCaseId:" + testCaseId + "--msg:driver is null.");
            return null;
        }

    }

