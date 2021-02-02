package com.test.foodcookybook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.test.foodcookybook.DatabaseHandler;
import com.test.foodcookybook.Models.MyList;

import java.util.ArrayList;
import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {
    private DatabaseHandler mFavHelper;
    private ArrayList<MyList> mFavs;

public     FavouritesViewModel(Application application) {
        super(application);
        mFavHelper = new DatabaseHandler(application);

    }
    public List<MyList> getFavs() {
        if (mFavs == null) {
            mFavs = new ArrayList<>();

mFavs= (ArrayList<MyList>) mFavHelper.getAllfav();
        }
        ArrayList<MyList> clonedFavs = new ArrayList<>(mFavs.size());
        for (int i = 0; i < mFavs.size(); i++) {
            clonedFavs.add(new MyList(mFavs.get(i)));
        }
        return clonedFavs;
    }
//    private void loadFavs() {
//
//        mFavs.clear();
//
//        SQLiteDatabase db = mFavHelper.getReadableDatabase();
//        Cursor cursor = db.query(DatabaseHandler.TABLE_FAV,
//                new String[]{
//                        DatabaseHandler.KEY_ID,
//                        DatabaseHandler.KEY_NAME,
//                        DatabaseHandler.KEY_IMG_URl,
//                        DatabaseHandler.KEY_CATEGORY,
//                        DatabaseHandler.KEY_AREA,
//                        DatabaseHandler.KEY_SELECTED
//                },
//                null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            int idxId = cursor.getColumnIndex(DbSettings.DBEntry._ID);
//            int idxUrl = cursor.getColumnIndex(DbSettings.DBEntry.COL_FAV_URL);
//            int idxDate = cursor.getColumnIndex(DbSettings.DBEntry.COL_FAV_DATE);
//            mFavs.add(new Favourites(cursor.getLong(idxId), cursor.getString(idxUrl), cursor.getLong(idxDate)));
//        }
//        cursor.close();
//        db.close();
//    }

}
