package br.com.marvy.persistence

import androidx.paging.DataSource
import androidx.room.*
import br.com.marvy.model.CharactersData

@Entity(tableName = "CharactersInfoTable")
data class CharactersInfo(
    @ColumnInfo(name = "pageIndex")
    val pageIndex: Int = 100,
    @ColumnInfo(name = "comics")
    val comics: List<String>,
    @ColumnInfo(name = "thumbnailPath")
    val thumbnailPath: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "resourceURI")
    val resourceURI: String,
    @ColumnInfo(name = "modified")
    val modified: String,
    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey
    val id: Int
)


fun transformCharactersData(data: CharactersData, nextIndex: Int): CharactersInfo {
    val thumbnailPath =
        "${data.thumbnail.path}/landscape_incredible.${data.thumbnail.extension}"
            .replace("http", "https")

    val comics = ArrayList<String>()

    data.comics.items.forEach { item ->
        comics.add("${item.name}@")
    }

    return CharactersInfo(
        thumbnailPath = thumbnailPath,
        description = data.description,
        resourceURI = data.resourceURI,
        modified = data.modified,
        pageIndex = nextIndex,
        name = data.name,
        comics = comics,
        id = data.id
    )
}

class Converters {

    @TypeConverter
    fun fromListToString(data: List<String>): String {
        return data.joinToString("@")
    }

    @TypeConverter
    fun fromStringToList(data: String): List<String> {
        return data.split("@")
    }
}

@Dao
interface CharactersInfoDAO {

    @Query("SELECT * FROM CharactersInfoTable")
    fun dataSource(): DataSource.Factory<Int, CharactersInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: CharactersInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<CharactersInfo>)
}

@TypeConverters(Converters::class)
@Database(entities = [CharactersInfo::class], version = 1)
abstract class CharactersDB : RoomDatabase() {
    abstract fun charactersDAO(): CharactersInfoDAO
}