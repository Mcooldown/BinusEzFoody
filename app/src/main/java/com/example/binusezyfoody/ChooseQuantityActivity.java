package com.example.binusezyfoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChooseQuantityActivity extends AppCompatActivity {

    EditText quantity;
    Item selectedItem;
    String itemCategory;
    int position;
    Button btnOrderMore, btnToMyOrder, btnSubmit;
    TextView itemName, itemPrice;
    ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quantity);

        btnOrderMore = findViewById(R.id.btn_order_more);
        btnToMyOrder = findViewById(R.id.btn_to_my_order);
        btnSubmit = findViewById(R.id.btn_order_submit);

        Intent fromItemList = getIntent();
        position = Integer.parseInt(fromItemList.getStringExtra("position"));
        itemCategory = fromItemList.getStringExtra("itemCategory");
        Repository repository = Repository.getInstance();
        selectedItem = repository.getItemByIndex(position);

        quantity = findViewById(R.id.text_quantity);
        itemName = findViewById(R.id.item_name_detail);
        itemPrice = findViewById(R.id.item_price_detail);
        itemImage = findViewById(R.id.item_image_detail);

        itemName.setText(selectedItem.name);
        itemPrice.setText(selectedItem.price + "");
        itemImage.setImageResource(selectedItem.image);
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
                Intent toMyOrder = new Intent(ChooseQuantityActivity.this, MyOrderActivity.class);
                startActivity(toMyOrder);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToMyOrder(View view){
        Intent toMyOrder = new Intent(ChooseQuantityActivity.this, MyOrderActivity.class);
        startActivity(toMyOrder);
    }

    public void onClickOrder(View view){

        if(quantity.getText().toString().equals("")){
            Toast.makeText(ChooseQuantityActivity.this, "Quantity must be filled", Toast.LENGTH_SHORT).show();}
        else if(Integer.parseInt(quantity.getText().toString()) <= 0){
            Toast.makeText(ChooseQuantityActivity.this, "Quantity minimum 1", Toast.LENGTH_SHORT).show();}
        else{

            Repository repository = Repository.getInstance();
            repository.addItemToOrder(selectedItem, Integer.parseInt(quantity.getText().toString()));

            Toast.makeText(ChooseQuantityActivity.this, "Order successfully added", Toast.LENGTH_SHORT).show();

            quantity.setFocusable(false);
            btnSubmit.setVisibility(View.GONE);
            btnToMyOrder.setVisibility(View.VISIBLE);
            btnOrderMore.setVisibility(View.VISIBLE);
        }
    }

    public void toItemList(View view){
        Intent toItemList = new Intent(ChooseQuantityActivity.this, ItemListActivity.class);
        toItemList.putExtra("itemCategory", itemCategory);
        startActivity(toItemList);
    }
}