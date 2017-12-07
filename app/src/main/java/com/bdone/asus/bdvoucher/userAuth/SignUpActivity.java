package com.bdone.asus.bdvoucher.userAuth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bdone.asus.bdvoucher.NetworkCall.RetrofitClient;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.AuthInterface;
import com.bdone.asus.bdvoucher.NetworkCall.models.SignUpPayLoad;
import com.bdone.asus.bdvoucher.NetworkCall.models.SignUpPayLoadModel;
import com.bdone.asus.bdvoucher.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    private static final int RC_GET_TOKEN = 9002;
    private static final int RC_SIGN_IN = 9001;
    public static int checkinsignin;
    private EditText mUserName;
    private EditText mUserMobileNumber;
    private EditText mUserRegPassword;
    private EditText mUserRegReTypePassword;
    private Button mUserSignUpButton;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private EditText mUserEmail;
    private int frgmentPostion;
    private View mProgressView;
    private TextView signInTV;
    private View mLoginFormView;
    private String str;
    private String text = "";
    private Button signUpCloseBtn;
    private Menu menu;
    private ImageView visibleBtnNot;
    private ImageView visibleBtn;
    private GoogleSignInOptions gso;
    private Button facebookBtn;
    private Retrofit retrofit;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SessionManager session;
    private SigninInformation signinInformation;
    private String mUrl;
    private String mTitle;
    private String mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForSignUpActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        supportInvalidateOptionsMenu();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(" ");
        }

        mUserName = (EditText) findViewById(R.id.userName);
        mUserMobileNumber = (EditText) findViewById(R.id.userMobileNumber);
        mUserRegPassword = (EditText) findViewById(R.id.userRegPassword);
        // mUserRegReTypePassword=(EditText)view.findViewById(R.id.userRegReTypePassword);
        mUserEmail = (EditText) findViewById(R.id.userEmail);
        mUserSignUpButton = (Button) findViewById(R.id.userSignUpButton);
        mLoginFormView = findViewById(R.id.login_form);
        visibleBtn = (ImageView) findViewById(R.id.visibleBtn);
        visibleBtnNot = (ImageView) findViewById(R.id.visibleBtn2);
        // RadioGroup radioGroup=(RadioGroup) getActivity().findViewById(R.id.radioGroup);
        signInTV = (TextView) findViewById(R.id.signInTV);
        //  storeUserInformation = new StoreUserInformation(getBaseContext());
        signinInformation = new SigninInformation(getBaseContext());
        retrofit = RetrofitClient.getInstance(getBaseContext());
        session = new SessionManager(getBaseContext());
        signInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerHome, SigninFragment.newInstance(signinModel, 0));
                ft.addToBackStack(null);
                ft.commit();*/
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);

            }
        });
        visibleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserRegPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                visibleBtn.setVisibility(View.INVISIBLE);
                mUserRegPassword.setSelection(mUserRegPassword.length());
                visibleBtnNot.setVisibility(View.VISIBLE);
            }
        });
        visibleBtnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserRegPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                visibleBtnNot.setVisibility(View.INVISIBLE);
                mUserRegPassword.setSelection(mUserRegPassword.length());
                visibleBtn.setVisibility(View.VISIBLE);
            }
        });

        signInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });


        mUserSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
                mUserEmail.onEditorAction(EditorInfo.IME_ACTION_DONE);
                mUserMobileNumber.onEditorAction(EditorInfo.IME_ACTION_DONE);
                mUserName.onEditorAction(EditorInfo.IME_ACTION_DONE);
                mUserRegPassword.onEditorAction(EditorInfo.IME_ACTION_DONE);
                //mUserRegReTypePassword.onEditorAction(EditorInfo.IME_ACTION_DONE);

                //Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

