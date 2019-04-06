package mx.mobilestudio.storage.roomdatabase

import androidx.room.*


@Entity(
    tableName = "tasksEntity",
    indices = [
        Index(
            value = ["id"],
            unique = true
        )
    ]
)
data class EntityTODO(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "description") var descriptionTask: String = ""
)

@Ignore
val date: String = ""