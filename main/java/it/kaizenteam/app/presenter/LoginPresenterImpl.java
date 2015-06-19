/*
* Name: LoginPresenterImpl.java
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
* v0.04 2015-06-02  Rubin Marco  Verify
* =================================================================
* v0.03 2015-05-30  Bucco Riccardo Edit
* =================================================================
* v0.02 2015-05-26  Moretto Alessandro   Verify
* =================================================================
* v0.01 2015-05-23  Dal Bianco Davide  Creation
* =================================================================
*
*/

package it.kaizenteam.app.presenter;


import it.kaizenteam.app.view.LoginView;

/**
 * This class represents the specialization of PresenterImpl.
 * It has the aims to change the view of the home. Its task is to ask HttpRequesterWithCookie the access to an instance of Norris through login.
 */
public class LoginPresenterImpl extends PresenterImpl implements LoginPresenter{
    static {
        //registro il tipo di grafico (DI)
        registerFactory(ChartType.LOGIN_TYPE, LoginPresenterFactory.getInstance());
    }

    /**
     * This method handles the gesture of a user click on the button in the login view. It will attempt to login by placing the signal waiting on view and if this has success shows the view with the list of charts otherwise appears on view an error message.
     * @param addressNorris
     * @param login
     * @param password
     */
    @Override
    public void onLoginClick(final String addressNorris, final String login, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ((LoginView)view).showProgress(true);
                try {
                    HttpRequesterWithCookie.getInstance().Login(addressNorris,login,password);
                } catch (Exception e) {
                    ((LoginView) view).showAuthenticationError(e.getMessage());
                    ((LoginView) view).showProgress(false);
                    return;
                }
                ((LoginView) view).showProgress(false);
                ((LoginView) view).showListView();
            }
        }).start();
    }

    /**
     * This method is the constructor. It is private because it can not be created an instance except from a request of his inner class factory.
     */
    private LoginPresenterImpl(){}

    /**
     *  This class deals with the creation of a LoginPresenterImpl presenter.
     */
    protected static class LoginPresenterFactory implements PresenterImpl.PresenterFactory {

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
            return new LoginPresenterFactory();
        }

        /**
         * This method is the constructor of the class. It is private because only getInstance() method can create an instance.
         */
        private LoginPresenterFactory(){
            instance=this;
        }

        /**
         * This method has the task of creating the relative presenter. It can access its constructor because this factory class is inner to the related presenter class.
         * @return presenter
         */
        @Override
        public PresenterImpl createPresenter() {
            return new LoginPresenterImpl();
        }
    }

}
