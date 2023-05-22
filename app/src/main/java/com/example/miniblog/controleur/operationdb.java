package com.example.miniblog.controleur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.miniblog.R;

public class operationdb extends SQLiteOpenHelper {
    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    private Context context;
    private static final String Database_Name = "MiniBlog.db";
    public static final int  Database_Version = 1;
    private static final String Articles_Table_name = "articles";
    private static final String Articles_column_id = "id";
    private static final String Articles_column_titre = "titre";
    private static final String Articles_column_contenu = "contenue";
    public operationdb(@Nullable Context context) {
        super(context, Database_Name, null, Database_Version);
        this.context = context;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = context.getString(R.string.create_table)+" "+ Articles_Table_name + "(" + Articles_column_id +" INTEGER primary key autoincrement,"
                + Articles_column_titre +" varchar(50),"+ Articles_column_contenu +" TEXT);";
        db.execSQL(query);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(context.getString(R.string.drop_table_if_exists) +" "+  Articles_Table_name);
        onCreate(db);
    }
    void ajouterArticle(String titreArticle,String contenuArticle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Articles_column_titre,titreArticle);
        cv.put(Articles_column_contenu,contenuArticle);
        long result = db.insert(Articles_Table_name,null,cv);
        if(result==-1){
            Toast.makeText(context, "erreur d'insertion ", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "insertion réussi", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor LireDonnee(){
        String query = "select * from " + Articles_Table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curseur = null;
        if(db != null){
            curseur = db.rawQuery(query,null);
        }
        return curseur;
    }
}