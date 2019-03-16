package org.emptybit.expensetracker.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.emptybit.expensetracker.Fragment.DepositFragment;
import org.emptybit.expensetracker.Fragment.ExpenseFragment;

public class AccountStatementViewPagerAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public AccountStatementViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new DepositFragment();
            case 1:
                return new ExpenseFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
