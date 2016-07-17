package com.github.handioq.fanshop.login;

import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class LoginPresenterImpl implements LoginPresenter {

    private NetworkService networkService;
    private Subscription subscription;
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView, NetworkService networkService) {
        this.loginView = loginView;
        this.networkService = networkService;
    }

    @Override
    public void loginValidate(String username, String password) {

        if (loginView != null) {
            loginView.showProgress();
        }

        loginModel = new LoginModelImpl(networkService);

        subscription = loginModel.getAuthState(username, password)
                .subscribe(new Observer<UserAuthState>() {

                    @Override
                    public void onCompleted() {
                        loginView.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginView.loginFailure(e);
                        loginView.hideProgress();
                    }

                    @Override
                    public void onNext(UserAuthState userAuthState) { // TODO: check for wrong data or
                        loginView.loginSuccess(userAuthState);
                        loginView.hideProgress();
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}