package com.test.foodcookybook.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.foodcookybook.FavouritesViewModel;
import com.test.foodcookybook.Models.MyList;
import com.test.foodcookybook.R;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
ListView lv_reciepe_fav;
Button btn_backtohome;
    private FavouritesAdapter mFavAdapter;
    private FavouritesViewModel mFavViewModel;
TextView tv_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        lv_reciepe_fav=findViewById(R.id.lv_reciepe_fav);
        btn_backtohome=findViewById(R.id.btn_backtohome);

        tv_no=findViewById(R.id.tv_no);


        btn_backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavoriteActivity.this,MainActivity.class));
            }
        });


        mFavViewModel = ViewModelProviders.of(this).get(FavouritesViewModel.class);

        List<MyList> favourites = mFavViewModel.getFavs();

        if(favourites.size()==0){
            tv_no.setVisibility(View.VISIBLE);
            lv_reciepe_fav.setVisibility(View.GONE);

        }
        else{
            tv_no.setVisibility(View.GONE);
            lv_reciepe_fav.setVisibility(View.VISIBLE);

            mFavAdapter = new FavouritesAdapter(this, R.layout.list_item_row, favourites);
            lv_reciepe_fav.setAdapter(mFavAdapter);
        }






    }
    public class FavouritesAdapter extends ArrayAdapter<MyList> {

        private class ViewHolder {
            TextView tv_1;
            TextView tv_2,tv_3;
            ImageView igv_pp,igv_icon;
        }

        public FavouritesAdapter(Context context, int layoutResourceId, List<MyList> todos) {
            super(context, layoutResourceId, todos);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        @NonNull
        public View getView(int position, View convertView, ViewGroup parent) {
            MyList favourites = getItem(position);
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_item_row, parent, false);
                viewHolder.tv_1 = convertView.findViewById(R.id.tv_1);
                viewHolder.tv_2 = convertView.findViewById(R.id.tv_2);
                viewHolder.tv_3 = convertView.findViewById(R.id.tv_3);
                viewHolder.igv_pp = convertView.findViewById(R.id.igv_pp);
                viewHolder.igv_icon = convertView.findViewById(R.id.igv_icon);
                convertView.setTag(R.id.VH, viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag(R.id.VH);

            }

            viewHolder.tv_1.setText(favourites.strMeal);
            viewHolder.tv_2.setText(favourites.strCategory);
            viewHolder.tv_3.setText(favourites.strArea);
            Picasso.with(getApplicationContext()).load(favourites.strMealThumb).placeholder(R.drawable.border_color)
                    .noFade().into(viewHolder.igv_pp);

            viewHolder.igv_icon.setImageDrawable(getApplicationContext().getDrawable(R.drawable.heartblack));
            convertView.setTag(R.id.POS, position);
            return convertView;
        }

    }
}