package ihm.menew.plat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ihm.menew.DetailActivity;
import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.RecetteActivity;
import ihm.menew.SecondActivity;
import ihm.menew.TestActivity;
import ihm.menew.favoris.mvc.FavorisActivity;
import ihm.menew.favoris.mvc.Model_Favoris;
import ihm.menew.favoris.mvc.View_Favoris;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlatAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Model_Favoris model;
    private View_Favoris viewFavoris;
    private Context context;

    public <T extends ViewGroup> PlatAdapter(Context context, View_Favoris viewFavoris) {
        inflater = LayoutInflater.from(context);
        this.viewFavoris = viewFavoris;
        this.context = context;
    }

    @Override
    public int getCount() {
        return model.getFavoris().size();
    }

    @Override
    public Object getItem(int i) {
        return model.getNomPlat(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FrameLayout layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = (FrameLayout) (view == null ? inflater.inflate(R.layout.fragment_plat, viewGroup, false) : view);

        //(2) : Récupération des TextView de notre layout
        TextView nomPlat = layoutItem.findViewById(R.id.nomPlat);
        TextView temps = layoutItem.findViewById(R.id.time);
        ImageView image = layoutItem.findViewById(R.id.imagePlat);

        //(3) : Renseignement des valeurs
        nomPlat.setText( model.getNomPlat(i) );
        temps.setText(model.getTempsPreparationPlat(i) + "min");
        image.setImageResource(model.getImage(i));

        ((ImageView)layoutItem.findViewById(R.id.check)).setImageResource(R.drawable.ic_twotone_star_24);
        ((ImageView)layoutItem.findViewById(R.id.check)).setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);



        //écouter si clic sur la vue
        layoutItem.setOnClickListener( clic ->  viewFavoris.onClickItem(i) );
        layoutItem.findViewById(R.id.info).setOnClickListener(clic -> {
            Intent activity = new Intent(context, RecetteActivity.class).putExtra("Plat", model.getPlat(i));
            activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activity);
        });

        layoutItem.findViewById(R.id.check).setOnClickListener(clic -> {
            model.removePlat(model.getPlat(i));
        });

        //On retourne l'item créé.
        return layoutItem;
    }

    public void updateModel(Model_Favoris model) {
        this.model = model;
    }

    public void refresh(Model_Favoris model) {
        this.model = model;
        notifyDataSetChanged();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
