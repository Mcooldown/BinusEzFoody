package com.example.binusezyfoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.ItemAdapter;

public class ItemListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> itemList;
    RecyclerView.Adapter adapter;
    TextView itemCategory;
    String itemCategoryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        itemCategory = findViewById(R.id.item_category);

        Intent fromMain = getIntent();
        itemCategoryString = fromMain.getStringExtra("itemCategory");
        itemCategory.setText(itemCategoryString);

        Repository repository = Repository.getInstance();
        itemList =repository.getItemByCategory(itemCategoryString);

        recyclerView = findViewById(R.id.recyclerview_item);
        adapter = new ItemAdapter(this, itemList, itemCategoryString);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
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
        Intent toMyOrder = new Intent(ItemListActivity.this, MyOrderActivity.class);
        startActivity(toMyOrder);
    }
}