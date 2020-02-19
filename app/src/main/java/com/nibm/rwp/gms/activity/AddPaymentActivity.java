package com.nibm.rwp.gms.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.nibm.rwp.gms.R;
import com.nibm.rwp.gms.common.BaseActivity;
import com.nibm.rwp.gms.dto.PaymentGatway;
import com.nibm.rwp.gms.interfaces.EndPoints;
import com.nibm.rwp.gms.utill.AppUtill;
import com.nibm.rwp.gms.utill.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPaymentActivity extends BaseActivity implements View.OnClickListener {

    //constant
    private static final String TAG = AddPaymentActivity.class.getSimpleName();

    //Ui components
    private Button mBtnPayment;
    private EditText mEtCardNo,mEtMonth,mEtYear,mEtSecurityCode,mEtFname,mEtLastname;
    public ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        setToolbar(getResources().getString(R.string.activity_addpayment), AddPaymentActivity.this);
        initView();
    }

    private void initView() {
        mEtCardNo = findViewById(R.id.activity_add_payment_et_crdNo);
        mEtMonth = findViewById(R.id.activity_add_payment_et_mm);
        mEtYear = findViewById(R.id.activity_add_payment_et_yy);
        mEtSecurityCode = findViewById(R.id.activity_add_payment_et_security_code);
        mEtFname = findViewById(R.id.activity_add_payment_et_fname);
        mEtLastname = findViewById(R.id.activity_add_payment_et_lname);
        mBtnPayment = findViewById(R.id.activity_add_payment_btn_doneeeeeeeeeee);
        mBtnPayment.setOnClickListener(this);
    }

    private void sendPaymentDetails(){
        try {
            Toast.makeText(AddPaymentActivity.this,"aaaa",Toast.LENGTH_SHORT).show();

            EndPoints service = RetrofitClient.getRetrofitInstance().create(EndPoints.class);
            Call<JsonElement> call = service.setPaymentGatway(setUserPayment());
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                    Toast.makeText(AddPaymentActivity.this,"onResponse....." ,Toast.LENGTH_LONG).show();
                    popUp();

                if (response.code()==200){

                        Toast.makeText(AddPaymentActivity.this,"Successfully....." ,Toast.LENGTH_LONG).show();
                }

                    if (response != null){
                        Log.w("RESP", "----------- "+response.body());

                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Log.i(TAG, t.getMessage());
                   // hideProgressDialogWithTitle();

                    String error = t.getMessage();
                    Toast.makeText(AddPaymentActivity.this,"Error " +error,Toast.LENGTH_LONG).show();
                    //requestActivityErrorDialog();
                }
            });
        } catch (Exception e) {
            Log.i(TAG, "Exception");
            Toast.makeText(AddPaymentActivity.this,""+e.toString(),Toast.LENGTH_LONG).show();
            mEtCardNo.setText(e.getMessage());
        }


    }

    private PaymentGatway setUserPayment() {
        PaymentGatway paymentGatway = new PaymentGatway();

        paymentGatway.setAcc_no(mEtCardNo.getText().toString());
        paymentGatway.setExp_year(mEtYear.getText().toString());
        paymentGatway.setExp_date(mEtMonth.getText().toString());
        paymentGatway.setCvv(mEtSecurityCode.getText().toString());
        paymentGatway.setF_name(mEtFname.getText().toString());
        paymentGatway.setL_name(mEtLastname.getText().toString());

        return  paymentGatway;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void popUp() {

        final AlertDialog dialog = new AlertDialog.Builder(AddPaymentActivity.this).create();
        AppUtill.showCustomStandardAlert(dialog,
                AddPaymentActivity.this,
                getResources().getString(R.string.payment_sucess),
                getResources().getString(R.string.payment_sucess2),
                getResources().getDrawable(R.drawable.icons8888),
                null,
                getResources().getString(R.string.ok_text), false);
    }

    public void SendDetails(){
        JSONObject payment = new JSONObject();
        try {
            payment.put("acc_no",mEtCardNo.getText() );
            payment.put("cvv", mEtSecurityCode.getText());
            payment.put("exp_year", mEtYear.getText());
            payment.put("exp_date", mEtMonth.getText());
            payment.put("f_name", "sdsds");
            payment.put("l_name", "dfd");
            payment.put("req_id", "1");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String urlString =  "http://garb.tk/payment/approve"; // URL to call
        //data to post
        OutputStream out = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(String.valueOf(payment));
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
        } catch (Exception e) {
            System.out.println("errpr");
            System.out.println(e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_add_payment_btn_doneeeeeeeeeee:
               // SendDetails();
                sendPaymentDetails();
               // popUp();
        }
    }
}
