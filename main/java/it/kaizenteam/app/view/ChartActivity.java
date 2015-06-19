/*
* Name: ChartActivity.java
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
* v0.04 2015-06-02  Rubin Marco  Verify
* =================================================================
* v0.03 2015-05-30  Dal Bianco Davide Edit
* =================================================================
* v0.02	2015-05-19	Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-19  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;


import it.kaizenteam.app.model.NorrisChart.ChartData;

/**
 * ChartActivity is an abstract class that represents an Activity for the representation of generic chart.
 */
public abstract class ChartActivity extends BaseActivity{
    protected String idChart;

    /**
     * This method is abstract and all specializations of this class must implement it. It will display properly the chart.
     * @param data the chart to display
     */
    public abstract void renderChart(ChartData data);

    /**
     * This method is abstract and all specializations of this class must implement it. It will display properly the title of the chart.
     * @param title title of the chart
     */
    public void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    /**
     * This method will display properly the description of the chart.
     * @param description description of the chart
     */
    public void setDescription(String description) {
        getSupportActionBar().setSubtitle(description);
    }

    /**
     * This method returns the id of the graph shown.
     * @return id of the graph shown.
     */
    public String getId(){
        return idChart;
    }
}
