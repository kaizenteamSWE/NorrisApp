/*
* Name: BarChartPresenter.java
* Package: it.kaizenteam.app.presenter
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/presenter
* Date: 2015-05-23
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-02  Pavanello Fabio Matteo  Verify
* =================================================================
* v0.03 2015-05-30  Dal Bianco Davide Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;


import android.graphics.Color;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.kaizenteam.app.model.NorrisChart.BarChartDataImpl;
import it.kaizenteam.app.model.NorrisChart.BarChartElementInPlaceUpdate;
import it.kaizenteam.app.model.NorrisChart.BarChartInPlaceUpdate;
import it.kaizenteam.app.model.NorrisChart.BarChartSettingsImpl;
import it.kaizenteam.app.model.NorrisChart.ChartSettings;
import it.kaizenteam.app.model.NorrisChart.ChartUpdate;
import it.kaizenteam.app.model.NorrisChart.LineChartDataImpl;
import it.kaizenteam.app.model.NorrisChart.LineChartElementInPlaceUpdate;
import it.kaizenteam.app.model.NorrisChart.LineChartElementStreamUpdate;
import it.kaizenteam.app.model.NorrisChart.LineChartInPlaceUpdate;
import it.kaizenteam.app.model.NorrisChart.LineChartSettingsImpl;
import it.kaizenteam.app.model.NorrisChart.LineChartStreamUpdate;
import it.kaizenteam.app.model.NorrisChart.MapChartDataImpl;
import it.kaizenteam.app.model.NorrisChart.MapChartDeleteUpdate;
import it.kaizenteam.app.model.NorrisChart.MapChartElementDeleteUpdate;
import it.kaizenteam.app.model.NorrisChart.MapChartElementInPlaceUpdate;
import it.kaizenteam.app.model.NorrisChart.MapChartElementStreamUpdate;
import it.kaizenteam.app.model.NorrisChart.MapChartInPlaceUpdate;
import it.kaizenteam.app.model.NorrisChart.MapChartMovieUpdate;
import it.kaizenteam.app.model.NorrisChart.MapChartSettingsImpl;
import it.kaizenteam.app.model.NorrisChart.MapChartStreamUpdate;
import it.kaizenteam.app.model.NorrisChart.MapPoint;
import it.kaizenteam.app.model.NorrisChart.MapSet;
import it.kaizenteam.app.model.NorrisChart.TableCell;
import it.kaizenteam.app.model.NorrisChart.TableCellInPlaceUpdate;
import it.kaizenteam.app.model.NorrisChart.TableDataImpl;
import it.kaizenteam.app.model.NorrisChart.TableInPlaceUpdate;
import it.kaizenteam.app.model.NorrisChart.TableRow;
import it.kaizenteam.app.model.NorrisChart.TableSettingsImpl;
import it.kaizenteam.app.model.NorrisChart.TableStreamUpdate;

/**
 * This class deals with parsing the update packages or a chart of data coming from the server in JSON format.
 */
public class JSONParser {
    /**
     * The static attribute is the unique instance of that class.
     */
    private static JSONParser instance;

    /**
     * This static method is called to receive the unique instance for the class.
     * @return unique instance for the class
     */
    public static JSONParser getInstance(){
        if(instance!=null)
            return instance;
        return new JSONParser();
    }

    /**
     * It is the manufacturer of JSONParser. It is private to force to use design pattern singleton through the method getInstance().
     */
    private JSONParser(){
        instance=this;
    }

