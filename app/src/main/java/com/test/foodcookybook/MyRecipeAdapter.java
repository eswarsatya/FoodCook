package com.test.foodcookybook;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.test.foodcookybook.Models.MyList;
import com.test.foodcookybook.Utils.OnItemClickListener;
import com.test.foodcookybook.databinding.MyListBinding;

import java.util.ArrayList;

public class MyRecipeAdapter extends RecyclerView.Adapter<MyRecipeAdapter.ViewHolder> {

    private ArrayList<MyListViewModel> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;
    public OnItemClickListener listener;
    DatabaseHandler db;
    public MyRecipeAdapter(ArrayList<MyListViewModel> arrayList, Context context,OnItemClickListener listener1 ) {
        this.arrayList = arrayList;
        this.context = context;
        this.listener = listener1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater==null){
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        MyListBinding myListBinding= DataBindingUtil.inflate(layoutInflater,R.layout.mylist,parent,false);
        return new ViewHolder(myListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final MyListViewModel myListViewModel=arrayList.get(position);
       // holder.bind(myListViewModel);
        holder.bind(arrayList.get(position), listener);
        db = new DatabaseHandler(context);
        holder.itemView.findViewById(R.id.igv_icon).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT).show();
               String id=arrayList.get(position).idMeal;

                holder.itemView.findViewById(R.id.igv_icon).setBackground(context.getDrawable(R.drawable.heartblack));
                db.addfav(new MyList(arrayList.get(position).idMeal,arrayList.get(position).strMeal,arrayList.get(position).strMealThumb,
                        arrayList.get(position).strCategory,arrayList.get(position).strArea,1));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private MyListBinding myListBinding;

        public ViewHolder(@NonNull MyListBinding myListBinding) {
            super(myListBinding.getRoot());
            this.myListBinding = myListBinding;
        }

        public void bind(final MyListViewModel myli, final OnItemClickListener listener) {
            this.myListBinding.setMylistmodel(myli);
            myListBinding.executePendingBindings();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(myli);
                }
            });
        }

        public MyListBinding getMyListBinding() {
            return myListBinding;
        }
    }
}
