package com.hfm.vdc;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigInteger;
import java.util.Random;

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
        Random random = new Random(1500);

        int c = 0;
        while (c++ < 100) {
            onView(ViewMatchers.withId((R.id.editText_fname))).perform(ViewActions.typeText(String.valueOf(random.nextInt(5000))), ViewActions.closeSoftKeyboard());
            onView(ViewMatchers.withId((R.id.editText_lname))).perform(ViewActions.typeText(String.valueOf(random.nextInt(5000))), ViewActions.closeSoftKeyboard());
            onView(ViewMatchers.withId((R.id.editText_phone))).perform(ViewActions.typeText(String.valueOf(random.nextInt(5000))), ViewActions.closeSoftKeyboard());
            onView(ViewMatchers.withId((R.id.editText_job))).perform(ViewActions.typeText(String.valueOf(random.nextInt(5000))), ViewActions.closeSoftKeyboard());
            onView(ViewMatchers.withId((R.id.editText_email))).perform(ViewActions.typeText(new BigInteger(32, random).toString()), ViewActions.closeSoftKeyboard());
            onView(ViewMatchers.withId(R.id.button_add)).perform(ViewActions.click());
        }
    }
}
