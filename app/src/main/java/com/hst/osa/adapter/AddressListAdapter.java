package com.hst.osa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hst.osa.R;
import com.hst.osa.bean.support.Address;
import com.hst.osa.utils.OSAValidator;

import java.util.ArrayList;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder> {

    private ArrayList<Address> addressList;
    Context context;
    int indexPos;
    String addressId = "";
    private OnItemClickListener onItemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtCustomerName, txtCustomerAddress1, txtCustomerAddress2, txtDistrict,
                txtPinCode, txtMobNumber, btnEdit, btnDelete;
        public RadioButton selectAddress;

        public MyViewHolder(View itemView) {
            super(itemView);

            selectAddress = (RadioButton) itemView.findViewById(R.id.sel_address);
            txtCustomerName = (TextView) itemView.findViewById(R.id.customerName);
            txtCustomerAddress1 = (TextView) itemView.findViewById(R.id.cusAddress1);
            txtCustomerAddress2 = (TextView) itemView.findViewById(R.id.cusAddress2);
            txtDistrict = (TextView) itemView.findViewById(R.id.cityName);
            txtPinCode = (TextView) itemView.findViewById(R.id.pinCode);
            txtMobNumber = (TextView) itemView.findViewById(R.id.mobNum);
            btnEdit = (TextView) itemView.findViewById(R.id.btnEdit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    indexPos = getAdapterPosition();
                    addressId = addressList.get(indexPos).getId();

                }
            });
            btnDelete = (TextView) itemView.findViewById(R.id.btnDelete);
        }

//        public void bind(Address address, OnItemClickListener listener) {
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    listener.onItemClickAddress(address, getAdapterPosition());
//                }
//            });

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClickAddress(v, getAdapterPosition());
            }
//            else {
//                onItemClickListener.onItemClickAddress(Selecttick);
//            }
        }
    }

    public AddressListAdapter(ArrayList<Address> addressArrayList, OnItemClickListener onItemClickListener) {

        this.addressList = addressArrayList;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClickAddress(View view, int position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_address, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Address address = addressList.get(position);

        holder.txtCustomerName.setText(address.getFull_name());

        if (OSAValidator.checkNullString(address.getHouse_no())) {
            holder.txtCustomerAddress1.setText(address.getHouse_no());
        }
        if (OSAValidator.checkNullString(address.getStreet())) {
            holder.txtCustomerAddress2.setText(address.getStreet());
        }
        holder.txtDistrict.setText(address.getCity());
        holder.txtPinCode.setText(address.getPincode());
        holder.txtMobNumber.setText(address.getMobile_number());

//        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickAddress(v, position);
//            }
//        });

//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickAddress(v, position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }
}
