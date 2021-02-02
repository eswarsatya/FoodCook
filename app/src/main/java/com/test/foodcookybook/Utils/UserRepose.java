package com.test.foodcookybook.Utils;

import androidx.lifecycle.MutableLiveData;

import com.test.foodcookybook.Models.MyList;
import com.test.foodcookybook.MyListViewModel;

import java.util.ArrayList;

public class UserRepose {
    public MutableLiveData<ArrayList<MyListViewModel>> arrayListMutableLiveData=new MutableLiveData<>();
    private ArrayList<MyListViewModel> arrayList;
public ArrayList<MyList> items;
    public UserRepose(){

    }

    public MutableLiveData<ArrayList<MyListViewModel>> getArrayListMutableLiveData() {
     final MyApi myApi= MyClient.getInstance().getMyApi();


        return arrayListMutableLiveData;
    }
}
