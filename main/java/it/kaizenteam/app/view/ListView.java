/*
* Name: ListView.java
* Package: it.kaizenteam.app.view
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/view
* Date: 2015-05-19
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* ================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-01  Bucco Riccardo  Verify
* =================================================================
* v0.03 2015-05-29  Pavanello Fabio Matteo Edit
* =================================================================
* v0.02 2015-05-22  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-19  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import java.util.ArrayList;

/**
 * This interface has the task of allowing the use of methods to change the view with the list of chart from the outside of the package View (then a ListPresenterImpl).
 */
public interface ListView extends View {
    /**
     * This method initiates the activity home. It is called by the presenter if the user log out.
     */
    void navigateToLoginView();

    /**
     * 
     * @param view
     */
    void onLogoutClick(android.view.View view);

    /**
     * This method is invoked with the purpose of display an array of chart (list parameter) in the list.
     * @param list array of chart
     */
    void renderList(ArrayList<String[]> list);

    /**
     * This method starts the activity in which will be present the detail of a specific chart. The choice of which activity to start it falls to it.
     * @param type type of chart
     * @param id id of chart
     */
    void showChartDetailView(String type, String id);

    void showWaitMessage(boolean show);
}