    /**
     * This method allows you to parse the data from a JSON format to BarChartDataImpl.
     * @param chartData It's the JSONObject of chart's data
     * @return returns a BarChartDataImpl with all data of the chartData.
     * @throws JSONException throws if the JSON object isn't good builds.
     */
    public BarChartDataImpl parseBarChart(JSONObject chartData) throws JSONException {
        //get the JSON obj of labels
        JSONArray labels= chartData.getJSONArray("labels");
        //create the arry of labels for the chart
        ArrayList<String> chartlabels = new ArrayList<>();
        for(int i = 0; i<labels.length();i++) {
            chartlabels.add(i,labels.getString(i));
        }
        //create che array of the sets of barchart
        ArrayList<BarDataSet> chartdataSets = new ArrayList<>();
        //get the JSON array of the sets of barchart
        JSONArray dataSets= chartData.getJSONArray("datasets");
        for(int i = 0; i<dataSets.length();i++) {
            //get the JSON obj of the item of the sets of barchart
            JSONObject data=dataSets.getJSONObject(i);
            //get or create the name
            String name;
            try {
                name=data.get("name").toString();
            }catch (Exception e){
                name="Set "+i;
            }
            //get and trasform the values of the sets
            JSONArray valori= data.getJSONArray("values");
            ArrayList<BarEntry> chartvalues = new ArrayList<>();
            for(int j = 0; j<labels.length();j++) {
                chartvalues.add(new BarEntry(valori.getInt(j),j));
            }
            BarDataSet set = new BarDataSet(chartvalues, name);
            //set the color of the chart set
            String color="#000000";
            try {
                color=data.getString("color");
            }catch (Exception e){}
            set.setColor(Color.parseColor(color));
            //add the set to the array of the sets
            chartdataSets.add(set);
        }
        return new BarChartDataImpl(new BarData(chartlabels,chartdataSets));
    }

    /**
     * This method allows you to parse the data from a JSON format to LineChartDataImpl.
     * @param chartData It's the JSONObject of chart's data
     * @return returns a LineChartDataImpl with all data of the chartData.
     * @throws JSONException throws if the JSON object isn't good builds.
     */
    public LineChartDataImpl parseLineChart(JSONObject chartData) throws JSONException {
        //get the JSON obj of labels
        JSONArray labels= chartData.getJSONArray("labels");
        //create the arry of labels for the chart
        ArrayList<String> chartlabels = new ArrayList<>();
        for(int i = 0; i<labels.length();i++) {
            chartlabels.add(labels.getString(i));
        }
        //create che array of the sets of barchart
        ArrayList<LineDataSet> chartdataSets = new ArrayList<>();
        //get the JSON array of the sets of barchart
        JSONArray dataSets= chartData.getJSONArray("datasets");
        for(int i = 0; i<dataSets.length();i++) {
            //get the JSON obj of the item of the sets of barchart
            JSONObject data=dataSets.getJSONObject(i);
            //get or create the name
            String name;
            try {
                name=data.get("name").toString();
            }catch (Exception e){
                name="Set "+i;
            }
            //get and trasform the values of the sets
            JSONArray valori= data.getJSONArray("values");
            ArrayList<Entry> chartvalues = new ArrayList<>();
            for(int j = 0; j<labels.length();j++) {
                chartvalues.add(new Entry(valori.getInt(j),j));
            }
            LineDataSet set = new LineDataSet(chartvalues, name);
            //set the color of the chart set
            String color="#000000";
            try {
                color=data.getString("color");
            }catch (Exception e){}
            set.setColor(Color.parseColor(color));
            //set the width of the line
            set.setLineWidth(2.5f);
            //add the set to the array of the sets
            chartdataSets.add(set);
        }
        return new LineChartDataImpl(new LineData(chartlabels,chartdataSets));
    }

    /**
     * This method allows you to parse the data from a JSON format to MapChartDataImpl.
     * @param chartData It's the JSONObject of chart's data
     * @return returns a MapChartDataImpl with all data of the chartData.
     * @throws JSONException throws if the JSON object isn't good builds.
     */
    public MapChartDataImpl parseMapChart(JSONObject chartData) throws JSONException {
        ArrayList<MapSet> datasets = new ArrayList<>();
        JSONArray items= chartData.getJSONArray("datasets");
        for(int i = 0; i<items.length();i++) {
            ArrayList<MapPoint> mapset=new ArrayList<>();
            JSONObject item= items.getJSONObject(i);
            //populate the set with the parsed values
            JSONArray valori= item.getJSONArray("values");
            for(int j = 0; j<valori.length();j++) {
                JSONObject valore=valori.getJSONObject(j);
                double lat= valore.getDouble("y");
                double lng= valore.getDouble("x");
                MapPoint mp=new MapPoint(lat,lng);
                try{
                    String id=valore.getString("id");
                    mp.setId(id);
                }catch (Exception e){}
                mapset.add(mp);
            }
            MapSet ms=new MapSet(mapset);
            //set the color of the set if exists
            String color="#000000";
            try {
                color=item.getString("color");
            }catch (JSONException e){}
            ms.setColor(color);
            //set the marker type of the set if exists
            String marker="";
            try {
                marker=item.get("marker").toString();
            }catch (Exception e){
            }
            ms.setMarker(marker);
            //set the name of the set if exists
            String name;
            try {
                name=item.get("name").toString();
            }catch (Exception e){
                name="Set "+i;
            }
            ms.setName(name);
            //add the set
            datasets.add(ms);
        }
        return new MapChartDataImpl(datasets);
    }

