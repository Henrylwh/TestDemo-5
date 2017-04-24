package cn.com.gf.demo.pages;

/**
 * Created by Administrator on 2017/4/24.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DeleteBookPage {
    @FindBy(how = How.ID, using = "messagebox-1001-displayfield-inputEl")
    public WebElement confirmMessage;
    @FindBy(how=How.ID,using="button-1006-btnIconEl")
    public WebElement btnYes;
    @FindBy(how=How.ID,using="button-1007-btnIconEl")
    public WebElement btnNo;
    @FindBy(how=How.ID,using="button-1005-btnIconEl")
    public WebElement btnOK;



}
