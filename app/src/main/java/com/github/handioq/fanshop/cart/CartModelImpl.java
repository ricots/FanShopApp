package com.github.handioq.fanshop.cart;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import rx.Observer;

public class CartModelImpl implements CartModel {

    private NetworkService networkService;
    private CartModel.Callback callback;

    private final static String TAG = "CartModelImpl";

    public CartModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void gerCartItems(int userId) {

        networkService.getApiService()
                .getCart(userId)
                .compose(NetworkService.<List<ProductDTO>>applyScheduler())
                .subscribe(new Observer<List<ProductDTO>>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onProductsLoadError(e);
                    }

                    @Override
                    public void onNext(List<ProductDTO> productDTOs) {
                        callback.onProductsLoaded(productDTOs);
                    }
                });

        Log.i(TAG, "getProductDTOs()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
