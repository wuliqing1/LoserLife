package com.example.y.mvp.mvp.presenter;


import com.example.y.mvp.R;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.utils.UIUtils;

/**
 * by 12406 on 2016/5/1.
 */
public class MainViewPresenterImpl extends BasePresenterImpl<BaseView.MainView>
        implements BasePresenter.MainViewPresenter {

    @Override
    public void switchId(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                getView().switchNews();
                break;
            case R.id.navigation_item_image_classification:
                getView().switchImageClassification();
                break;
//            case R.id.navigation_item_new_image:
//                getView().switchNewImage();
//                break;
            case R.id.navigation_item_joke:
                getView().switchJoke();
                break;
//            case R.id.navigation_about:
//                getView().switchAbout();
//                break;
            case R.id.navigation_item_video:
                getView().switchVideo();
                break;
//            case R.id.navigation_theme:
//                getView().switchTheme();
//                break;
        }
    }

    @Override
    public void switchTitle(int id) {
        int title_res_id = R.string.navigation_news;
        switch (id) {
            case R.id.navigation_item_news:
                title_res_id = R.string.navigation_news;
                break;
            case R.id.navigation_item_image_classification:
                title_res_id = R.string.navigation_image_classification;
                break;
//            case R.id.navigation_item_new_image:
//                break;
            case R.id.navigation_item_joke:
                title_res_id = R.string.navigation_joke;
                break;
//            case R.id.navigation_about:
//                break;
            case R.id.navigation_item_video:
                title_res_id = R.string.navigation_video;
                break;
        }
        getView().switchTitle(UIUtils.getString(title_res_id));
    }
}
