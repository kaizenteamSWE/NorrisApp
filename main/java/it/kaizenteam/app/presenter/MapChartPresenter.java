/*
* Name: MapChartPresenter.java
* Package: it.kaizenteam.app.presenter
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/presenter
* Date: 2015-05-25
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-03  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-29  Bigarella Chiara Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-25  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;


/**
 * This class create a LoginPresenterImpl presenter.
 */
public interface MapChartPresenter extends Presenter{

    /**
     * This methods ask to Norris the chart data, start the updates events and ask the renderization to the view
     * @param id id of the chart
     */
    void setChart(String id);

    /**
     * execute the code when the activity go to pause state
     */
    void onPause();
}
