//package com.example.arjun.poczomato;
//
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.arch.persistence.room.TypeConverters;
//import android.content.Context;
//
//import com.example.arjun.poczomato.model.Model.category.Categories;
//
//@Database(entities = {Categories.class}, version = 1)
//@TypeConverters({CategoriesTypeConverters.class})
//public abstract class ZomatoRoomDatabase extends RoomDatabase {
//    public abstract ZomatoDao wordDao();
//
//    private static volatile ZomatoRoomDatabase INSTANCE;
//
//    public static ZomatoRoomDatabase getDatabase(final Context context) {
//        if (INSTANCE == null) {
//            synchronized (ZomatoRoomDatabase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            ZomatoRoomDatabase.class, "word_database")
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//}
