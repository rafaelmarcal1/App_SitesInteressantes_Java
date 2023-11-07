package br.edu.ifsp.dmo.sitesinteressantes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo.sitesinteressantes.model.Site;
import br.edu.ifsp.dmo.sitesinteressantes.model.TagSite;

public class SiteDao {
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;
    private Context context;

    public SiteDao(Context context){
        this.context = context;
        mHelper = new SQLiteHelper(context);
    }

    public void create(Site site){
        TagSiteDao tagDao = new TagSiteDao(context);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TableSite.COLUMN_TITLE, site.getTitle());
        values.put(DatabaseContract.TableSite.COLUMN_URL, site.getTitle());
        values.put(DatabaseContract.TableSite.COLUMN_TAG_ID, tagId);

        mDatabase = mHelper.getWritableDatabase();
        mDatabase.insert(DatabaseContract.TableSite.TABLE_NAME,null,values);
    }

    public List<Site> recuperateAll(){
        String query = "SELECT " +
                "S." + DatabaseContract.TableSite.COLUMN_TITLE + ", " +
                "S. " + DatabaseContract.TableSite.COLUMN_URL + ", " +
                "T. " + DatabaseContract.TableTag.COLUMN_TAG +
                " FROM " + DatabaseContract.TableSite.TABLE_NAME + " AS S" +
                " INNER JOIN " + DatabaseContract.TableTag.TABLE_NAME + " AS T" +
                " ON S." + DatabaseContract.TableSite.COLUMN_TAG_ID +
                " = T." + DatabaseContract.TableTag._ID +
                " ORDER BY S." + DatabaseContract.TableSite.COLUMN_TITLE;

        mDatabase = mHelper.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery(query, null);

        List<Site> list new ArrayList<>();

        while (cursor.moveToNext()){
            list.add(
                    new Site(cursor.getString(0),
                            cursor.getString(1),
                            new TagSite(cursor.getString(2)))
            );
        }

        cursor.close();

    }
}