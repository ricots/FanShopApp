package com.github.handioq.fanshop.account.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.handioq.R;
import com.github.handioq.fanshop.model.dto.OrderDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.details_button)
    ImageButton detailsButton;

    @BindView(R.id.order_id)
    TextView orderIdView;

    @BindView(R.id.order_status)
    TextView orderStatusView;

    private OrderDTO orderDTO;

    static OrderViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    private OrderViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(OrderDTO order) {
        orderDTO = order;

        orderIdView.setText(itemView.getContext().getString(R.string.order_id, order.getId()));
        orderStatusView.setText(order.getStatus());
    }
}