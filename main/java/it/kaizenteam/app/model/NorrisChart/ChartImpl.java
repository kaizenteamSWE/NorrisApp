/*
* Name: ChartImpl.java
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
* v0.04 2015-06-03  Moretto Alessandro  Verify
* =================================================================
* v0.03 2015-05-30  Bucco Riccardo Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-22  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.NorrisChart;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * This class is a generic chart and for this reason it is an abstract class. It contains within it the data (DataObject) and settings (SettingsObject) relative to a chart. It also contains the interface and two ChartFactory hashmap: the first deals with the correspondence between the types of graphic and their factory classes, while the second deals with the correspondence between the various types of renovation and the class that implements this update a specific type of chart. ChartImpl has a dependency on the interface UpdateObject, as it has to know the type of package updates.
 */
public abstract class ChartImpl extends Observable implements ChartModel{
    private static Map<String,ChartFactory> factories = new HashMap<>();
    private static HashMap<String,ChartUpdater> updaters = new HashMap<>();
    final String id;
    final String type;
    ChartData data;
    ChartSettings settings;

    static {
        //carico le classi per formare gli hashmap statici
        String[] classi=new String[]{
                "it.kaizenteam.app.model.NorrisChart.LineChartImpl",
                "it.kaizenteam.app.model.NorrisChart.BarChartImpl",
                "it.kaizenteam.app.model.NorrisChart.MapChartImpl",
                "it.kaizenteam.app.model.NorrisChart.TableImpl"
        };

        new ClassLoader() {
            public void load(String[] classi){
                for (int i=0;i<classi.length;i++){
                    try {
                        Class.forName(classi[i]);
                    }catch (Exception e){}
                }
            }
        }.load(classi);
    }

    /**
     * This method creates the chart associated with the type parameter and returns the interface of ChartModel created instance.
     * @param type type of the graph
     * @param id id of the chart
     * @return return the interface of ChartModel created instance
     */
    public static ChartModel create(String type, String id) {
        ChartFactory factory = factories.get(type);

        if(factory != null)
            return factory.createChart(id);
        return null;
    }

    /**
     * This method is used to record a certain factory at its chart nell'hashmap class (factories).
     * @param chartType type of the chart
     * @param factory hashmap of the class
     */
    protected static void registerFactory(String chartType, ChartFactory factory) {
        factories.put(chartType, factory);
    }

    /**
     * This method is used to record a updater to its type of update in the hashmap of the class (updaters).
     * @param updatetype type of update
     * @param updater hashmap of the class
     */
    protected static void registerUpdater(String updatetype, ChartUpdater updater) {
        updaters.put(updatetype, updater);
    }

    /**
     * This method is the constructor of the class. It is only accessible to subclasses in the creation of the subobject.
     * @param chartType chart type
     * @param chartId id of the chart
     */
    protected ChartImpl(String chartType, String chartId) {
        this.id = chartId;
        this.type=chartType;
    }

    /**
     * This method has the task of returning the data of the chart.
     * @return the data of the chart.
     */
    public ChartData getData(){
        return data;
    }

    /**
     * This method has the task to return the id of the chart.
     * @return id of the chart
     */
    public String getId(){
        return id;
    }

    /**
     * This method has the task to return the settings of the chart.
     * @return the chart settings
     */
    public ChartSettings getSettings(){
        return settings;
    }

    /**
     * This method has the task to return the type of the chart.
     * @return type of the chart
     */
    public String getType(){
        return type;
    }

    /**
     * This method has the task to set the data of the chart.
     * @param data data of the chart to set
     */
    public void setData(ChartData data){
        this.data=data;

    }

    /**
     * This method has the task of setting the settings of the chart.
     * @param settings chart settings to set
     */
    public void setSettings(ChartSettings settings){
        this.settings=settings;
    }

    /**
     * This method has the task of updating the chart using the type of update of updateType and the update package UpdateData.
     * @param updateType type of update
     * @param updateData update package
     */
    public void update(String updateType, ChartUpdate updateData) {
        ChartUpdater updater = updaters.get(updateType);

        if (updater != null)
            updater.update(this,updateData);

    }

    /**
     * ChartFactory is the interface of the factory classes that deal with the creation of various types of graphs. It is internal to the class ChartImpl.
     */
    protected interface ChartFactory {
        ChartModel createChart(String chartId);
    }
}
