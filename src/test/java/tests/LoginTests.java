package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDTOWith;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest{

    @BeforeMethod(alwaysRun = true)
    public void preconditionsLogin() {
        logoutIfLogin();

    }

    @AfterMethod(alwaysRun = true)
    public void postConditionsLogin() {
        app.getUserHelper().clickOkPopUpSuccessLogin();
        app.getUserHelper().pause(5);
        app.getUserHelper().refresh();

    }

    @Test(groups = {"smoke"})
    public void positiveLoginUserDTO() {
        UserDTO userDTO = new UserDTO("testqa20@gmail.com", "123456Aa$");
        app.getUserHelper().login(userDTO);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test(groups = {"regression"})
    public void positiveLoginUserDTOWith() {
        UserDTOWith userDTOWith = new UserDTOWith()
                .withEmail("testqa20@gmail.com")
                .withPassword("123456Aa$");
        app.getUserHelper().login(userDTOWith);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }
//dataProvider = "positiveDataLogin", dataProviderClass = DataProviderLogin.class
    @Test(dataProvider = "loginCSV", dataProviderClass = DataProviderLogin.class)
    public void positiveLogin(UserDtoLombok userDP) {

//         UserDtoLombok userDtoLombok = UserDtoLombok.builder()
//            .email("testqa20@gmail.com")
//            .password("123456Aa$")
//            .build();

        app.getUserHelper().loginUserDtoLombok(userDP);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }
//groups = {"smoke"},dataProvider = "negativePasswordDataLogin", dataProviderClass = DataProviderLogin.class
    @Test(dataProvider = "negativeLoginCSV", dataProviderClass = DataProviderLogin.class )
    public void negativePasswordWithoutSymbol(UserDtoLombok userDP) {
//        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
//                .email("testqa20@gmail.com")
//                .password("123456Aaa")
//                .build();
        app.getUserHelper().loginUserDtoLombok(userDP);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test()
    public void negativePasswordWithoutNumbers() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("ddsdhjAa$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test()
    public void negativePasswordWithoutLetters() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("12345678$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
}
