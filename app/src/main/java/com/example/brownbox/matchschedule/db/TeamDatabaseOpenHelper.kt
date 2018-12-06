package com.example.brownbox.matchschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.brownbox.matchschedule.favorite.TeamFavorites
import org.jetbrains.anko.db.*

class TeamDatabaseOpenHelper(ctx:Context):
    ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: TeamDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): TeamDatabaseOpenHelper {
            if (instance == null) {
                instance =
                        TeamDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as TeamDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(TeamFavorites.TABLE_TEAM_FAVORITE, true,
            TeamFavorites.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavorites.TEAM_ID to TEXT,
            TeamFavorites.TEAM_NAME to TEXT,
            TeamFavorites.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TeamFavorites.TABLE_TEAM_FAVORITE, true)
    }
}

val Context.teamDatabase: TeamDatabaseOpenHelper
get() = TeamDatabaseOpenHelper.getInstance(applicationContext)
