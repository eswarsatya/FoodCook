package com.test.foodcookybook;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.test.foodcookybook.Models.MyList;
import com.test.foodcookybook.Models.MylistMeals;
import com.test.foodcookybook.Utils.MyApi;
import com.test.foodcookybook.Utils.MyClient;
import com.test.foodcookybook.Utils.UserRepose;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyListViewModel  extends ViewModel {

    public String idMeal="";
    public String strMeal="";
    public String strMealThumb="";
    public String strCategory="";
    public String strArea="";
    public int strisel=0;

    public MutableLiveData<ArrayList<MyListViewModel>> mutableLiveData=new MutableLiveData<ArrayList<MyListViewModel>>();
    public MutableLiveData<ArrayList<MyListViewModel>> mutableLiveData1=new MutableLiveData<ArrayList<MyListViewModel>>();
    public MutableLiveData<ArrayList<MyListViewModel>> mutableLiveData2=new MutableLiveData<ArrayList<MyListViewModel>>();

    private ArrayList<MyListViewModel>arrayList,arrayList1,arrayList2;
    private MutableLiveData<MylistMeals>myList,myList1,myList2;
    private UserRepose userRepose;

    public String getImageurl(){
        return strMealThumb;
    }
    @BindingAdapter({"imageUrl"})
    public static void loadimage(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(imageView);
        //Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    public MyListViewModel(){

    }

    public MyListViewModel(MyList myList){
        this.idMeal=myList.idMeal;
        this.strMeal=myList.strMeal;
        this.strMealThumb=myList.strMealThumb;
        this.strCategory=myList.strCategory;
        this.strArea=myList.strArea;
        this.strisel=myList.issel;
    }

    public MutableLiveData<ArrayList<MyListViewModel>> getMutableLiveData() {

        arrayList=new ArrayList<>();

        MyApi api= MyClient.getInstance().getMyApi();
        Call<MylistMeals> call=api.getreciepedata();
        call.enqueue(new Callback<MylistMeals>() {
            @Override
            public void onResponse(Call<MylistMeals> call, Response<MylistMeals> response) {
                myList=new MutableLiveData<>();
                myList.setValue(response.body());
                for (int i=0; i<myList.getValue().getResults().size(); i++){
                    MyList myk=myList.getValue().getResults().get(i);
                    MyListViewModel myListViewModel=new MyListViewModel( myk);
                    arrayList.add(myListViewModel);
                    mutableLiveData.setValue(arrayList);
                }

            }

            @Override
            public void onFailure(Call<MylistMeals> call, Throwable t) {




            }
        });

        return mutableLiveData;
    }
    public MutableLiveData<ArrayList<MyListViewModel>> getMutableLiveData1(String query) {

        arrayList1=new ArrayList<>();

        MyApi api= MyClient.getInstance().getMyApi();
        Call<MylistMeals> call=api.searchRecipe(query);
        call.enqueue(new Callback<MylistMeals>() {
            @Override
            public void onResponse(Call<MylistMeals> call, Response<MylistMeals> response) {
                Log.i("fsd0",new Gson().toJson(response));

                myList1=new MutableLiveData<>();
                if(response.isSuccessful()) {

                    if(response.body().getResults()==null){
                        Log.i("jcn","error");
                        arrayList1.clear();
                        mutableLiveData1.setValue(arrayList1);
                    }
                     else {

                        myList1.setValue(response.body());
                        for (int i = 0; i < myList1.getValue().getResults().size(); i++) {
                            MyList myk = myList1.getValue().getResults().get(i);
                            MyListViewModel myListViewModel = new MyListViewModel(myk);
                            arrayList1.add(myListViewModel);
                            mutableLiveData1.setValue(arrayList1);

                        }
                    }

                }
//

            }

            @Override
            public void onFailure(Call<MylistMeals> call, Throwable t) {




            }
        });

        return mutableLiveData1;
    }
    public MutableLiveData<ArrayList<MyListViewModel>> getMutableLiveData2(String query) {

        arrayList2=new ArrayList<>();

        MyApi api= MyClient.getInstance().getMyApi();
        Call<MylistMeals> call=api.searchRecipe1(query);
        call.enqueue(new Callback<MylistMeals>() {
            @Override
            public void onResponse(Call<MylistMeals> call, Response<MylistMeals> response) {
                Log.i("fsd2",new Gson().toJson(response));

                myList2=new MutableLiveData<>();
                if(response.isSuccessful()) {

                    if (response.body().getResults() == null) {
                        Log.i("jcn", "error");
                        arrayList2.clear();
                        mutableLiveData2.setValue(arrayList2);
                    } else {

                        myList2.setValue(response.body());
                        for (int i = 0; i < myList2.getValue().getResults().size(); i++) {
                            MyList myk = myList2.getValue().getResults().get(i);
                            MyListViewModel myListViewModel = new MyListViewModel(myk);
                            arrayList2.add(myListViewModel);
                            mutableLiveData2.setValue(arrayList2);

                        }
                    }
                }






            }

            @Override
            public void onFailure(Call<MylistMeals> call, Throwable t) {




            }
        });


        return mutableLiveData2;
    }




    }




