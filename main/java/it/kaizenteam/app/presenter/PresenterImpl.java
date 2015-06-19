/*
* Name: PresenterImpl.java
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
* v0.03 2015-05-30  Bigarella Chiara Edit
* =================================================================
* v0.02 2015-05-26  Dal Bianco Davide   Verify
* =================================================================
* v0.01 2015-05-25  Moretto Alessandro  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;

import java.util.HashMap;
import java.util.Map;

import it.kaizenteam.app.view.View;

/**
 * This class represents a generic presenter and therefore is an abstract class. It contains inside the reference to the view in which it is associated.
 * It contains also the interface PresenterFactory and a hashmap that deals with the correspondence between the types of presenters and their factory classes.
 * The class contains constants static serving to identify the various types of presenter.
 */
public class PresenterImpl implements Presenter{
    /**
     * Hashmap that deals with the correspondence between the types of presenters and their factory classes
     */
    private static Map<ChartType,PresenterFactory> factories=new HashMap<>();

    public enum ChartType {LOGIN_TYPE,LIST_TYPE,BARCHART_TYPE,MAPCHART_TYPE,LINECHART_TYPE,TABLE_TYPE}

    /**
     * This attribute is a reference to the view on the presenter.
     */
    protected View view;


    static {
        //carico le classi per formare gli hashmap statici
        String[] classi=new String[]{
                "it.kaizenteam.app.presenter.MapChartPresenterImpl",
                "it.kaizenteam.app.presenter.BarChartPresenterImpl",
                "it.kaizenteam.app.presenter.LineChartPresenterImpl",
                "it.kaizenteam.app.presenter.TablePresenterImpl",
                "it.kaizenteam.app.presenter.ListPresenterImpl",
                "it.kaizenteam.app.presenter.LoginPresenterImpl",
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
     * This method is used to record a certain factory to its presenter nell'hashmap class.
     * @param presenterType
     * @param factory
     */
    protected static void registerFactory(ChartType presenterType, PresenterFactory factory){
        factories.put(presenterType,factory);
    }

    /**
     * This method creates the presenter of the parameter presenterType, initializes the view field with the parameter view and finally returns the interface Presenter of the instance created.
     * @param presenterType
     * @param view
     * @return the interface Presenter of the instance created
     */
    public static Presenter create(ChartType presenterType, View view){
        PresenterImpl ret = factories.get(presenterType).createPresenter();
        ret.view=view;
        return ret;
    }

    /**
     * This method is the constructor of the class. It is protected to disallow direct creation of an instance of that class by classes not authorized.
     */
    protected PresenterImpl(){}

    /**
     * PresenterFactory is the interface of the factory classes that deal creation of various types of presenter. It is internal to the class PresenterImpl.
     */
    protected interface PresenterFactory{
        PresenterImpl createPresenter();
    }
}
