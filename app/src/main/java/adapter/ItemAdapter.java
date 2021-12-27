package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.binusezyfoody.ChooseQuantityActivity;
import com.example.binusezyfoody.Item;
import com.example.binusezyfoody.ItemListActivity;
import com.example.binusezyfoody.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    LayoutInflater mInflater;
    ArrayList<Item> itemList;
    Context context;
    String itemCategory;

    public ItemAdapter(Context context, ArrayList<Item> itemList, String itemCategory){
        mInflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.context = context;
        this.itemCategory = itemCategory;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item, parent, false);
        return new ItemViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Item currentItem = itemList.get(position);
        holder.itemName.setText(currentItem.name);
        holder.itemPrice.setText("Rp" + currentItem.price);
        holder.itemImage.setImageResource(currentItem.image);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer position = Integer.parseInt(v.getTag().toString());

                if(itemCategory.equals("Snacks")){
                    position += 3;
                }else if(itemCategory.equals("Foods")){
                    position += 5;
                }else if(itemCategory.equals("TopUp")){
                    position += 7;
                }
                Intent toChooseQuantity = new Intent(context, ChooseQuantityActivity.class);
                toChooseQuantity.putExtra("position", position.toString());
                toChooseQuantity.putExtra("itemCategory", itemCategory);
                context.startActivity(toChooseQuantity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemPrice;
        RecyclerView.Adapter adapter;
        ImageView itemImage;

        public ItemViewHolder(@NonNull View itemView, ItemAdapter adapter) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_image);

            this.adapter = adapter;
        }
    }
}
