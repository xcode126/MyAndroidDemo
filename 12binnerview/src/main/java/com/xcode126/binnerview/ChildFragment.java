package com.xcode126.binnerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcode126.binnerview.model.ApartmentBean;
import com.xcode126.binnerview.utils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by sky on 2017/3/1.
 */

public class ChildFragment extends Fragment {

    private ArrayList<ApartmentBean> listdata;
    private int apartmentIndex;

    private ImageView imageView;
    private TextView apartmentTitle;
    private TextView apartmentArea;
    private TextView apartmentPrice;
    private TextView aprtmentHousesstyle1;
    private TextView aprtmentHousesstyle2;
    private TextView aprtmentHousesstyle3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        Bundle bundle = getArguments();
        listdata = (ArrayList<ApartmentBean>) bundle.getSerializable("apartment_list");
        apartmentIndex = bundle.getInt("apartment_index");
        initViews(view);
        setContentMethod(apartmentIndex);
        return view;
    }

    private void initViews(View view){
        imageView = (ImageView) view.findViewById(R.id.frag_first_image);
        apartmentTitle = (TextView) view.findViewById(R.id.frag_first_apartment);
        apartmentArea = (TextView) view.findViewById(R.id.frag_first_area);
        apartmentPrice = (TextView) view.findViewById(R.id.frag_first_saleprice);
        aprtmentHousesstyle1 = (TextView) view.findViewById(R.id.frag_first_housestyle1);
        aprtmentHousesstyle2 = (TextView) view.findViewById(R.id.frag_first_housestyle2);
        aprtmentHousesstyle3 = (TextView) view.findViewById(R.id.frag_first_housestyle3);
    }

    private void setContentMethod(int apartmentIndex) {
//		imageView;

//        Picasso.with(getActivity()).load(listdata.get(apartmentIndex).getImageUrl()).placeholder(R.drawable.header_houses_default).into(imageView);;
        BitmapUtils.getInstance().loadImage(getActivity(),imageView, listdata.get(apartmentIndex).getImageUrl(), R.mipmap.header_houses_default, R.mipmap.header_houses_default);
        apartmentTitle.setText("户型："+listdata.get(apartmentIndex).getApartmentTitle());
        apartmentArea.setText("面积："+listdata.get(apartmentIndex).getApartmentArea());
        apartmentPrice.setText("售价："+listdata.get(apartmentIndex).getApartmentPrice());
        aprtmentHousesstyle1.setText(listdata.get(apartmentIndex).getApartmentFeaturedOne());
        aprtmentHousesstyle2.setText(listdata.get(apartmentIndex).getApartmentFeaturedTwo());
        aprtmentHousesstyle3.setText(listdata.get(apartmentIndex).getApartmentFeaturedThree());
    }
}
