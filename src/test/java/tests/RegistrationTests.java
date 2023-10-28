package tests;

import data.DataProviderLogin;
import data.DataProviderReg;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTests extends BaseTest{

    @BeforeMethod(alwaysRun = true)
    public void preconditionsLogin() {

        logoutIfLogin();

    }

    @AfterMethod(alwaysRun = true)
    public void postConditionsLogin() {
        if(app.getUserHelper().popUpMessageSuccessAfterRegistrationExist())
            app.getUserHelper().clickOkPopUpSuccessLogin();
        app.getUserHelper().pause(5);
        app.getUserHelper().refresh();

    }

    @Test(groups = {"smoke", "regression"})
    public void positiveRegistration() {
        String email = randomUtils.generateEmail(7);

        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa$")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillRegistrationForm(user);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }
//dataProvider = "regCSV", dataProviderClass = DataProviderReg.class
@Test ( dataProvider = "regCSV", dataProviderClass = DataProviderReg.class)
    public void positiveRegistration(UserDtoLombok userDP) {
        app.getUserHelper().fillRegistrationForm(userDP);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }
//dataProvider = "negativeEmailDataReg", dataProviderClass = DataProviderReg.class
    @Test(dataProvider = "negativeRegCSV", dataProviderClass = DataProviderReg.class)
    public void negativeRegistrationWrongEmail(UserDtoLombok userDP) {
//        UserDtoLombok user = UserDtoLombok.builder()
//                .email("abc@")
//                .password("123456Aa$")
//                .lastName("abdfg")
//                .name("test")
//                .build();

        app.getUserHelper().fillNegativeRegistrationForm(userDP);
        Assert.assertTrue(app.getUserHelper().validateMessageIncorrectEmailReg());
    }

    @Test
    public void negativeRegistrationWrongPassword() {
        String email = randomUtils.generateEmail(7);

        UserDtoLombok user = UserDtoLombok.builder()
                .email(email)
                .password("123456Aa")
                .lastName("abdfg")
                .name("test")
                .build();

        app.getUserHelper().fillNegativeRegistrationForm(user);
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
        Assert.assertTrue(app.getUserHelper().validateErrorEmptyEmailReg());
    }
}
