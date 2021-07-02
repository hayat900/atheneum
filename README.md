# Atheneum

### Step 1:To run this app,open cmd in windows or terminal in linux and then type the following
## git clone https://github.com/hayat900/atheneum.git
You can also run this app by downloading the folder attached.
### Step 2:Open the downloaded project in android studio.
### Step 3:In Android Studio, select Run > Run ‘app' or click the Run icon in the toolbar.The icon will change when your app is already running.In Run > Select Device, under Available devices,select the device on which you would like to install the app.

There are two modules in this app.1.Admin module  2.User Module
To run admin module,give username and password as admin.

To run user module,register with username,email id and password.



Libraries used:
    'androidx.appcompat:appcompat:1.2.0'
    'com.google.android.material:material:1.3.0'
    'androidx.constraintlayout:constraintlayout:2.0.4'
    'com.google.firebase:firebase-firestore:22.1.2'
    'junit:junit:4.+'
    'androidx.test.ext:junit:1.1.2'
    'androidx.test.espresso:espresso-core:3.3.0'
    'androidx.appcompat:appcompat:1.2.0'
    'com.google.android.material:material:1.3.0'
    'androidx.constraintlayout:constraintlayout:2.0.4'
    'com.google.firebase:firebase-storage:19.2.2'
    'com.google.firebase:firebase-firestore:22.1.2'
    'com.google.firebase:firebase-database:19.7.0'
    'junit:junit:4.+'
    'androidx.test.ext:junit:1.1.2'
    'androidx.test.espresso:espresso-core:3.3.0'
    'androidx.multidex:multidex:2.0.1'
    'androidx.recyclerview:recyclerview:1.1.0'
    'androidx.cardview:cardview:1.0.0'
    'com.firebaseui:firebase-ui-database:6.2.1'
    'com.github.bumptech.glide:glide:4.11.0'
    'de.hdodenhof:circleimageview:3.1.0'
    "com.google.firebase:firebase-firestore:11.4.2"
    "com.firebaseui:firebase-ui-auth:3.0.0"
    "com.firebaseui:firebase-ui-firestore:3.0.0"
    'com.firebaseui:firebase-ui-firestore:6.1.0'
    'com.orhanobut:dialogplus:1.11@aar'
    
Step 1: Create a Firebase project
Before you can add Firebase to your Android app, you need to create a Firebase project to connect to your Android app. 

Step 2: Register your app with Firebase
To use Firebase in your Android app, you need to register your app with your Firebase project. Registering your app is often called "adding" your app to your project.    
Steps to register:   
a.Go to the Firebase console.
b.In the center of the project overview page, click the Android icon (plat_android) or Add app to launch the setup workflow.
c.Enter your app's package name in the Android package name field.
d.(Optional) Enter other app information: App nickname and Debug signing certificate SHA-1.
e.Click Register app.

Step 3: Add a Firebase configuration file
a.Add the Firebase Android configuration file to your app:
Click Download google-services.json to obtain your Firebase Android config file (google-services.json).
Move your config file into the module (app-level) directory of your app.
b.To enable Firebase products in your app, add the google-services plugin to your Gradle files.
In your root-level (project-level) Gradle file (build.gradle), add rules to include the Google Services Gradle plugin. Check that you have Google's Maven repository, as well.
In your module (app-level) Gradle file (usually app/build.gradle), apply the Google Services Gradle plugin:

Step 4: Add Firebase SDKs to your app
Using the Firebase Android BoM, declare the dependencies for the Firebase products that you want to use in your app. Declare them in your module (app-level) Gradle file (usually app/build.gradle).
Sync your app to ensure that all dependencies have the necessary versions.

### In firebase(cloud firestore) create 4 tables as follows:
admin,students,requests and books

