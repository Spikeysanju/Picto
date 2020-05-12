package www.spikeysanju.picto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import www.spikeysanju.picto.model.Post

@Database(
    entities = [Post::class],
    version = 1
)

abstract class PostDatabase: RoomDatabase()  {

    abstract fun getPostDao(): PostDao

    companion object {
        @Volatile
        private var instance: PostDatabase? = null
        private val LOCK = Any()

        // Check for DB instance if not null then get or insert or else create new DB Instance
        operator fun invoke(context: Context) = instance?: synchronized(LOCK){

            instance?: createDatabase(context).also{ instance = it }

        }

        // create db instance
        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PostDatabase::class.java,
            "post_db.db"
        ).build()


    }

}