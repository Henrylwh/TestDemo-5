package cn.com.gf.demo.flows;

import cn.com.gf.demo.pages.DeleteBookPage;
import org.apache.log4j.Logger;
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
        return factory.confirmMessage.getText();
    }
    public void clickOKBtn(){
        factory.btnOK.click();
    }
    public void clickYesBtn(){
        factory.btnYes.click();
    }
    public void clickNoBtn(){
        factory.btnNo.click();
    }
}
