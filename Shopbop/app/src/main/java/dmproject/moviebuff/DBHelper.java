package dmproject.moviebuff;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

/**
 * Created by 1 on 29.05.2016.
 */
public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    public static final String NAME_COLLUMN_LEVEL = "levels";
    public static final String NAME_COLLUMN_FINISHED = "finished";
    public static final String NAME_DB = "mydb.db";
    public static final String NAME_TABLE = "data";
    public static final int DB_VERSION = 1;
    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + NAME_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + NAME_COLLUMN_LEVEL
            + " integer, " + NAME_COLLUMN_FINISHED + " integer);";

    public DBHelper(Context context){
        super(context, NAME_DB, null, DB_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
