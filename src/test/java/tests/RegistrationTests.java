package tests;

import data.DataProviderLogin;
import data.DataProviderReg;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTests extends BaseTest{


    @AfterMethod(alwaysRun = true)
    public void postConditionsLogin() {
        if(flagPopUp) {
            flagPopUp = false;
            app.getUserHelper().clickOkPopUpSuccessLogin();
        }
        if(flagLogin) {
            flagLogin = false;
            app.getUserHelper().logout();
        }
//        if(app.getUserHelper().popUpMessageSuccessAfterRegistrationExist())
//            app.getUserHelper().clickOkPopUpSuccessLogin();
//        app.getUserHelper().pause(5);
//        app.getUserHelper().refresh();

    }

    @Test(groups = {"smoke", "regression"})
    public void positiveRegistration() {
        app.getUserHelper().refresh();
        String email = randomUtils.generateEmail(7);

        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa$")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flagLogin = true;
        flagPopUp = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }
//dataProvider = "regCSV", dataProviderClass = DataProviderReg.class
@Test (enabled = false, dataProvider = "regCSV", dataProviderClass = DataProviderReg.class)
    public void positiveRegistration(UserDtoLombok userDP) {
        app.getUserHelper().fillRegistrationForm(userDP);
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    flagLogin = true;
    flagPopUp = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }
//dataProvider = "negativeEmailDataReg", dataProviderClass = DataProviderReg.class
    @Test(enabled = false,dataProvider = "negativeRegCSV", dataProviderClass = DataProviderReg.class)
    public void negativeRegistrationWrongEmail(UserDtoLombok userDP) {
//        UserDtoLombok user = UserDtoLombok.builder()
//                .email("abc@")
//                .password("123456Aa$")
//                .lastName("abdfg")
//                .name("test")
//                .build();

        app.getUserHelper().fillNegativeRegistrationForm(userDP);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validateMessageIncorrectEmailReg());
    }

    @Test(groups = {"smoke"})
    public void negativeRegistrationWrongPassword() {
        String email = randomUtils.generateEmail(7);

        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillNegativeRegistrationForm(user);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validateMessageWrongPasswordReg());
    }

    @Test
    public void negativeRegistrationBlankEmail() {
        UserDtoLombok user = UserDtoLombok.builder()
                .email("")
                .password("123456Aa$")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillNegativeRegistrationForm(user);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validateErrorEmptyEmailReg());
    }
}
