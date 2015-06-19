/*
* Name: MapChartActivity.java
* Package: it.kaizenteam.app.view
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/view
* Date: 2015-05-23
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-02  Bucco Riccardo  Verify
* =================================================================
* v0.03 2015-05-30  Bigarella Chiara Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import it.kaizenteam.app.R;
import it.kaizenteam.app.model.NorrisChart.ChartData;
import it.kaizenteam.app.model.NorrisChart.MapChartDataImpl;
import it.kaizenteam.app.model.NorrisChart.MapPoint;
import it.kaizenteam.app.model.NorrisChart.MapSet;
import it.kaizenteam.app.presenter.MapChartPresenter;
import it.kaizenteam.app.presenter.PresenterImpl;

/**
 * MapChartActivity specializes ChartActivity and constitutes an Activity for map charts. It provides static constants that represent the possible values to be passed to methods to change the view.
 */
public class MapChartActivity extends ChartActivity implements MapChartView {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    /**
     * This method is performed by android at the creation of the Activity. It will be tasked to initializing its presenter.
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_chart);
        setUpMapIfNeeded();
        presenter= PresenterImpl.create(PresenterImpl.ChartType.MAPCHART_TYPE,this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        ((MapChartPresenter)presenter).setChart(getIntent().getStringExtra("id"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((MapChartPresenter)presenter).onPause();
    }


    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

    }

    ArrayList<Marker>markers=new ArrayList<>();
    /**
     * This method will display correctly the chart passed as a parameter.
     * @param data chart
     */
    @Override
    public void renderChart(ChartData data) {
        markers.clear();
        mMap.clear();
        ArrayList<MapSet> sets= ((MapChartDataImpl)data).getData();
        for(int i=0;i<sets.size();i++){
            BitmapDescriptor setmarkerico=null;
            ArrayList<MapPoint> set=sets.get(i).getData();
            if(sets.get(i).getMarker().equals("")||sets.get(i).getMarker().equals("standard"))
                setmarkerico=BitmapDescriptorFactory.fromResource(R.drawable.standard);
            else if(sets.get(i).getMarker().equals("custom"))
                setmarkerico=BitmapDescriptorFactory.fromResource(R.drawable.custom);
            else if(sets.get(i).getMarker().equals("plane"))
                setmarkerico=BitmapDescriptorFactory.fromResource(R.drawable.plane);
            else if(sets.get(i).getMarker().equals("flag"))
                setmarkerico=BitmapDescriptorFactory.fromResource(R.drawable.flag);
            else if(sets.get(i).getMarker().equals("bus"))
                setmarkerico=BitmapDescriptorFactory.fromResource(R.drawable.bus);
            //TODO more...
            for(int j = 0;j<set.size();j++){
                LatLng coord = new LatLng(set.get(j).getLatitude(),set.get(j).getLongitude());

                MarkerOptions mo=new MarkerOptions()
                        .position(coord)
                        .snippet("" + coord.toString())
                        .icon(setmarkerico);
                if(set.get(j).getId()!=null)
                    mo.title("" + j + "/" + sets.get(i).getName()+ " (id: "+ set.get(j).getId()+")");
                else
                    mo.title("" + j + "/" + sets.get(i).getName());

                markers.add(mMap.addMarker(mo));

                /*if(j!=0)
                    mMap.addPolyline(new PolylineOptions()
                            .color(Color.parseColor(sets.get(i).getColor()))
                            .add(new LatLng(set.get(j - 1).getLatitude(), set.get(j - 1).getLongitude()))
                            .add(coord));*/
            }
        }
    }

    /**
     * This method provides the ability to change the display position of the map chart, or the coordinates of the central point.
     * @param latitude latitude coordinate of the central point
     * @param longitude longitude coordinate of the central point
     */
    @Override
    public void setCameraCoordinate(double latitude, double longitude) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
    }

    /**
     * This method provides the ability to change the display height of the map chart. The permitted heights are available in static constants of class (X_ZOOM_LEVEL) or intermediate values to them.
     * @param zoomLevel level of display height
     */
    @Override
    public void setCameraZoom(int zoomLevel) {
        mMap.moveCamera(CameraUpdateFactory.zoomTo(zoomLevel));
    }
}
