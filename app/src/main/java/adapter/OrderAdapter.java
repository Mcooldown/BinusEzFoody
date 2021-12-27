package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.binusezyfoody.MainActivity;
import com.example.binusezyfoody.OrderItem;
import com.example.binusezyfoody.R;
import com.example.binusezyfoody.Repository;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    LayoutInflater mInflater;
    ArrayList<OrderItem> orderList;
    Context context;

    public OrderAdapter(Context context, ArrayList<OrderItem> orderList){
        mInflater = LayoutInflater.from(context);
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mOrderView = mInflater.inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(mOrderView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem currentOrder = orderList.get(position);
        holder.itemName.setText(currentOrder.item.name);
        holder.itemPrice.setText("Rp" + currentOrder.item.price);
        holder.itemQuantity.setText(currentOrder.quantity + "pcs");
        holder.itemImage.setImageResource(currentOrder.item.image);

        if(context.toString().contains("MyOrderActivity")){
            holder.btnDelete.setTag(position);
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Integer position = Integer.parseInt(v.getTag().toString());

                Repository repository = Repository.getInstance();
                repository.deleteOrderByIndex(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, orderList.size());
                }
            });
        }else{
            holder.btnDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemPrice, itemQuantity;
        RecyclerView.Adapter adapter;
        Button btnDelete;
        ImageView itemImage;

        public OrderViewHolder(@NonNull View itemView, OrderAdapter adapter) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name_order);
            itemPrice = itemView.findViewById(R.id.item_price_order);
            itemQuantity = itemView.findViewById(R.id.item_quantity_order);
            itemImage = itemView.findViewById(R.id.item_image_order);
            btnDelete = itemView.findViewById(R.id.btn_delete_order);

            this.adapter = adapter;
        }
    }
}
