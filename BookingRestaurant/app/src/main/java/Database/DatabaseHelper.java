package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Order;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DB_NAME ="BookingRestaurant.db";
    private static final int DB_VER = 1;
    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCart()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] dbSelect = {"ProductId", "ProductName", "Quatity", "Price"};
        String sqlTable = "Order";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db,dbSelect, null, null, null, null, null);
        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price"))));
            }while (c.moveToNext());
        }
        return result;
    }

    public void addtToCart(Order order)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Order(ProductId, ProductName, Quantity, Price) VALUES('%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice());
        db.execSQL(query);
    }
}
