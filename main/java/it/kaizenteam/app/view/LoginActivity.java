/*
* Name: LoginActivity.java
* Package: it.kaizenteam.app.view
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/view
* Date: 2015-05-23
* Version: v0.02
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-02  Bigarella Chiara  Verify
* =================================================================
* v0.03 2015-05-30  Bucco Riccardo Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import it.kaizenteam.app.R;
import it.kaizenteam.app.presenter.LoginPresenter;
import it.kaizenteam.app.presenter.PresenterImpl;


/**
 * LoginActivity shows the authentication screen, where the user can enter the address of the instance of Norris, uername and password. If the user clicks the login button will be experienced its presenter who will handle the request.
 */
public class LoginActivity extends BaseActivity implements LoginView{

    /**
     * This method is performed by android at the creation of the Activity. It will be tasked to initializing its presenter.
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //I do that to permit the post reqest to sdk up the 9
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        presenter= PresenterImpl.create(PresenterImpl.ChartType.LOGIN_TYPE,this);
    }

    /**
     * This method shows the view of the message authentication failure.
     */
    @Override
    public void showAuthenticationError(final String err) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(R.id.errorloginlbl)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.errorloginlbl)).setText(err);
            }
        });
    }

    /**
     * This method starts the activity in which it will present the list of the instance of the Norris chart.
     */
    @Override
    public void showListView() {
        Intent i = new Intent(this,ListActivity.class);
        //TODO evitare che premendo back e quindi abbassandola poi ritorni la home
        startActivity(i);
    }

    /**
     * This method displays the view of a waiting signal if the parameter is true and hides otherwise.
     * @param show display / hide the view of a waiting signal
     */
    @Override
    public void showProgress(final boolean show) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(show) {
                    (findViewById(R.id.progressimage)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.loglayout)).setVisibility(View.INVISIBLE);
                }
                else{
                    (findViewById(R.id.progressimage)).setVisibility(View.INVISIBLE);
                    (findViewById(R.id.loglayout)).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * This method is called automatically by Android when the button is clicked login. It will notify its presenter have pressed.
     * @param view
     */
    @Override
    public void onLoginClick(View view) {
        ((LoginPresenter)presenter).onLoginClick(
                ((EditText)findViewById(R.id.addresstbx)).getText().toString(),
                ((EditText)findViewById(R.id.usernametbx)).getText().toString(),
                ((EditText)findViewById(R.id.passwordtbx)).getText().toString()
        );
    }
}
