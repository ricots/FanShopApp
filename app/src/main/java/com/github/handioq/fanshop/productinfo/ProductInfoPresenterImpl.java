package com.github.handioq.fanshop.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class ProductInfoPresenterImpl implements ProductInfoPresenter, ProductInfoModel.Callback {

    private ProductInfoView productInfoView;
    private ProductInfoModel productInfoModel;

    private NetworkService networkService;

    private final static String TAG = "ProductInfoPresenterImp";

    @Inject
    public ProductInfoPresenterImpl(NetworkService networkService) {

        productInfoModel = new ProductInfoModelImpl(networkService);
        productInfoModel.setCallback(this);
    }

    @Override
    public void setView(ProductInfoView productInfoView) {
        this.productInfoView = productInfoView;
    }

    @Override
    public void getProduct(int id) {
        if (productInfoView != null) {
            productInfoView.showProgress();
            Log.i(TAG, "showProgress() on productInfoView");
        }

        productInfoModel.getProduct(id);
    }

    @Override
    public void onProductLoaded(ProductDTO productDTO) {
        productInfoView.setProduct(productDTO);
        productInfoView.hideProgress();
        Log.i(TAG, "get productDTO: " + productDTO.getName() + " " + productDTO.getId());
    }

    @Override
    public void onProductLoadError(Throwable error) {
        productInfoView.onError(error);
        productInfoView.hideProgress();
        Log.i(TAG, "onError");
        error.printStackTrace();
    }
}
