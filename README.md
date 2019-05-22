# Table of Contents  
- [Tree](#tree)
- [Built With](#built-with)
    - [Languages](#languages)
- [How to Configure](#how-to-configure)
    - [Configure Base Project](#configure-base-project)
        - [Change Application Launcher Icon](#change-application-launcher-icon)
        - [Change Application Name ](#change-application-name)
        - [Change Package Name](#change-package-name)
        - [Change Font](#change-font)
    - [Configure Firebase Core](#configure-firebase-core)
    - [Configure Push Notification](#configure-push-notification)
    - [Configure Crashlytics](#configure-crashlytics)
- [Extras](#extras)
    - [Fetch Signing Fingerprint](#fetch-signing-fingerprint)
    - [Fetch Android Hash Key](#fetch-android-hash-key)
    - [Show Application Local Data](#show-application-local-data)
- [Authors](#authors)
- [Technical Documentation](#technical-documentation)
- [Releases](#releases)
- [Contributing](#contributing)

# Tree

Tree is a native application which contains all the essential codes (boiler-plate) to kick start an Android project.

# Built With
## Languages
1. <code>Kotlin</code>
2. <code>Java</code>
3. <code>XML</code>
4. <code>MarkDown</code>
    
# How to Configure

## Configure Base Project
### Change Application ID
| No | Action | Screenshot |
| :---: | :---: | :---: |
| 1 | Go to <code>build.gradle(app)</code> file and copy the <code>applicationId</code> | ![Visual](readme-images/appid01.PNG) | 
| 2 | Go to <code>Title Bar Menu > Edit > Find > Replace in Path</code> | ![Visual](readme-images/appid02.PNG) |
| 3 | Paste the old application id in the first field and write down the new application id in the second field. Then press <code>Replace All</code> | ![Visual](readme-images/appid03.PNG) | 
| 4 | If alert dialog pops up, then click on <code>Replace</code> | ![Visual](readme-images/appid04.PNG) |
| 5 | <ol><li>Wait</li><li>Go to <code>Build > Clean Project</code></li><li>Sync gradle</li></ol> | |

### Change Package Name
| No | Action | Screenshot |
| :---: | :---: | :---: |
| 1 | Open the project, Select the <code>Project</code> type as file structure(top left) | ![Visual](readme-images/refactor01.PNG) | 
| 2 | Right click and go to <code>Refactor > Rename</code> | ![Visual](readme-images/refactor02.PNG) |
| 3 | Put new app name | ![Visual](readme-images/refactor03.PNG) | 
| 4 | Click on <code>Do Refactor</code> if the dialog pops up | ![Visual](readme-images/refactor04.PNG) |
| 5 | Wait | ![Visual](readme-images/refactor05.PNG) |

### Change Application Launcher Icon
| No | Action | Screenshot |
| :---: | :---: | :---: |
| 1 | Open the project, Select the <code>Android</code> type as file structure(top left). Then right click on the <code>res</code> folder. | ![Visual](readme-images/launcher01.PNG) | 
| 2 | Go to <code>New > Image Asset</code> | ![Visual](readme-images/launcher02.PNG) |
| 3 | There are two main tabs here, <code>Foreground Layer</code> & <code>Background Layer</code>. We can select assets for foreground and background from these tabs. | ![Visual](readme-images/launcher03.PNG) | 
| 4 | (Optional) Browse images (vector is also acceptable) clicking on the folder icon | ![Visual](readme-images/launcher04.PNG) |
| 5 | Choose the asset | ![Visual](readme-images/launcher05.PNG) |
| 6 | <code>Show Grid</code> can be ticked in order to see the boundaries. Other settings can also be modified. | ![Visual](readme-images/launcher06.PNG) |
| 7 | Asset can be picked for background, in the <code>Background Layer</code> tab. Click on next if it seems okay. | ![Visual](readme-images/launcher07.PNG) |
| 8 | Finish the dialog and your launcher icon is changed now | ![Visual](readme-images/launcher08.PNG) |

### Change Application Name 
| No | Action | Screenshot |
| :---: | :---: | :---: |
| 1 | Change <code>app_name</code> from <code>string.xml</code> of all the languages | ![Visual](readme-images/refactor00.PNG) | 

### Change Font
| No | Action | Screenshot |
| :---: | :---: | :---: |
| 1 | Overwrite <code>regular.ttf</code> from <code>font</code> directory in order to achieve text changes globally in the application of regular fonts. Additional fonts can be added; i.e. <code>bold</code>, <code>italic</code> etc. | ![Visual](readme-images/font01.PNG)|
    
## Configure Firebase Core
1. Visit [here](https://tinyurl.com/yyebfbnu) and complete step 1-3
2. Activate at <code>build.gradle</code>
    * <code>implementation 'com.google.firebase:firebase-core:16.0.8'</code>
    
## Configure Push Notification
1. [Configure firebase core](#configure-firebase-core)  
2. Uncomment at <code>build.gradle</code> file 
    * <code>implementation 'com.google.firebase:firebase-messaging:17.6.0'</code>
    * <code>apply plugin: 'com.google.gms.google-services'</code>
3. Uncomment at <code>NotificationService.kt</code>
    * All the commented lines
4. Uncomment at <code>AndroidManifest.xml</code>
    * <code>com.lusosmile.main.data.remote.service.NotificationService</code>
5. To know about getting the device registration token, [visit here](https://tinyurl.com/y6lndsa4)

## Configure Crashlytics
1. [Configure firebase core](#configure-firebase-core)
2. Configure crashlytics at firebase console. To know more, [visit here](http://bit.ly/2VBDluy) 
3. Uncomment at <code>build.gradle</code> 
    * <code>apply plugin: 'io.fabric'</code>
    * <code>implementation 'com.crashlytics.sdk.android:crashlytics:2.9.9'</code>
    * <code>apply plugin: 'com.google.gms.google-services'</code>
4. Uncomment at <code>BaseApplication.kt</code>
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
1. Uncomment at <code>BaseApplication.kt</code>
    * <code>import com.boilerplate.utils.helper.DataUtils</code>
    * <code>DataUtils.getAndroidHashKey()</code> at <code>onCreate()</code>
2. Connect a device via <code>adb</code> and install the application into it
3. Open the application at device
4. Open Logcat from Android Studio
5. Filter the <code>Info</code> logs
6. Search for the tag <code>Hash</code>
7. Get the hash key depending on the build variant; i.e: <code>debug, release</code>

## Show Application Local Data
1. Uncomment at <code>build.gradle</code>
    * <code>debugImplementation 'com.awesomedroidapps:inappstoragereader:1.0.2'</code>
2. Visit <code>App Data</code> from your device. It will have similar icon as the app launcher.

# Authors
* Mohd. Asfaq-E-Azam Rifat, Executive Software Engineer - [Rifat](https://github.com/rifat15913)

# Technical Documentation
The technical documentation is located [here.](app/documentation/)

# Releases
Please visit [this link](app/release/) to get the latest build.

# Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.