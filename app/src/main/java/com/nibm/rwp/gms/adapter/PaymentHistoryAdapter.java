package com.nibm.rwp.gms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nibm.rwp.gms.R;
import com.nibm.rwp.gms.dto.PaymentHistory;

import java.util.List;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.PaymentHistoryViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<PaymentHistory> productList;

    //getting the context and product list with constructor
    public PaymentHistoryAdapter(Context mCtx, List<PaymentHistory> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public PaymentHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_payment_history, null);
        return new PaymentHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentHistoryViewHolder holder, int position) {

        PaymentHistory paymentHistory = productList.get(position);
        holder.mName.setText(paymentHistory.getFirst_name());
        holder.mAddress1.setText(paymentHistory.getTotal_payment());
        holder.mAddress2.setText(paymentHistory.getDate());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class PaymentHistoryViewHolder extends RecyclerView.ViewHolder {

        TextView mName, mAddress1, mAddress2, mContact,mDate;

        public PaymentHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.layout_payment_History_tv_name);
            mAddress1 = itemView.findViewById(R.id.layout_payment_History_tv_address1);
            mAddress2 = itemView.findViewById(R.id.layout_item_payment_History_tv_address2);
//            mContact = itemView.findViewById(R.id.layout_item_payment_History_tv_contact);
//            mDate = itemView.findViewById(R.id.layout_item_payment_History_tv_date);
        }
    }
}
