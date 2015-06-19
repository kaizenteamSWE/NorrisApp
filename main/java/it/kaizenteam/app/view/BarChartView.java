/*
* Name: BarChartView.java
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
* v0.04 2015-06-02  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-30  Rubin Marco Edit
* =================================================================
* v0.02 2015-05-22  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-19  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import it.kaizenteam.app.model.NorrisChart.ChartData;

/**
 * This interface has the task of allowing the use of methods to change the view to represent a bar chart from the outside of the package View (indicated by a BarChartPresenterImpl).
 */
public interface BarChartView extends View {
    /**
     * This method will display correctly the chart as a parameter.
     * @param data chart to display
     */
    void renderChart(ChartData data);

    /**
     * This method provides the ability to display in the view the name of axes of the chart.
     * @param x name of x-axis
     * @param y name of y-axis
     */
    void setAxisName(String x, String y);

    /**
     * This method provides the ability to view or not the grid in the chart. If the parameter is true appears and hidden otherwise.
     * @param show boolean show / hidden the chart grid
     */
    void showGrid(boolean show);

    /**
     * This method provides the ability to set the position of the legend. The positions available are eligible as static variables of the class.
     * @param position position of the legend
     */
    void setLegendPosition(int position);

    /**
     * This method provides the ability to change the orientation of the chart based on the parameter.
     * The parameters allowed are available in static constants in the class.
     * @param orientation orientation of the chart
     */
    void setOrientation(String orientation);

    /**
     * This method will display properly the description of the chart.
     * @param description description of the chart
     */
    public void setDescription(String description);

    /**
     * Sets the stace between two sets
     * @param barDataSetSpacing space between two sets
     */
    public void setBarDataSetSpacing(int barDataSetSpacing);

    /**
     * Sets the stace between two bar
     * @param barValueSpacing space between two bar
     */
    public void setBarValueSpacing(int barValueSpacing);

    /**
     * Stets the title of the chart
     * @param title title of the chart
     */
    void setTitle(String title);
}
