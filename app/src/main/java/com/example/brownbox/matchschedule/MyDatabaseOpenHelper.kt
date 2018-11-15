package com.example.brownbox.matchschedule

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.brownbox.matchschedule.favorite.Favorites
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance?: MyDatabaseOpenHelper(ctx)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Favorites.TABLE_FAVORITES, true,
            Favorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorites.ID_EVENT to TEXT,
            Favorites.DATE_EVENT to TEXT,
            Favorites.STR_HOME_TEAM to TEXT,
            Favorites.ID_HOME_TEAM to TEXT,
            Favorites.INT_HOME_SCORE to TEXT,

            Favorites.STR_AWAY_TEAM to TEXT,
            Favorites.ID_AWAY_TEAM to TEXT,
            Favorites.INT_AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorites.TABLE_FAVORITES, true)
    }

}

val Context.database: MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)