package com.test.foodcookybook.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MylistMeals {

    @SerializedName("meals")
    private List<MyList> results = null;

    public List<MyList> getResults() {
        return results;
    }

    public void setResults(List<MyList> results) {
        this.results = results;
    }
}
