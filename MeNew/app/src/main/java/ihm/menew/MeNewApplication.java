package ihm.menew;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ihm.menew.choiceOfDishes.dessert.Controller_Dessert;
import ihm.menew.choiceOfDishes.dessert.Model_Dessert;
import ihm.menew.choiceOfDishes.dessert.View_Dessert;
import ihm.menew.choiceOfDishes.dish.Controller_Dishes;
import ihm.menew.choiceOfDishes.dish.Model_Dishes;
import ihm.menew.choiceOfDishes.dish.View_Dishes;
import ihm.menew.choiceOfDishes.starter.Controller_Starter;
import ihm.menew.choiceOfDishes.starter.Model_Starter;
import ihm.menew.choiceOfDishes.starter.View_Starter;
import ihm.menew.favoris.mvc.Controller_Favoris;
import ihm.menew.favoris.mvc.Model_Favoris;
import ihm.menew.favoris.mvc.View_Favoris;
import ihm.menew.mvc.Controller_PreparerPlat;
import ihm.menew.mvc.Model_PreparerPlat;
import ihm.menew.mvc.View_PreparerPlat;
import ihm.menew.notifications.NotificationService2;
import ihm.menew.semaine.PLatEnregistre;
import ihm.menew.semaine.Plat;
import ihm.menew.semaine.PlatPrevu;