    /**
     * This method allows you to parse the data from a JSON format to TableDataImpl.
     * @param chartData It's the JSONObject of chart's data
     * @return returns a TableDataImpl with all data of the chartData.
     * @throws JSONException throws if the JSON object isn't good builds.
     */
    public TableDataImpl parseTable(JSONObject chartData) throws JSONException {
        ArrayList<String> chartlabels=new ArrayList<>();
        JSONArray headers= (JSONArray) chartData.get("headers");
        //populate de labels array
        for(int i = 0; i<headers.length();i++) {
            chartlabels.add(headers.getString(i));
        }
        //parse the chart datas
        ArrayList<TableRow> chartsets=new ArrayList<>();
        JSONArray dataSets= chartData.getJSONArray("datasets");
        //populate the chart sets's array
        for(int i = 0; i<dataSets.length();i++) {
            ArrayList<TableCell> chartset=new ArrayList<>();
            JSONArray items=dataSets.getJSONObject(i).getJSONArray("row");
            //populate the chartset
            for(int j = 0; j<items.length();j++) {
                JSONObject item=items.getJSONObject(j);
                //if exists parse the font color
                String colorFont="#000000";
                try {
                    colorFont=item.getString("color");
                }catch(Exception e){}
                //if exists parse the background color
                String colorBG="#EDEDED";
                try {
                    colorBG= item.getString("background");
                }catch(Exception e){}
                //if exists parse the value of the cell
                String value= item.getString("value");
                chartset.add(new TableCell(value,colorFont,colorBG));
            }
            chartsets.add(new TableRow(chartset));
        }
        return new TableDataImpl(chartsets,chartlabels);
    }

    /**
     * This method lets you turn the settings of a chart from JSON format to ChartSettings format.
     * @param settingsData
     * @return ChartSettings format
     */
    public ChartSettings parseTableSettings(JSONObject  settingsData){
        return new TableSettingsImpl(settingsData);
    }

    /**
     * This method lets you turn the settings of a chart from JSON format to ChartSettings format.
     * @param settingsData
     * @return ChartSettings format
     */
    public ChartSettings parseLineChartSettings(JSONObject  settingsData){
        return new LineChartSettingsImpl(settingsData);
    }

    /**
     * This method lets you turn the settings of a chart from JSON format to ChartSettings format.
     * @param settingsData
     * @return ChartSettings format
     */
    public ChartSettings parseBarChartSettings(JSONObject  settingsData){
        return new BarChartSettingsImpl(settingsData);
    }

    /**
     * This method lets you turn the settings of a chart from JSON format to ChartSettings format.
     * @param settingsData
     * @return ChartSettings format
     */
    public ChartSettings parseMapChartSettings(JSONObject  settingsData){
        return new MapChartSettingsImpl(settingsData);
    }

    /**
     * This method allows you to transform an update from the JSON format to ChartUpdate.
     * @param updateData
     * @return ChartUpdate
     */
    public ChartUpdate parseTableStreamUpdate(JSONObject updateData) throws JSONException {
        //get the items of the array of stream update
        JSONArray rows = updateData.getJSONArray("stream");
        ArrayList<TableRow> updaterows = new ArrayList<>();
        //populate the rows of the update pack
        for(int i =0; i<rows.length(); i++ ){
            ArrayList<TableCell> updaterow= new ArrayList<>();
            JSONArray row = rows.getJSONObject(i).getJSONArray("row");
            //populate the array of a row update
            for(int j=0;j<row.length();j++){
                JSONObject cell = row.getJSONObject(j);
                String value= cell.getString("value");
                String fontcolor="#000000";
                try {
                    fontcolor=cell.getString("color");
                }catch (Exception e){}
                String bgcolor="#EDEDED";
                try {
                    bgcolor=cell.getString("background");
                }catch (Exception e){}
                //add this cell to the row
                updaterow.add(new TableCell(value,fontcolor,bgcolor));
            }
            //add this row to the rows
            updaterows.add(new TableRow(updaterow));
        }
        return new TableStreamUpdate(updaterows);
    }

