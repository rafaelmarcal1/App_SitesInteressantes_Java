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
    private SQliteHelper mHelper;

    private SQLiteDatabase mDatabase;

    private Context context;

    public SiteDao(Context context){
        this.context = context;
        mHelper = new SQliteHelper(context);
    }

    public void create(Site site){
        TagSiteDao tagDao = new TagSiteDao(context);

        int tagId = tagDao.recuperateTagId(site.getTag());

        ContentValues values = new ContentValues();
        values.put(DatabaseContracts.TableSite.COLUMN_TITLE, site.getTitle());
        values.put(DatabaseContracts.TableSite.COLUMN_URL, site.getUrl());
        values.put(DatabaseContracts.TableSite.COLUMN_TAG_ID, tagId );

        mDatabase = mHelper.getWritableDatabase();
        mDatabase.insert(DatabaseContracts.TableSite.CREATE_TABLE,
                null,
                values);
        mDatabase.close();
    }

    public List<Site> recuperateAll(){
        String query  = "SELECT " +
                "S." + DatabaseContracts.TableSite.COLUMN_TITLE + ", " +
                "S." + DatabaseContracts.TableSite.COLUMN_URL + ", " +
                "T." + DatabaseContracts.TableTag.COLUMN_TAG +
                " FROM " + DatabaseContracts.TableSite.TABLE_NAME + " AS S" +
                " INNER JOIN " + DatabaseContracts.TableTag.TABLE_NAME + " AS T" +
                " ON S." + DatabaseContracts.TableSite.COLUMN_TAG_ID + " = T." + DatabaseContracts.TableTag._ID +
                " ORDER BY S." + DatabaseContracts.TableSite.COLUMN_TITLE;

        mDatabase = mHelper.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery(query, null);
        List<Site> list = new ArrayList<>();

        while (cursor.moveToNext()){
            list.add(
                    new Site(cursor.getString(0),
                            cursor.getString(1),
                            new TagSite(cursor.getString(2)
                            )
                    )
            );
        }

        cursor.close();
        return list;
    }
}
