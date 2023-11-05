package manager;

import dto.AddCarDTO;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CarHelper extends BaseHelper{
    public CarHelper(WebDriver driver) {
        super(driver);
    }
    By buttonLetTheCarWork = By.xpath("//a[contains(@href, '/let-car-work')]");
    By inputLocationOfTheCar = By.xpath("//input[@placeholder='Enter your address']");
    By inputLocationHaifa = By.xpath("//div[@class='pac-item']//span[@class='pac-matched']");
    By inputManufacture = By.xpath("//input[@id='make']");
    By inputModel = By.xpath("//input[@id='model']");
    By inputYear = By.xpath("//input[@id='year']");
    By inputFuelOption = By.xpath("//option[@value='Hybrid']");
    By carAddedSuccessful = By.xpath("//h1[text()='Car added']");
    By inputSeats = By.xpath("//input[@id='seats']");
    By inputCarClass = By.xpath("//input[@id='class']");
    By inputSerialNumber = By.xpath("//input[@id='serialNumber']");
    By inputPricePerDay = By.xpath("//input[@id='price']");
    By inputAboutCar = By.xpath("//textarea[@id='about']");
    By saveNewCar = By.xpath("//button[@type='submit']");
   // By ok = By.xpath("//button[@type='button']");
    //document.querySelector('#pickUpPlace').autocomplete='on'

    public void addNewCar(AddCarDTO addCarDTO){
      //  clickBase(buttonLetTheCarWork);
        Actions action = new Actions(driver);
        WebElement letTheCar = driver.findElement(buttonLetTheCarWork);
        action.moveToElement(letTheCar).click().perform();
        //action.moveToElement(driver.findElement(buttonLetTheCarWork).click().perform();
        typeTextBase(inputLocationOfTheCar, addCarDTO.getCity());
        clickBase(inputLocationHaifa);
       // action.sendKeys(Keys.TAB).perform();
        typeTextBase(inputManufacture, addCarDTO.getManufacture());
        typeTextBase(inputModel, addCarDTO.getModel());
        typeTextBase(inputYear, addCarDTO.getYear());
        //clickBase(inputFuelOption);
        typeFuel(addCarDTO.getFuel());
        typeTextBase(inputSeats, addCarDTO.getSeats());
        typeTextBase(inputCarClass, addCarDTO.getCarClass());
        typeTextBase(inputSerialNumber, addCarDTO.getSerialNumber());
        typeTextBase(inputPricePerDay, addCarDTO.getPricePerDay());
        typeTextBase(inputAboutCar, addCarDTO.getAbout());
        loadPicture();
        pause(3);
        clickBase(saveNewCar);
        pause(3);
        //clickBase(ok);

    }

    private void loadPicture() {
        driver.findElement(By.xpath("//input[@id='photos']")).
                sendKeys("C:\\QA_Projects\\car1.jpg");
    }

    private void typeFuel(String fuel) {
        String[] fuelTypes = fuel.split(",");//{"Diesel", "Petrol", "Hybrid", "Electric", "Gas"};;
        for (String element:fuelTypes) {
            switch (element) {
                case "Diesel":
                    clickBase(By.xpath("//option[@value='Diesel']"));
                    break;
                case "Petrol":
                    clickBase(By.xpath("//option[@value='Petrol']"));
                    break;
                case "Hybrid":
                    clickBase(By.xpath("//option[@value='Hybrid']"));
                    break;
                case "Electric":
                    clickBase(By.xpath("//option[@value='Electric']"));
                    break;
                case "Gas":
                    clickBase(By.xpath("//option[@value='Gas']"));
                    break;
            }
        }
    }


    public boolean validateCarCreated() {
        return isTextEqual(carAddedSuccessful, "Car added");
    }
}
