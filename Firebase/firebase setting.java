  compile 'com.firebase:firebase-client-android:2.5.2+'
  
  
  android {
    ...
    packagingOptions {
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE-FIREBASE.txt'
        exclude 'META-INF/NOTICE'
    }
}

<uses-permission android:name="android.permission.INTERNET" />


@Override
public void onCreate() {                 //in a class extends application
    super.onCreate();
    Firebase.setAndroidContext(this);
    // other setup code
}

//-->add as a new class and refer this class in <application> name tag