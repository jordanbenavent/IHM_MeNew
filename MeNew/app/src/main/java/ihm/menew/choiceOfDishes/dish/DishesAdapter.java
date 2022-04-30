package ihm.menew.choiceOfDishes.dish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.RecetteActivity;
import ihm.menew.choiceOfDishes.starter.Model_Starter;
import ihm.menew.choiceOfDishes.starter.View_Starter;

public class DishesAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Model_Dishes model;
    private View_Dishes viewDishes;
    private Context context;
    private boolean selected = false;

    public <T extends ViewGroup> DishesAdapter(Context context, View_Dishes viewDishes) {
        inflater = LayoutInflater.from(context);
        this.viewDishes = viewDishes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return model.getDishes().size();
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
        temps.setText(model.getTempsPreparationPlat(i)+ "min");
        image.setImageResource(model.getImage(i));

        ((ImageView)layoutItem.findViewById(R.id.check)).setImageResource(R.drawable.ic_baseline_check_circle_24);



        //écouter si clic sur la vue
        layoutItem.setOnClickListener( clic ->  viewDishes.onClickItem(i) );
        layoutItem.findViewById(R.id.info).setOnClickListener(clic -> {
            Intent activity = new Intent(context, RecetteActivity.class).putExtra("Plat", model.getDish(i));
            activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activity);
        });

        layoutItem.findViewById(R.id.check).setOnClickListener(clic -> {
            selected = !selected;
            ((ImageView)layoutItem.findViewById(R.id.check)).setColorFilter( !selected ? Color.WHITE : Color.BLUE);
            if(selected) MeNewApplication.platsCHoisis.add(model.getPlat(i));
            else MeNewApplication.platsCHoisis.remove(model.getPlat(i));
            System.out.println(MeNewApplication.platsCHoisis);

        });

        //On retourne l'item créé.
        System.out.println("Jai créé");
        return layoutItem;
    }

    public void updateModel(Model_Dishes model) {
        this.model = model;
    }

    public void refresh(Model_Dishes model) {
        this.model = model;
        notifyDataSetChanged();
    }
}
