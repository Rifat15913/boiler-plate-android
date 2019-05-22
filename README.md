# Tree

Tree is a native application which contains all the essential codes (boiler-plate) to kick start an Android project.

# Built With
## Languages
1. Kotlin
2. Java
3. XML
4. MarkDown
    
# How to Configure
## Configure Base Project
### Change Application Name
1. Change "app_name" from string.xml of all the languages
    ![Visual](readme-images/refactor00.PNG)
### Change Package Name
| No | Action | Screenshot |
| :---: | :---: | :---: |
| 1 | Open the project, Select the "Project" type as file structure(top left) | ![Visual](readme-images/refactor01.PNG) | 
| 2 | Right click and go to Refactor > Rename | ![Visual](readme-images/refactor02.PNG) |
| 3 | Put new app name | ![Visual](readme-images/refactor03.PNG) | 
| 4 | Click on "Do Refactor" if the dialog pops up | ![Visual](readme-images/refactor04.PNG) |
| 5 | Wait | ![Visual](readme-images/refactor05.PNG) |
## Configure Firebase Core
1. Activate at <code>build.gradle</code>
    * <code>implementation 'com.google.firebase:firebase-core:16.0.8'</code>
## Configure Crashlytics
1. Configure crashlytics at firebase console. To know more, [visit here](http://bit.ly/2VBDluy)
2. Configure firebase core 
3. Activate at <code>build.gradle</code> file 
    * <code>apply plugin: 'io.fabric'</code>
    * <code>implementation 'com.crashlytics.sdk.android:crashlytics:2.9.9'</code>
    * <code>apply plugin: 'com.google.gms.google-services'</code>
4. Activate at <code>BaseApplication</code> class
    * <code>import com.google.firebase.analytics.FirebaseAnalytics</code>
    * <code>FirebaseAnalytics.getInstance(context)</code>
# Extras
## Fetch Signing Fingerprint
Release:
1. Add the path of <code>keytool</code> from <code>JDK</code> to System Variable <code>PATH</code>
2. Go to <code>keystore</code> folder
3. Press <code>Shift + Right</code> Click
4. Start command prompt
5. Put command <code>keytool -exportcert -alias ALIAS_NAME -keystore KEYSTORE_NAME_WITH_EXTENSION -list -v</code>

Debug:
1. Go to right side of Android Studio.
2. Execute <code>Gradle > root > Tasks > android > signingReport</code>
## Fetch Android Hash Key
1. Activate at <code>BaseApplication</code> class
    * <code>import com.boilerplate.utils.helper.DataUtils</code>
    * <code>DataUtils.getAndroidHashKey()</code> at <code>onCreate()</code>
2. Connect a device via <code>adb</code> and install the application into it
3. Open the application at device
4. Open Logcat from Android Studio
5. Filter the <code>Info</code> logs
6. Search for the tag <code>Hash</code>
7. Get the hash key depending on the build variant; i.e: <code>debug, release</code>
# Authors
* Mohd. Asfaq-E-Azam Rifat, Executive Software Engineer - [Rifat](https://github.com/rifat15913)

# Technical Documentation
The technical documentation is located [here.](app/documentation/)

# Releases
Please visit [this link](app/release/) to get the latest build.

# Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.