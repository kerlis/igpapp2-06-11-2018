package sistemasfireg.igp.org.sismosperu;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;
public class Databasesqlitehelper extends SQLiteOpenHelper  {

    public static final  String DATABASE_NAME = "configuracion_notificaciones";
    public static final  String DABLE_NAME = "configuracion_mapa";
    public static final  String COL1 = "configuracion_mapa";
    public static final  String COL2 = "configuracion_mapa";
    public static final  String COL3 = "configuracion_mapa";
    public static final  String COL4 = "configuracion_mapa";

    public Databasesqlitehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
