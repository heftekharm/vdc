package com.hfm.vdc;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
/**
 * Created by Hosein on 4/27/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityEspTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void ensure_activity_can_handle_many_add_operation() {
        onView(ViewMatchers.withId((R.id.editText_fname))).perform(ViewActions.typeText("Hosein"), ViewActions.closeSoftKeyboard());
    }
}
