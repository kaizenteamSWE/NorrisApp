/*
* Name: ChartUpdater.java
* Package: it.kaizenteam.app.model.NorrisChart
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/model/NorrisChart
* Date: 2015-05-18
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
* v0.03 2015-05-31  Pavanello Fabio Matteo Edit
* =================================================================
* v0.02 2015-05-24  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-18  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

/**
 * ChartUpdater is the interface of classes that deal with implementation of various types of update for each type of chart.
 */
public interface ChartUpdater {
    /**
     * This method has the task of updating the chart as a parameter (chart) using the update package (UpdateData).
     * @param chart
     * @param updateData
     */
    void update(ChartImpl chart, ChartUpdate updateData);
}
