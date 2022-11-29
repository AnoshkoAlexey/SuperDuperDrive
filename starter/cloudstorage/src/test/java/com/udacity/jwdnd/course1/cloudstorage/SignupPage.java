package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "buttonSignUp")
    private WebElement buttonSignUp;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void setFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        inputLastName.sendKeys(lastName);
    }

    public void setUserName(String userName) {
        inputUserName.sendKeys(userName);
    }

    public void setPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void clickButtonSignUp() {
        buttonSignUp.click();
    }
}
