/*
* Name: LineChartView.java
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
* v0.03 2015-05-30  Dal Bianco Davide Edit
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
 * This interface has the task of allowing the use of methods to change the view to represent a line chart from the outside of the package View (indicated by a LineChartPresenterImpl).
 */
public interface LineChartView extends View {
    /**
     * This method will display correctly the chart passed as a parameter.
     * @param data chart
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
     * This method will display properly the description of the chart.
     * @param description description of the chart
     */
    void setDescription(String description);

    /**
     * This method allows to display the lines of the line chart cubic or not (depending on the boolean parameter).
     * @param cubics disply / not display the lines of the line chart cubic
     */
    void setCubicLines(boolean cubics);

    /**
     * this methods set the radius of the dot of the chart
     * @param dotRadius dot radius value
     */
    void setDotRadius(int dotRadius);

    /**
     * Stets the title of the chart
     * @param title title of the chart
     */
    void setTitle(String title);

}
