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
import org.emptybit.expensetracker.Adapter.ExpenseStatementAdapter;
import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.Model.AccountModel;
import org.emptybit.expensetracker.Model.CategoryModel;
import org.emptybit.expensetracker.Model.SubCategoryModel;
import org.emptybit.expensetracker.R;

import java.util.ArrayList;

import static org.emptybit.expensetracker.Database.DatabaseQuery.getAllExpenseQuery;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getExpenseList;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getSingleSubCategoryData;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getSingleSubCategoryQuery;

public class ExpenseFragment extends Fragment {

    DatabaseHelper helper;
    RecyclerView mListView;
    ExpenseStatementAdapter mAdapter;
    ArrayList<AccountModel> mArrayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_statement, container, false);

        helper =  new DatabaseHelper(getContext());
        mListView = view.findViewById(R.id.fragment_expense_list);
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));

        mArrayList = getExpenseList(helper.getData(getAllExpenseQuery()));

        for (AccountModel accountModel : mArrayList) {
            CategoryModel categoryModel = new CategoryModel();
            SubCategoryModel subCategoryModel = getSingleSubCategoryData(helper.getData(getSingleSubCategoryQuery(accountModel.getSubCategory().getId())));
            categoryModel.setId(subCategoryModel.getCategory().getId());
            String[] array  = (getResources().getStringArray(R.array.category_array));
            categoryModel.setName(array[categoryModel.getId()]);
            accountModel.getSubCategory().setCategory(categoryModel);
        }

        mAdapter = new ExpenseStatementAdapter(getContext(), mArrayList);
        mListView.setAdapter(mAdapter);
        return view;
    }
}
