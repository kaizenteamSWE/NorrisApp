/*
* Name: MapChartPresenterImpl.java
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
* v0.04 2015-06-02 Bucco Riccardo  Verify
* =================================================================
* v0.03 2015-05-30  Dal Bianco Davide Edit
* =================================================================
* v0.02 2015-05-26  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-25  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;

import it.kaizenteam.app.model.NorrisChart.ChartData;
import it.kaizenteam.app.model.NorrisChart.ChartImpl;
import it.kaizenteam.app.model.NorrisChart.ChartSettings;
import it.kaizenteam.app.model.NorrisChart.ChartUpdate;
import it.kaizenteam.app.model.NorrisChart.MapChartSettingsImpl;
import it.kaizenteam.app.view.MapChartActivity;
import it.kaizenteam.app.view.MapChartView;

/**
 * This class is the implementation of a presenter for map chart.
 */
public class MapChartPresenterImpl extends ChartPresenterImpl implements MapChartPresenter{
    static {
        //registro il tipo di grafico (DI)
        registerFactory(ChartType.MAPCHART_TYPE, MapChartPresenterFactory.getInstance());
    }

    /**
     * This method is the constructor. It is private because it can not be created an instance except from a request of his inner class factory.
     */
    private MapChartPresenterImpl(){}

    /**
     * This method has the task of updating the data model if it receives an update of these ones and finally show in view these updated data.
     * @param observable
     * @param data
     */
    @Override
    public void update(Observable observable, Object data) {
        //if the update is a chart
        if(((String[])data)[0].toString().equals("mapchart")) {
            try {
                ChartData mapChartData = JSONParser.getInstance().parseMapChart(new JSONObject(((String[])data)[2].toString()));
                ChartSettings mapChartSettings = JSONParser.getInstance().parseMapChartSettings(new JSONObject(((String[])data)[1].toString()));
                //create the chart model and set its data and settings
                chart= ChartImpl.create("mapchart", id);
                chart.setData(mapChartData);
                chart.setSettings(mapChartSettings);
                //show the chart in the view with its settings
                ((MapChartActivity)view).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((MapChartView) view).renderChart(chart.getData());
                        applySettings(chart.getSettings());
                    }
                });
            } catch (JSONException e) {}
        }
        else {
            try {
                //if the data it's an update in place updates the chart
                if (((String[])data)[0].toString().equals("inplace")) {
                    ChartUpdate update = JSONParser.getInstance().parseMapChartInPlaceUpdate(new JSONObject(((String[])data)[1].toString()));
                    chart.update("mapchart:inplace", update);
                    //asks to the view the new renderize of the chart
                    ((MapChartActivity) view).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((MapChartView) view).renderChart(chart.getData());
                        }
                    });
                } else if (((String[])data)[0].toString().equals("movie")) {
                    //if the data it's an update movie updates the chart
                    ChartUpdate update = JSONParser.getInstance().parseMapChartMovieUpdate(new JSONObject(((String[])data)[1].toString()));
                    chart.update("mapchart:movie", update);
                    //asks to the view the new renderize of the chart
                    ((MapChartActivity) view).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((MapChartView) view).renderChart(chart.getData());
                        }
                    });
                }
            } catch (JSONException e) {
            }
        }
    }

    /**
     * This method has the task to change the view according to the settings of the chart.
     * @param settings
     */
    @Override
    protected void applySettings(ChartSettings settings) {
        ((MapChartView) view).setCameraCoordinate(((MapChartSettingsImpl) settings).getXCameraCoordinate(), ((MapChartSettingsImpl) settings).getYCameraCoordinate());
        ((MapChartView) view).setCameraZoom((((MapChartSettingsImpl) settings).getCameraZoomHeight()));
        ((MapChartView) view).setDescription(((MapChartSettingsImpl) settings).getDescription());
        ((MapChartView) view).setTitle(((MapChartSettingsImpl) settings).getTitle());
    }

    /**
     *  This class deals with the creation of a MapChartPresenterImpl presenter.
     */
    protected static class MapChartPresenterFactory implements PresenterImpl.PresenterFactory {
        /**
          * The static attribute is the unique instance of that class.
          */
        private static PresenterFactory instance;

	/**
         * This method has the task of returning the unique instance of the class, and creating it if it not exists.
         * @return the unique instance of the class
         */
        private static PresenterFactory getInstance(){
            if(instance!=null)
                return instance;
            return new MapChartPresenterFactory();
        }

        /**
         * This method is the constructor of the class. It is private because only getInstance() method can create an instance.
         */
        private MapChartPresenterFactory(){
            instance=this;
        }

        /**
         * This method has the task of creating the relative presenter. It can access its constructor because this factory class is inner to the related presenter class.
         * @return presenter
         */
        @Override
        public PresenterImpl createPresenter() {
            return new MapChartPresenterImpl();
        }
    }
}
