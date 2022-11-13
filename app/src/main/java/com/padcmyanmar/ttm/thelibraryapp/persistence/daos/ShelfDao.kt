package com.padcmyanmar.ttm.thelibraryapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO

@Dao
interface ShelfDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelves(
        shelfVO: ShelfVO
    )


    @Query("SELECT * FROM shelves")
    fun getAllShelves(): List<ShelfVO>

    @Query("DELETE FROM shelves WHERE id = :shelfId")
    fun deleteByShelfId(shelfId: Int)


}