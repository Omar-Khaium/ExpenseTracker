package org.emptybit.expensetracker.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.emptybit.expensetracker.Model.AccountModel;
import org.emptybit.expensetracker.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DepositStatementAdapter extends RecyclerView.Adapter<DepositStatementAdapter.ViewHolder> {

    Context context;
    ArrayList<AccountModel> arrayList;

    public DepositStatementAdapter(Context context, ArrayList<AccountModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public DepositStatementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_deposit_row_layout, viewGroup, false);
        DepositStatementAdapter.ViewHolder holder = new DepositStatementAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DepositStatementAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.mAmount.setText(String.valueOf(arrayList.get(i).getPrice()));
        viewHolder.mDate.setText(arrayList.get(i).getDate());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAmount, mDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAmount = itemView.findViewById(R.id.deposit_row_layout_amount);
            mDate = itemView.findViewById(R.id.deposit_row_layout_date);
        }
    }
}