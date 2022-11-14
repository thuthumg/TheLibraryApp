package com.padcmyanmar.ttm.thelibraryapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.persistence.daos.ReadBooksDao
import com.padcmyanmar.ttm.thelibraryapp.persistence.daos.ShelfDao

@Database(
    entities = [ShelfVO::class,BooksListVO::class],
    version = 3,
    exportSchema = false
)
abstract class TheLibraryAppDatabase: RoomDatabase() {
    companion object {
        const val DB_NAME = "THE_LIBRARY_DB"

        var dbInstance: TheLibraryAppDatabase? = null

        fun getDBInstance(context: Context): TheLibraryAppDatabase? {

            when (dbInstance) {
                null -> {
                    dbInstance =
                        Room.databaseBuilder(context, TheLibraryAppDatabase::class.java, DB_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()

                }
            }

            return dbInstance
        }

    }

    abstract fun shelvesDao():ShelfDao

    abstract fun readBooksDao():ReadBooksDao

}