package cn.com.gf.demo.pages;

/**
 * Created by Administrator on 2017/4/22.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.*;
public class BookListPage {

    @FindBy(how = How.ID, using = "gridview-1043-body")
    public WebElement bookListTb;
    @FindBy(how = How.ID, using = "button-1045-btnInnerEl")
    public WebElement btnCreate;
    @FindBy(how = How.ID, using = "button-1046-btnInnerEl")
    public WebElement btnDelete;
    @FindBy(how = How.ID, using = "button-1047-btnInnerEl")
    public WebElement btnUpdate;
    @FindBy(how = How.ID, using = "button-1048-btnInnerEl")
    public WebElement btnRefresh;
}

