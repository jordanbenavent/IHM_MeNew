package ihm.menew.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageManager {
    private static final String TAG = "StorageManager";

    public static boolean isEmptyDirectory(File directory) {
        if (directory==null || directory.listFiles()==null) return true;
        return directory.listFiles().length==0;
    }

    public static File[] listFiles(File directory) {
        return directory.listFiles();
    }


    public static void saveBitmapToStorage(Context context, Bitmap bitmapImage, File file){
        Log.d(TAG,"file name = "+file);
        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(file);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 80, fileStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fileStream!=null) {
                try {
                    fileStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
            Log.d(TAG, "New picture saved");
        } catch (FileNotFoundException e) {
            Log.d(TAG, "ERROR: picture not saved");
            e.printStackTrace();
        }
    }


}
