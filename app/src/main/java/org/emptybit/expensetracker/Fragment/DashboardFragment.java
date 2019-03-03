package org.emptybit.expensetracker.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.Model.UserModel;
import org.emptybit.expensetracker.R;

import java.io.File;

import static org.emptybit.expensetracker.Activity.LoginActivity.USER_ID;
import static org.emptybit.expensetracker.Database.DatabaseQuery.TABLE_USERS;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getSingleUserData;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getSingleUserQuery;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getUserList;

public class DashboardFragment extends Fragment {

    ImageView mProfileImage;
    TextView mProfileName, mProfileCurrentMonthTotalDeposit, mProfileCurrentMonthTotalWithdrawn, mProfileCurrentMonthDailyBudgetLeft;

    DatabaseHelper databaseHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mProfileImage = view.findViewById(R.id.fragment_dashboard_profile_picture);
        mProfileName = view.findViewById(R.id.fragment_dashboard_profile_name);
        mProfileCurrentMonthTotalDeposit = view.findViewById(R.id.fragment_dashboard_profile_monthly_total_deposit);
        mProfileCurrentMonthTotalWithdrawn = view.findViewById(R.id.fragment_dashboard_profile_monthly_total_withdrawn);
        mProfileCurrentMonthDailyBudgetLeft = view.findViewById(R.id.fragment_dashboard_profile_daily_budget_left);

        databaseHelper = new DatabaseHelper(getContext());

        UserModel userModel = getSingleUserData(databaseHelper.getData(getSingleUserQuery(USER_ID)));

        mProfileName.setText(userModel.getName());
        return view;
    }

}
