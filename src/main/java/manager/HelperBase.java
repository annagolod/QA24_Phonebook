package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HelperBase {

    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        //element.click();
        clearNew(locator);
        //element.clear();
        //clickNew(locator);
            if (text != null) {
                element.sendKeys(text);
        }

    }
    public void click(By locator){
        WebElement element = wd.findElement(locator);
        element.click();

    }

//    public void clickNew(By locator){
//        WebElement el = wd.findElement(locator);
//        Rectangle rect = el.getRect();
//        int w = rect.getWidth();
//        Actions actions = new Actions(wd);
//        int xOffSet = - (w / 2);
//        actions.moveToElement(el, xOffSet, 0).click().release().perform();
//
//    }

    public void clearNew(By locator){
        WebElement el = wd.findElement(locator);
        String operationSystem = System.getProperty("os.name");
        if(operationSystem.startsWith("Win"))
            el.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        else if(operationSystem.startsWith("Mac"))
            el.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));

    }

    public boolean isElementPresent(By locator){
        List<WebElement> list = wd.findElements(locator);
        return !list.isEmpty();
    }

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd, 10)
                .until(ExpectedConditions.alertIsPresent());
        if(alert != null && alert.getText().contains(message)){
            //System.out.println(alert.getText());
            // click OK --> alert.accept();
            // click cancel --> alert.dismiss();
            // type into alert --> alert.sendKeys("hello");
            //pause(2000);
            alert.accept();
            return true;
        }
        return false;

    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp, new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
