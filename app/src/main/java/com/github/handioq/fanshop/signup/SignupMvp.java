package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.AuthDTO;
import com.github.handioq.fanshop.net.Response;

public interface SignupMvp {

    interface Model extends Mvp.Model {

        void getSignupState(AuthDTO authDTO);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(Response response);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void signupSuccess(Response response);

        void signupFailure(Throwable e);

        void showProgress();

        void hideProgress();

        void onCompleted();

    }

    interface Presenter extends Mvp.Presenter<SignupMvp.View> {

        void signupValidate(AuthDTO authDTO); // what else? whatever...

    }
}
