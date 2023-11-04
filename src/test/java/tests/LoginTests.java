package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest{



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
//        app.getUserHelper().clickOkPopUpSuccessLogin();
//        app.getUserHelper().pause(5);
//        app.getUserHelper().refresh();

    }

    @Test(groups = {"smoke"})
    public void positiveLoginUserDTO() {
        UserDTO userDTO = new UserDTO("testqa20@gmail.com", "123456Aa$");
        app.getUserHelper().login(userDTO);
        flagLogin = true;
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test(groups = {"regression"})
    public void positiveLoginUserDTOWith() {
        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail("testqa20@gmail.com")
                .withPassword("123456Aa$");
        app.getUserHelper().login(userDTOWith);
        flagLogin = true;
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }
//dataProvider = "positiveDataLogin", dataProviderClass = DataProviderLogin.class
    @Test(enabled = false,dataProvider = "loginCSV", dataProviderClass = DataProviderLogin.class)
    public void positiveLogin(UserDtoLombok userDP) {
        app.getUserHelper().loginUserDtoLombok(userDP);
        flagLogin = true;
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }
//groups = {"smoke"},dataProvider = "negativePasswordDataLogin", dataProviderClass = DataProviderLogin.class
    @Test(enabled = false,dataProvider = "negativeLoginCSV", dataProviderClass = DataProviderLogin.class )
    public void negativePasswordWithoutSymbol(UserDtoLombok userDP) {
        app.getUserHelper().loginUserDtoLombok(userDP);
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test(groups = {"smoke"})
    public void negativePasswordWithoutNumbers() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("ddsdhjAa$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test()
    public void negativePasswordWithoutLetters() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("12345678$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        flagPopUp = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
}
