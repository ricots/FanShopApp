package com.github.handioq.fanshop.catalog;

import com.github.handioq.fanshop.model.Product;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class CatalogPresenterImpl implements CatalogPresenter {

    private CatalogView catalogView;
    private NetworkService networkService;
    private Subscription subscription;
    private CatalogModel catalogModel;

    public CatalogPresenterImpl(CatalogView catalogView, NetworkService networkService) {
        this.catalogView = catalogView;
        this.networkService = networkService;
    }

    @Override
    public void getProducts() {
        if (catalogView != null) {
            catalogView.showProgress();
        }

        catalogModel = new CatalogModelImpl(networkService);

        subscription = catalogModel.getProducts()
                .subscribe(new Observer<List<Product>>() {

                    @Override
                    public void onCompleted() {
                        //loginView.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        catalogView.onError(e);
                    }

                    @Override
                    public void onNext(List<Product> products) { // TODO: check for wrong data or
                        catalogView.setItems(products);
                    }
                });
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDestroy() {

    }
}
