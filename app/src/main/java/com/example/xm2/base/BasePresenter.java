package com.example.xm2.base;






import com.example.xm2.interfaces.IBasePresenter;
import com.example.xm2.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    //为什么这是泛型而不是IBaseView接口
    protected V mView; //p层所关联的v层
    //弱引用V层 解决Activity或者Fragment使用mvp的内存泄漏问题
    WeakReference<V> weakReference;
    //rxjava2 背压式网络请求处理
    CompositeDisposable compositeDisposable;
    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }

    /**
     * 把请求网络的数据对象加入到排列中
     * @param disposable
     */
    public void addSubscribe(Disposable disposable){
        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void chearSubscribe(){
        if (compositeDisposable != null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
