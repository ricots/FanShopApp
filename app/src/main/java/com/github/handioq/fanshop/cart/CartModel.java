package com.github.handioq.fanshop.cart;

import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.List;

public interface CartModel {

    void gerCartItems(int userid);

    void setCallback(Callback callback);

    interface Callback {

        void onProductsLoaded(List<ProductDTO> productDTOs);

        void onProductsLoadError(Throwable error);

        void onCompleted();
    }

}