//      String radioSexButton=radioButton.getText().toString();


        mUserSignUpButton.setEnabled(true);




        /* googleSignInButton.setSize(SignInButton.SIZE_WIDE);
        googleSignInButton.setScopes(gso.getScopeArray());*/

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void menuInitialize() {
       /* new HomeActivity().checksgininActivity(getApplicationContext());
        new HomeActivity().intialize();*/

        setResult(RESULT_OK);
        hideProgressDialog();

    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
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


    public void signup() {
        if (!validate()) {

            onSignupFailed();
            mUserSignUpButton.setEnabled(true);

        } else {
            showProgressDialog();

            Log.e("Tersting", "signup: " + text);

            mUserSignUpButton.setEnabled(false);
            // Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
            mUserSignUpButton.setError(null);
            String userName = mUserName.getText().toString();
            String userMolbile = mUserMobileNumber.getText().toString();
            String userPassword = mUserRegPassword.getText().toString();
            String userEmail = mUserEmail.getText().toString();
            //String userReTypePassword=mUserRegReTypePassword.getText().toString();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            String formattedDate = formatter.format(date);

            AuthInterface authInterface = retrofit.create(AuthInterface.class);
            authInterface.signUp(new SignUpPayLoadModel(userName, userEmail, userMolbile, userPassword, formattedDate))
                    .enqueue(new Callback<SignUpPayLoad>() {

                        /*he nakamu nakamuka nakamuakaa */

                        @Override
                        public void onResponse(Call<SignUpPayLoad> call, Response<SignUpPayLoad> response) {

                            //Toast.makeText(getApplicationContext(),response.body().getSignUpEmail(),Toast.LENGTH_SHORT).show();
                            if (response.isSuccessful()) {

                                hideProgressDialog();

                                session.createLoginSession(response.body().getName(),response.body().getPassword(), response.body().getEmail(), response.body().getMobile(), Integer.parseInt(response.body().getId()));
                                signinInformation.storeSignin(1, -1, -1);
                                menuInitialize();
                                Toast.makeText(getBaseContext(), "Sign Up Successful", Toast.LENGTH_LONG).show();
                                onBackPressed();


                            } else {
                                hideProgressDialog();
                                mUserSignUpButton.setEnabled(true);
                                Toast.makeText(getBaseContext(), "User Already Exists", Toast.LENGTH_SHORT).show();
                            }



                        }

                        @Override
                        public void onFailure(Call<SignUpPayLoad> call, Throwable t) {
                            Log.e(TAG, "onFailure: "+String.valueOf(t) );
                            mUserSignUpButton.setEnabled(true);
                            hideProgressDialog();
                            Toast.makeText(getBaseContext(), "Unable to connect", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    public boolean validate() {
        boolean valid = true;
        String userName = mUserName.getText().toString();
        String userMolbile = mUserMobileNumber.getText().toString();
        String userPassword = mUserRegPassword.getText().toString();
        String userEmail = mUserEmail.getText().toString();
        //String userReTypePassword=mUserRegReTypePassword.getText().toString();

        if (TextUtils.isEmpty(userName) || userName.length() < 3) {
            mUserName.setError("at least five charachter");
            valid = false;
        } else {
            mUserName.setError(null);
        }
        if (TextUtils.isEmpty(userEmail) || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            mUserEmail.setError("enter a valid email");
            valid = false;
        } else {
            mUserEmail.setError(null);
        }

        if (TextUtils.isEmpty(userMolbile) || !Patterns.PHONE.matcher(userMolbile).matches() || userMolbile.length() < 11 || userMolbile.length() > 12) {
            mUserMobileNumber.setError("enter a valid mobile");
            valid = false;
        } else {
            mUserMobileNumber.setError(null);
        }

        if (TextUtils.isEmpty(userPassword) || userPassword.length() < 3 || userPassword.length() > 10) {
            mUserRegPassword.setError("between four five alphanumeric character");
            valid = false;
        } else {
            mUserRegPassword.setError(null);
        }
/*        if(userPassword.isEmpty()||!userReTypePassword.equals(userPassword))
        {
            mUserRegReTypePassword.setError("password not match");
            valid=false;
        }*/
  /*      else {
            mUserRegReTypePassword.setError(null);
        }*/

        return valid;
    }


    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Please try later, we found some problem", Toast.LENGTH_LONG).show();

        mUserSignUpButton.setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
