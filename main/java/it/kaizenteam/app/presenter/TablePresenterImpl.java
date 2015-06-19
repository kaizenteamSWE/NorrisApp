/*
* Name: TablePresenterImpl.java
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
* v0.04 2015-06-01  Bucco Riccardo  Verify
* =================================================================
* v0.03 2015-05-31 Pavanello Fabio Matteo Edit
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
import it.kaizenteam.app.model.NorrisChart.TableSettingsImpl;
import it.kaizenteam.app.view.TableActivity;
import it.kaizenteam.app.view.TableView;

/**
 * This class is the implementation of a presenter for table.
 */
public class TablePresenterImpl extends ChartPresenterImpl implements TablePresenter{
    static {
        //register the chart type and the updater in the super class (dependency injection)
        registerFactory(ChartType.TABLE_TYPE, TablePresenterFactory.getInstance());
    }

    /**
     * This method has the task of updating the data model if it receives an update of these ones and finally show in view these updated data.
     * @param observable
     * @param data
     */
    @Override
    public void update(Observable observable, Object data) {
        //this observer will notify when a chart data arrives or the update data
        //if it's a chart type
        if(((String[])data)[0].toString().equals("table")) {
            try {
                //create the data model with data and settings
                ChartData tableData = JSONParser.getInstance().parseTable(new JSONObject(((String[])data)[2].toString()));
                ChartSettings tableSettings = JSONParser.getInstance().parseTableSettings(new JSONObject(((String[])data)[1].toString()));
                chart= ChartImpl.create("table", id);
                chart.setData(tableData);
                chart.setSettings(tableSettings);
                //tell to the view to render the chart and apply his settings (it must do in the ui thread in android)
                ((TableActivity)view).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((TableView) view).renderChart(chart.getData());
                        applySettings(chart.getSettings());
                    }
                });
            } catch (JSONException e) {}
        }
        else{
            //if it's an update type
            try {
                if(((String[])data)[0].toString().equals("inplace")) {
                    //update in place the chart
                    ChartUpdate update = JSONParser.getInstance().parseTableInPlaceUpdate(new JSONObject(((String[])data)[1].toString()));
                    chart.update("table:inplace", update);
                    //ask to renderize the new data
                    ((TableActivity)view).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((TableView) view).renderChart(chart.getData());
                        }
                    });
                }
                else
                    if(((String[])data)[0].toString().equals("stream")) {
                        //update stream the chart
                        ChartUpdate update = JSONParser.getInstance().parseTableStreamUpdate(new JSONObject(((String[])data)[1].toString()));
                        chart.update("table:stream", update);
                        //ask to renderize the new data
                        ((TableActivity)view).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((TableView) view).renderChart(chart.getData());
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
        ((TableView) view).showCellBorderLine(((TableSettingsImpl) settings).getBorderLineVisibility());
        ((TableView) view).setDescription(((TableSettingsImpl) settings).getDescription());
        ((TableView) view).setTitle(((TableSettingsImpl) settings).getTitle());
    }

    /**
     * This method is the constructor. It is private because it can not be created an instance except from a request of his inner class factory.
     */
    private TablePresenterImpl(){}

    /**
     * This class deals with the creation of a TablePresenterImpl presenter.
     */
    protected static class TablePresenterFactory implements PresenterImpl.PresenterFactory {
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
            return new TablePresenterFactory();
        }

        /**
         * This method is the constructor of the class. It is private because only getInstance() method can create an instance.
         */
        private TablePresenterFactory(){
            instance=this;
        }

        /**
         * This method has the task of creating the relative presenter. It can access its constructor because this factory class is inner to the related presenter class.
         * @return presenter
         */
        @Override
        public PresenterImpl createPresenter() {
            return new TablePresenterImpl();
        }
    }
}
