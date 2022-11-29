package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String userName) {
        inputUsername.sendKeys(userName);
    }

    public void setPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void clickSubmit(){
        submitButton.click();
    }

    public void clickSignupLink() {
        this.signupLink.click();
    }
}
