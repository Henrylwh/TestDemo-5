package cn.com.gf.demo.flows;

/**
 * Created by Administrator on 2017/4/22.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cn.com.gf.demo.pages.Homepage;
import cn.com.gf.demo.base.utils.Logger;
public class HomepageFlow {
    private static final Logger logger = Logger.getLogger(HomepageFlow.class);
    private WebDriver driver = null;
    private Homepage factory = null;
    public HomepageFlow(WebDriver driver) {
        this.driver = driver;
        factory = PageFactory.initElements(driver, Homepage.class);
    }
    public void clickTree() {
        factory.tree.click();
        logger.info("Click book management button");
    }

}
