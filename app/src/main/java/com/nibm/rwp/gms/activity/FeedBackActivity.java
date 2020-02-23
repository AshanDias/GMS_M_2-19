package com.nibm.rwp.gms.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.nibm.rwp.gms.R;
import com.nibm.rwp.gms.common.BaseActivity;
import com.nibm.rwp.gms.dto.GarbageRequest;
import com.nibm.rwp.gms.dto.UserFeedback;
import com.nibm.rwp.gms.interfaces.EndPoints;
import com.nibm.rwp.gms.utill.RetrofitClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    //constant
    private static final String TAG = FeedBackActivity.class.getSimpleName();

    //Ui components
    private RatingBar mRatingBar;
    private TextView mTvRateCount, mTvRateMessage;
    private ProgressDialog progressDialog;
    private EditText mEtComment;
    private Button mBtnGiveFeedback;

    private double mRatedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        setToolbar(getResources().getString(R.string.acitivty_feedback), FeedBackActivity.this);

        initView();
        giveUserFeedback();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initView() {
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mTvRateCount = (TextView) findViewById(R.id.tvRateCount);
        mTvRateMessage = (TextView) findViewById(R.id.tvRateMessage);
        mEtComment = findViewById(R.id.activity_feedback_et_comment);
        mBtnGiveFeedback = findViewById(R.id.activity_feedback_btn_giveFeedback);
        mBtnGiveFeedback.setOnClickListener(this);
    }

    private void giveUserFeedback() {
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                mRatedValue = ratingBar.getRating();
                mTvRateCount.setText("Your Rating : "
                        + mRatedValue + " /5");
                if(mRatedValue <1){
                    mTvRateMessage.setText("Very Bad");
                }else if(mRatedValue <2){
                    mTvRateMessage.setText("Not Bad");
                }else if(mRatedValue <3){
                    mTvRateMessage.setText("Good");
                }else if(mRatedValue <4){
                    mTvRateMessage.setText("Nice");
                }else if(mRatedValue <5){
                    mTvRateMessage.setText("Very Good");
                }else if(mRatedValue ==5){
                    mTvRateMessage.setText("Awesome!!!");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_feedback_btn_giveFeedback:
                sendCustomerFeedback();

        }
    }

    private void showProgressDialogWithTitle(String substring) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(substring);
        progressDialog.show();
    }

    public void hideProgressDialogWithTitle() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.dismiss();
    }

    private  void  sendCustomerFeedback() {

        try {
            showProgressDialogWithTitle("Uploading....");
            EndPoints service = RetrofitClient.getRetrofitInstance().create(EndPoints.class);
            Call<JsonElement> call = service.setUserFeedback(getCustomerFeedback());
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    hideProgressDialogWithTitle();

                    if (response.code() == 200) {

                        Toast.makeText(FeedBackActivity.this,"response 200",Toast.LENGTH_LONG).show();

                        if (response.isSuccessful()) {

                        }
                    }
                }


                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.i(TAG, t.getMessage());
                    hideProgressDialogWithTitle();

                    String error = t.getMessage();
                    Toast.makeText(FeedBackActivity.this, "Error " + error, Toast.LENGTH_LONG).show();
                    //  requestActivityErrorDialog();
                }
            });
        } catch (Exception e) {
            Log.i(TAG, "Exception");
        }
    }

    private UserFeedback getCustomerFeedback() {
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUser_id("1");
        userFeedback.setStatus(mTvRateMessage.getText().toString());
        userFeedback.setComment(mEtComment.getText().toString());

        return userFeedback;
    }
}
