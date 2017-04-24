package cn.com.gf.demo.base.utils;

import org.testng.Reporter;

import java.io.IOException;

public class Logger {

	private Object obj = null;

	public Logger(Object obj) {
		this.setObj(obj);
	}

	private String formatLog(Object msg) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[{");
		buffer.append(Thread.currentThread().hashCode());
		buffer.append("}]***");
		buffer.append(msg.toString());
		buffer.append("\r\n");
		return buffer.toString();
	}

	public void info(Object msg) {
		Reporter.log(formatLog(msg), true);

	}

	public void error(Object msg) {
		Reporter.log(formatLog(msg), true);
	}

	public void warn(Object msg) {
		Reporter.log(formatLog(msg), true);
	}

	public static Logger getLogger(Object obj) {
		return new Logger(obj);
	}

	public void printToFile(String testCaseId, String msg) {
		info(msg);
		TestObjectManager.addLog(testCaseId, msg);
	}

	public static void main(String[] str) throws IOException {

	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
