package org.qtum.mromanovsky.qtum.ui.fragment.TransactionFragment;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.qtum.mromanovsky.qtum.R;
import org.qtum.mromanovsky.qtum.ui.fragment.BaseFragment.BaseFragment;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;


public class TransactionFragment extends BaseFragment implements TransactionFragmentView {

    @BindView(R.id.tv_value)
    TextView mTextViewValue;
    @BindView(R.id.tv_received_time)
    TextView mTextViewReceivedTime;
    @BindView(R.id.tv_from)
    TextView mTextViewFrom;
    @BindView(R.id.tv_to)
    TextView mTextViewTo;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    @OnClick({R.id.ibt_back})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.ibt_back:
                getActivity().onBackPressed();
                break;
        }
    }

    final static String POSITION = "position";

    TransactionFragmentPresenterImpl mTransactionFragmentPresenter;

    public static TransactionFragment newInstance(int position) {
        TransactionFragment transactionFragment = new TransactionFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        transactionFragment.setArguments(args);
        return transactionFragment;
    }

    @Override
    protected void createPresenter() {
        mTransactionFragmentPresenter = new TransactionFragmentPresenterImpl(this);
    }

    @Override
    protected TransactionFragmentPresenterImpl getPresenter() {
        return mTransactionFragmentPresenter;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_transaction;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().openTransactionView(getArguments().getInt(POSITION));
    }


    @Override
    public void setUpTransactionData(double value, String receivedTime, String from, String to) {

        mTextViewValue.setText(String.valueOf(value));
        mTextViewReceivedTime.setText(receivedTime);
        mTextViewFrom.setText(from);
        mTextViewTo.setText(to);
        if (value > 0) {
            mAppBarLayout.setBackgroundResource(R.drawable.background_tb_sent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getFragmentActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.green));
            }
        } else {
            mAppBarLayout.setBackgroundResource(R.drawable.background_tb_received);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getFragmentActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.pink_lite));
            }
        }
    }
}
