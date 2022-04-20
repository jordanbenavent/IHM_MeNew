package ihm.menew.fragments;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import ihm.menew.R;
import ihm.menew.factories.PermissionFactory;


public class PhotoFragment extends Fragment implements View.OnClickListener {
    private final String TAG = "menew_"+getClass().getSimpleName();

    public PhotoFragment(){
        // Empty Constructor
    }

    public ImageView click_image_id;

    private static final int pic_id = 123;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View result = inflater.inflate(R.layout.fragment_photo, container, false);
        //Set onClickListener buttons
        result.findViewById(R.id.Camera_button).setOnClickListener(this);
        click_image_id = result.findViewById(R.id.Photo);
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
    /*
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data)
    {

        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
            Bitmap photo = (Bitmap)data.getExtras().get("data");

            // Set the image in imageview for display
            click_image_id.setImageBitmap(photo);
        }
    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionFactory.REQUEST_ID_IMAGE_CAPTURE) {
            switch (resultCode) {
                case RESULT_OK:
                    Bitmap photo = (Bitmap)data.getExtras().get("data");
                    click_image_id.setImageBitmap(photo);
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