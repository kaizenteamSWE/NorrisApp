/*
* Name: ListPresenter.java
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
* v0.04 2015-06-02  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-30  Rubin Marco Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;

/**
 * ListPresenter is the interface of ListPresenterImpl.
 */
public interface ListPresenter extends Presenter{
    /**
     * This method is invoked by pressing a list item. It will ask the display of chartActivity specification of the chart represented in the item.
     * @param type
     * @param id
     */
    void onItemClicked(String type,String id);

    /**
     * This method is invoked when you press the button to logout in view. It will carry out of the session (through HttpRequesterWithCookie).
     */
    void onLogoutClick();

    /**
     * This method is invoked in response to an event in the view, or the display of Activity with the list of chart. Its task is to get the list (through SWHttpRequesterWithCookie) and change the view with those values.
     */
    void onResume();
}
