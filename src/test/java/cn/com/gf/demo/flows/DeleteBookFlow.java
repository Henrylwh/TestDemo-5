package cn.com.gf.demo.flows;

import cn.com.gf.demo.pages.DeleteBookPage;
import cn.com.gf.demo.base.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Administrator on 2017/4/24.
 */
public class DeleteBookFlow {
    private static final Logger logger = Logger.getLogger(DeleteBookFlow.class);
    private WebDriver driver = null;
    private DeleteBookPage factory = null;
    public DeleteBookFlow(WebDriver driver) {
        this.driver = driver;
        factory = PageFactory.initElements(driver, DeleteBookPage.class);
    }
    public String getPopUpMsg(){
        String msg=factory.confirmMessage.getText();
        logger.info("The confirm message text is "+msg);
        return msg;
    }
    public void clickOKBtn(){
        factory.btnOK.click();
        logger.info("Click OK button");
    }
    public void clickYesBtn(){
        factory.btnYes.click();
        logger.info("Click Yes button");
    }
    public void clickNoBtn(){
        factory.btnNo.click();
        logger.info("Click No button");
    }
}
