/*
* Name: LoginView.java
* Package: it.kaizenteam.app.view
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/view
* Date: 2015-05-23
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-02  Bucco Riccardo  Verify
* =================================================================
* v0.03 2015-05-30  Moretto Alessandro Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

/**
 * This interface has the task of allowing the use of methods to change the view from the outside of the package to login View (therefore from a LoginPresenterImpl).
 */
public interface LoginView extends View {
    /**
     * This method shows the view of the message of an error.
     */
    void showAuthenticationError(String err);

    /**
     * This method starts the activity in which it will present the list of the instance of the Norris chart.
     */
    void showListView();

    /**
     * This method displays the view of a waiting signal if the parameter is true and hides otherwise.
     * @param show display / hide the view of a waiting signal
     */
    void showProgress(boolean show);

    /**
     * This method is called automatically by Android when the button is clicked login. It will notify its presenter have pressed.
     * @param view
     */
    void onLoginClick(android.view.View view);
}
