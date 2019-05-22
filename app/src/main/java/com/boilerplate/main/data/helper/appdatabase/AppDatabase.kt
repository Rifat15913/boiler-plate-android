package com.boilerplate.main.data.helper.appdatabase

import android.content.Context
import androidx.room.Database
import com.boilerplate.main.data.helper.appdatabase.AppDatabase.Companion.sInstance
import com.boilerplate.main.data.local.user.UserDao
import com.boilerplate.main.data.local.user.UserEntity
import com.boilerplate.utils.helper.DataUtils
import com.boilerplate.utils.libs.room.BaseDatabase
import com.itechsoftsolutions.tree.R

/**
 * This is the database class of the LusoSmile project. If we try to have local database in future,
 * we are providing room database class for our app here.
 * @property sInstance instance of the app database class
 * @author Mohd. Asfaq-E-Azam Rifat
 */
@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : BaseDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var sInstance: AppDatabase? = null

        /**
         * This method returns an instance of the database class
         *
         * @return instance of the database class
         * */
        @Synchronized
        fun on(): AppDatabase? {
            return sInstance
        }

        /**
         * This method initializes the database class
         * @param context application context
         * */
        fun init(context: Context) {
            synchronized(AppDatabase::class.java) {
                sInstance = createDb(context,
                        DataUtils.getString(R.string.app_name), AppDatabase::class.java)
            }
        }
    }
}
