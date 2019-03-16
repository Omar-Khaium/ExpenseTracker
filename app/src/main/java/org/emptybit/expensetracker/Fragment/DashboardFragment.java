package org.emptybit.expensetracker.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.emptybit.expensetracker.Database.DatabaseHelper;
import org.emptybit.expensetracker.Model.UserModel;
import org.emptybit.expensetracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

import static org.emptybit.expensetracker.Activity.LoginActivity.USER_ID;
import static org.emptybit.expensetracker.Database.DatabaseQuery.TotalCostOfTodayQuery;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getSingleUserData;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getSingleUserQuery;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getTotalCostOfToday;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getTotalDepositOfThisMonth;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getTotalDepositOfThisMonthQuery;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getTotalWithdrawnOfThisMonth;
import static org.emptybit.expensetracker.Database.DatabaseQuery.getTotalWithdrawnOfThisMonthOfQuery;

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
        mProfileCurrentMonthTotalWithdrawn = view.findViewById(R.id.fragment_dashboard_profile_monthly_total_expensen);
        mProfileCurrentMonthDailyBudgetLeft = view.findViewById(R.id.fragment_dashboard_profile_daily_budget_left);

        databaseHelper = new DatabaseHelper(getContext());

        UserModel userModel = getSingleUserData(databaseHelper.getData(getSingleUserQuery(USER_ID)));
        mProfileName.setText(userModel.getName());

        int totalDepositOfThisMonth = getTotalDepositOfThisMonth(databaseHelper.getData(getTotalDepositOfThisMonthQuery()));
        mProfileCurrentMonthTotalDeposit.setText(String.valueOf(totalDepositOfThisMonth) + " BDT");

        int totalWithdrawnOfThisMonth = getTotalWithdrawnOfThisMonth(databaseHelper.getData(getTotalWithdrawnOfThisMonthOfQuery()));
        mProfileCurrentMonthTotalWithdrawn.setText(String.valueOf(totalWithdrawnOfThisMonth) + " BDT");

        int totalCostOfToday = getTotalCostOfToday(databaseHelper.getData(TotalCostOfTodayQuery()));

        double dailyBudgetLeft;
        if (totalWithdrawnOfThisMonth > totalDepositOfThisMonth) {
            dailyBudgetLeft = totalDepositOfThisMonth - totalWithdrawnOfThisMonth - totalCostOfToday;
        } else {
            Calendar cal = Calendar.getInstance();
            int res = cal.getActualMaximum(Calendar.DATE);
            dailyBudgetLeft = ((double) totalDepositOfThisMonth / res) - totalCostOfToday;
        }
        mProfileCurrentMonthDailyBudgetLeft.setText(String.valueOf(dailyBudgetLeft) + " BDT");

        return view;
    }

}
