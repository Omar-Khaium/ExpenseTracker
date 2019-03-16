package org.emptybit.expensetracker.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.emptybit.expensetracker.Adapter.AccountStatementViewPagerAdapter;
import org.emptybit.expensetracker.R;

public class HistoryFragment extends Fragment {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    AccountStatementViewPagerAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        mTabLayout = view.findViewById(R.id.account_statement_tab_layout);
        mViewPager = view.findViewById(R.id.account_statement_view_pager);

        mTabLayout.addTab(mTabLayout.newTab().setText("Deposit"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Expense"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mAdapter = new AccountStatementViewPagerAdapter(getFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                mTabLayout.setScrollPosition(position, v, true);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }
}
