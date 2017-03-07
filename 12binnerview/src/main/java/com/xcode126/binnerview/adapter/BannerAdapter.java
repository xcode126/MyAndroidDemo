package com.xcode126.binnerview.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.xcode126.binnerview.model.AdvertisModel;
import com.xcode126.binnerview.R;
import com.xcode126.binnerview.utils.BitmapUtils;

import java.util.List;

/**
 * Created by sky on 2017/2/28.
 * 广告Adapter
 */

public class BannerAdapter extends LoopPagerAdapter {
    private List<AdvertisModel> bannerArray;

    public BannerAdapter(RollPagerView viewPager, List<AdvertisModel> bannerArray) {
        super(viewPager);
        this.bannerArray = bannerArray;
    }

    public void setBannerArray(List<AdvertisModel> bannerArray) {
        this.bannerArray = bannerArray;
        notifyDataSetChanged();
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //设置数据
        AdvertisModel advertisModel = bannerArray.get(position);
        BitmapUtils.getInstance().loadImage(container.getContext(), view, advertisModel.getAd_url(), R.mipmap.car_models_select_b, R.mipmap.car_models_select_b);
        return view;
    }

    @Override
    public int getRealCount() {
        return bannerArray == null ? 0 : bannerArray.size();
    }
}
