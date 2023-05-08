package com.example.trydesign;

import static com.example.trydesign.ShopItemDetails.items;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //instances of fragments and classes
    BottomNavigationView bottomNavigationView;

    CalandarFragment calandarFragment = new CalandarFragment();
    GardenFragment gardenFragment = new GardenFragment();
    HomeFragment homeFragment = new HomeFragment();
    ShopFragment shopFragment = new ShopFragment();
    StatisticsFragment statisticsFragment = new StatisticsFragment();

    public static DBHandler dbHandlerr;

    public void loadItemsFromDatabase(){
        //this method should get items from the database using dbHandlerr and store them in an array
        ArrayList<ShopItemDetails> items = new ArrayList<>();

        SQLiteDatabase db = dbHandlerr.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHandler.TABLE_NAME,  new String[]{});
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            String description = cursor.getString(3);
            int imageID = cursor.getInt(4);
            int amount = cursor.getInt(5);
            ShopItemDetails item = new ShopItemDetails(name, imageID, price, description, amount);
            items.add(item);
        }
        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DBHandler.DB_NAME,
                DBHandler.DESCRIPTION_COL,
                DBHandler.IMG_COL,
                DBHandler.NAME_COL,
                DBHandler.PRICE_COL
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();

        DBHandler dbHandlerr = new DBHandler(context);
        SQLiteDatabase d = dbHandlerr.getWritableDatabase();

        ShopItemDetails.init();
        String tableName = DBHandler.NAME_COL;
        dbHandlerr.doesYaaTableExist();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //me tryingtings
        String plantName = "";
        int plantPrice = 1;
        String plantDescription = "";
        String plantImg = "";

        //using addNewPlant with default values
        dbHandlerr.addNewPlant(plantName, plantPrice, plantDescription, plantImg, 0);

        //navigation-ing**********************
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            //if the item is selected the corresponding fragment will be loaded
            switch (item.getItemId()) {
                case R.id.homeId:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                case R.id.shopId:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, shopFragment).commit();
                    return true;
                case R.id.gardenId:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, gardenFragment).commit();
                    return true;
                case R.id.calandarId:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, calandarFragment).commit();
                    return true;
                case R.id.statisticsId:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, statisticsFragment).commit();
                    return true;
            }

            return false;
        });


    }
    public static ShopItemDetails findItemById(int id) {
        //Returns the information for an item when given the id of the item
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).itemID == id){
                return items.get(i);
            }
        }
        return null;
    }
}