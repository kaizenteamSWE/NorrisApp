/*
* Name: LineChartSettingsImpl.java
* Package: it.kaizenteam.app.model.NorrisChart
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/model/NorrisChart
* Date: 2015-05-22
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-01  Bigarella Chiara  Verify
* =================================================================
* v0.03 2015-05-29  Pavanello Fabio Matteo Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-22  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents the settings for a line chart
 */
public class LineChartSettingsImpl implements ChartSettings{
    /**
     * This attribute stores the JSON object with the settings of the chart.
     */
    private JSONObject settings;

    /**
     * This method is the constructor to create the settings for a line chart
     * @param settings chart settings
     */
    public LineChartSettingsImpl(JSONObject settings){
        this.settings=settings;
    }

    /**
     *get the title of the chart
     * @return the title
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public String getTitle() {
        try {
            return settings.getString("title");
        } catch (JSONException e) {
            return"";
        }
    }

    /**
     *get the desctiption of the chart
     * @return the desctiption
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public String getDescription() {
        try {
            return settings.getString("description");
        } catch (JSONException e) {
            return"";
        }
    }

    /**
     *get the max value of the chart
     * @return the max value
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public int getMaxValue() {
        try {
            return settings.getInt("maxItems");
        } catch (JSONException e) {
            return 10;
        }
    }

    /**
     * This method has the task of returning the name of the abscissa axis.
     * @return the name of the abscissa axis
     */
    public String getXAxisName() {
        try {
            return settings.getString("xLabel");
        } catch (JSONException e) {
            return"";
        }
    }

    /**
     * This method has the task of returning the name of the ordinate axis.
     * @return the name of the ordinate axis
     */
    public String getYAxisName() {
        try {
            return settings.getString("yLabel");
        } catch (JSONException e) {
            return"";
        }
    }

    /**
     * This method has the task to return a boolean that says if the grid is displayed or not.
     * @return display / hide grid
     */
    public boolean getGridVisibility() {
        try {
            return settings.getJSONObject("style").getBoolean("showGrid");
        } catch (JSONException e) {
            return true;
        }
    }

    /**
     * This method has the task of returning the position of the legend.
     * @return the position of the legend
     */
    public String getLegendPosition() {
        try {
            return settings.getString("legendPosition");
        } catch (JSONException e) {
            return "none";
        }
    }

    /**
     * get the radius of the dot
     * @return radius of the dot
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public int getDotRadius() {
        try {
            return settings.getJSONObject("style").getInt("pointDotSize");
        } catch (JSONException e) {
            return 1;
        }
    }

    /**
     * get the setting value on the line display cubic
     * @return true if the lines will display cubic, false otherwise
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public boolean getCubicCurves(){
        try {
            return settings.getJSONObject("style").getBoolean("bezierCurve");
        } catch (JSONException e) {
            return false;
        }
    }
}
