package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "nav-notes-tab")
    private WebElement navigateToNotesTab;

    @FindBy(id = "add-note-button")
    private WebElement addNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "save-note-button")
    private WebElement saveNoteButton;

    @FindBy(className = "note-title-content")
    private WebElement noteTitleContent;

    @FindBy(className = "note-description-content")
    private WebElement noteDescriptionContent;

    @FindBy(id = "edit-note-button")
    private WebElement editNoteButton;

    @FindBy(id = "delete-note-button")
    private WebElement deleteNoteButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navigateToCredentialsTab;

    @FindBy(id = "add-credential-button")
    private WebElement addCredentialButton;

    @FindBy(id = "save-credential-button")
    private WebElement saveCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialURLInput;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(className = "credential-url-content")
    private WebElement credentialURLContent;

    @FindBy(className = "credential-username-content")
    private WebElement credentialUsernameContent;

    @FindBy(className = "credential-password-content")
    private WebElement credentialPasswordContent;

    @FindBy(id = "edit-credential-button")
    private WebElement editCredentialButton;

    @FindBy(id = "delete-credential-button")
    private WebElement deleteCredentialButton;

    private final WebDriverWait webDriverWait;

    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 2);
        webDriverWait.until(ExpectedConditions.titleContains("Home"));

    }

    public void goToNotesTab() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
        navigateToNotesTab.click();

    }

    public void createNote(String title, String description) {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("add-note-button")));

        addNoteButton.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("save-note-button")));

        noteTitleInput.sendKeys(title);
        noteDescriptionInput.sendKeys(description);
        saveNoteButton.click();
    }

    public Note getNote() {

        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("note-title-content")));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("note-description-content")));
        } catch (TimeoutException timeoutException) {
            return null;
        }

        Note note = new Note();
        note.setNoteTitle(noteTitleContent.getText());
        note.setNoteDescription(noteDescriptionContent.getText());
        return note;
    }

    public void editNote(String newTitle, String newDescription) {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(editNoteButton));
        editNoteButton.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("save-note-button")));

        noteTitleInput.click();
        noteTitleInput.clear();
        noteTitleInput.sendKeys(newTitle);

        noteDescriptionInput.click();
        noteDescriptionInput.clear();
        noteDescriptionInput.sendKeys(newDescription);

        saveNoteButton.click();
    }

    public void deleteNote() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteNoteButton));
        deleteNoteButton.click();
    }

    public void goToCredentialsTab() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(navigateToCredentialsTab));
        navigateToCredentialsTab.click();
    }

    public void createCredential(String url, String userName, String password) {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(addCredentialButton));
        addCredentialButton.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton));

        credentialURLInput.sendKeys(url);
        credentialUsernameInput.sendKeys(userName);
        credentialPasswordInput.sendKeys(password);
        saveCredentialButton.click();
    }

    public Credential getCredentialEncrypted() {

        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("credential-url-content")));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("credential-username-content")));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("credential-password-content")));
        } catch (TimeoutException timeoutException) {
            return null;
        }

        Credential credential = new Credential();
        credential.setUrl(credentialURLContent.getText());
        credential.setUsername(credentialUsernameContent.getText());
        credential.setPassword(credentialPasswordContent.getText());
        return credential;
    }

    public Credential getCredentialDecrypted() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editCredentialButton));
        editCredentialButton.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));

        Credential credential = new Credential();
        credential.setUrl(credentialURLInput.getAttribute("value"));
        credential.setUsername(credentialUsernameInput.getAttribute("value"));
        credential.setPassword(credentialPasswordInput.getAttribute("value"));
        return credential;
    }

    public void editCredential(String newUrl, String newUserName, String newPassword) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(editCredentialButton));
        editCredentialButton.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-password")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveCredentialButton));

        credentialURLInput.click();
        credentialURLInput.clear();
        credentialURLInput.sendKeys(newUrl);

        credentialUsernameInput.click();
        credentialUsernameInput.clear();
        credentialUsernameInput.sendKeys(newUserName);

        credentialPasswordInput.click();
        credentialPasswordInput.clear();
        credentialPasswordInput.sendKeys(newPassword);

        saveCredentialButton.click();
    }

    public void deleteCredential() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteCredentialButton));
        deleteCredentialButton.click();
    }
}