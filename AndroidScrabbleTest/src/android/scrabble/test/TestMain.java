package android.scrabble.test;

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
	
	public void testLogin() {
		System.out.println("Test login is running");
		solo.enterText(0, "kohina");
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("Trorolol", MainMenuActivity.class);
	}
	
	public void testInvalidLogin(){
		System.out.println("Test invalid login is running");
		solo.enterText(0, "kooooooooooooooohina");
		solo.clickOnButton("Log in!");
		solo.assertCurrentActivity("Sign up activity", SignupActivity.class);
	}
}
