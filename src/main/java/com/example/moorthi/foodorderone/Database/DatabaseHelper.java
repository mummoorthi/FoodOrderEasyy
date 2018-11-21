package com.example.moorthi.foodorderone.Database;

/**
 * Created by moorthy on 11/20/18.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.moorthi.foodorderone.Activity.CartDetails;
import com.example.moorthi.foodorderone.MainActivity;
import com.example.moorthi.foodorderone.Pojo.FoodDetails;
import com.example.moorthi.foodorderone.Pojo.cartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_FOODLIST = "FOODLIST";
    private static final String TABLE_ORDER = "ORDERLIST";

    // User Table Columns names
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_USER_ITEM = "item_name";
    private static final String COLUMN_USER_PRICE = "price";
    private static final String COLUMN_USER_COUNT = "count";

    private static final String COLUMN_ORDER_NAME="name";
    private static final String COLUMN_ORDER_PRICE="price";
    private static final String COLUMN_ORDER_COUNT="count";

    // create table sql query
    private String CREATE_FOODDETAIL_TABLE = "CREATE TABLE " + TABLE_FOODLIST + "("
            + COLUMN_RATING + " TEXT," + COLUMN_IMAGE + " TEXT,"
            + COLUMN_USER_ITEM + " TEXT," + COLUMN_USER_PRICE + " TEXT"+ ")";

    // create table sql query
    private String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
            + COLUMN_ORDER_NAME + " TEXT," + COLUMN_ORDER_PRICE + " TEXT," + COLUMN_ORDER_COUNT + " TEXT"+ ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_FOODLIST;
    private String DROP_ORDER_TABLE = "DROP TABLE IF EXISTS " + TABLE_ORDER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Activity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("CHECK_CTEATE_TABLE USER :"+CREATE_FOODDETAIL_TABLE);
        System.out.println("CHECK_CTEATE_TABLE ORDER :"+CREATE_ORDER_TABLE);
        db.execSQL(CREATE_FOODDETAIL_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_ORDER_TABLE);

        // Create tables again
        onCreate(db);

    }


    public void addFoodDetail(FoodDetails food) {
        if (!checkFoodExist(food.getItem_name())) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_RATING, food.getAverage_rating());
            values.put(COLUMN_IMAGE, food.getImage_url());
            values.put(COLUMN_USER_ITEM, food.getItem_name());
            values.put(COLUMN_USER_PRICE, food.getItem_price().toString());

            // Inserting Row
            db.insert(TABLE_FOODLIST, null, values);
            db.close();
        }
    }

    public Integer addOrder(FoodDetails food) {
        if (!checkOrderExist(food.getItem_name())) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ORDER_NAME, food.getItem_name());
            values.put(COLUMN_ORDER_PRICE, food.getItem_price().toString());
            values.put(COLUMN_ORDER_COUNT, "1");

            // Inserting Row
            db.insert(TABLE_ORDER, null, values);
            db.close();
            System.out.println("CHECK_DATA ADDORDER RETUNRN 1");
            return 1;
        }else {
            System.out.println("CHECK_DATA ADDORDER RETURN ELSE");
            return updateUser(food,true);
        }
    }

    public Integer removeOrder(FoodDetails food){
        System.out.println("CHECK_DATA REMOVEORDER CALLED");
        if (checkOrderExist(food.getItem_name())){
            System.out.println("CHECK_DATA REMOVEORDER INSIDE");
            return updateUser(food,false);
        }
        return 0;
    }


    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<FoodDetails> getAllFoods() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_RATING,
                COLUMN_IMAGE,
                COLUMN_USER_ITEM,
                COLUMN_USER_PRICE,
        };

        List<FoodDetails> foodList = new ArrayList<FoodDetails>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOODLIST,
                columns,
                null,
                null,
                null,
                null,
                null);


        if (cursor.moveToFirst()) {
            do {
                FoodDetails food = new FoodDetails();
                food.setAverage_rating(cursor.getDouble(cursor.getColumnIndex(COLUMN_RATING)));
                food.setImage_url(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                food.setItem_name(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ITEM)));
                BigDecimal decimal=new BigDecimal(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PRICE)));
                food.setItem_price(decimal);
                int count=getCountCurrentItem(food.getItem_name());
                food.setCount(count);
                foodList.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return foodList;
    }


    private boolean checkFoodExist(String item_name) {

        String[] columns = {
                COLUMN_USER_ITEM
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_ITEM + " = ?";

        String[] selectionArgs = {item_name};

        Cursor cursor = db.query(TABLE_FOODLIST,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    private boolean checkOrderExist(String item_name) {

        String[] columns = {
                COLUMN_ORDER_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_ORDER_NAME + " = ?";

        String[] selectionArgs = {item_name};


        Cursor cursor = db.query(TABLE_ORDER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    private String getCost(String item_name) {

        String[] columns = {
                COLUMN_USER_PRICE
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_ITEM + " = ?";

        // selection argument
        String[] selectionArgs = {item_name};


        Cursor cursor = db.query(TABLE_FOODLIST,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();

        if (cursorCount > 0) {
            while (cursor.moveToNext()) {
                String price =  cursor.getString(cursor.getColumnIndex(COLUMN_USER_PRICE));
                return price;
            }
        }
        cursor.close();
        db.close();

        return null;
    }


    private Integer updateUser(FoodDetails food,Boolean wantToAdd) {
        System.out.println("CHECK_DATA UPDATE_USER CALLED : "+wantToAdd);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_NAME, food.getItem_name());
        values.put(COLUMN_USER_PRICE, food.getItem_price().toString());

        int count=getCountCurrentItem(food.getItem_name());
        System.out.println("CHECK_DATA UPDATE_USER RETURN_COUNT :"+count);
            if (wantToAdd) {
                count = count+1;
            }else {
                count=count-1;
            }
            values.put(COLUMN_ORDER_COUNT, String.valueOf(count));

        // updating row
        db.update(TABLE_ORDER, values, COLUMN_ORDER_NAME + " = ?",
                new String[]{food.getItem_name()});
        db.close();
        System.out.println("CHECK_DATA UPDATE_USER COUNT : "+count);
        return count;
    }

    private Integer getCountCurrentItem(String item_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+COLUMN_ORDER_COUNT+" FROM "+TABLE_ORDER+" WHERE "+COLUMN_ORDER_NAME+" ='" + item_name+"'" ;
        System.out.println("CHECK_DATA SELECT : "+query);
        Cursor  cursor = db.rawQuery(query,null);
        System.out.println("CHECK_DATA GETCOUNT : "+cursor.getCount());
        while (cursor.moveToNext()) {
            String count =  cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_COUNT));
            System.out.println("CHECK_DATA GETCOUNT count : "+count);
             return Integer.parseInt(count);
        }
        cursor.close();
        return 0;
    }

    public Integer getGrandTotal() {
        SQLiteDatabase db = this.getReadableDatabase();
        int countTotal=0;
        String query = "SELECT "+COLUMN_ORDER_COUNT+" FROM "+TABLE_ORDER ;
        System.out.println("CHECK_DATA SELECT : "+query);
        Cursor  cursor = db.rawQuery(query,null);
        System.out.println("CHECK_DATA GETCOUNT : "+cursor.getCount());
        while (cursor.moveToNext()) {
            String count =  cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_COUNT));
            countTotal=countTotal+Integer.parseInt(count);
        }
        cursor.close();
        return countTotal;
    }

    public List<cartItem> getCheckout() {
        List<cartItem> datalist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        BigDecimal total=new BigDecimal("0");
        String query = "SELECT "+COLUMN_ORDER_COUNT+","+COLUMN_ORDER_NAME+" FROM "+TABLE_ORDER ;
        System.out.println("CHECK_CHECKOUT_PAGE GRAN_FINAL SELECT : "+query);
        Cursor  cursor = db.rawQuery(query,null);
        System.out.println("CHECK_CHECKOUT_PAGE GRAN_FINAL GETCOUNT : "+cursor.getCount());

        while (cursor.moveToNext()) {
            cartItem listcart = new cartItem();
            listcart.setCount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_COUNT))));
            String count =  cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_COUNT));
            String item_name =  cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_NAME));
            listcart.setItemname(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_NAME)));
            BigDecimal num_total=getFinalCost(count,getCost(item_name));
            total=total.add(num_total);
            //listcart.setGranttotal(String.valueOf(total.add(num_total)));
            listcart.setGranttotal(String.valueOf(num_total));
            listcart.setGranttoall(String.valueOf(total));
            datalist.add(listcart);

            System.out.println("CHECK_CHECKOUT_PAGE GRAN_FINAL ITEMNAME :"+item_name+" & COUNT :"+count+" & PRICE :"+getCost(item_name)+" & TOTAL :"+num_total);
        }

        System.out.println("CHECK_CHECKOUT_PAGE GRAN_FINAL GRANDTOTAL $$$$$$$$ :"+total);
        if(!cursor.isClosed()){
            cursor.close();
        }
        return datalist;
    }

    private BigDecimal getFinalCost(String count,String amount){
        BigDecimal int_count=new BigDecimal(count);
        BigDecimal int_amount=new BigDecimal(amount);
        return int_amount.multiply(int_count);

    }

}