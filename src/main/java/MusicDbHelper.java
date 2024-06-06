import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MusicDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "music_database";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ARTIST = "artist";
    public static final String COLUMN_ALBUM = "album";
    public static final String COLUMN_DURATION = "duration";

    // SQL statement to create the songs table
    private static final String SQL_CREATE_SONGS_TABLE = "CREATE TABLE " + TABLE_SONGS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_ARTIST + " TEXT, " +
            COLUMN_ALBUM + " TEXT, " +
            COLUMN_DURATION + " TEXT)";

    public MusicDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SONGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }
}
