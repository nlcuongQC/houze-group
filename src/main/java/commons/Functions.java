package commons;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Functions {
    private WebDriver driver;

    public Functions(WebDriver driver) {
        this.driver = driver;
    }

    public static Functions getFunctions(WebDriver driver) {
        return new Functions(driver);
    }

    public static String convertMoneyAmount(String num) {
        int           index;
        List<String>  a = new ArrayList<>();
        StringBuilder number;
        if (num.length() % 3 == 0) {
            for (int i = 0; i <= num.length() - 3; i += 3) {
                a.add(num.substring(i, i + 3));
            }
            index  = num.length() / 3;
            number = new StringBuilder(a.get(0));
            for (int j = 1; j < index; j++) {
                number.append(",").append(a.get(j));
            }
        } else {
            a.add(num.substring(0, num.length() % 3));
            for (int i = (num.length() % 3); i <= num.length() - 3; i += 3) {
                a.add(num.substring(i, i + 3));
            }
            index  = num.length() / 3 + 1;
            number = new StringBuilder(a.get(0));
            for (int j = 1; j < index; j++) {
                number.append(",").append(a.get(j));
            }
        }
        num = String.valueOf(number);
        return num;
    }

    public static int convertMoneyStringToInt(String money) {
        NumberFormat format = NumberFormat.getInstance(Locale.UK);
        try {
            return format.parse(money).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Attachment(value = "Failed step screenshot: ", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
