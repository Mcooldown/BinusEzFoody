package com.example.binusezyfoody;

import java.util.ArrayList;

public class Repository {

    private static Repository repository= null;
    private static ArrayList<Item> itemList;
    private static ArrayList<OrderItem> orderList;

    public static Repository getInstance(){

        if(repository == null){
            repository = new Repository();

            itemList = new ArrayList<>();
            itemList.add(new Item("Drink 1", "Drinks", 30000, R.drawable.drink));
            itemList.add(new Item("Drink 2", "Drinks", 10000, R.drawable.drink));
            itemList.add(new Item("Drink 3", "Drinks", 20000, R.drawable.drink));
            itemList.add(new Item("Snack 1", "Snacks", 10000, R.drawable.snack));
            itemList.add(new Item("Snack 2", "Snacks", 20000, R.drawable.snack));
            itemList.add(new Item("Food 1", "Foods", 50000, R.drawable.food));
            itemList.add(new Item("Food 2", "Foods", 20000, R.drawable.food));
            itemList.add(new Item("Top-up 50000", "TopUp", 51500, R.drawable.topup));

            orderList = new ArrayList<>();
        }
        return repository;
    }

    public ArrayList<Item> getItemByCategory(String category){

        ArrayList<Item> itemListByCategory = new ArrayList<>();

        for(int i=0 ;i < itemList.size();i++){
            if(itemList.get(i).category.equals(category)){
                itemListByCategory.add(itemList.get(i));
            }
        }
        return itemListByCategory;
    }

    public void addItemToOrder(Item item , int quantity){

        for(int i=0;i<orderList.size();i++){
            if(orderList.get(i).item.name.equals(item.name)){
                orderList.get(i).quantity += quantity;
                return;
            }
        }

        OrderItem orderItem = new OrderItem(item, quantity);
        orderList.add(orderItem);
    }

    public ArrayList<OrderItem> getAllOrder(){
        return orderList;
    }

    public void clearAllOrder(){
        orderList.clear();
    }

    public Item getItemByIndex(int index){
        return itemList.get(index);
    }

    public void deleteOrderByIndex(int index){
        orderList.remove(index);
    }
}

