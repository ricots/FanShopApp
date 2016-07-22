package com.github.handioq.fanshop.catalog;

import android.util.Log;
import android.view.View;

import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

public class CatalogPresenterImpl implements CatalogPresenter, CatalogModel.Callback {

    private CatalogView catalogView;
    private CatalogModel catalogModel;

    private NetworkService networkService;

    private final static String TAG = "CatalogPresenterImpl";

    public CatalogPresenterImpl(CatalogView catalogView, NetworkService networkService) {
        this.catalogView = catalogView;
        this.networkService = networkService;

        catalogModel = new CatalogModelImpl(networkService);
        catalogModel.setCallback(this);
    }

    /*
    public void setCatalogView(CatalogView catalogView) {
        this.catalogView = catalogView;
        catalogModel.setCallback(this);
    }
    */

    @Override
    public void getProducts() {
        if (catalogView != null) {
            catalogView.showProgress();
            Log.e(TAG, "showProgress() on catalogView");
        }

        catalogModel.getProducts(0, 20);
    }

    @Override
    public void onItemClicked(View view, int position) {
        catalogView.onItemClicked(view, position);
    }

    @Override
    public void onProductLoaded(List<Product> products) {
        // TODO add to database
        catalogView.setProducts(products);
        catalogView.hideProgress();
        Log.e(TAG, "get products: " + products.size());
    }

    @Override
    public void onProductsLoadError(Throwable error) {
        catalogView.onError(error);
        catalogView.hideProgress();
        Log.e(TAG, "onError");
    }
}
