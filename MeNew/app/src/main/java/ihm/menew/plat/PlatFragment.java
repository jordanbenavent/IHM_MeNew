package ihm.menew.plat;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.function.BiPredicate;

import ihm.menew.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlatFragment extends Fragment {

    private String nomDuPlat = "Nom Du plat";
    private Bitmap image;
    private int duree = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlatFragment newInstance(String param1, int param2) {
        PlatFragment fragment = new PlatFragment();
        Bundle args = new Bundle();
        args.putString(fragment.nomDuPlat, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plat, container, false);
    }
}