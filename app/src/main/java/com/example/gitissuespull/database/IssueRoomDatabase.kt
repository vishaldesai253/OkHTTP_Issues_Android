package com.example.gitissuespull.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gitissuespull.model.Comment
import com.example.gitissuespull.model.Issue

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Issue::class,Comment::class], version = 1, exportSchema = false)
public abstract class IssueRoomDatabase : RoomDatabase() {

    abstract fun issueDao(): IssueDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: IssueRoomDatabase? = null

        fun getDatabase(context: Context): IssueRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IssueRoomDatabase::class.java,
                    "issue_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}