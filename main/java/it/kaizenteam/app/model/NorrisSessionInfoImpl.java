/*
* Name: NorrisSessionInfoImpl.java
* Package: it.kaizenteam.app.model
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/model
* Date: 2015-05-23
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
* v0.03 2015-05-30  Rubin Marco Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.model;

import org.apache.http.cookie.Cookie;

import java.util.ArrayList;

/** This class stores the various data necessary for the session. In fact it contains the various authentication cookie and the address to which the session belongs. */
public class NorrisSessionInfoImpl implements NorrisSessionInfo {
    /**
    * The static attribute is the unique instance of that class.
    */
    private static NorrisSessionInfoImpl instance;

    /**
     * This attribute is the address belonging to Norris active session.
     */
    private String address="";

    /**
     * This attribute is the endpoint belonging to Norris active session.
     */
    private String endpoint="";

    /**
    * This attribute represents the state of the session.
    */
    private boolean isLogged=false;

    /**
    * This attribute represents the cookie authentication session to be sent to make requests.
    */
    private ArrayList<Cookie> authCookie;

    /**
    * This method is responsible for returning the unique instance of this class and creating it if it not exists.
    * @return the unique Norris instance
    */
    public static NorrisSessionInfo getInstance(){
        if(instance!=null)
            return instance;
        return new NorrisSessionInfoImpl();
    }

    /**
    * This method is the constructor of the class. It is private because only through getInstance() method it is allowed to create the instance.
    */
    private NorrisSessionInfoImpl(){
        instance=this;
    }

    /**
    * This method has the task to return the address of the instance of Norris.
    * @return Norris instance address
    */
    public String getAddress(){
        return address;
    }

    /**
    * This method has the task of storing the address of the instance of Norris accessed.
    * @param address Norris instance address
    */
    public void setAddress(String address){
        this.address=address;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint=endpoint;
    }

    /**
    * This method has the task of storing that login happened in instance of Norris.
    */
    public void login(){
        isLogged=true;
    }

    /**
    * This method has the task of storing that logout happened in instance of Norris.
    */
    public void logout(){
        isLogged=false;
    }

    /**
    * This method has the task of informing through a Boolean value if the session instance of Norris is active.
    * @return Norris instance active / not active
    */
    public boolean isLogged(){
        return isLogged;
    }

    /**
     * This method is responsible for returning the list of authentication cookies for the instance of Norris.
     * @return Cookie[] list of authentication cookies
     */
    public ArrayList<Cookie> getAuthCookie(){
        return authCookie;
    }

    /**
     * This method is responsible for returning the list of authentication cookies for the instance of Norris.
     * @return Cookie[] list of authentication cookies
     */
    public void setAuthCookie(ArrayList<Cookie> cookies){
        this.authCookie=cookies;
    }
}
