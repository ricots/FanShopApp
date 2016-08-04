package com.github.handioq.fanshop.application;

import android.app.Application;
import android.util.Log;

import com.github.handioq.BuildConfig;
import com.github.handioq.fanshop.di.component.CatalogComponent;
import com.github.handioq.fanshop.di.component.DaggerCatalogComponent;
import com.github.handioq.fanshop.di.component.DaggerDatabaseComponent;
import com.github.handioq.fanshop.di.component.DaggerLoginComponent;
import com.github.handioq.fanshop.di.component.DaggerNetComponent;
import com.github.handioq.fanshop.di.component.DaggerProductInfoComponent;
import com.github.handioq.fanshop.di.component.DaggerSignupComponent;
import com.github.handioq.fanshop.di.component.DatabaseComponent;
import com.github.handioq.fanshop.di.component.LoginComponent;
import com.github.handioq.fanshop.di.component.NetComponent;
import com.github.handioq.fanshop.di.component.ProductInfoComponent;
import com.github.handioq.fanshop.di.component.SignupComponent;
import com.github.handioq.fanshop.di.module.AppModule;
import com.github.handioq.fanshop.di.module.CatalogModule;
import com.github.handioq.fanshop.di.module.DatabaseModule;
import com.github.handioq.fanshop.di.module.LoginModule;
import com.github.handioq.fanshop.di.module.NetModule;
import com.github.handioq.fanshop.di.module.ProductInfoModule;
import com.github.handioq.fanshop.di.module.SignupModule;

public class FanShopApp extends Application {

    private NetComponent netComponent;
    private DatabaseComponent databaseComponent;
    private CatalogComponent catalogComponent;
    private LoginComponent loginComponent;
    private SignupComponent signupComponent;
    private ProductInfoComponent productInfoComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

        databaseComponent = DaggerDatabaseComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule(this))
                .build();

        catalogComponent = DaggerCatalogComponent.builder()
                .netComponent(netComponent)
                .catalogModule(new CatalogModule())
                .build();

        loginComponent = DaggerLoginComponent.builder()
                .netComponent(netComponent)
                .loginModule(new LoginModule())
                .build();

        signupComponent = DaggerSignupComponent.builder()
                .netComponent(netComponent)
                .signupModule(new SignupModule())
                .build();

        productInfoComponent = DaggerProductInfoComponent.builder()
                .netComponent(netComponent)
                .productInfoModule(new ProductInfoModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public DatabaseComponent getDatabaseComponent() {
        return databaseComponent;
    }

    public CatalogComponent getCatalogComponent() {
        return catalogComponent;
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public SignupComponent getSignupComponent() {
        return signupComponent;
    }

    public ProductInfoComponent getProductInfoComponent() {
        return productInfoComponent;
    }

    public void setLoginComponent(LoginComponent loginComponent) {
        this.loginComponent = loginComponent;
    }

    /*private static FanShopApp instance;
    private NetworkService networkService;
    private Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        onAppCreated();
        checkBuild();
    }

    public static FanShopApp getInstance() {
        return instance;
    }

    private void onAppCreated() {
        networkService = new NetworkService();

        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
    }

    private void checkBuild() {
        if (BuildConfig.DEBUG) {
            Log.d("BUILD", BuildConfig.BUILD_TYPE);
        }
    }

    public NetworkService getNetworkService() {
        return networkService;
    }

    public Realm getRealm() {
        return realm;
    }*/
}