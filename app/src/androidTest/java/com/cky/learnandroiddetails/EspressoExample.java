package com.cky.learnandroiddetails;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cky.learnandroiddetails.SpannableStringLearn.SpannableStringMainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by cuikangyuan on 2017/3/27.
 */

@RunWith(AndroidJUnit4.class)
public class EspressoExample {

    @Rule
    public ActivityTestRule<SpannableStringMainActivity> mActivityTestRule
            = new ActivityTestRule(SpannableStringMainActivity.class);

    @Test
    public void testAttemptLogin() {
        // Type text and then press the button.
        SystemClock.sleep (1000000);

    }
}
