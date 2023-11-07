package br.edu.ifsp.dmo.sitesinteressantes.dao;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract(){

    }

    public static final String DATABASE_NAME = "sites_interessantes.db";
    public static final int VERSION = 1;

    public static class TableSite implements BaseColumns{
        public static final String TABLE_NAME = "sites";
        public static final String COLUMN_TITLE = "titulo";
        public static final String COLUMN_URL =  "endereco";
        public static final String COLUMN_TAG = "tag_id";

        public static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " +
                        TABLE_NAME + " ( " +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_TITLE + " TEXT NOT NUL, " +
                        COLUMN_URL + " TEXT, " +
                        COLUMN_TAG + " INTEGER, " +
                        "FOREIGN KEY (" + COLUMN_TAG + ") REFERENCES " +
                        TableTag.TABLE_NAME + " ( " + TableTag._ID + ") )";
    }

    public static class TableTag implements BaseColumns{
        public static final String TABLE_NAME = "tags";
        public static final String COLUMN_TAG = "tag";
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " ( " +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TAG + " TEXT UNIQUE)";
    }
}
