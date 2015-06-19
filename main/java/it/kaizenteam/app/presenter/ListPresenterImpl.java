/*
* Name: ListPresenterImpl.java
* Package: it.kaizenteam.app.presenter
* Location: Sources/Applicazione/main/java/it/kaizenteam/app/presenter
* Date: 2015-05-23
* Version: v0.02
*
* History:
* =================================================================
* Version	Date	Programmer	Changes
* =================================================================
* v1.00 2015-06-15  Carlon Chiara  Approved
* =================================================================
* v0.04 2015-06-02  Bucco Riccardo  Verify
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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import it.kaizenteam.app.view.ListView;

/**
 * This class represents the specialization of PresenterImpl. It has the purpose to change the view of the list of graphics inside the instance of Norris and then request to HttpRequesterWithCookie the list of chart inside the instance. The class can finally permit logout of the active session.
 */
public class ListPresenterImpl extends PresenterImpl implements ListPresenter{
    static {
        //registro il tipo di grafico (DI)
        registerFactory(ChartType.LIST_TYPE, ListPresenterFactory.getInstance());
    }

    /**
     * This method is invoked by pressing a list item. It will ask the display of chartActivity specification of the chart represented in the item.
     * @param type
     * @param id
     */
    @Override
    public void onItemClicked(String type, String id) {
        ((ListView)view).showChartDetailView(type, id);
    }

    /**
     * This method is invoked when you press the button to logout in view. It will carry out of the session (through HttpRequesterWithCookie).
     */
    @Override
    public void onLogoutClick() {
        HttpRequesterWithCookie.getInstance().Logout();
        ((ListView) view).navigateToLoginView();
    }

    /**
     * This method is invoked in response to an event in the view, or the display of Activity with the list of chart. Its task is to get the list (through
HttpRequesterWithCookie) and change the view with those values.
     */
    @Override
    public void onResume() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ((ListView)view).showWaitMessage(true);
                ArrayList<String[]> a= new ArrayList<>();

                JSONArray list= HttpRequesterWithCookie.getInstance().getlist();
                if(list!=null)
                    for(int i =0;i<list.length();i++){
                        String title="";
                        try {
                            title = list.getJSONObject(i).getString("title");
                        } catch (JSONException e) {}
                        String description = "";
                        try {
                            description = list.getJSONObject(i).getString("description");
                        } catch (JSONException e) {}
                        try {
                            String id=list.getJSONObject(i).getString("id");
                            String type=list.getJSONObject(i).getString("type");
                            a.add(new String[]{id,type,title,description});
                            ((ListView)view).showWaitMessage(false);
                            ((ListView)view).renderList(a);
                        } catch (JSONException e) {
                        }
                    }
                else
                    ((ListView)view).showWaitMessage(false);
            }
        }).start();
    }

    /**
     * This method is the constructor. It is private because it can not be created an instance except from a request of his inner class factory.
     */
    private ListPresenterImpl(){}

    /**
     *  This class deals with the creation of a ListPresenterImpl presenter.
     */
    protected static class ListPresenterFactory implements PresenterImpl.PresenterFactory {

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
            return new ListPresenterFactory();
        }

        /**
         * This method is the constructor of the class. It is private because only getInstance() method can create an instance.
         */
        private ListPresenterFactory(){
            instance=this;
        }

        /**
         * This method has the task of creating the relative presenter. It can access its constructor because this factory class is inner to the related presenter class.
         * @return presenter
         */
        @Override
        public PresenterImpl createPresenter() {
            return new ListPresenterImpl();
        }
    }
}
