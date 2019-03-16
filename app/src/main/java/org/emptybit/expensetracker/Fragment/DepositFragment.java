package org.emptybit.expensetracker.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.emptybit.expensetracker.Adapter.DepositStatementAdapter;
import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.Model.AccountModel;
import org.emptybit.expensetracker.R;

import java.util.ArrayList;

import static org.emptybit.expensetracker.Database.DatabaseQuery.getAllDepositQuery;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getDepositList;

public class DepositFragment extends Fragment {

    DatabaseHelper helper;
    RecyclerView  mListView;
    DepositStatementAdapter mAdapter;
    ArrayList<AccountModel> mArrayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deposit_statement, container, false);

        helper =  new DatabaseHelper(getContext());
        mListView = view.findViewById(R.id.fragment_deposit_list);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));

        mArrayList = getDepositList(helper.getData(getAllDepositQuery()));
        mAdapter = new DepositStatementAdapter(getContext(), mArrayList);
        mListView.setAdapter(mAdapter);
        return view;
    }
}
