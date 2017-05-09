package com.xcode126.searchlistview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xcode126.searchlistview.bean.CityBean;
import com.xcode126.searchlistview.bean.CountyBean;
import com.xcode126.searchlistview.bean.ProvinceBean;
import com.xcode126.searchlistview.utils.CharacterParser;
import com.xcode126.searchlistview.view.SideBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 主界面
 */
public class MainActivity extends Activity {
    private EditText searchEt;
    private ListView listView;
    private ProgressDialog pd;
    private SideBar sideBar;
    private TextView hintTv;
    private CityListAdapter adapter;
    private List<CityBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();
    }

    private void initView() {
        hintTv = (TextView) findViewById(R.id.centerHintTv);
        searchEt = (EditText) findViewById(R.id.searchEt);
        listView = (ListView) findViewById(R.id.listview);
        sideBar = (SideBar) findViewById(R.id.sidebar);
        pd = new ProgressDialog(this);
        pd.setTitle("提示");
        pd.setMessage("正在解析数据，请稍等...");
        pd.setCancelable(false);
    }

    private void initEvent() {
        new ParseXmlTask().execute();
        searchEt.addTextChangedListener(new SearchTextChangedListener());
        //监听SideBar的手指按下和抬起事件
        sideBar.setOnSelectListener(new SideBarOnSelectListener());
    }

    /**
     * 开启异步任务解析数据
     */
    private class ParseXmlTask extends AsyncTask<Void, Void, Void> {

        private XmlPullParser pullParser;
        private List<ProvinceBean> provinceList;
        private ProvinceBean province;
        private CityBean city;
        private CountyBean county;

        public ParseXmlTask() {
            provinceList = new ArrayList<>();
        }

        /**
         * 这里是最终用户调用Excute时的接口，当任务执行之前开始调用此方法，可以在这里显示进度对话框。
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //开始解析前，新建解析器
            pullParser = Xml.newPullParser();
            //从raw目录下获取呆解析的数据的输入流
            InputStream is = getResources().openRawResource(R.raw.cities);
            try {
                pullParser.setInput(is, "UTF-8");
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            //显示进度对话框
            pd.show();
        }

        /**
         * 后台执行，比较耗时的操作都可以放在这里。
         * 注意这里不能直接操作UI。此方法在后台线程执行，完成任务的主要工作，
         * 通常需要较长的时间。在执行过程中可以调用publicProgress(Progress…)来更新任务的进度。
         * AsyncTask定义了三种泛型类型 Params，Progress和Result。
         * Params 启动任务执行的输入参数，比如HTTP请求的URL。
         * Progress 后台任务执行的百分比。
         * Result 后台执行任务最终返回的结果，比如String。
         */
        @Override
        protected Void doInBackground(Void... params) {
            int eventType = 1;
            try {
                eventType = pullParser.getEventType();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            //while 循环解析xml数据
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = pullParser.getName();
                        if ("province".equals(startTag)) {//省
                            province = new ProvinceBean();
                            province.setId(pullParser.getAttributeValue(null, "id"));
                            province.setName(pullParser.getAttributeValue(null, "name"));
                        } else if ("city".equals(startTag)) {//市
                            city = new CityBean();
                            city.setId(pullParser.getAttributeValue(null, "id"));
                            city.setName(pullParser.getAttributeValue(null, "name"));
                            String name = city.getName();
                            if (!TextUtils.isEmpty(name) && name.length() > 0) {
                                //获取城市名的首字母（这里获取的是小写）
                                String pinyin = CharacterParser.getInstance().getPinYinSpelling(name.substring(0, 1));
                                //-32获取小写首字母对应的大写字母
                                city.setFirstLetter((char) (pinyin.charAt(0) - 32));
                            }
                        } else if ("county".equals(startTag)) {
                            county = new CountyBean();
                            county.setId(pullParser.getAttributeValue(null, "id"));
                            county.setName(pullParser.getAttributeValue(null, "name"));
                            county.setWeatherCode(pullParser.getAttributeValue(null, "weatherCode"));
                            if (city != null) {
                                city.getCountyList().add(county);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = pullParser.getName();
                        if ("city".equals(endTag) && province != null) {
                            province.getCityList().add(city);
                        } else if ("province".equals(endTag) && province != null) {
                            provinceList.add(province);
                        }
                        break;
                }
                try {
                    eventType = pullParser.next();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (provinceList != null) {
                //for循环获取所有的城市名
                for (ProvinceBean province : provinceList) {
                    List<CityBean> cityList = province.getCityList();
                    for (CityBean city : cityList) {
                        list.add(city);
                    }
                }
                Collections.sort(list, comparator);
            }
            return null;
        }

        /**
         * 相当于Handler 处理UI的方式，在这里面可以使用在doInBackground
         * 得到的结果处理操作UI。 此方法在主线程执行，任务执行的结果作为此方法的参数返回
         *
         * @param result
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pd.dismiss();
            showListView(list);
        }

        /**
         * 可以使用进度条增加用户体验度。 此方法在主线程执行，用于显示任务执行的进度。
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 用户调用取消时，要做的操作
         */
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }
    }

    /**
     * 比较拼音的迭代器
     */
    private Comparator<CityBean> comparator = new Comparator<CityBean>() {

        @Override
        public int compare(CityBean arg0, CityBean arg1) {
            //获取城市名对应的拼音，通过比较拼音来确定城市的先后次序
            String pinyin0 = CharacterParser.getInstance().getPinYinSpelling(arg0.getName());
            String pinyin1 = CharacterParser.getInstance().getPinYinSpelling(arg1.getName());
            return pinyin0.compareTo(pinyin1);
        }
    };

    /**
     * 输入框监听
     */
    private class SearchTextChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (adapter != null) {
                int len = s.length();
                if (len == 0) {
                    adapter.resetData();
                } else if (len > 0) {
                    adapter.queryData(s.toString());
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /**
     * SideBar的手指按下和抬起监听
     */
    private class SideBarOnSelectListener implements SideBar.OnSelectListener {

        @Override
        public void onSelect(String s) {
            //手指按下时显示中央的字母
            hintTv.setVisibility(View.VISIBLE);
            hintTv.setText(s);
            //如果SideBar按下的是#号，则ListView定位到位置0
            if ("#".equals(s)) {
                listView.setSelection(0);
                return;
            }
            //获取手指按下的字母所在的块索引
            int section = s.toCharArray()[0];
            //根据块索引获取该字母首次在ListView中出现的位置
            int pos = adapter.getPositionForSection(section - 65);
            //定位ListView到按下字母首次出现的位置
            listView.setSelection(pos);
        }

        @Override
        public void onMoveUp(String s) {
            hintTv.setVisibility(View.GONE);
            hintTv.setText(s);
        }
    }

    /**
     * 显示城市列表
     *
     * @param list
     */
    private void showListView(final List<CityBean> list) {
        adapter = new CityListAdapter(this, list, R.layout.list_item);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //点击ListView的某个item后显示当前选择的城市名
                String name = adapter.getItem(arg2).getName();
                Toast toast = Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }
}
