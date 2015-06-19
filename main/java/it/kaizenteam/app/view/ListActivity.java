/*
* Name: ListActivity.java
* Package: it.kaizenteam.app.view
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/view
* Date: 2015-05-19
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
* v0.03 2015-05-30  Rubin Marco Edit
* =================================================================
* v0.02 2015-05-22  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-19  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import it.kaizenteam.app.R;
import it.kaizenteam.app.presenter.ListPresenter;
import it.kaizenteam.app.presenter.PresenterImpl;


/**
 * ListActivity shows a list of graphics content in an Norris instance. Each element in the chart represent an instance that clicked warn its presenter of the user gesture.
 */
public class ListActivity extends BaseActivity implements ListView,AdapterView.OnItemClickListener {

    /**
     * This method is performed by android at the creation of the Activity. It will be tasked to initializing its presenter.
     *
     * @param savedInstanceState instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ((android.widget.ListView) findViewById(R.id.chartslist)).setOnItemClickListener(this);
        presenter = PresenterImpl.create(PresenterImpl.ChartType.LIST_TYPE, this);
        //I do that to permit the get reqest to sdk up the 9
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ((ListPresenter) presenter).onResume();
    }

    /**
     * This method initiates the activity home. It is called by the presenter if the user log out.
     */
    @Override
    public void navigateToLoginView() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    /**
     * This method is called automatically by Android when the button is clicked logout. It will notify its presenter the pressure.
     *
     * @param view
     */
    @Override
    public void onLogoutClick(View view) {
        ((ListPresenter) presenter).onLogoutClick();
    }

    /**
     * This method is invoked with the purpose of display an array of chart (list parameter) in the list.
     *
     * @param list array of chart
     */
    @Override
    public void renderList(final ArrayList<String[]> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayList<HashMap<String, Object>> data = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    HashMap<String, Object> dataMap = new HashMap<>();//creiamo una mappa di valori
                    dataMap.put("id", list.get(i)[0]);
                    dataMap.put("type", list.get(i)[1]);
                    if (list.get(i)[1].equals("barchart"))
                        dataMap.put("img", BitmapFactory.decodeResource(getResources(), R.drawable.barchart));
                    if (list.get(i)[1].equals("linechart"))
                        dataMap.put("img", BitmapFactory.decodeResource(getResources(), R.drawable.linechart));
                    if (list.get(i)[1].equals("mapchart"))
                        dataMap.put("img", BitmapFactory.decodeResource(getResources(), R.drawable.mapchart));
                    if (list.get(i)[1].equals("table"))
                        dataMap.put("img", BitmapFactory.decodeResource(getResources(), R.drawable.table));
                    dataMap.put("title", list.get(i)[2]);
                    dataMap.put("description", list.get(i)[3]);
                    data.add(dataMap);  //aggiungiamo la mappa di valori alla sorgente dati
                }

                String[] from = {"id", "type", "img", "description", "title"}; //dai valori contenuti in queste chiavi
                int[] to = {R.id.id_listitem, R.id.type_listitem, R.id.image_listitem, R.id.image_listdescr, R.id.image_listtitle};//agli id delle view
                //costruzione dell adapter
                SimpleAdapter adapter = new SimpleAdapter(
                        getApplicationContext(),
                        data,//sorgente dati
                        R.layout.listitem, //layout contenente gli id di "to"
                        from,
                        to);
                adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Object data,
                                                String textRepresentation) {
                        if ((view instanceof ImageView) & (data instanceof Bitmap)) {
                            ImageView iv = (ImageView) view;
                            Bitmap bm = (Bitmap) data;
                            iv.setImageBitmap(bm);
                            return true;
                        }
                        return false;

                    }

                });
                //utilizzo dell'adapter
                ((android.widget.ListView) findViewById(R.id.chartslist)).setAdapter(adapter);
            }
        });
    }

    /**
     * This method starts the activity in which will be present the detail of a specific chart. The method decide which activity to start.
     *
     * @param type type of the chart
     * @param id   id of the chart
     */
    @Override
    public void showChartDetailView(String type, String id) {
        Intent i = null;
        if (type.equals("barchart"))
            i = new Intent(this, BarChartActivity.class);
        if (type.equals("linechart"))
            i = new Intent(this, LineChartActivity.class);
        if (type.equals("mapchart"))
            i = new Intent(this, MapChartActivity.class);
        if (type.equals("table"))
            i = new Intent(this, TableActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    @Override
    public void showWaitMessage(final boolean show) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (show) {
                    (findViewById(R.id.waitimg)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.chartslist)).setVisibility(View.INVISIBLE);
                } else {
                    (findViewById(R.id.waitimg)).setVisibility(View.INVISIBLE);
                    (findViewById(R.id.chartslist)).setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_reload:
                ((ListPresenter) presenter).onResume();
                return true;
            case R.id.action_logout:
                ((ListPresenter) presenter).onLogoutClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((ListPresenter) presenter).onItemClicked(((TextView) view.findViewById(R.id.type_listitem)).getText().toString(), ((TextView) view.findViewById(R.id.id_listitem)).getText().toString());
    }
}