    /**
     * This method allows you to transform an update from the JSON format to ChartUpdate.
     * @param updateData
     * @return ChartUpdate
     */
    public ChartUpdate parseTableInPlaceUpdate(JSONObject updateData) throws JSONException {
        //get the items of the array of in place update
        JSONArray rows = updateData.getJSONArray("inplace");
        ArrayList<TableCellInPlaceUpdate> values= new ArrayList<>();
        //populate the array
        for(int i =0; i<rows.length(); i++ ){
            JSONObject row= rows.getJSONObject(i);
            //get the values of the object to substitute
            JSONObject oldvalue=row.getJSONObject("position");
            int x=oldvalue.getInt("x");
            int y=oldvalue.getInt("y");
            //get the values of the new object
            JSONObject newvalue=row.getJSONObject("data");
            String value= newvalue.getString("value");
            String fontcolor="#000000";
            try {
                fontcolor=newvalue.getString("color");
            }catch (Exception e){}
            String bgcolor="#EDEDED";
            try {
                bgcolor=newvalue.getString("background");
            }catch (Exception e){}
            //add the value of an update to the array of all update
            values.add(new TableCellInPlaceUpdate(x,y,new TableCell(value,fontcolor,bgcolor)));
        }
        return new TableInPlaceUpdate(values);
    }

    /**
     * This method allows you to transform an update from the JSON format to ChartUpdate.
     * @param updateData
     * @return ChartUpdate
     */
    public ChartUpdate parseMapChartInPlaceUpdate(JSONObject updateData) throws JSONException {
        ArrayList<MapChartElementInPlaceUpdate> updatevalues=new ArrayList<>();
        JSONArray values=updateData.getJSONArray("inplace");
        //populate the array for the update
        for(int i =0;i<values.length();i++){
            JSONObject updatevalue = values.getJSONObject(i);
            //parse the new values
            JSONObject updatevaluedata=updatevalue.getJSONObject("data");
            double x=updatevaluedata.getDouble("x");
            double y=updatevaluedata.getDouble("y");
            MapPoint point=new MapPoint(y,x);
            try {
                String id = updatevaluedata.getString("id");
                point.setId(id);
            }catch(Exception e){}
            //parse the old values
            JSONObject position=updatevalue.getJSONObject("position");
            MapChartElementInPlaceUpdate updateelement;
            int series=position.getInt("series");
            int index=position.getInt("index");
            updateelement=new MapChartElementInPlaceUpdate(point,series,index);
            String id;
            try{
                id=position.getString("id");
                updateelement.setId(id);
            }catch (JSONException e){}
            //add the element to the array of update values
            updatevalues.add(updateelement);
        }
        return new MapChartInPlaceUpdate(updatevalues);
    }

    /**
     * This method allows you to transform an update from the JSON format to ChartUpdate.
     * @param updateData
     * @return ChartUpdate
     */
    public ChartUpdate parseMapChartMovieUpdate(JSONObject updateData) {
        MapChartInPlaceUpdate inplace=null;
        try {
            JSONObject inplaceobj = new JSONObject();
            inplaceobj.put("inplace", updateData.getJSONArray("inplace"));
            inplace = (MapChartInPlaceUpdate) parseMapChartInPlaceUpdate(inplaceobj);
        }catch (JSONException e){}

        MapChartStreamUpdate stream=null;
        try {
            stream = parseMapChartStreamUpdate(updateData.getJSONArray("stream"));
        }catch (JSONException e){}

        MapChartDeleteUpdate delete=null;
        try{
            delete= parseMapChartDeleteUpdate(updateData.getJSONArray("delete"));
        }catch(JSONException e){}
        return new MapChartMovieUpdate(inplace,stream,delete);
    }

