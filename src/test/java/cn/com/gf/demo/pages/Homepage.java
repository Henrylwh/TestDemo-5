package cn.com.gf.demo.pages;

/**
 * Created by Administrator on 2017/4/22.
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class Homepage {
    @FindBy(how = How.ID, using = "treeview-1015-record-book_manage")
    public WebElement tree;
    @FindBy(how = How.ID, using = "password")
    public WebElement txtPassword;

}
