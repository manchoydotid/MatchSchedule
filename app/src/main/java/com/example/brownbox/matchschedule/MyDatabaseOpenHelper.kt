package com.example.brownbox.matchschedule

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return  instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Favorites.TABLE_FAVORITES, true,
            Favorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorites.ID_EVENT to TEXT,
            Favorites.DATE_EVENT to TEXT,
            Favorites.STR_HOME_TEAM to TEXT,
            Favorites.ID_HOME_TEAM to TEXT,
            Favorites.INT_HOME_SCORE to TEXT,
//            Favorites.INT_HOME_SHOTS to TEXT,
//            Favorites.STR_HOME_GOAL_DETAILS to TEXT,
//            Favorites.STR_HOME_LINEUP_GOALKEEPER to TEXT,
//            Favorites.STR_HOME_LINEUP_DEFENSE to TEXT,
//            Favorites.STR_HOME_LINEUP_MIDFIELD to TEXT,
//            Favorites.STR_HOME_LINEUP_FORWARD to TEXT,
//            Favorites.STR_HOME_LINEUP_SUBSTITUTES to TEXT,

            Favorites.STR_AWAY_TEAM to TEXT,
            Favorites.ID_AWAY_TEAM to TEXT,
            Favorites.INT_AWAY_SCORE to TEXT
//            Favorites.INT_AWAY_SHOTS to TEXT,
//            Favorites.STR_AWAY_GOAL_DETAILS to TEXT,
//            Favorites.STR_AWAY_LINEUP_GOALKEEPER to TEXT,
//            Favorites.STR_AWAY_LINEUP_DEFENSE to TEXT,
//            Favorites.STR_AWAY_LINEUP_MIDFIELD to TEXT,
//            Favorites.STR_AWAY_LINEUP_FORWARD to TEXT,
//            Favorites.STR_AWAY_LINEUP_SUBSTITUTES to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorites.TABLE_FAVORITES, true)
    }

}

val Context.database: MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)