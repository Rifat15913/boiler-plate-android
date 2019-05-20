# Boiler-plate-android

LusoSmile is a native application which books appointments at different clinics.

## Built With
### Languages
    * Kotlin
    * Java
    * XML
    * MarkDown
    
## How to Configure
### Configure Firebase Core
1. Activate at build.gradle
    * <code>"implementation 'com.google.firebase:firebase-core:16.0.8'"</code>
### Configure Crashlytics
1. Configure crashlytics at firebase console. To know more, [visit here](http://bit.ly/2VBDluy)
2. Configure firebase core 
3. Activate at build.gradle file 
    * "apply plugin: 'com.google.gms.google-services'"
    * "implementation 'com.crashlytics.sdk.android:crashlytics:2.9.9'"
    * "apply plugin: 'io.fabric'"
4. Activate at BaseApplication class
    * "import com.google.firebase.analytics.FirebaseAnalytics"
    * "FirebaseAnalytics.getInstance(context)"
    
## Authors
* Mohd. Asfaq-E-Azam Rifat, Executive Software Engineer - [Rifat](https://github.com/rifat15913)

## Technical Documentation
The technical documentation is located [here.](app/documentation/)

## Releases
Please visit [this link](app/release/) to get the latest build.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.