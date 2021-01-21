package commons;

import org.openqa.selenium.WebDriver;
import pageobjects.houze.LoginPageObject;
import pageobjects.houzeinvest.admin.*;
import pageobjects.houzeinvest.investor.*;

public class PageGeneratorManager {
    public static class HouzeInvest {
        public static HomePageObject getHomePageObject(WebDriver driver) {
            return new HomePageObject(driver);
        }

        public static pageobjects.houzeinvest.investor.BasePageObject getBasePageObject(WebDriver driver) {
            return new pageobjects.houzeinvest.investor.BasePageObject(driver);
        }

        public static ProjectPageObject getProjectPageObject(WebDriver driver) {
            return new ProjectPageObject(driver);
        }

        public static ProjectDetailPageObject getProjectDetailPageObject(WebDriver driver) {
            return new ProjectDetailPageObject(driver);
        }

        public static CheckoutPageObject getCheckoutPageObject(WebDriver driver) {
            return new CheckoutPageObject(driver);
        }

        public static PortfolioPageObject getPortfolioPageObject(WebDriver driver) {
            return new PortfolioPageObject(driver);
        }

        public static AccountPageObject getAccountPageObject(WebDriver driver) {
            return new AccountPageObject(driver);
        }

        public static ProfilePageObject getProfilePageObject(WebDriver driver) {
            return new ProfilePageObject(driver);
        }

        public static LoginAdminPageObject getLoginAdminPageObject(WebDriver driver) {
            return new LoginAdminPageObject(driver);
        }

        public static BaseAdminPageObject getBaseAdminPageObject(WebDriver driver) {
            return new BaseAdminPageObject(driver);
        }

        public static CustomerDetailPageObject getCustomerDetailAdminPage(WebDriver driver) {
            return new CustomerDetailPageObject(driver);
        }

        public static CustomerPageObject getCustomerAdminPage(WebDriver driver) {
            return new CustomerPageObject(driver);
        }

        public static WalletPageObject getWalletPage(WebDriver driver) {
            return new WalletPageObject(driver);
        }

        public static TransactionOrderPageObject getTransactionOrderPage(WebDriver driver) {
            return new TransactionOrderPageObject(driver);
        }

        public static TradingPageObject getTradingPage(WebDriver driver) {
            return new TradingPageObject(driver);
        }

        public static TradingBuyPageObject getTradingBuyPage(WebDriver driver) {
            return new TradingBuyPageObject(driver);
        }

        public static TradingSellPageObject getTradingSellPage(WebDriver driver) {
            return new TradingSellPageObject(driver);
        }

        public static PropertyPageObject getPropertyPage(WebDriver driver) {
            return new PropertyPageObject(driver);
        }

        public static PropertyDetailPageObject getPropertyDetailPage(WebDriver driver) {
            return new PropertyDetailPageObject(driver);
        }
    }

    public static class Houze {
        public static LoginPageObject getLoginPage(WebDriver driver) {
            return new LoginPageObject(driver);
        }

        public static pageobjects.houze.BasePageObject getBasePageObject(WebDriver driver) {
            return new pageobjects.houze.BasePageObject(driver);
        }
    }
}
