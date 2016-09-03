package utilidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private Context mContext;
    //llamamos al constructor
    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
        mContext = context;
    }

    //creamos la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        llenarDatos(db);
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        llenarDatos(db);
    }

    public void llenarDatos(SQLiteDatabase db){
        InputStream is = null;
        try {
            is = mContext.getAssets().open("Taxi.sql");
            if (is != null) {
                db.beginTransaction();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while (!TextUtils.isEmpty(line)) {
                    db.execSQL(line);
                    line = reader.readLine();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            // Muestra log
        } finally {
            db.endTransaction();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // Muestra log
                }
            }
        }
    }
}