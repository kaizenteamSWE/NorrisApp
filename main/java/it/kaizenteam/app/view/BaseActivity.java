/*
* Name: BaseActivity.java
* Package: it.kaizenteam.app.view
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/view
* Date: 2015-05-19
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-01  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-31  Dal Bianco Davide Edit
* =================================================================
* v0.02 2015-05-22  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-19  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;

import it.kaizenteam.app.presenter.Presenter;

/**
 * This class is the abstraction of an Activity. It contains the reference to the generic presenter that will be instantiated by the specializations of this class.
 */
public class BaseActivity extends ActionBarActivity implements View {
    protected Presenter presenter;
}
