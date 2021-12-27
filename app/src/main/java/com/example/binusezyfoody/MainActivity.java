package com.example.binusezyfoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.option_my_order:
                goToMyOrder();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToMyOrder(){
        Intent toMyOrder = new Intent(MainActivity.this, MyOrderActivity.class);
        startActivity(toMyOrder);
    }

    public void handleSelectCategory(View view){
        Intent goToItemList = new Intent(MainActivity.this, ItemListActivity.class);
        goToItemList.putExtra("itemCategory", view.getTag().toString());
        startActivity(goToItemList);
    }

}