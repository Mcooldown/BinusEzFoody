package com.example.binusezyfoody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.OrderAdapter;

public class MyOrderActivity extends AppCompatActivity {

    ArrayList<OrderItem> orderList;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    TextView totalPrice;

    public int sumTotalPrice(){
        int total = 0;
        for(int i=0; i< orderList.size();i++){
            total += orderList.get(i).item.price*orderList.get(i).quantity;
        }
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        Repository repository = Repository.getInstance();
        orderList = repository.getAllOrder();

        recyclerView = findViewById(R.id.recyclerview_order);
        adapter = new OrderAdapter(this, orderList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        totalPrice = findViewById(R.id.text_total_price);
        totalPrice.setText("Total Price: Rp" + sumTotalPrice());
    }

    public void onClickPay(View view){

        if(orderList.size() <= 0){
            Toast.makeText(MyOrderActivity.this, "Please order an item first", Toast.LENGTH_SHORT).show();
        }else{
            Intent toOrderComplete = new Intent(MyOrderActivity.this, OrderCompleteActivity.class);
            startActivity(toOrderComplete);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(MyOrderActivity.this, "Cannot back to previous step", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.option_to_main:
                Intent backToMenu = new Intent(MyOrderActivity.this, MainActivity.class);
                startActivity(backToMenu);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}