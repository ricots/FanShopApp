package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class SignupModel implements SignupMvp.Model {

    private NetworkService networkService;
    private SignupModel.Callback callback;

    public SignupModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getSignupState(UserDTO userDTO) {

        networkService.getApiService()
                .signup(userDTO)
                .compose(NetworkService.<UserDTO>applyScheduler())
                .subscribe(new Subscriber<UserDTO>() {

                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                       callback.onError(e);
                    }

                    @Override
                    public void onNext(UserDTO userDTO) { // TODO: check for wrong data or
                       callback.onSuccess(userDTO);
                    }
                });

    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
