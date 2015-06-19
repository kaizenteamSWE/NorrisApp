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
* v0.04 2015-06-03  Rubin Marco  Verify
* =================================================================
* v0.03 2015-05-29  Dal Bianco Davide Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.kaizenteam.app.model.NorrisSessionInfoImpl;

/**
 * This class extends httpclient implementing the ability to add any requests also http cookies. It has the aim to use the api external Norris such as authentication and request the list of graphics. It saves the information in the model of the session.
 */
class HttpRequesterWithCookie {
    private static HttpRequesterWithCookie instance;

    private HttpRequesterWithCookie(){
        instance=this;
    }

    public static HttpRequesterWithCookie getInstance(){
        if(instance!=null)
            return instance;
        return new HttpRequesterWithCookie();
    }

    public JSONArray getlist(){
        try {
            return new JSONArray(makeGetRequest(NorrisSessionInfoImpl.getInstance().getAddress()+NorrisSessionInfoImpl.getInstance().getEndpoint()+"/list"));
        } catch (JSONException e) {
            return null;
        }
    }

    private String makeGetRequest(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        ArrayList<Cookie> cookies= NorrisSessionInfoImpl.getInstance().getAuthCookie();
        if (cookies != null && cookies.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < cookies.size(); i++) {
                sb.append(cookies.get(i).getName()).append('=').append(cookies.get(i).getValue()).append(";");
            }
            String sck = sb.toString();
            if (sck.length() > 0) {
                request.setHeader("Cookie", sck);
            }
        }
        // making request
        try {
            HttpResponse response;
            response = client.execute(request);
            HttpEntity entity = response.getEntity();
            // to worry about connection release
            if (entity != null) {
                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                // now you have the string representation of the HTML request
                instream.close();
                return result;
            }
        }catch (IOException e) {Log.e("ERRORRRRRR","");}
        return "";
    }

    private String convertStreamToString(InputStream is) {
    /*
     * To convert the InputStream to String we use the BufferedReader.readLine()
     * method. We iterate until the BufferedReader return null which means
     * there's no more data to read. Each line will appended to a StringBuilder
     * and returned as String.
     */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void makePostRequestLogin(String url,Map<String,String> params, List<Cookie> cookieListRef) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost(url);
        //set the Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
        Set<String> paramskey=params.keySet();
        if(params.size()!=0){
            for (String s : paramskey)
                nameValuePair.add(new BasicNameValuePair(s, params.get(s)));
            //Encoding POST data
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            } catch (UnsupportedEncodingException e) {}
        }

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            // to worry about connection release
            if (entity != null) {
                // A Simple JSON Response Read
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                // now you have the string representation of the HTML request
                instream.close();
                //check if the login to norris has effect
                Log.e("RESULTTTT",result);
                if(result.equals("OK\n")){
                    List<Cookie> postCookies = ((DefaultHttpClient)httpClient).getCookieStore().getCookies();
                    for(int i =0; i< postCookies.size();i++){
                        cookieListRef.add(postCookies.get(i));
                    }
                }
                else{
                    //if(result.equals("Unauthorized\n")){
                    throw new Exception("Wrong username or password");
                }
            }
        }  catch (IOException e) {throw new Exception("Wrong address");}
    }

    private boolean makePostRequestLogout(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // replace with your url
        HttpPost httpPost = new HttpPost(url);

        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void Login(String addressNorris, String username, String password) throws Exception {
        String address,endpoint;
        //get the address and the endpoint
        if(addressNorris.substring(0,7).equals("http://")) {
            address = "http://" + addressNorris.substring(7).split("/")[0];
            endpoint = addressNorris.substring(address.length());
        }
        else{
            address = addressNorris.split("/")[0];
            endpoint = addressNorris.substring(address.length());
        }

        Map<String,String> params=new HashMap<>();
        if(username.equals("")||password.equals("")){
            params.put("username", "Moretto");
            params.put("password", "Alessandro");
        }
        else{
            params.put("username", username);
            params.put("password", password);
        }
        ArrayList<Cookie> cookies=new ArrayList<>();
        makePostRequestLogin(addressNorris + "/auth/login", params, cookies);
        if(cookies.size()!=0)
            NorrisSessionInfoImpl.getInstance().setAuthCookie(cookies);
        NorrisSessionInfoImpl.getInstance().setAddress(address);
        NorrisSessionInfoImpl.getInstance().setEndpoint(endpoint);
        NorrisSessionInfoImpl.getInstance().login();
    }

    public boolean Logout(){
        if(makePostRequestLogout(NorrisSessionInfoImpl.getInstance().getAddress() + "/auth/logout")){
            NorrisSessionInfoImpl.getInstance().setAuthCookie(null);
            NorrisSessionInfoImpl.getInstance().setAddress("");
            NorrisSessionInfoImpl.getInstance().setEndpoint("");
            NorrisSessionInfoImpl.getInstance().logout();
            return true;
        }
        return false;
    }

    public boolean KeepAlive(){
        //TODO
        return false;
    }
}
