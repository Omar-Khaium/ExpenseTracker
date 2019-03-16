package org.emptybit.expensetracker.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.emptybit.expensetracker.Model.AccountModel;
import org.emptybit.expensetracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExpenseStatementAdapter extends RecyclerView.Adapter<ExpenseStatementAdapter.ViewHolder> {

    Context context;
    ArrayList<AccountModel> arrayList;

    public ExpenseStatementAdapter(Context context, ArrayList<AccountModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExpenseStatementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_expense_row_layout, viewGroup, false);
        ExpenseStatementAdapter.ViewHolder holder = new ExpenseStatementAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseStatementAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.mAmount.setText(String.valueOf(arrayList.get(i).getPrice()));
        viewHolder.mCategory.setText(String.valueOf(arrayList.get(i).getSubCategory().getCategory().getName()));
        viewHolder.mSubCategory.setText(String.valueOf(arrayList.get(i).getSubCategory().getName()));
        viewHolder.mDate.setText(arrayList.get(i).getDate());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mAmount, mDate, mCategory, mSubCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAmount = itemView.findViewById(R.id.expense_row_layout_amount);
            mCategory = itemView.findViewById(R.id.expense_row_layout_category);
            mSubCategory = itemView.findViewById(R.id.expense_row_layout_sub_category);
            mDate = itemView.findViewById(R.id.expense_row_layout_date);
        }
    }
}