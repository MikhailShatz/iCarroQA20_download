package tests;

import dto.AddCarDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CarTests extends BaseTest{
    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass(){
        if(app.isPageUrlHome()){
            app.getUserHelper().openLoginPage();
        }
        app.getUserHelper().loginUserDtoLombokCar(userDtoLombok);
    }
    @AfterClass(alwaysRun = true)
    public void postConditions(){
        app.navigateToMainPage();
       app.getUserHelper().logout();
    }

    @Test
    public void addNewCarPositive() {
        String serialNumber = randomUtils.generateStringDigits(6);
        //System.out.println("serial number for the new car: " + serialNumber);
        logger.info("serial number for the new car" + serialNumber);
        AddCarDTO addCarDTO = AddCarDTO.builder()
                .about("Nice car")
                .carClass("Premium")
                .city("Haifa, Israel")
                .fuel("Hybrid")
                .manufacture("Ford")
                .model("Kuga")
                .pricePerDay("125")
                .seats("4")
                .serialNumber(serialNumber)
                .year("2013")
                .build();
        app.getCarHelper().addNewCar(addCarDTO);
        Assert.assertTrue(app.getCarHelper().validateCarCreated());
    }
}
