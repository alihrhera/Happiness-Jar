package era.apps.happinessjar.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Message") // room table name  for Message
// room table name  for Message
data class AppMessage(
        var status: Int,
        var content: String,
        val type: String,
){
    @PrimaryKey(autoGenerate = true) var id: Int=0
    set(value) { field = value
    }
    get() = field



    @Ignore
    fun isLiked(): Boolean {
        return status == 1
    }

}