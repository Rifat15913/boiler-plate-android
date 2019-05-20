# Boiler-plate-android

LusoSmile is a native application which books appointments at different clinics.

## Built With
### Languages
    * Kotlin
    * Java
    * XML
    * MarkDown
    
## How to Configure
### Configure Base Project
1. Change "app_name" from string.xml of all the languages
![Visual](readme-images/refactor00.PNG)
2. Change package name according to the new app name
    No | Action | Screenshot
     --- | --- | --- 
     A | Open the project, Select the "Project" type as file structure(top left) | ![Visual](readme-images/refactor01.PNG) 
     B | Right click and go to Refactor > Rename | ![Visual](readme-images/refactor02.PNG) 
     C | Put new app name | ![Visual](readme-images/refactor03.PNG) 
     D | Click on "Do Refactor" if the dialog pops up | ![Visual](readme-images/refactor04.PNG) 
     E | Wait | ![Visual](readme-images/refactor05.PNG) 
### Configure Firebase Core
1. Activate at build.gradle
    * <code>"implementation 'com.google.firebase:firebase-core:16.0.8'"</code>
### Configure Crashlytics
1. Configure crashlytics at firebase console. To know more, [visit here](http://bit.ly/2VBDluy)
2. Configure firebase core 
3. Activate at build.gradle file 
    * <code>"apply plugin: 'io.fabric'"</code>
    * <code>"implementation 'com.crashlytics.sdk.android:crashlytics:2.9.9'"</code>
    * <code>"apply plugin: 'com.google.gms.google-services'"</code>
4. Activate at BaseApplication class
    * <code>"import com.google.firebase.analytics.FirebaseAnalytics"</code>
    * <code>"FirebaseAnalytics.getInstance(context)"</code>
    
## Authors
* Mohd. Asfaq-E-Azam Rifat, Executive Software Engineer - [Rifat](https://github.com/rifat15913)

## Technical Documentation
The technical documentation is located [here.](app/documentation/)

## Releases
Please visit [this link](app/release/) to get the latest build.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.