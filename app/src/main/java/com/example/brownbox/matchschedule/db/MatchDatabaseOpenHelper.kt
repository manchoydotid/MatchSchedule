package com.example.brownbox.matchschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.brownbox.matchschedule.favorite.MatchFavorites
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context)
    : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) = instance
            ?: MyDatabaseOpenHelper(ctx)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            MatchFavorites.TABLE_FAVORITES, true,
            MatchFavorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchFavorites.ID_EVENT to TEXT,
            MatchFavorites.DATE_EVENT to TEXT,
            MatchFavorites.STR_HOME_TEAM to TEXT,
            MatchFavorites.ID_HOME_TEAM to TEXT,
            MatchFavorites.INT_HOME_SCORE to TEXT,

            MatchFavorites.STR_AWAY_TEAM to TEXT,
            MatchFavorites.ID_AWAY_TEAM to TEXT,
            MatchFavorites.INT_AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(MatchFavorites.TABLE_FAVORITES, true)
    }

}

val Context.database: MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)