public class MeNewApplication extends Application {
    public static final String CHANNEL_DEFAULT = "Channel_Defaut";
    public static final String CHANNEL_HIGH = "Channel_High";
    public static PLatEnregistre plats;
    public static PlatPrevu mesPlat = new PlatPrevu();
    public static List<Plat> favoris = new ArrayList<>();
    public static List<Plat> platsCHoisis = new ArrayList<>();
    public static NotificationManager notificationManager;
    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        this.plats = new PLatEnregistre();
        Log.e(getClass().getSimpleName(),"CREATION");
        createNotificationChannelDefaut("Defaut", "Without Picture", NotificationManager.IMPORTANCE_DEFAULT);
        createNotificationChannelHigh("High", "With Picture", NotificationManager.IMPORTANCE_HIGH);
        someFavoriteDish();
    }

    public void someFavoriteDish(){
        favoris.add(MeNewApplication.plats.data.get("Tartiflette"));
        favoris.add(MeNewApplication.plats.data.get("Mousse au chocolat"));
        favoris.add(MeNewApplication.plats.data.get("Salade"));
    }


    private void createNotificationChannelHigh(String name, String description, int importance) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            Log.e(getClass().getSimpleName(),"CREATIONCHANNEL");
            NotificationChannel channel = new NotificationChannel(CHANNEL_HIGH, name, importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
        Log.e(getClass().getSimpleName(), "ici");
    }

    private void createNotificationChannelDefaut(String name, String description, int importance) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_DEFAULT, name, importance);
            channel.setDescription(description);
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    public <T extends ViewGroup> void onViewCreated(T layout){
        //create VIEW with XML layout
        System.out.println(layout);
        View_PreparerPlat view = new View_PreparerPlat( getApplicationContext(), layout );
        Model_PreparerPlat model = new Model_PreparerPlat(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW

        Controller_PreparerPlat controller = new Controller_PreparerPlat( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

    public <T extends ViewGroup> void onViewFavorisCreated(T layout){
        //create VIEW with XML layout
        System.out.println(layout);
        View_Favoris view = new View_Favoris( getApplicationContext(), layout );
        Model_Favoris model = new Model_Favoris(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        //model.addPlatDefault();

        Controller_Favoris controller = new Controller_Favoris( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

    public <T extends ViewGroup> void onViewStarterCreated(T layout) {
        //create VIEW with XML layout
        platsCHoisis = new ArrayList<>();
        System.out.println(layout);
        View_Starter view = new View_Starter( getApplicationContext(), layout );
        Model_Starter model = new Model_Starter(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        //model.addPlatDefault();

        Controller_Starter controller = new Controller_Starter( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

    public <T extends ViewGroup> void onViewDishesCreated(T layout) {
        View_Dishes view = new View_Dishes( getApplicationContext(), layout );
        Model_Dishes model = new Model_Dishes(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        //model.addPlatDefault();

        Controller_Dishes controller = new Controller_Dishes( view, model );
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

    public <T extends ViewGroup> void onViewDessertCreated(T layout, Intent intent) {
        System.out.println(intent.getStringExtra("temps") + "on create menew");
        View_Dessert view = new View_Dessert( getApplicationContext(), layout, intent );
        Model_Dessert model = new Model_Dessert(null);    //controller not still created so the controller reference will be sent later
        model.addObserver(view);    //MODEL is observable from VIEW
        //model.addPlatDefault();

        Controller_Dessert controller = new Controller_Dessert(view, model) {
            @Override
            public AssetManager getAssets() {
                return null;
            }

            @Override
            public Resources getResources() {
                return null;
            }

            @Override
            public PackageManager getPackageManager() {
                return null;
            }

            @Override
            public ContentResolver getContentResolver() {
                return null;
            }

            @Override
            public Looper getMainLooper() {
                return null;
            }

            @Override
            public Context getApplicationContext() {
                return null;
            }

            @Override
            public void setTheme(int i) {

            }

            @Override
            public Resources.Theme getTheme() {
                return null;
            }

            @Override
            public ClassLoader getClassLoader() {
                return null;
            }

            @Override
            public String getPackageName() {
                return null;
            }

            @Override
            public ApplicationInfo getApplicationInfo() {
                return null;
            }

            @Override
            public String getPackageResourcePath() {
                return null;
            }

            @Override
            public String getPackageCodePath() {
                return null;
            }

            @Override
            public SharedPreferences getSharedPreferences(String s, int i) {
                return null;
            }

            @Override
            public boolean moveSharedPreferencesFrom(Context context, String s) {
                return false;
            }

            @Override
            public boolean deleteSharedPreferences(String s) {
                return false;
            }

            @Override
            public FileInputStream openFileInput(String s) throws FileNotFoundException {
                return null;
            }

            @Override
            public FileOutputStream openFileOutput(String s, int i) throws FileNotFoundException {
                return null;
            }

            @Override
            public boolean deleteFile(String s) {
                return false;
            }

            @Override
            public File getFileStreamPath(String s) {
                return null;
            }

            @Override
            public File getDataDir() {
                return null;
            }

            @Override
            public File getFilesDir() {
                return null;
            }

            @Override
            public File getNoBackupFilesDir() {
                return null;
            }

            @Nullable
            @Override
            public File getExternalFilesDir(@Nullable String s) {
                return null;
            }

            @Override
            public File[] getExternalFilesDirs(String s) {
                return new File[0];
            }

            @Override
            public File getObbDir() {
                return null;
            }

            @Override
            public File[] getObbDirs() {
                return new File[0];
            }

            @Override
            public File getCacheDir() {
                return null;
            }

            @Override
            public File getCodeCacheDir() {
                return null;
            }

            @Nullable
            @Override
            public File getExternalCacheDir() {
                return null;
            }

            @Override
            public File[] getExternalCacheDirs() {
                return new File[0];
            }

            @Override
            public File[] getExternalMediaDirs() {
                return new File[0];
            }

            @Override
            public String[] fileList() {
                return new String[0];
            }

            @Override
            public File getDir(String s, int i) {
                return null;
            }

            @Override
            public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory) {
                return null;
            }

            @Override
            public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory, @Nullable DatabaseErrorHandler databaseErrorHandler) {
                return null;
            }

            @Override
            public boolean moveDatabaseFrom(Context context, String s) {
                return false;
            }

            @Override
            public boolean deleteDatabase(String s) {
                return false;
            }

            @Override
            public File getDatabasePath(String s) {
                return null;
            }

            @Override
            public String[] databaseList() {
                return new String[0];
            }

            @Override
            public Drawable getWallpaper() {
                return null;
            }

            @Override
            public Drawable peekWallpaper() {
                return null;
            }

            @Override
            public int getWallpaperDesiredMinimumWidth() {
                return 0;
            }

            @Override
            public int getWallpaperDesiredMinimumHeight() {
                return 0;
            }

            @Override
            public void setWallpaper(Bitmap bitmap) throws IOException {

            }

            @Override
            public void setWallpaper(InputStream inputStream) throws IOException {

            }

            @Override
            public void clearWallpaper() throws IOException {

            }

            @Override
            public void startActivity(Intent intent) {

            }

            @Override
            public void startActivity(Intent intent, @Nullable Bundle bundle) {

            }

            @Override
            public void startActivities(Intent[] intents) {

            }

            @Override
            public void startActivities(Intent[] intents, Bundle bundle) {

            }

            @Override
            public void startIntentSender(IntentSender intentSender, @Nullable Intent intent, int i, int i1, int i2) throws IntentSender.SendIntentException {

            }

            @Override
            public void startIntentSender(IntentSender intentSender, @Nullable Intent intent, int i, int i1, int i2, @Nullable Bundle bundle) throws IntentSender.SendIntentException {

            }

            @Override
            public void sendBroadcast(Intent intent) {

            }

            @Override
            public void sendBroadcast(Intent intent, @Nullable String s) {

            }

            @Override
            public void sendOrderedBroadcast(Intent intent, @Nullable String s) {

            }

            @Override
            public void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String s, @Nullable BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s1, @Nullable Bundle bundle) {

            }

            @Override
            public void sendBroadcastAsUser(Intent intent, UserHandle userHandle) {

            }

            @Override
            public void sendBroadcastAsUser(Intent intent, UserHandle userHandle, @Nullable String s) {

            }

            @Override
            public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, @Nullable String s, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s1, @Nullable Bundle bundle) {

            }

            @Override
            public void sendStickyBroadcast(Intent intent) {

            }

            @Override
            public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s, @Nullable Bundle bundle) {

            }

            @Override
            public void removeStickyBroadcast(Intent intent) {

            }

            @Override
            public void sendStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {

            }

            @Override
            public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s, @Nullable Bundle bundle) {

            }

            @Override
            public void removeStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {

            }

            @Nullable
            @Override
            public Intent registerReceiver(@Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
                return null;
            }

            @Nullable
            @Override
            public Intent registerReceiver(@Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i) {
                return null;
            }

            @Nullable
            @Override
            public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable String s, @Nullable Handler handler) {
                return null;
            }

            @Nullable
            @Override
            public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable String s, @Nullable Handler handler, int i) {
                return null;
            }

            @Override
            public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {

            }

            @Nullable
            @Override
            public ComponentName startService(Intent intent) {
                return null;
            }

            @Nullable
            @Override
            public ComponentName startForegroundService(Intent intent) {
                return null;
            }

            @Override
            public boolean stopService(Intent intent) {
                return false;
            }

            @Override
            public boolean bindService(Intent intent, @NonNull ServiceConnection serviceConnection, int i) {
                return false;
            }

            @Override
            public void unbindService(@NonNull ServiceConnection serviceConnection) {

            }

            @Override
            public boolean startInstrumentation(@NonNull ComponentName componentName, @Nullable String s, @Nullable Bundle bundle) {
                return false;
            }

            @Override
            public Object getSystemService(@NonNull String s) {
                return null;
            }

            @Nullable
            @Override
            public String getSystemServiceName(@NonNull Class<?> aClass) {
                return null;
            }

            @Override
            public int checkPermission(@NonNull String s, int i, int i1) {
                return PackageManager.PERMISSION_DENIED;
            }

            @Override
            public int checkCallingPermission(@NonNull String s) {
                return PackageManager.PERMISSION_DENIED;
            }

            @Override
            public int checkCallingOrSelfPermission(@NonNull String s) {
                return PackageManager.PERMISSION_DENIED;
            }

            @Override
            public int checkSelfPermission(@NonNull String s) {
                return PackageManager.PERMISSION_DENIED;
            }

            @Override
            public void enforcePermission(@NonNull String s, int i, int i1, @Nullable String s1) {

            }

            @Override
            public void enforceCallingPermission(@NonNull String s, @Nullable String s1) {

            }

            @Override
            public void enforceCallingOrSelfPermission(@NonNull String s, @Nullable String s1) {

            }

            @Override
            public void grantUriPermission(String s, Uri uri, int i) {

            }

            @Override
            public void revokeUriPermission(Uri uri, int i) {

            }

            @Override
            public void revokeUriPermission(String s, Uri uri, int i) {

            }

            @Override
            public int checkUriPermission(Uri uri, int i, int i1, int i2) {
                return PackageManager.PERMISSION_DENIED;
            }

            @Override
            public int checkCallingUriPermission(Uri uri, int i) {
                return PackageManager.PERMISSION_DENIED;
            }

            @Override
            public int checkCallingOrSelfUriPermission(Uri uri, int i) {
                return PackageManager.PERMISSION_DENIED;
            }

            @Override
            public int checkUriPermission(@Nullable Uri uri, @Nullable String s, @Nullable String s1, int i, int i1, int i2) {
                return PackageManager.PERMISSION_DENIED;
            }

            @Override
            public void enforceUriPermission(Uri uri, int i, int i1, int i2, String s) {

            }

            @Override
            public void enforceCallingUriPermission(Uri uri, int i, String s) {

            }

            @Override
            public void enforceCallingOrSelfUriPermission(Uri uri, int i, String s) {

            }

            @Override
            public void enforceUriPermission(@Nullable Uri uri, @Nullable String s, @Nullable String s1, int i, int i1, int i2, @Nullable String s2) {

            }

            @Override
            public Context createPackageContext(String s, int i) throws PackageManager.NameNotFoundException {
                return null;
            }

            @Override
            public Context createContextForSplit(String s) throws PackageManager.NameNotFoundException {
                return null;
            }

            @Override
            public Context createConfigurationContext(@NonNull Configuration configuration) {
                return null;
            }

            @Override
            public Context createDisplayContext(@NonNull Display display) {
                return null;
            }

            @Override
            public Context createDeviceProtectedStorageContext() {
                return null;
            }

            @Override
            public boolean isDeviceProtectedStorage() {
                return false;
            }
        };
        model.setController(controller);    //sent for principe but in this exercice, MODEL doesn't need controller
        view.setListener( controller );
        model.notifyObservers();
    }

}
