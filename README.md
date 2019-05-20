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
    1. Make "implementation 'com.google.firebase:firebase-core:16.0.8'" active at build.gradle file
### Configure Crashlytics
    1. Configure crashlytics at firebase console. You can follow [this](https://firebase.google.com/docs/crashlytics/get-started?platform=android#setup-in-console)
    2. Configure firebase core 
    3. Make "apply plugin: 'com.google.gms.google-services'", "implementation 'com.crashlytics.sdk.android:crashlytics:2.9.9'", "apply plugin: 'io.fabric'" active at build.gradle file 
    4. Make "import com.google.firebase.analytics.FirebaseAnalytics" active at BaseApplication class
    5. Make "FirebaseAnalytics.getInstance(context)" active at initiate() method of BaseApplication class
    
## Authors
* Mohd. Asfaq-E-Azam Rifat, Executive Software Engineer - [Rifat](https://github.com/rifat15913)

## Technical Documentation
The technical documentation is located [here.](app/documentation/)

## Releases
Please visit [this link](app/release/) to get the latest build.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.