package org.emptybit.expensetracker.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.emptybit.expensetracker.Activity.NewCategoryActivity;
import org.emptybit.expensetracker.R;

public class CreateTransactionFragment extends Fragment {

    MaterialCardView xNewCategory, xDeposit, xExpense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_transaction, container, false);
        xNewCategory = view.findViewById(R.id.create_transaction_in_category);
        xDeposit = view.findViewById(R.id.create_transaction_in_deposit);
        xExpense = view.findViewById(R.id.create_transaction_in_expense);

        xNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NewCategoryActivity.class));
            }
        });

        return view;
    }
}
