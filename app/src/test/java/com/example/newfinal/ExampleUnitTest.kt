package com.example.newfinal

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newfinal.activity.BookDao
import com.example.newfinal.model.BookDatabase
import com.example.newfinal.model.BookSearchResultData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    private lateinit var bookDao: BookDao
    private lateinit var db: BookDatabase



    @Before
    public fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BookDatabase::class.java
        ).build()
        bookDao = db.bookDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadBook() = runBlocking {
        val book = BookSearchResultData("mfdakf", "", "Harry Potter", "Someone", "THis book is about small boy", "", "", false)
        bookDao.addBook(book)
        val spends = bookDao.readAllData()
        assertNotNull(spends)
    }

}