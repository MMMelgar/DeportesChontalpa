package com.example.DeportesChontalpa.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public static final String ID="_idNote";

    private static final String DATABASE="Tablas";

    private static final String TABLE1="Arti";
    private static final String TABLE2="Talla";
    private static final String TABLE3="Color";
    private static final String TABLE4="Categoria";
    private static final String TABLE5="Deporte";
    private static final String TABLE6="Marca";
    private static final String TABLE7="User";
    private static final String TABLE8="Direccion";
    private static final String TABLE9="FormasPago";

    private static final String Nombre="Nom";

    private static final int Database_Version=1;

    public AdminSQLiteOpenHelper(Context context){
        super(context, DATABASE, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table " + TABLE1 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT, DESCRIPCION TEXT, " +
                "PRECIO TEXT, DISPONIBLES TEXT, IDT INTEGER NOT NULL, IDCO INTEGER NOT NULL, IDM INTEGER NOT NULL, IDCA INTEGER NOT NULL," +
                " Novedades INTEGER NOT NULL, Ofertas INTEGER NOT NULL, Recomendados INTEGER NOT NULL, IDDE INTEGER NOT NULL, " +
                "FOREIGN KEY (IDT) REFERENCES " + TABLE2 + " ("+ ID + "), FOREIGN KEY (IDCO) REFERENCES " + TABLE3 + " ("+ ID + "), " +
                "FOREIGN KEY (IDM) REFERENCES " + TABLE6 + " ("+ ID + "), " + "FOREIGN KEY (IDCA) REFERENCES " + TABLE4 + " ("+ ID + "), " +
                "FOREIGN KEY (Novedades) REFERENCES " + TABLE4 + " ("+ ID + "), " + "FOREIGN KEY (Ofertas) REFERENCES " + TABLE4 + " ("+ ID + "), " +
                "FOREIGN KEY (Recomendados) REFERENCES " + TABLE4 + " ("+ ID + "), " + "FOREIGN KEY (IDDE) REFERENCES " + TABLE5 + " ("+ ID + "))");
        BaseDeDatos.execSQL("create table " + TABLE2 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT)");
        BaseDeDatos.execSQL("create table " + TABLE3 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT)");
        BaseDeDatos.execSQL("create table " + TABLE4 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT)");
        BaseDeDatos.execSQL("create table " + TABLE5 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT)");
        BaseDeDatos.execSQL("create table " + TABLE6 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT)");
        BaseDeDatos.execSQL("create table " + TABLE7 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT, TU TEXT, TELEFONO TEXT)");
        BaseDeDatos.execSQL("create table " + TABLE8 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT, CALLE TEXT, COLONIA TEXT, NOEXT TEXT, NOINT TEXT)");
        BaseDeDatos.execSQL("create table " + TABLE9 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Nombre + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE5);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE6);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE7);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE8);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE9);
        onCreate(sqLiteDatabase);
    }

    public void updateT(String TABLE, String nombre, int id,String condicion){
        ContentValues valores = new ContentValues();
        valores.put(nombre, id);
        String args[]= {condicion};
        this.getWritableDatabase().update(TABLE, valores,Nombre +"=?",args);
    }

    public void updateT(String TABLE, String nombre, String condicion){
        ContentValues valores = new ContentValues();
        valores.put(Nombre, nombre);
        String args[]= {condicion};
        this.getWritableDatabase().update(TABLE, valores,Nombre +"=?",args);
    }

    public void updateT(String nombre, String tu, String telefono, String condicion){
        ContentValues valores = new ContentValues();
        valores.put(Nombre, nombre);
        valores.put("TU", tu);
        valores.put("TELEFONO", telefono);
        String args[]= {condicion};
        this.getWritableDatabase().update(TABLE7, valores,Nombre +"=?",args);
    }

    public void updateT(String nombre, String descripcion,String precio, String disponibles, int idtalla, int idcolor, int idmarca, int idcategoria, int iddeporte, String condicion){
        ContentValues valores = new ContentValues();
        valores.put(Nombre, nombre);
        valores.put("DESCRIPCION", descripcion);
        valores.put("PRECIO", precio);
        valores.put("DISPONIBLES", disponibles);
        valores.put("IDT", idtalla);
        valores.put("IDCO", idcolor);
        valores.put("IDM", idmarca);
        valores.put("IDCA1", idcategoria);
        valores.put("IDCA2", -1);
        valores.put("IDCA3", -1);
        valores.put("IDCA4", -1);
        valores.put("IDDE", iddeporte);
        String args[]= {condicion};
        this.getWritableDatabase().update(TABLE1, valores,Nombre +"=?",args);
    }

    public void updateT(String nombre, String calle, String colonia, String noext, String noint, String condicion){
        ContentValues valores = new ContentValues();
        valores.put(Nombre, nombre);
        valores.put("CALLE", calle);
        valores.put("COLONIA", colonia);
        valores.put("NOEXT", noext);
        valores.put("NOINT", noint);
        String args[]= {condicion};
        this.getWritableDatabase().update(TABLE8, valores,Nombre +"=?",args);
    }

    public void AddNoteT(String TABLE, String nombre){
        ContentValues valores = new ContentValues();
        valores.put(Nombre, nombre);
        this.getWritableDatabase().insert(TABLE, null, valores);
    }

    public void AddNoteT(String nombre, String tu, String telefono){
        ContentValues valores = new ContentValues();
        valores.put(Nombre, nombre);
        valores.put("TU", tu);
        valores.put("TELEFONO", telefono);
        this.getWritableDatabase().insert(TABLE7, null, valores);
    }

    public void AddNoteT(String nombre, String descripcion,String precio, String disponibles, int idtalla, int idcolor, int idmarca, int idcategoria, int iddeporte){
        ContentValues valores = new ContentValues();
        valores.put(Nombre, nombre);
        valores.put("DESCRIPCION", descripcion);
        valores.put("PRECIO", precio);
        valores.put("DISPONIBLES", disponibles);
        valores.put("IDT", idtalla);
        valores.put("IDCO", idcolor);
        valores.put("IDM", idmarca);
        valores.put("IDCA", idcategoria);
        valores.put("IDDE", iddeporte);
        this.getWritableDatabase().insert(TABLE1, null, valores);
    }

    public void AddNoteT(String nombre, String calle, String colonia, String noext, String noint){
        ContentValues valores = new ContentValues();
        valores.put(Nombre, nombre);
        valores.put("CALLE", calle);
        valores.put("COLONIA", colonia);
        valores.put("NOEXT", noext);
        valores.put("NOINT", noint);
        this.getWritableDatabase().insert(TABLE8, null, valores);
    }

    public Cursor getNota(String TABLE, String condicion){
        String[] args= new String[]{condicion};
        Cursor c= this.getReadableDatabase().query(TABLE, null, Nombre+"=?",args,null,null, null);
        return c;
    }

    public void deleteNota(String TABLE, String condicion){
        String args[]= {condicion};
        this.getWritableDatabase().delete(TABLE,Nombre +"=?", args);
    }

    public Cursor getNotas(String TABLE){
        Cursor c= this.getReadableDatabase().query(TABLE, null, null, null,null,null, null);
        return c;
    }

    public void deleteNotasTablas(String TABLE){
        this.getWritableDatabase().delete(TABLE,null,null);
    }

}
