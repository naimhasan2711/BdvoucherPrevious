package com.bdone.asus.bdvoucher.userAuth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bdone.asus.bdvoucher.NetworkCall.RetrofitClient;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.AuthInterface;
import com.bdone.asus.bdvoucher.NetworkCall.models.SignInPayload;
import com.bdone.asus.bdvoucher.NetworkCall.models.signInRequestBody;
import com.bdone.asus.bdvoucher.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {

    private Button googleSignInButton;
    private static final int RC_GET_TOKEN = 9002;
    private GoogleSignInOptions gso;
    private static final String TAG = "SignInActivity";
    private EditText userEmail;
    private EditText userPassword;
    private Button aDsigninButton;
    private TextView signUpTV, forgotPassTV;

    private Button signinCloseBtn;
    private static final String DEALID_IF_UNLOG = "dealidforunlog";
    private Button facebookBtn;

    private Retrofit retrofit;
    public static int checkinsignin;

    private static final int REQUEST_RESOLVE_ERROR = 1001;


    private static final int RC_SIGN_IN = 9001;
    private static int msChecksigninfromCheckoutProcess = 0;
    private static int checkedfromdialog = 0;
    private int mDistrictId;
    private ImageView shareit;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private SigninInformation signinInformation;
    private Menu menu;
    private ImageView visibleBtnNot;
    private ImageView visibleBtn;
    private SessionManager session;
    private AuthInterface authInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForSignInActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        supportInvalidateOptionsMenu();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForSignInActivity);

        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        ab.setTitle(" ");
        ab.setDisplayHomeAsUpEnabled(true);


        visibleBtn = (ImageView) findViewById(R.id.visibleBtn);
        visibleBtnNot = (ImageView) findViewById(R.id.visibleBtn2);
        userEmail = (EditText) findViewById(R.id.signinUserEmail);
        userPassword = (EditText) findViewById(R.id.signInUserPassword);
        aDsigninButton = (Button) findViewById(R.id.email_sign_in_button);
        signUpTV = (TextView) findViewById(R.id.signUpTV);
        signinInformation = new SigninInformation(getBaseContext());

        forgotPassTV = (TextView) findViewById(R.id.forgotPasswordTV);
        retrofit = RetrofitClient.getInstance(getBaseContext());
        authInterface = retrofit.create(AuthInterface.class);

        session = new SessionManager(getBaseContext());
        // mDistrictInformation = new DistrictInformation(getBaseContext());
     /*   HashMap<String, Integer> districtId = mDistrictInformation.getDistrictIdDeliveryCharge();
        mDistrictId = districtId.get(DistrictInformation.DISTRICT_ID);*/


        visibleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                visibleBtn.setVisibility(View.INVISIBLE);
                userPassword.setSelection(userPassword.length());
                visibleBtnNot.setVisibility(View.VISIBLE);
            }
        });
        visibleBtnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                visibleBtnNot.setVisibility(View.INVISIBLE);
                userPassword.setSelection(userPassword.length());
                visibleBtn.setVisibility(View.VISIBLE);
            }
        });


        //set toolbar appearance
        // toolbar.setBackground(R.color.material_blue_grey_800);

        //for crate home button


        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        forgotPassTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    String url = "http://m.ajkerdeal.com/MCustomer/ResetPassword.aspx";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(SignInActivity.this, Uri.parse(url));


             *//*   Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://m.ajkerdeal.com/MCustomer/ResetPassword.aspx"));
                startActivity(intent);*/
            }
        });


        aDsigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validate()) {
                    Toast.makeText(getBaseContext(), "Give correct Information", Toast.LENGTH_LONG).show();
                } else {
                    showProgressDialog();
                    userSignIn();
                    userPassword.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    userEmail.onEditorAction(EditorInfo.IME_ACTION_DONE);
                }

            }
        });

        //profilePhoto = (NetworkImageView) findViewById(R.id.profileImage);

        // tokenId=(ImageView)findViewById(R.id.tokenId);
        //Initializing google signin option



  /*      googleSignInButton.setSize(SignInButton.SIZE_WIDE);
        googleSignInButton.setScopes(gso.getScopeArray());*/

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.


        //Setting onclick listener to signing button


    }


    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String, Integer> checksigninfor = signinInformation.getSigninInformation();

        if (checksigninfor.get(SigninInformation.AJKERDEAL_SIGNIN) == 1) {
            menuInitialize();
        }
    }


    public void userSignIn() {

        final String email = userEmail.getText().toString();
        final String password = userPassword.getText().toString();


        authInterface.signIn(new signInRequestBody(email, password)).enqueue(new Callback<SignInPayload>() {
            @Override
            public void onResponse(Call<SignInPayload> call, Response<SignInPayload> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), String.valueOf(response.body().getName()), Toast.LENGTH_LONG).show();
                    session.createLoginSession(response.body().getName(), response.body().getPassword(), response.body().getEmail(), response.body().getMobile(), response.body().getId());
                    signinInformation.storeSignin(1, -1, -1);
                    menuInitialize();
                    onBackPressed();
                    hideProgressDialog();
                    Log.e(TAG, "onResponse: session" + String.valueOf(session));
                }
            }

            @Override
            public void onFailure(Call<SignInPayload> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something wrong try in right way", Toast.LENGTH_LONG).show();
                hideProgressDialog();
            }
        });


