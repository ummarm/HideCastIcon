HideCastIcon - small overlay app to mask top-right cast icon on Android TV / Chromecast with Google TV.

How to build:
1. Open this folder in Android Studio (File -> Open -> choose /path/to/HideCastIcon_project).
2. Let Android Studio sync Gradle and download required SDKs.
3. Build -> Build Bundle(s) / APK(s) -> Build APK(s).
4. Locate the generated APK in app/build/outputs/apk/debug/app-debug.apk (or release if you build release).
5. Install on device:
   adb install -r app/build/outputs/apk/debug/app-debug.apk
6. Grant overlay permission via ADB:
   adb shell appops set com.hidecasticon SYSTEM_ALERT_WINDOW allow
7. Start the service manually (or reboot to let BootReceiver start it):
   adb shell am start -n com.hidecasticon/.MainActivity

Notes:
- You may need to enable "Unknown sources" for the file manager or use adb install.
- If Android Studio asks for Gradle wrapper, use the default it suggests; this project uses Kotlin DSL files.
- MinSDK is 26 so it will run on modern Chromecast with Google TV devices.
