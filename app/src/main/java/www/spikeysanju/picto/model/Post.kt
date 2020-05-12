package www.spikeysanju.picto.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "posts"
)
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val url: String
)