package com.redsponge.coolapp.menu;

import com.redsponge.coolapp.projects.CatchTheButtonActivity;
import com.redsponge.coolapp.projects.ColourBarsActivity;
import com.redsponge.coolapp.projects.CookieClickerActivity;
import com.redsponge.coolapp.projects.ImageSelectorActivity;
import com.redsponge.coolapp.projects.ImageCapturerActivity;
import com.redsponge.coolapp.projects.MathChallengeActivity;
import com.redsponge.coolapp.projects.RandomColourGenerator;
import com.redsponge.coolapp.projects.RandomNumberActivity;
import com.redsponge.coolapp.projects.SendSMSActivity;

public class MenuEntries {

    public static final MenuEntry[] ENTRIES = {
            new MenuEntry("Random Colour Generator", "0", RandomColourGenerator.class),
            new MenuEntry("Random Number Generator", "1", RandomNumberActivity.class),
            new MenuEntry("Catch The Button", "1.5", CatchTheButtonActivity.class),
            new MenuEntry("Send SMS", "2", SendSMSActivity.class),
            new MenuEntry("Image Capturer", "3", ImageCapturerActivity.class),
            new MenuEntry("Image Selector", "3.5", ImageSelectorActivity.class),
            new MenuEntry("Math Challenge", "4", MathChallengeActivity.class),
            new MenuEntry("Cookie Clicker", "5", CookieClickerActivity.class),
            new MenuEntry("Colour Bars", "6", ColourBarsActivity.class)
    };

}
