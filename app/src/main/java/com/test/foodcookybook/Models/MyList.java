package com.test.foodcookybook.Models;

import com.google.gson.annotations.SerializedName;

public class MyList {


    @SerializedName("idMeal")
    public String idMeal="";

    @SerializedName("strMeal")
    public String strMeal="";

    @SerializedName("strMealThumb")
    public String strMealThumb="";

    @SerializedName("strCategory")
    public String strCategory="";



    @SerializedName("strArea")
    public String strArea="";

    public int issel=0;

    public MyList(String idMeal, String strMeal, String strMealThumb, String strCategory, String strArea,int issel) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.strCategory = strCategory;
        this.strArea=strArea;
        this.issel=issel;
    }

    public MyList() {
    }

    public MyList(MyList myList) {
        idMeal = myList.idMeal;
        strMeal =  myList.strMeal;
        strMealThumb = myList. strMealThumb;
        strCategory =  myList.strCategory;
       strArea= myList.strArea;
        issel= myList.issel;
    }


    public int getIssel() {
        return issel;
    }

    public void setIssel(int issel) {
        this.issel = issel;
    }
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }
}
