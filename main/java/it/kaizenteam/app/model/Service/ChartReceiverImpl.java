/*
* Name: ChartReceiverImpl.java
* Package: it.kaizenteam.app.model.service
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/model/Service
* Date: 2015-05-22
* Version: v1.00
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-01  Bigarella Chiara  Verify
* =================================================================
* v0.03 2015-05-31  Pavanello Fabio Matteo Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-22  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model.Service;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.engineio.client.Transport;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Manager;
import com.github.nkzawa.socketio.client.Socket;

import org.apache.http.cookie.Cookie;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

import it.kaizenteam.app.model.NorrisSessionInfoImpl;

/**
 * This class has a responsibility to communicate and receive events through the socket channel between app and server. 
 * Updates can be started or stopped out or it can be made the request for a full chart by external api of Norris.
 */
public class ChartReceiverImpl extends Observable implements ChartReceiver {
    private static ChartReceiverImpl instance;
    private Socket socket;

    /**
     * This method has the task of returning the unique instance of that class, and creating it if it not exists
     * @return the unique instance of that class
     */
    public static ChartReceiver getInstance(){
        if(instance!=null)
            return instance;
        return new ChartReceiverImpl();
    }

    /**
     * This method is the constructor of the class. It is private because only getInstance() method is allowed to create an instance.
     */
    private ChartReceiverImpl(){
        instance=this;
    }

    /**
     * This method has the task of finishing the receipt of the updates through the socket channel
     */
    @Override
    public void stopUpdateEvent() {
        socket.io().off();
        socket.off("update");
        socket.disconnect();
        socket.close();
    }

    /**
     * This method has the task of finding data and settings of a chart whose id is chartId. This method returns a HashMap in which are stored data with the keys "data" and "settings".
     * @param chartId id of the chart
     */
    @Override
    public void getChart(String chartId) {
        //inizializzo il socket con l'indirizzo di norris
        try {
            IO.Options opt=new IO.Options();
            opt.path=NorrisSessionInfoImpl.getInstance().getEndpoint()+"/chart";
            socket = IO.socket(NorrisSessionInfoImpl.getInstance().getAddress()+"/"+chartId,opt);
        }
        catch (Exception e){
        }

        socket.io().on(Manager.EVENT_TRANSPORT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Transport transport = (Transport) args[0];
                transport.on(Transport.EVENT_REQUEST_HEADERS, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        @SuppressWarnings("unchecked")
                        Map<String, String> headers = (Map<String, String>) args[0];

                        ArrayList<Cookie> cookies = NorrisSessionInfoImpl.getInstance().getAuthCookie();
                        if(cookies!=null){
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < cookies.size(); i++) {
                                sb.append(cookies.get(i).getName()).append('=').append(cookies.get(i).getValue()).append(";");
                            }
                            String sck = sb.toString();
                            headers.put("Cookie", sck);
                        }
                    }
                });
            }
        });

        socket.on("chart" , new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                setChanged();
                String[]arg={args[0].toString(),args[1].toString(),args[2].toString()};
                ChartReceiverImpl.this.notifyObservers(arg);
                socket.off("chart");

                socket.on("update" , new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        setChanged();
                        String[] arg={args[0].toString(),args[1].toString()};
                        ChartReceiverImpl.this.notifyObservers(arg);
                        Log.d("Chart update", args[0].toString());
                    }
                });
            }
        });


        socket.connect();
    }
}
