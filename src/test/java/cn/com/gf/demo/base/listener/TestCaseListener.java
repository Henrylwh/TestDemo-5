package cn.com.gf.demo.base.listener;

import cn.com.gf.demo.base.cons.BaseCons;
import cn.com.gf.demo.base.utils.TestObjectManager;
import cn.com.gf.demo.base.utils.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.io.File;

public class TestCaseListener extends TestListenerAdapter {
	private static Logger logger = Logger.getLogger(TestCaseListener.class);
	@Override
	public void onTestFailure(ITestResult result) {
		super.onTestFailure(result);
		try {
			takeScreenShot(result);
		} catch (Exception e) {
			logger.error(e);
		}
		String caseName = result.getName();
		logger.info("{FAILED}--" + caseName);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		super.onTestSuccess(result);
		String caseName = result.getName();
		logger.info("{PASSED}--" + caseName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		super.onTestSkipped(result);
		String caseName = result.getName();
		try {
			takeScreenShot(result);
		} catch (Exception e) {
			logger.error(e);
		}
		logger.info("{SKIPPED}--" + caseName);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		super.onTestFailedButWithinSuccessPercentage(result);
		logger.info("{onTestFailedButWithinSuccessPercentage}--" + result.getName());
	}

	@Override
	public void onConfigurationFailure(ITestResult result) {
		// TODO Auto-generated method stub
		super.onConfigurationFailure(result);
		logger.info("{onConfigurationFailure}--" + result.getName());
	}

	@Override
	public void onConfigurationSkip(ITestResult result) {
		// TODO Auto-generated method stub
		super.onConfigurationSkip(result);
		logger.info("{onConfigurationSkip}--" + result.getName());
}


	public void takeScreenShot(ITestResult result) throws Exception {
		String outputPath = result.getTestContext().getOutputDirectory() + File.separator + BaseCons.SCREENSHOT_FOLDER;
		String caseName = result.getName();

		// Add status into snapshot file name
		String status = null;
		switch (result.getStatus()) {
			case 2:
				status = "FAILURE";
				break;
			case 3:
				status = "SKIP";
				break;
			case 4:
				status = "SUCCESS_PERCENTAGE_FAILURE";
				break;
			case 16:
				status = "STARTED";
				break;
			default:
				status = "SUCCESS";
		}

		String fileName = caseName  + "-" + status + "-" + System.currentTimeMillis() + ".png";
		String filePath = outputPath + File.separator + fileName;
		logger.info("screen shot = " + filePath);
		WebDriver driver = TestObjectManager.getDriver(caseName);
		logger.info("screenshot driver= " + driver);

		TakesScreenshot tsDriver;
		if (driver instanceof RemoteWebDriver) {
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			tsDriver = (TakesScreenshot) augmentedDriver;
			logger.info("screenshot: Remote WebDriver");
		} else {
			tsDriver = (TakesScreenshot) driver;
			logger.info("screenshot: Local WebDriver");
		}
		File temFile = tsDriver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(temFile, new File(filePath));
		logger.info("success: screenShot");
	}

}
