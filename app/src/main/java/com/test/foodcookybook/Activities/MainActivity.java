package com.test.foodcookybook.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.test.foodcookybook.MyListViewModel;
import com.test.foodcookybook.MyRecipeAdapter;
import com.test.foodcookybook.Utils.OnItemClickListener;
import com.test.foodcookybook.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private MyListViewModel myListViewModel;
    private MyRecipeAdapter myRecipeAdapter;
    private SearchView searchView;
    TextView tv_no;
    Button btn_fav;
    private NetworkInfo activeNetworkInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview=(RecyclerView)findViewById(R.id.rc_reciepe);
        tv_no=findViewById(R.id.tv_no);
        searchView=findViewById(R.id.search);

        btn_fav=findViewById(R.id.btn_fav);

        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,FavoriteActivity.class));

            }
        });
        isNetworkAvailable();
        if (activeNetworkInfo==null){
            Toast.makeText(MainActivity.this,"Check Your internet connection",Toast.LENGTH_SHORT).show();
        }else {

            myListViewModel = ViewModelProviders.of(this).get(MyListViewModel.class);
            myListViewModel.getMutableLiveData().observe(this, new Observer<ArrayList<MyListViewModel>>() {
                @Override
                public void onChanged(final ArrayList<MyListViewModel> myListViewModels) {
                    myRecipeAdapter = new MyRecipeAdapter(myListViewModels, MainActivity.this, new OnItemClickListener() {
                        @Override
                        public void onItemClick(MyListViewModel item) {
                            // Toast.makeText(getApplicationContext(), "Item Clicked"+myListViewModel.strMeal, Toast.LENGTH_LONG).show();
                        }
                    });
                    recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerview.setAdapter(myRecipeAdapter);
                }
            });
        }
        searchView.setQueryHint("Food");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Log.i("ddsxzc", ""+s.length());
                if(s.length()==0){
                    Toast.makeText(getApplicationContext(),"Please enter letter or word to search the details",Toast.LENGTH_SHORT).show();
                }

                else {


                    boolean digitsOnly = false;
                    if (s.length() > 1) {
                        digitsOnly = TextUtils.isDigitsOnly(s);
                    }
                    Log.i("ddsxzc", s);


                    if (s.length() == 1) {
                        Log.i("ddsxzc", String.valueOf(1));
                        recyclerview.setAdapter(null);
                        isNetworkAvailable();
                        if (activeNetworkInfo==null){
                            Toast.makeText(MainActivity.this,"Check Your internet connection",Toast.LENGTH_SHORT).show();
                        }else {
                            myListViewModel.getMutableLiveData2(s).observe(MainActivity.this, new Observer<ArrayList<MyListViewModel>>() {
                                @Override
                                public void onChanged(ArrayList<MyListViewModel> myListViewModels) {
                                    if (myListViewModels.size() == 0) {
                                        recyclerview.setVisibility(View.GONE);
                                        tv_no.setVisibility(View.VISIBLE);


                                    } else {
                                        recyclerview.setVisibility(View.VISIBLE);
                                        tv_no.setVisibility(View.GONE);
                                        myRecipeAdapter = new MyRecipeAdapter(myListViewModels, MainActivity.this, new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(MyListViewModel item) {
                                                // Toast.makeText(getApplicationContext(), "Item Clicked"+myListViewModel.strMeal, Toast.LENGTH_LONG).show();
                                            }
                                        });
                                        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        recyclerview.setAdapter(myRecipeAdapter);
                                    }
                                }

                            });
                        }
                    } else if (s.length() > 1 && !digitsOnly) {
                        Log.i("ddsxzc", String.valueOf(2));
                        recyclerview.setAdapter(null);
                        isNetworkAvailable();
                        if (activeNetworkInfo==null){
                            Toast.makeText(MainActivity.this,"Check Your internet connection",Toast.LENGTH_SHORT).show();
                        }else {
                            myListViewModel.getMutableLiveData1(s).observe(MainActivity.this, new Observer<ArrayList<MyListViewModel>>() {
                                @Override
                                public void onChanged(final ArrayList<MyListViewModel> myListViewModels) {
                                    if (myListViewModels.size() == 0) {
                                        recyclerview.setVisibility(View.GONE);
                                        tv_no.setVisibility(View.VISIBLE);


                                    } else {
                                        recyclerview.setVisibility(View.VISIBLE);
                                        tv_no.setVisibility(View.GONE);
                                        myRecipeAdapter = new MyRecipeAdapter(myListViewModels, MainActivity.this, new OnItemClickListener() {
                                            @Override
                                            public void onItemClick(MyListViewModel item) {
//                                    int a=myListViewModels.size();
//                                    for (int i=0; i<a;i++) {
//                                        Toast.makeText(getApplicationContext(), "Item Clicked" +myListViewModels.get(i).strMeal, Toast.LENGTH_LONG).show();
//                                    }
                                            }
                                        });
                                        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        recyclerview.setAdapter(myRecipeAdapter);
                                    }
                                }
                            });
                        }
                    }
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}