package com.gsnathan.compass;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.View;
import android.widget.TextView;

import com.franmontiel.attributionpresenter.AttributionPresenter;
import com.franmontiel.attributionpresenter.entities.Attribution;
import com.franmontiel.attributionpresenter.entities.License;

public class AboutActivity extends AppCompatActivity {

    TextView versionView;   //shows the version
    private final String APP_VERSION_RELEASE = "Version " + Utils.getAppVersion();   //contains Version + the version number
    private final String APP_VERSION_DEBUG = "Version " + Utils.getAppVersion() + "-debug";   //contains Version + the version number + debug
    private Toolbar toolbar;
    private CoordinatorLayout aboutLay;
    private boolean useDarkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initUI();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);
    }

    private void changeTheme() {
        // Use the chosen theme
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        useDarkTheme = preferences.getBoolean("theme_pref", false);
        if (useDarkTheme) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
    }

    private void initUI() {
        //initialize the textview
        versionView = (TextView) findViewById(R.id.text_version);
        //initialize the toolbar
        ((TextView)findViewById(R.id.toolbar_view)).setVisibility(View.GONE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // check if app is debug
        if (BuildConfig.DEBUG)
            versionView.setText(APP_VERSION_DEBUG);
        else    //if app is release
            versionView.setText(APP_VERSION_RELEASE);

        aboutLay = (CoordinatorLayout) findViewById(R.id.about_layout);
        if (useDarkTheme)
            aboutLay.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        else
            aboutLay.setBackgroundColor(getResources().getColor(R.color.colorWhite));

    }

    public void showPriv(View v) {
        startActivity(Utils.webIntent("https://github.com/JavaCafe01/MaterialCompass/blob/master/privacy_policy.md"));
    }

    public void showLicense(View v) {
        startActivity(Utils.webIntent("https://github.com/JavaCafe01/MaterialCompass/blob/master/LICENSE"));
    }

    public void showLibraries(View v) {

        //Library builder
        //Library title, copyright notice, license type, website url

        AttributionPresenter attributionPresenter = new AttributionPresenter.Builder(this)

                .addAttributions(
                        new Attribution.Builder("AttributionPresenter")
                                .addCopyrightNotice("Copyright 2017 Francisco José Montiel Navarro")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/franmontiel/AttributionPresenter")
                                .build()
                )

                .addAttributions(
                        new Attribution.Builder("material-intro")
                                .addCopyrightNotice("Copyright 2017 Jan Heinrich Reimer")
                                .addLicense(License.MIT)
                                .setWebsite("https://github.com/heinrichreimer/material-intro")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Android Open Source Project")
                                .addCopyrightNotice("Copyright 2016 The Android Open Source Project")
                                .addLicense(License.APACHE)
                                .setWebsite("http://developer.android.com/tools/support-library/index.html")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Android Support Libraries")
                                .addCopyrightNotice("Copyright 2016 The Android Open Source Project")
                                .addLicense(License.APACHE)
                                .setWebsite("http://developer.android.com/tools/support-library/index.html")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("CardView")
                                .addCopyrightNotice("Copyright 2016 The Android Open Source Project")
                                .addLicense(License.APACHE)
                                .setWebsite("https://developer.android.com/reference/android/support/v7/widget/CardView.html")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Material Ripple Layout")
                                .addCopyrightNotice("Copyright 2015 Balys Valentukevicius")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/balysv/material-ripple")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("Android-RateThisApp")
                                .addCopyrightNotice("Copyright 2013-2017 Keisuke Kobayashi")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/kobakei/Android-RateThisApp")
                                .build()
                )
                .addAttributions(
                        new Attribution.Builder("CircleImageView")
                                .addCopyrightNotice("Copyright Henning Dodenhof")
                                .addLicense(License.APACHE)
                                .setWebsite("https://github.com/hdodenhof/CircleImageView")
                                .build()
                )
                .build();

        //show license dialogue
        attributionPresenter.showDialog("Open Source Libraries");
    }

    public void emailDev(View v) {
        startActivity(Utils.emailIntent("gokulswamilive@gmail.com", "Material Compass", APP_VERSION_RELEASE, "Send email..."));
    }

    public void navToGit(View v) {
        startActivity(Utils.webIntent("https://github.com/JavaCafe01"));
    }

    public void navToSourceCode(View v) {
        startActivity(Utils.webIntent("https://github.com/JavaCafe01/MaterialCompass"));
    }


    @Override
    public void onBackPressed() {
        startActivity(Utils.navIntent(getApplicationContext(), MainActivity.class));
    }

}
