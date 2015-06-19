/*
 * Name: BarChartSettingsImpl
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
 * v0.04 2015-06-02  Dal Bianco Davide  Verify
 * =================================================================
 * v0.03 2015-05-30  Moretto Alessandro Edit
 *  =================================================================
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
 * This class represents the settings for a bar chart.
 */
public class BarChartSettingsImpl implements ChartSettings {
    /*
     * This attribute stores the JSON object with the settings of the chart.
     */
    private JSONObject settings;

    /**
     * Constructor
     * @param settings
     */
    public BarChartSettingsImpl(JSONObject settings){
        this.settings=settings;
    }

    /**
     *get the title of the chart
     * @return the title
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public String getTitle()  {
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
    public String getDescription()  {
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
    public int getMaxValue() throws JSONException {
        return settings.getInt("maxItems");
    }

    /**
     * This method has the task of returning the name of the abscissa axis.
     * @return the name of the abscissa axis
     */
    public String getXAxisName()  {
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
    public String getYAxisName()  {
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
            return"none";
        }
    }

    /**
     * This method has the task of returning the orientation of the chart.
     * @return the chart orientation
     */
    public String getOrientation()  {
        try {
            return settings.getString("orientation");
        } catch (JSONException e) {
            return "vertical";
        }
    }

    /**
     * get the spacing between the values of the chart
     * @return spacing between two bar
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public int getBarValueSpacing()  {
        try {
            return settings.getJSONObject("style").getInt("barValueSpacing");
        } catch (JSONException e) {
            return 5;
        }
    }

    /**
     * get the spacing between the series of the chart
     * @return spacing between two series of the chart
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public int getBarDataSetSpacing()  {
        try {
            return settings.getJSONObject("style").getInt("barDatasetSpacing");
        } catch (JSONException e) {
            return 10;
        }
    }
}