/*
        Retrofit retrofit = RetrofitSingleton.getInstance(getBaseContext());
        final SignInSignUpApiInterface signInSignUpApiInterface = retrofit.create(SignInSignUpApiInterface.class);
        signInSignUpApiInterface.getSignin(new SigninModel(email, password)).enqueue(new Callback<SigninModel>() {
            @Override
            public void onResponse(Call<SigninModel> call, Response<SigninModel> response) {


                if (response.isSuccessful() && response.body() != null) {
//                    sharedPreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    final int userLoginId = response.body().getId();

                    Toast.makeText(SignInActivity.this, R.string.success_in_signin, Toast.LENGTH_SHORT).show();
                    //for storing user information
                    String username = "";
                    String useremail = "";
                    String usermobile = "";
                    String usergender = "";
                    String useraddress = "";
                    String userdistricbangla = "";
                    String userdistrictenglish = "";
                    String userknowaboutus = "";
                    String userMobile = "";
                    int userdistrictid = -1;
                    int userareaid = -1;
                    if (response.body().getUserName() != null) {
                        username = response.body().getUserName();
                    }
                    if (response.body().getEmail() != null) {
                        useremail = response.body().getEmail();
                    }
                    if (response.body().getMobile() != null) {
                        usermobile = response.body().getMobile();
                    }
                    if (response.body().getAddress() != null) {
                        useraddress = response.body().getAddress();
                    }
                    if (response.body().getGender() != null) {
                        usergender = response.body().getGender();
                    }
                    if (response.body().getDistrict() != null) {
                        userdistricbangla = response.body().getDistrict();
                    }
                    if (response.body().getLocation() != null) {
                        userdistrictenglish = response.body().getLocationBng();
                    }
                    if (response.body().getKnowingSource() != null) {
                        userknowaboutus = response.body().getKnowingSource();
                    }
                    if (response.body().getDistrictId() > -1) {
                        userdistrictid = response.body().getDistrictId();
                    }
                    if (response.body().getAreaId() > -1) {
                        userareaid = response.body().getAreaId();
                    }

                    storeUserInformation.storeUserInformation(useremail, username, usermobile, usergender, useraddress, userdistricbangla, userdistrictenglish, userknowaboutus, userdistrictid, userareaid);
                    //end stroing user information
                    if (response.body().getMobile() != null) {
                        userMobile = response.body().getMobile();

                    }
                    session.createLoginSession(password, email, userMobile, userLoginId);
                    signinInformation.storeSignin(1, -1, -1);
                    checkIfDistrictSelected();

                    menuInitialize();

                    msChecksigninfromCheckoutProcess = 0;

                } else {
                    Toast.makeText(getApplicationContext(), R.string.wrong_email_password, Toast.LENGTH_SHORT).show();
                    hideProgressDialog();
                }

            }

            @Override
            public void onFailure(Call<SigninModel> call, Throwable t) {

                hideProgressDialog();
                Toast.makeText(getApplicationContext(), R.string.sunable_to_connect, Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    public void menuInitialize() {
       /* new HomeActivity().checksgininActivity(getApplicationContext());
        new HomeActivity().intialize();*/
        Log.e(TAG, "menuInitialize: aaakkkkkaaa");
        setResult(RESULT_OK);
        hideProgressDialog();

    }
/*    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.clear(); // Clear the menu first

            *//* Add the menu items *//*

        return super.onPrepareOptionsMenu(menu);
    }*/


    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading..");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
            // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean validate() {
        boolean valid = true;
        String mUserEmail = userEmail.getText().toString();
        String mUserPassword = userPassword.getText().toString();

        if (TextUtils.isEmpty(mUserEmail)) {
            userEmail.setError("enter a valid email address");
            valid = false;
        } else {
            userEmail.setError(null);
        }

        if (TextUtils.isEmpty(mUserPassword)) {
            userPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            userPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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


    }

}
