package ihm.menew.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ihm.menew.R;


public class MainFragment extends Fragment implements View.OnClickListener {

    //2 - Declare callback
    private OnButtonClickedListener mCallback;

    // 1 - Declare our interface that will be implemented by any container activity
    public interface OnButtonClickedListener {
        void onButtonClicked(View view);
    }

    public MainFragment() {
        // Empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View result = inflater.inflate(R.layout.fragment_main, container, false);
        //Set onClickListener buttons
        result.findViewById(R.id.fragment_main_button).setOnClickListener(this);
        result.findViewById(R.id.fragment_search_button).setOnClickListener(this);
        result.findViewById(R.id.fragment_favorite_button).setOnClickListener(this);
        result.findViewById(R.id.fragment_history_button).setOnClickListener(this);
        result.findViewById(R.id.fragment_plan_button).setOnClickListener(this);

        return result;
    }

    @Override
    public void onClick(View v) {
        mCallback.onButtonClicked(v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 4 - Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    // 3 - Create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            //Parent activity will automatically subscribe to callback
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnButtonClickedListener");
        }
    }

}