package com.bdone.asus.bdvoucher.dialogFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bdone.asus.bdvoucher.NetworkCall.RetrofitClient;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.OderPlaceInterface;
import com.bdone.asus.bdvoucher.NetworkCall.models.OderPlaceBodyModel;
import com.bdone.asus.bdvoucher.NetworkCall.models.OderPlacePayLoadModel;
import com.bdone.asus.bdvoucher.R;
import com.bdone.asus.bdvoucher.userAuth.SessionManager;
import com.bdone.asus.bdvoucher.userAuth.SignInActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ASUS on 3/25/2017.
 */

public class GetCodeDilaog extends DialogFragment {
    Retrofit retrofit;
    OderPlaceInterface oderPlaceInterface;
    int companyId, productID;
    private SessionManager session;
    private String offerCode;
    private ProgressDialog mProgressDialog;

    public GetCodeDilaog() {
    }

    public static GetCodeDilaog newInstance(String offerCode, int companyID, int productID) {
        GetCodeDilaog fragment = new GetCodeDilaog();
        fragment.offerCode = offerCode;
        fragment.companyId = companyID;
        fragment.productID = productID;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_get_code, null);
        session = new SessionManager(getActivity());

        retrofit = RetrofitClient.getInstance(getActivity());
        oderPlaceInterface = retrofit.create(OderPlaceInterface.class);
        Button done = (Button) view.findViewById(R.id.buttonApply);
        final EditText editTextMobileNumber = (EditText) view.findViewById(R.id.editTextSpending);

        if (session.isLoggedIn()) {
            //  setUserImage();
            editTextMobileNumber.setText(session.getUserDetails().get("phoneKey"));
            // mEmail.setText(session.getUserDetails().get("email"));
        }
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextMobileNumber.getText().toString().equals("")) {
                    Toast.makeText(view.getContext(), "You don't give any number", Toast.LENGTH_SHORT).show();
                    dismiss();
                }


                    if (session.isLoggedIn()) {
                        showProgressDialog();
                        //  setUserImage();

                        // mEmail.setText(session.getUserDetails().get("email"));

                        //   int a= session.getUserID().get("userIdKey");
                        oderPlaceInterface.oderPlace(new OderPlaceBodyModel(session.getUserID().get("userIdKey"),
                                offerCode,
                                companyId,
                                "Android",
                                0,
                                0,
                                0,
                                productID,
                                "+88"+editTextMobileNumber.getText().toString()
                        )).enqueue(new Callback<OderPlacePayLoadModel>() {
                            @Override
                            public void onResponse(Call<OderPlacePayLoadModel> call, Response<OderPlacePayLoadModel> response) {
                                if (response.isSuccessful()) {
                                    hideProgressDialog();
                                    dismiss();
                                    Toast.makeText(getActivity(), "you will get your code on sms", Toast.LENGTH_LONG).show();
                                    Log.e("oderCheck", "onResponse: "+String.valueOf(response.body()) );
                                } else {
                                }
                            }

                            @Override
                            public void onFailure(Call<OderPlacePayLoadModel> call, Throwable t) {
                                Log.e("oderCheck", "onResponse: "+String.valueOf(t) );

                                Toast.makeText(getActivity(), "Something wrong try latter", Toast.LENGTH_LONG).show();
                                dismiss();
                                hideProgressDialog();
                            }
                        });


                    } else {
                        Toast.makeText(view.getContext(), "Please sign in first", Toast.LENGTH_LONG).show();
                        dismiss();
                        startActivity(new Intent(getActivity(), SignInActivity.class));
                    }


                    // MainActivity.copyAnotherIteamWithAmount(suppliers, subjects, course, suppliersId, view, editTextSpending.getText().toString());
                }


        });

        alert.setView(view, 0, 0, 0, 0);


        return alert.create();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("Loading Please Wait");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

}
