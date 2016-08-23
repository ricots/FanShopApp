package com.github.handioq.fanshop.account;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.model.dvo.OrderDVO;

import java.util.List;

public interface OrderMvp {

    interface Model extends Mvp.Model {

        void gerOrders(int userId);

        void setCallback(Callback callback);

        interface Callback {

            void onOrdersLoaded(List<OrderDVO> orders);

            void onOrdersLoadError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setOrders(List<OrderDVO> orders);

        void onError(Throwable e);

        //void onItemClicked(View view, int position);

    }

    interface Presenter extends Mvp.Presenter<OrderMvp.View> {

        void getOrders(int userId);

    }
}
