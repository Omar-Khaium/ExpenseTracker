package org.emptybit.expensetracker.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.emptybit.expensetracker.R;

import java.util.ArrayList;

public class RemarkAdapter extends RecyclerView.Adapter<RemarkAdapter.ViewHolder> {

    Context context;
    ArrayList<String> arrayList;

    public RemarkAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RemarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_remarks, viewGroup, false);
        RemarkAdapter.ViewHolder holder = new RemarkAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RemarkAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.textView.setText(arrayList.get(i));
        viewHolder.textView.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(i);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Chip textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.remark_layout_item);
        }
    }
}