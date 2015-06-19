/*
* Name: LoginPresenter.java
* Package: it.kaizenteam.app.presenter
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/presenter
* Date: 2015-05-23
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
* v0.03 2015-05-29  Pavanello Fabio Matteo Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;

/**
 * LoginPresenter is the interface LoginPresenterImpl.
 */
public interface LoginPresenter extends Presenter{
    /**
     * This method handles the gesture of a user click on the button in the login view. It will attempt to login by placing the waiting signal on view and if it has success shows the view with the list of chart otherwise appears on view an error message.
     * @param addressNorris
     * @param login
     * @param password
     */
    void onLoginClick(String addressNorris, String login, String password);
}
