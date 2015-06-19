/*
* Name: TableSettingsImpl.java
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
* v0.04 2015-06-01  Bucco Riccardo  Verify
* =================================================================
* v0.03 2015-05-31  Bigarella Chiara Edit
* =================================================================
* v0.02 2015-05-24  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-18  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents the settings for a table.
 */
public class TableSettingsImpl implements ChartSettings {
    /**
     * This attribute stores the JSON object with the settings of the chart.
     */
    private JSONObject settings;

    /**
     * Constructor
     * @param settings
     */
    public TableSettingsImpl(JSONObject settings){
        this.settings=settings;
    }

    /**
     * this metods get the title of the chart
     * @return the title of the chart
     */
    public String getTitle() {
        try {
            return settings.getString("title");
        } catch (JSONException e) {
            return "";
        }
    }

    /**
     * This method get the description of the chart
     * @return the description of the chart
     */
    public String getDescription() {
        try {
            return settings.getString("description");
        } catch (JSONException e) {
            return "";
        }
    }

    /**
     * this method get the max value of the chart
     * @return the max value setting
     */
    public int getMaxValue() {
        try {
            return settings.getInt("maxItems");
        } catch (JSONException e) {
            return 10;
        }
    }

    /**
     * This method has the task to returning a boolean that says if the border lines of the cells must be visible or not.
     * @return boolean that says if the border lines of the cells must be visible or not
     */
    public boolean getBorderLineVisibility() {
        try {
            return settings.getBoolean("showGrid");
        } catch (JSONException e) {
            return true;
        }
    }

    /**
     * return where a new line must be insert
     * @return
     */
    public String getNewLinePosition() {
        try {
            return settings.getString("newLinePosition");
        } catch (JSONException e) {
            return "bottom";
        }
    }
}
