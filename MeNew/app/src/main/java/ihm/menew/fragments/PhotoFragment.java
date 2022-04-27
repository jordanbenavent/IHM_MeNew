package ihm.menew.fragments;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;


import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import ihm.menew.R;
import ihm.menew.factories.PermissionFactory;
import ihm.menew.storage.StorageManager;


public class PhotoFragment extends Fragment implements View.OnClickListener {
    private static final float TRANSPARENT = 0.3f;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 123;
    private final String TAG = "menew_"+getClass().getSimpleName();
    private static final String PICTURE_NAME = "_test.jpg";
    private Bitmap photo;
    private Button save;

    private File internalDirectory;

    public PhotoFragment(){
        // Empty Constructor
    }

    public ImageView click_image_id;


    private void actionAfterCallbackIfPermissionGranted(String action, String folder){
        File directory = new File (folder);
        Log.d(TAG,"directory = "+directory);
        if (action.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Log.d(TAG,"action when permission granted ==> save");
            StorageManager.saveBitmapToStorage(getContext(), photo, new File(directory, PICTURE_NAME));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        internalDirectory = new File( getContext().getDir("imageDir", Context.MODE_PRIVATE).toString() );
        Log.d(TAG,">>>>>>>>>>>> internalDirectory =  "+internalDirectory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View result = inflater.inflate(R.layout.fragment_photo, container, false);
        //Set onClickListener buttons
        result.findViewById(R.id.Camera_button).setOnClickListener(this);
        click_image_id = result.findViewById(R.id.Photo);
        click_image_id.setMaxHeight( photo != null ? 5 : 450 );
        save = result.findViewById(R.id.Save_button);
        save.setAlpha( photo!=null && internalDirectory!=null ? 1f : TRANSPARENT) ;

        result.findViewById(R.id.Save_button).setOnClickListener(click -> {
            if (photo!=null) {    //manage authorizations
                Log.d(TAG, "want to save internal " + PICTURE_NAME);
                StorageManager.saveBitmapToStorage(getContext(), photo, new File(internalDirectory, PICTURE_NAME));
                ActivityCompat.requestPermissions(
                                (Activity) getContext(),
                                new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        });

        return result;
    }

    @Override
    public void onClick(View view) {
        // Create the camera_intent ACTION_IMAGE_CAPTURE
        // it will open the camera for capture the image
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Start the activity with camera_intent,
        // and request pic id startActivityForResult(camera_intent, pic_id);
        startActivityForResult(camera_intent, PermissionFactory.REQUEST_ID_IMAGE_CAPTURE);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionFactory.REQUEST_ID_IMAGE_CAPTURE) {
            switch (resultCode) {
                case RESULT_OK:
                    photo = (Bitmap)data.getExtras().get("data");
                    click_image_id.setImageBitmap(photo);
                    save.setAlpha( photo!=null && internalDirectory!=null ? 1f : TRANSPARENT) ;
                    click_image_id.setMaxHeight( photo != null ? 25 : 450 );

                    Log.d(TAG,"camera result: RESULT_OK");
                    break;
                case RESULT_CANCELED:
                    Toast.makeText( getContext(),"Action canceled", Toast.LENGTH_LONG).show();
                    Log.d(TAG,"camera result: RESULT_CANCELED");
                    break;
                default:
                    Toast.makeText(getContext(), "camera result: Action Failed", Toast.LENGTH_LONG).show();
                    break;
            }//end switch
        }
    }
}