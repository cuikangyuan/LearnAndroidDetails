package com.cky.learnandroiddetails;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cky.learnandroiddetails.UnitTestExample.TestUnitTestAct;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * 作者：cky
 * 时间：2016/9/28 15:18
 * 描述：
 */
@RunWith(AndroidJUnit4.class)
public class MyActivityTest {

    @Rule
    public ActivityTestRule<TestUnitTestAct> mActivityTestRule = new ActivityTestRule<>(TestUnitTestAct.class);

    @Test
    public void testGreat() {

        onView(withId(R.id.edit_text)).perform(typeText("Tim"), closeSoftKeyboard());

        SystemClock.sleep (5000);

        onView(withText("Hello")).perform(click());

        SystemClock.sleep (5000);

        onView(withId(R.id.text_view)).check(matches(withText("Hello, Tim")));

        SystemClock.sleep (5000);

    }
}
