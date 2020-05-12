package www.spikeysanju.picto.data.api.db

import androidx.lifecycle.LiveData
import androidx.room.*
import www.spikeysanju.picto.model.Post

@Dao
interface PostDao  {

    // Update or Insert Post
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPost(post: Post):Long

    // Get All Post from DB
    @Query("SELECT * FROM posts")
    fun getAllPost(): LiveData<List<Post>>

    // delete post from DB
    @Delete
    suspend fun deletePost(post: Post)


}