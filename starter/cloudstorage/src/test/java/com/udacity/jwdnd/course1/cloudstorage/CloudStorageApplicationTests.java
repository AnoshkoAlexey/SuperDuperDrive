package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	// New Selenium tests
	@Test
	public void testUnauthorizedUserAccess() {

		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("http://localhost:" + this.port + "/signup", driver.getCurrentUrl());

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertNotEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());

		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	@Test
	public void testHomePageAccess() {

		doMockSignUp("HomePageAccess", "test", "HomePageAccess", "123");
		doLogIn("HomePageAccess", "123");
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
		Assertions.assertEquals("Home", driver.getTitle());

		assert driver.getTitle().equals("Home");
		driver.findElement(By.id("logout-button")).click();

		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertNotEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());
		Assertions.assertNotEquals("Home", driver.getTitle());
	}

	@Test
	public void testNoteCreation() throws InterruptedException {
		doMockSignUp("NoteCreation", "test", "NoteCreation", "123");
		doLogIn("NoteCreation", "123");

		String title = "title";
		String description = "description";

		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		homePage.createNote(title, description);

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToNotesTab();
		Note note = homePage.getNote();

		Assertions.assertEquals(title, note.getNoteTitle());
		Assertions.assertEquals(description, note.getNoteDescription());
	}

	@Test
	public void testNoteEdition() throws InterruptedException {

		doMockSignUp("NoteEdition", "test", "NoteEdition", "123");
		doLogIn("NoteEdition", "123");

		String title = "title";
		String description = "description";
		String newTitle = "new title!";
		String newDescription = "new description!";

		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		homePage.createNote(title, description);

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToNotesTab();
		homePage.editNote(newTitle, newDescription);

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToNotesTab();
		Note newNote = homePage.getNote();

		Assertions.assertEquals(newTitle, newNote.getNoteTitle());
		Assertions.assertEquals(newDescription, newNote.getNoteDescription());
	}

	@Test
	public void testNoteDeletion() throws InterruptedException {

		doMockSignUp("NoteDeletion", "test", "NoteDeletion", "123");
		doLogIn("NoteDeletion", "123");

		String testTitle = "title";
		String testDescription = "description";

		HomePage homePage = new HomePage(driver);
		homePage.goToNotesTab();
		homePage.createNote(testTitle, testDescription);

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToNotesTab();
		homePage.deleteNote();

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToNotesTab();
		Note note = homePage.getNote();

		Assertions.assertNull(note);
	}

	@Test
	public void testCredentialCreation() throws InterruptedException {

		doMockSignUp("CredentialCreation", "test", "CredentialCreation", "123");
		doLogIn("CredentialCreation", "123");

		String url = "http:test.com";
		String userName = "user";
		String password = "password";

		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.createCredential(url, userName, password);

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		Credential credential = homePage.getCredentialEncrypted();

		Assertions.assertEquals(url, credential.getUrl());
		Assertions.assertEquals(userName, credential.getUsername());

		Assertions.assertNotEquals(password, credential.getPassword());
	}

	@Test
	public void testCredentialEdition() throws InterruptedException {

		doMockSignUp("CredentialEdition", "test", "CredentialEdition", "123");
		doLogIn("CredentialEdition", "123");

		String url = "url";
		String username = "username";
		String password = "password";
		String newURL = "newUrl";
		String newUsername = "new username";
		String newPassword = "new password";

		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.createCredential(url, username, password);

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		Credential credential = homePage.getCredentialDecrypted();

		Assertions.assertEquals(url, credential.getUrl());
		Assertions.assertEquals(username, credential.getUsername());
		Assertions.assertEquals(password, credential.getPassword());

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.editCredential(newURL, newUsername, newPassword);

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		Credential newCredential = homePage.getCredentialDecrypted();

		Assertions.assertEquals(newURL, newCredential.getUrl());
		Assertions.assertEquals(newUsername, newCredential.getUsername());
		Assertions.assertEquals(newPassword, newCredential.getPassword());
	}

	@Test
	public void testCredentialDeletion() throws InterruptedException {
		doMockSignUp("CredentialDeletion", "test", "CredentialDeletion", "123");
		doLogIn("CredentialDeletion", "123");

		String url = "url";
		String username = "username";
		String password = "password";

		HomePage homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.createCredential(url, username, password);

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		homePage.deleteCredential();

		driver.get("http://localhost:" + this.port + "/home");
		homePage = new HomePage(driver);
		homePage.goToCredentialsTab();
		Credential credential = homePage.getCredentialEncrypted();

		Assertions.assertNull(credential);
	}

}
