/*
* Name: BarChartPresenter.java
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
* v0.04 2015-06-02  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-30  Dal Bianco Davide Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-25  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;

import it.kaizenteam.app.model.NorrisChart.BarChartSettingsImpl;
import it.kaizenteam.app.model.NorrisChart.ChartData;
import it.kaizenteam.app.model.NorrisChart.ChartImpl;
import it.kaizenteam.app.model.NorrisChart.ChartSettings;
import it.kaizenteam.app.model.NorrisChart.ChartUpdate;
import it.kaizenteam.app.view.BarChartActivity;
import it.kaizenteam.app.view.BarChartView;

/**
 * This class is the implementation of a presenter for bar chart.
 */
public class BarChartPresenterImpl extends ChartPresenterImpl implements BarChartPresenter{
    static {
        // register the type of graph (DI)
        registerFactory(ChartType.BARCHART_TYPE, BarChartPresenterFactory.getInstance());
    }
    /**
     * This method is the constructor. It is private because it can not be created an instance except from a request of his inner class factory.
     */
    private BarChartPresenterImpl(){

    }

    /**
     * This method has the task of updating the data model if it receives an update of these ones and finally show in view these updated data.
     * @param observable
     * @param data
     */
    @Override
    public void update(Observable observable, Object data) {
        if(((String[])data)[0].toString().equals("barchart")) {
            try {
                ChartData barChartData = JSONParser.getInstance().parseBarChart(new JSONObject(((String[])data)[2].toString()));
                ChartSettings barChartSettings = JSONParser.getInstance().parseBarChartSettings(new JSONObject(((String[])data)[1].toString()));
                chart= ChartImpl.create("barchart", id);
                chart.setData(barChartData);
                chart.setSettings(barChartSettings);
                ((BarChartActivity)view).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((BarChartView) view).renderChart(chart.getData());
                        applySettings(chart.getSettings());
                    }
                });

            } catch (JSONException e) {}
        }
        else{
            try {
                if(((String[])data)[0].toString().equals("inplace")) {
                    ChartUpdate update;
                    update = JSONParser.getInstance().parseBarChartInPlaceUpdate(new JSONObject(((String[])data)[1].toString()));
                    chart.update("barchart:inplace", update);
                    ((BarChartActivity)view).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((BarChartView) view).renderChart(chart.getData());
                        }
                    });
                }
            } catch (JSONException e) {}
        }
    }


    /**
     * This method has the task to change the view according to the settings of the chart.
     * @param settings
     */
    @Override
    protected void applySettings(ChartSettings settings) {
        ((BarChartView) view).setTitle(((BarChartSettingsImpl) settings).getTitle());
        ((BarChartView) view).setDescription(((BarChartSettingsImpl) settings).getDescription());
        ((BarChartView) view).setOrientation(((BarChartSettingsImpl) settings).getOrientation());
        ((BarChartView) view).setAxisName(((BarChartSettingsImpl) settings).getXAxisName(), ((BarChartSettingsImpl) settings).getYAxisName());
        //((BarChartView) view).setBarDataSetSpacing(((BarChartSettingsImpl) settings).getBarDataSetSpacing());
        ((BarChartView) view).setBarValueSpacing(((BarChartSettingsImpl) settings).getBarValueSpacing());
        ((BarChartView) view).showGrid(((BarChartSettingsImpl) settings).getGridVisibility());

        if(((BarChartSettingsImpl) settings).getLegendPosition().equals("left"))
            ((BarChartView) view).setLegendPosition(0);
        if(((BarChartSettingsImpl) settings).getLegendPosition().equals("right"))
            ((BarChartView) view).setLegendPosition(2);
        if(((BarChartSettingsImpl) settings).getLegendPosition().equals("top"))
            ((BarChartView) view).setLegendPosition(3);
        if(((BarChartSettingsImpl) settings).getLegendPosition().equals("bottom"))
            ((BarChartView) view).setLegendPosition(1);
        if(((BarChartSettingsImpl) settings).getLegendPosition().equals("in"))
            ((BarChartView) view).setLegendPosition(4);
        if(((BarChartSettingsImpl) settings).getLegendPosition().equals("none"))
            ((BarChartView) view).setLegendPosition(5);
    }

    /**
     *  This class deals with the creation of a BarChartPresenterImpl presenter.
     */
    protected static class BarChartPresenterFactory implements PresenterImpl.PresenterFactory {

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
            return new BarChartPresenterFactory();
        }

        /**
         * This method is the constructor of the class. It is private because only getInstance() method can create an instance.
         */
        private BarChartPresenterFactory(){
            instance=this;
        }

        /**
         * This method has the task of creating the relative presenter. It can access its constructor because this factory class is inner to the related presenter class.
         * @return presenter
         */
        @Override
        public PresenterImpl createPresenter() {
            return new BarChartPresenterImpl();
        }
    }
}
