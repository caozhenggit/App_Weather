package com.caozheng.xfastmvp.mvp;

import android.os.Bundle;
import android.view.View;

/**
 * @author caozheng
 * @date 2017/10/28
 *
 * describe:
 */
public abstract class AppFragment<P extends BasePresenter> extends BaseFragment {
    protected P mPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
