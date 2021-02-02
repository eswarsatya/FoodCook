package com.test.foodcookybook.Utils;

import com.test.foodcookybook.Models.MylistMeals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {

    @GET("random.php")
    Call<MylistMeals> getreciepedata();

    @GET("search.php")
    Call<MylistMeals> searchRecipe(@Query("s") String key);

    @GET("search.php")
    Call<MylistMeals> searchRecipe1(@Query("f") String key);




}
