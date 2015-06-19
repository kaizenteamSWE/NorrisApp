/*
* Name: MapChartSettingsImpl.java
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
* v0.04 2015-06-02  Bigarella Chiara  Verify
* =================================================================
* v0.03 2015-05-30  Moretto Alessandro Edit
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
 * This class represents the settings for a map chart.
 */
public class MapChartSettingsImpl implements ChartSettings {
    /**
      * This attribute stores the JSON object with the settings of the chart.
      */
    private JSONObject settings;

    /**
     * Constructor of the class
     * @param settings of the chart
     */
    public MapChartSettingsImpl(JSONObject settings){
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
            return "";
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
            return "";
        }
    }

    /**
     *get the max value of the chart
     * @return the max value
     * @throws JSONException if there is an error in the JSONObject settings passed to the constructor
     */
    public int getMaxValue()  {
        try {
            return settings.getInt("maxItems");
        } catch (JSONException e) {
            return 10;
        }
    }

    /**
     * This method has the task of returning the abscissas of the position of the camera in map chart.
     * @return abscissas of the position of the camera in map chart
     */
    public double getXCameraCoordinate() {
        try {
            return settings.getJSONObject("centerCoordinates").getDouble("x");
        } catch (JSONException e) {
            return 0;
        }
    }

    /**
     * This method has the task of returning the ordinates of the position of the camera in map chart.
     * @return ordinates of the position of the camera in map chart
     */
    public double getYCameraCoordinate() {
        try {
            return settings.getJSONObject("centerCoordinates").getDouble("y");
        } catch (JSONException e) {
            return 0;
        }
    }

    /**
     * This method has the task of returning the height of the camera in the map chart.
     * @return height of the camera in the map chart
     */
    public int getCameraZoomHeight() {
        try {
            return settings.getInt("area");
        } catch (JSONException e) {
            return 1;
        }
    }

}
