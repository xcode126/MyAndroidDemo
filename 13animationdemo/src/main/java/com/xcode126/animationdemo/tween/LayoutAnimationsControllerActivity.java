package com.xcode126.animationdemo.tween;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xcode126.animationdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * LayoutAnimationsController可以用于实现使多个控件按顺序一个一个的显示。
 * 1)LayoutAnimationsController用于为一个layout里面的控件，或者是一个ViewGroup里面的控件设置统一的动画效果。
 * 2)每一个控件都有相同的动画效果。
 * 3)控件的动画效果可以在不同的时间显示出来。
 * 4)LayoutAnimationsController可以在xml文件当中设置，以可以在代码当中进行设置。
 */
public class LayoutAnimationsControllerActivity extends ListActivity {
    private Button button = null;
    private ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animations_controller);
        listView = getListView();
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ButtonListener());
    }

    private ListAdapter createListAdapter() {
        List<HashMap<String, String>> list =
                new ArrayList<HashMap<String, String>>();
        HashMap<String, String> m1 = new HashMap<String, String>();
        m1.put("name", "bauble");
        m1.put("sex", "male");
        HashMap<String, String> m2 = new HashMap<String, String>();
        m2.put("name", "Allorry");
        m2.put("sex", "male");
        HashMap<String, String> m3 = new HashMap<String, String>();
        m3.put("name", "Allotory");
        m3.put("sex", "male");
        HashMap<String, String> m4 = new HashMap<String, String>();
        m4.put("name", "boolbe");
        m4.put("sex", "male");
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this, list, R.layout.item, new String[]{"name", "sex"},
                new int[]{R.id.name, R.id.sex});
        return simpleAdapter;
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            listView.setAdapter(createListAdapter());
        }
    }
}