    private MapChartDeleteUpdate parseMapChartDeleteUpdate(JSONArray updateData) throws JSONException {
        ArrayList<MapChartElementDeleteUpdate> points=new ArrayList<>();
        //populate the array with the information of the point to delete
        for(int i =0; i < updateData.length();i++){
            //get and parse the information of an element of the delete update
            JSONObject point = updateData.getJSONObject(i);
            MapChartElementDeleteUpdate mcedu;
            try{
                int series=point.getInt("series");
                int index=point.getInt("index");
                mcedu=new MapChartElementDeleteUpdate(series,index);
            }catch (Exception e){
                String id=point.getString("id");
                mcedu=new MapChartElementDeleteUpdate(id);
            }
            //add the element to the array
            points.add(mcedu);
        }
        return new MapChartDeleteUpdate(points);
    }

    private MapChartStreamUpdate parseMapChartStreamUpdate(JSONArray updateData) throws JSONException {
        ArrayList<MapChartElementStreamUpdate> sets=new ArrayList<>();
        //populate the array with the stream elements
        for(int i=0;i<updateData.length();i++){
            //get and parse the element of a stream update
            JSONObject point = updateData.getJSONObject(i);
            int series=point.getInt("series");
            MapPoint mp =new MapPoint( point.getJSONObject("data").getDouble("y"),point.getJSONObject("data").getDouble("x"));
            try{
                String id=point.getJSONObject("data").getString("id");
                mp.setId(id);
            }catch(Exception e){}
            sets.add(new MapChartElementStreamUpdate(series, mp));
        }
        return new MapChartStreamUpdate(sets);
    }

    /**
     * This method allows you to transform an update from the JSON format to ChartUpdate.
     * @param updateData
     * @return ChartUpdate
     */
    public ChartUpdate parseLineChartStreamUpdate(JSONObject updateData) throws JSONException {
        JSONArray values = updateData.getJSONArray("stream");
        ArrayList<LineChartElementStreamUpdate> updatevalues=new ArrayList<>();
        //populate the array of elements of update
        for(int i =0;i<values.length();i++){
            JSONObject updatevalue=values.getJSONObject(i);
            String label=updatevalue.getString("label");

            JSONArray data=updatevalue.getJSONArray("data");
            ArrayList<Integer> updatedatas=new ArrayList();
            //populate the datas array
            for (int j =0;j<data.length();j++)
                updatedatas.add(new Integer(data.getInt(j)));
            updatevalues.add(new LineChartElementStreamUpdate(label, updatedatas));
        }
        return new LineChartStreamUpdate(updatevalues);
    }

    /**
     * This method allows you to transform an update from the JSON format to ChartUpdate.
     * @param updateData
     * @return ChartUpdate
     */
    public ChartUpdate parseLineChartInPlaceUpdate(JSONObject updateData) throws JSONException {
        JSONArray values=updateData.getJSONArray("inplace");
        ArrayList<LineChartElementInPlaceUpdate> updatevalues=new ArrayList<>();
        //populate the array of the values
        for (int i =0;i<values.length();i++){
            JSONObject element = values.getJSONObject(i);
            JSONObject position=element.getJSONObject("position");
            int x=position.getInt("x");
            int y=position.getInt("y");
            int value=element.getInt("data");
            updatevalues.add(new LineChartElementInPlaceUpdate(x,y,value));
        }
        return new LineChartInPlaceUpdate(updatevalues);
    }

    /**
     * This method allows you to transform an update from the JSON format to ChartUpdate.
     * @param updateData
     * @return ChartUpdate
     */
    public ChartUpdate parseBarChartInPlaceUpdate(JSONObject updateData) throws JSONException {
        JSONArray values=updateData.getJSONArray("inplace");
        ArrayList<BarChartElementInPlaceUpdate> updatevalues=new ArrayList<>();
        //populate the array of the values
        for (int i =0;i<values.length();i++){
            JSONObject element = values.getJSONObject(i);
            JSONObject position=element.getJSONObject("position");
            int x=position.getInt("x");
            int y=position.getInt("y");
            int value=element.getInt("data");
            updatevalues.add(new BarChartElementInPlaceUpdate(x,y,value));
        }
        return new BarChartInPlaceUpdate(updatevalues);
    }
}