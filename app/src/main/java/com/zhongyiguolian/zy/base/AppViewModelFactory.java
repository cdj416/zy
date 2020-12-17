package com.zhongyiguolian.zy.base;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.zhongyiguolian.zy.data.Injection;
import com.zhongyiguolian.zy.data.MyRepository;

import java.lang.reflect.Constructor;

/**
 * @author cdj
 * @date 2020/12/10
 */
public class AppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")

    /**
     * 烦
     */
    private static volatile AppViewModelFactory INSTANCE;
    private final Application mApplication;
    private final MyRepository mRepository;

    public static AppViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (AppViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppViewModelFactory(application, Injection.provideDemoRepository());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private AppViewModelFactory(Application application, MyRepository repository) {
        this.mApplication = application;
        this.mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        try {
            //获取构造函数类的对象
            Constructor constroctor = modelClass.getConstructor(Application.class,MyRepository.class);
            return (T) constroctor.newInstance(mApplication, mRepository);
        }catch (Exception e){
            e.printStackTrace();
        }

        /*if (modelClass.isAssignableFrom(FragmentHomeViewModel.class)) {
            return (T) new FragmentHomeViewModel(mApplication, mRepository);
        }else if(modelClass.isAssignableFrom(LoginPassWordViewModel.class)){
            return (T) new LoginPassWordViewModel(mApplication, mRepository);
        }*/
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
