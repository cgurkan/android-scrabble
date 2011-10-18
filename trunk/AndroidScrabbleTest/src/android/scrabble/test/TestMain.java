package android.scrabble.test;

import android.scrabble.AboutViewActivity;
import android.scrabble.HelpViewActivity;
import android.scrabble.LoginActivity;
import android.scrabble.MainMenuActivity;
import android.scrabble.SignupActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

public class TestMain extends ActivityInstrumentationTestCase2<MainMenuActivity> {
	
	private Solo solo;

	public TestMain() {
		super(MainMenuActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		try {
			solo.finalize();
		} catch (Throwable e) {
			System.out.println("Throwable error");
		}
	}
	
	public void testLogIn() throws InterruptedException {
		System.out.println("Test login is running");
		solo.enterText(0, "kohina");
		solo.wait(2000);
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("MainMenuActivity", MainMenuActivity.class);
	}
	
	public void testInvalidLogin() throws InterruptedException{
		System.out.println("Test invalid login is running");
		solo.enterText(0, "kooooooooooooooohina");
		solo.wait(2000);
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("Sign up activity", SignupActivity.class);
	}
	
	public void testLogOut() throws InterruptedException{
		solo.enterText(0, "kohina");
		solo.wait(2000);
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("MainMenuActivity", MainMenuActivity.class);
		solo.wait(2000);
		solo.clickOnMenuItem("Log out");
		solo.assertCurrentActivity("Log in Activity", LoginActivity.class);
	}
	
	public void testEditSettings() throws InterruptedException{
		solo.enterText(0, "kohina");
		solo.wait(2000);
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("MainMenuActivity", MainMenuActivity.class);
		solo.wait(2000);
		solo.clickOnButton("Edit settings");
		solo.wait(5000);
		solo.clickInList(1);
		solo.wait(2000);
		solo.clickOnButton("Save settings");
		solo.wait(2000);
		solo.goBack();
		assertTrue(solo.searchButton("BAJS"));
		solo.wait(2000);
		solo.clickOnButton("BAJS");
		solo.wait(2000);
		solo.clickInList(0);
		solo.wait(2000);
		solo.clickOnButton("Save settings");
		solo.wait(2000);
		solo.goBack();
		assertTrue(solo.searchButton("Edit settings"));
	}
	
	public void testAbout() throws InterruptedException{
		solo.enterText(0, "kohina");
		solo.wait(2000);
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("MainMenuActivity", MainMenuActivity.class);
		solo.wait(2000);
		solo.clickOnButton("About");
		solo.assertCurrentActivity("AboutViewActivity", AboutViewActivity.class);
	}
	
	public void testHelp() throws InterruptedException{
		solo.enterText(0, "kohina");
		solo.wait(2000);
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("MainMenuActivity", MainMenuActivity.class);
		solo.wait(2000);
		solo.clickOnButton("Help");
		solo.assertCurrentActivity("HelpViewActivity", HelpViewActivity.class);
	}
	
	public void testNewGame() throws InterruptedException{
		solo.enterText(0, "kohina");
		solo.wait(2000);
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("MainMenuActivity", MainMenuActivity.class);
		solo.wait(2000);
		solo.clickOnButton("New game");
		//TODO: finish here
	}
}