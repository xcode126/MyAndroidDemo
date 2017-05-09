package com.xcode126.sidelistview;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends Activity implements NavView.OnTouchingLetterChangedListener {

    private Window window;
    private OverlayThread overlayThread = new OverlayThread();

    private ExpandableListView expandableListView;
    private NavView navView;
    private ExpandableListAdapter adapter;
    private List<TownBean> list = new ArrayList<TownBean>();
    ;
    private TextView tv;

    private Thread thread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x10:
                    Toast.makeText(MainActivity.this, "开始解析数据", Toast.LENGTH_SHORT).show();
                    hadleData();
                    break;
                case 0x20:
                    Toast.makeText(MainActivity.this, "解析数据成功", Toast.LENGTH_SHORT).show();
                    adapter = new ExpandableListAdapter(MainActivity.this, list);
                    expandableListView.setAdapter(adapter);
                    for (int i = 0; i < list.size(); i++) {
                        expandableListView.expandGroup(i);
                    }
                    break;
                case 0x30:
                    Toast.makeText(MainActivity.this, "解析数据失败", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    private void hadleData() {
        if (thread == null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String result = getFromAssets("myData.txt");
                    getJsonData(result);
                }
            });
            thread.start();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        intView();
    }

    private void intView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expanda_listview_source);
        navView = (NavView) findViewById(R.id.nv_fragment_source);
        tv = (TextView) findViewById(R.id.tv_souce2);
        navView.setOnTouchingLetterChangedListener(this);
//        list = new ArrayList<TownBean>();
        hadleData();
//        adapter = new ExpandableListAdapter(MainActivity.this, list);
//        expandableListView.setAdapter(adapter);
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "被点击", 0);
//                mHandler.sendEmptyMessage(0x10);
//            }
//        });
    }


    private String getFromAssets(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = getResources().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private void getJsonData(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TownBean townBean = new TownBean();
                townBean.setTownName(jsonObject.getString("townName"));
                JSONArray array = jsonObject.getJSONArray("list");
                List<FactoryBean> factoryBeanList = new ArrayList<>();
                for (int j = 0; j < array.length(); j++) {
                    JSONObject object = array.getJSONObject(j);
                    FactoryBean factoryBean = new FactoryBean();
                    factoryBean.setType(object.getString("type"));
                    factoryBean.setFactoryName(object.getString("factoryName"));
                    factoryBeanList.add(factoryBean);
                }
                townBean.setList(factoryBeanList);
                list.add(townBean);
            }
            mHandler.sendEmptyMessage(0x20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onTouchingLetterChanged(String s) {
        // TODO Auto-generated method stub
        if (alphaIndexer(s) > 0) {
            int position = alphaIndexer(s);
            expandableListView.setSelection(position);
            tv.setText(s);
            tv.setVisibility(View.VISIBLE);
            handler.removeCallbacks(overlayThread);
            handler.postDelayed(overlayThread, 1500);
        }
    }

    public static int alphaIndexer(String s) {
        int i = 0;
        for (; i < sCheeseStrings.length; i++) {
            if (sCheeseStrings[i].startsWith(s)) {
                break;
            }
        }
        return i;
    }

    private Handler handler = new Handler() {
    };

    private class OverlayThread implements Runnable {

        public void run() {
            tv.setVisibility(View.GONE);
        }

    }

    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }

        if (str.trim().length() == 0) {
            return "#";
        }

        char c = str.trim().substring(0, 1).charAt(0);
        Pattern pattern = Pattern.compile("^[A-Za-z]+{1}");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else {
            return "#";
        }
    }

    public static final String[] sCheeseStrings = {"Abbaye de Belloc",
            "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
            "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
            "Alverca", "Ambert", "American Cheese", "Ami du Chambertin",
            "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
            "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String",
            "Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees",
            "Autun", "Avaxtskyr", "Baby Swiss", "Babybel",
            "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal",
            "Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese",
            "Bath Cheese", "Bavarian Bergkase", "Baylough", "Beaufort",
            "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
            "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir",
            "Bierkase", "Bishop Kennedy", "Blarney", "Bleu d'Auvergne",
            "Bleu de Gex", "Bleu de Laqueuille", "Bleu de Septmoncel",
            "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
            "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini",
            "Bocconcini (Australian)", "Boeren Leidenkaas", "Bonchester",
            "Bosworth", "Bougon", "Boule Du Roves", "Boulette d'Avesnes",
            "Boursault", "Boursin", "Bouyssou", "Bra", "Braudostur",
            "Breakfast Cheese", "Brebis du Lavort", "Brebis du Lochois",
            "Brebis du Puyfaucon", "Bresse Bleu", "Brick", "Brie",
            "Brie de Meaux", "Brie de Melun", "Brillat-Savarin", "Brin",
            "Brin d' Amour", "Brin d'Amour", "Brinza (Burduf Brinza)",
            "Briquette de Brebis", "Briquette du Forez", "Broccio",
            "Broccio Demi-Affine", "Brousse du Rove", "Bruder Basil",
            "Brusselae Kaas (Fromage de Bruxelles)", "Bryndza",
            "Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase",
            "Button (Innes)", "Buxton Blue", "Cabecou", "Caboc", "Cabrales",
            "Cachaille", "Caciocavallo", "Caciotta", "Caerphilly",
            "Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
            "Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux",
            "Capricorn Goat", "Capriole Banon", "Carre de l'Est",
            "Casciotta di Urbino", "Cashel Blue", "Castellano", "Castelleno",
            "Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
            "Celtic Promise", "Cendre d'Olivet", "Cerney", "Chabichou",
            "Chabichou du Poitou", "Chabis de Gatine", "Chaource", "Charolais",
            "Chaumes", "Cheddar", "Cheddar Clothbound", "Cheshire", "Chevres",
            "Chevrotin des Aravis", "Chontaleno", "Civray",
            "Coeur de Camembert au Calvados", "Coeur de Chevre", "Colby",
            "Cold Pack", "Comte", "Coolea", "Cooleney", "Coquetdale",
            "Corleggy", "Cornish Pepper", "Cotherstone", "Cotija",
            "Cottage Cheese", "Cottage Cheese (Australian)", "Cougar Gold",
            "Coulommiers", "Coverdale", "Crayeux de Roncq", "Cream Cheese",
            "Cream Havarti", "Crema Agria", "Crema Mexicana", "Creme Fraiche",
            "Crescenza", "Croghan", "Crottin de Chavignol",
            "Crottin du Chavignol", "Crowdie", "Crowley", "Cuajada", "Curd",
            "Cure Nantais", "Curworthy", "Cwmtawe Pecorino",
            "Cypress Grove Chevre", "Danablu (Danish Blue)", "Danbo",
            "Danish Fontina", "Daralagjazsky", "Dauphin", "Delice des Fiouves",
            "Denhany Dorset Drum", "Derby", "Dessertnyj Belyj", "Devon Blue",
            "Devon Garland", "Dolcelatte", "Doolin", "Doppelrhamstufel",
            "Dorset Blue Vinney", "Double Gloucester", "Double Worcester",
            "Dreux a la Feuille", "Dry Jack", "Duddleswell", "Dunbarra",
            "Dunlop", "Dunsyre Blue", "Duroblando", "Durrus",
            "Dutch Mimolette (Commissiekaas)", "Edam", "Edelpilz",
            "Emental Grand Cru", "Emlett", "Emmental", "Epoisses de Bourgogne",
            "Esbareich", "Esrom", "Etorki", "Evansdale Farmhouse Brie",
            "Evora De L'Alentejo", "Exmoor Blue", "Explorateur", "Feta",
            "Feta (Australian)", "Figue", "Filetta", "Fin-de-Siecle",
            "Finlandia Swiss", "Finn", "Fiore Sardo", "Fleur du Maquis",
            "Flor de Guia", "Flower Marie", "Folded",
            "Folded cheese with mint", "Fondant de Brebis", "Fontainebleau",
            "Fontal", "Fontina Val d'Aosta", "Formaggio di capra", "Fougerus",
            "Four Herb Gouda", "Fourme d' Ambert", "Fourme de Haute Loire",
            "Fourme de Montbrison", "Fresh Jack", "Fresh Mozzarella",
            "Fresh Ricotta", "Fresh Truffles", "Fribourgeois", "Friesekaas",
            "Friesian", "Friesla", "Frinault", "Fromage a Raclette",
            "Fromage Corse", "Fromage de Montagne de Savoie", "Fromage Frais",
            "Fruit Cream Cheese", "Frying Cheese", "Fynbo", "Gabriel",
            "Galette du Paludier", "Galette Lyonnaise",
            "Galloway Goat's Milk Gems", "Gammelost", "Gaperon a l'Ail",
            "Garrotxa", "Gastanberra", "Geitost", "Gippsland Blue", "Gjetost",
            "Gloucester", "Golden Cross", "Gorgonzola", "Gornyaltajski",
            "Gospel Green", "Gouda", "Goutu", "Gowrie", "Grabetto", "Graddost",
            "Grafton Village Cheddar", "Grana", "Grana Padano", "Grand Vatel",
            "Grataron d' Areches", "Gratte-Paille", "Graviera", "Greuilh",
            "Greve", "Gris de Lille", "Gruyere", "Gubbeen", "Guerbigny",
            "Halloumi", "Halloumy (Australian)", "Haloumi-Style Cheese",
            "Harbourne Blue", "Havarti", "Heidi Gruyere", "Hereford Hop",
            "Herrgardsost", "Herriot Farmhouse", "Herve", "Hipi Iti",
            "Hubbardston Blue Cow", "Hushallsost", "Iberico", "Idaho Goatster",
            "Idiazabal", "Il Boschetto al Tartufo", "Ile d'Yeu",
            "Isle of Mull", "Jarlsberg", "Jermi Tortes", "Jibneh Arabieh",
            "Jindi Brie", "Jubilee Blue", "Juustoleipa", "Kadchgall", "Kaseri",
            "Kashta", "Kefalotyri", "Kenafa", "Kernhem", "Kervella Affine",
            "Kikorangi", "King Island Cape Wickham Brie", "King River Gold",
            "Klosterkaese", "Knockalara", "Kugelkase", "L'Aveyronnais",
            "L'Ecir de l'Aubrac", "La Taupiniere", "La Vache Qui Rit",
            "Laguiole", "Lairobell", "Lajta", "Lanark Blue", "Lancashire",
            "Langres", "Lappi", "Laruns", "Lavistown", "Le Brin",
            "Le Fium Orbo", "Le Lacandou", "Le Roule", "Leafield", "Lebbene",
            "Leerdammer", "Leicester", "Leyden", "Limburger",
            "Lincolnshire Poacher", "Lingot Saint Bousquet d'Orb", "Liptauer",
            "Little Rydings", "Livarot", "Llanboidy", "Llanglofan Farmhouse",
            "Loch Arthur Farmhouse", "Loddiswell Avondale", "Longhorn",
            "Lou Palou", "Lou Pevre", "Lyonnais", "Maasdam", "Macconais",
            "Mahoe Aged Gouda", "Mahon", "Malvern", "Mamirolle", "Manchego",
            "Manouri", "Manur", "Marble Cheddar", "Marbled Cheeses",
            "Maredsous", "Margotin", "Maribo", "Maroilles", "Mascares",
            "Mascarpone", "Mascarpone (Australian)", "Mascarpone Torta",
            "Matocq", "Maytag Blue", "Meira", "Menallack Farmhouse",
            "Menonita", "Meredith Blue", "Mesost", "Metton (Cancoillotte)",
            "Meyer Vintage Gouda", "Mihalic Peynir", "Milleens", "Mimolette",
            "Mine-Gabhar", "Mini Baby Bells", "Mixte", "Molbo",
            "Monastery Cheeses", "Mondseer", "Mont D'or Lyonnais", "Montasio",
            "Monterey Jack", "Monterey Jack Dry", "Morbier",
            "Morbier Cru de Montagne", "Mothais a la Feuille", "Mozzarella",
            "Mozzarella (Australian)", "Mozzarella di Bufala",
            "Mozzarella Fresh, in water", "Mozzarella Rolls", "Munster",
            "Murol", "Mycella", "Myzithra", "Naboulsi", "Nantais",
            "Neufchatel", "Neufchatel (Australian)", "Niolo", "Nokkelost",
            "Northumberland", "Oaxaca", "Olde York", "Olivet au Foin",
            "Olivet Bleu", "Olivet Cendre", "Orkney Extra Mature Cheddar",
            "Orla", "Oschtjepka", "Ossau Fermier", "Ossau-Iraty", "Oszczypek",
            "Oxford Blue", "P'tit Berrichon", "Palet de Babligny", "Paneer",
            "Panela", "Pannerone", "Pant ys Gawn", "Parmesan (Parmigiano)",
            "Parmigiano Reggiano", "Pas de l'Escalette", "Passendale",
            "Pasteurized Processed", "Pate de Fromage", "Patefine Fort",
            "Pave d'Affinois", "Pave d'Auge", "Pave de Chirac",
            "Pave du Berry", "Pecorino", "Pecorino in Walnut Leaves",
            "Pecorino Romano", "Peekskill Pyramid", "Pelardon des Cevennes",
            "Pelardon des Corbieres", "Penamellera", "Penbryn", "Pencarreg",
            "Perail de Brebis", "Petit Morin", "Petit Pardou", "Petit-Suisse",
            "Picodon de Chevre", "Picos de Europa", "Piora",
            "Pithtviers au Foin", "Plateau de Herve", "Plymouth Cheese",
            "Podhalanski", "Poivre d'Ane", "Polkolbin", "Pont l'Eveque",
            "Port Nicholson", "Port-Salut", "Postel", "Pouligny-Saint-Pierre",
            "Pourly", "Prastost", "Pressato", "Prince-Jean",
            "Processed Cheddar", "Provolone", "Provolone (Australian)",
            "Pyengana Cheddar", "Pyramide", "Quark", "Quark (Australian)",
            "Quartirolo Lombardo", "Quatre-Vents", "Quercy Petit",
            "Queso Blanco", "Queso Blanco con Frutas --Pina y Mango",
            "Queso de Murcia", "Queso del Montsec", "Queso del Tietar",
            "Queso Fresco", "Queso Fresco (Adobera)", "Queso Iberico",
            "Queso Jalapeno", "Queso Majorero", "Queso Media Luna",
            "Queso Para Frier", "Queso Quesadilla", "Rabacal", "Raclette",
            "Ragusano", "Raschera", "Reblochon", "Red Leicester",
            "Regal de la Dombes", "Reggianito", "Remedou", "Requeson",
            "Richelieu", "Ricotta", "Ricotta (Australian)", "Ricotta Salata",
            "Ridder", "Rigotte", "Rocamadour", "Rollot", "Romano",
            "Romans Part Dieu", "Roncal", "Roquefort", "Roule",
            "Rouleau De Beaulieu", "Royalp Tilsit", "Rubens", "Rustinu",
            "Saaland Pfarr", "Saanenkaese", "Saga", "Sage Derby",
            "Sainte Maure", "Saint-Marcellin", "Saint-Nectaire",
            "Saint-Paulin", "Salers", "Samso", "San Simon", "Sancerre",
            "Sap Sago", "Sardo", "Sardo Egyptian", "Sbrinz", "Scamorza",
            "Schabzieger", "Schloss", "Selles sur Cher", "Selva", "Serat",
            "Seriously Strong Cheddar", "Serra da Estrela", "Sharpam",
            "Shelburne Cheddar", "Shropshire Blue", "Siraz", "Sirene",
            "Smoked Gouda", "Somerset Brie", "Sonoma Jack",
            "Sottocenare al Tartufo", "Soumaintrain", "Sourire Lozerien",
            "Spenwood", "Sraffordshire Organic", "St. Agur Blue Cheese",
            "Stilton", "Stinking Bishop", "String", "Sussex Slipcote",
            "Sveciaost", "Swaledale", "Sweet Style Swiss", "Swiss",
            "Syrian (Armenian String)", "Tala", "Taleggio", "Tamie",
            "Tasmania Highland Chevre Log", "Taupiniere", "Teifi", "Telemea",
            "Testouri", "Tete de Moine", "Tetilla", "Texas Goat Cheese",
            "Tibet", "Tillamook Cheddar", "Tilsit", "Timboon Brie", "Toma",
            "Tomme Brulee", "Tomme d'Abondance", "Tomme de Chevre",
            "Tomme de Romans", "Tomme de Savoie", "Tomme des Chouans",
            "Tommes", "Torta del Casar", "Toscanello", "Touree de L'Aubier",
            "Tourmalet", "Trappe (Veritable)", "Trois Cornes De Vendee",
            "Tronchon", "Trou du Cru", "Truffe", "Tupi", "Turunmaa",
            "Tymsboro", "Tyn Grug", "Tyning", "Ubriaco", "Ulloa",
            "Vacherin-Fribourgeois", "Valencay", "Vasterbottenost", "Venaco",
            "Vendomois", "Vieux Corse", "Vignotte", "Vulscombe",
            "Waimata Farmhouse Blue", "Washed Rind Cheese (Australian)",
            "Waterloo", "Weichkaese", "Wellington", "Wensleydale",
            "White Stilton", "Whitestone Farmhouse", "Wigmore",
            "Woodside Cabecou", "Xanadu", "Xynotyro", "Yarg Cornish",
            "Yarra Valley Pyramid", "Yorkshire Blue", "Zamorano",
            "Zanetti Grana Padano", "Zanetti Parmigiano Reggiano"};
//
//    private List<TownBean> getTownData(String result) {
//        List<TownBean> townBeanList = new ArrayList<TownBean>();
//        try {
//            JSONArray jsonArray = new JSONArray(result);
//            for (int i = 0; i < 20; i++) {
//                JSONObject object = jsonArray.getJSONObject(i);
//                TownBean townBean = new TownBean();
//                townBean.setTownName(object.getString("zSuperIorName"));
//                townBean.setTownId(object.getString("zSuperIorId"));
//                townBeanList.add(townBean);
//            }
//        } catch (JSONException e) {
//            Log.i("TAG", "解析数据出现问题");
//            mHandler.sendEmptyMessage(0x30);
//        }
//        return townBeanList;
//    }
//
//    private List<FactoryBean> getFactoryData(String result) {
//        List<FactoryBean> factoryBeanList = new ArrayList<FactoryBean>();
//        try {
//            JSONArray jsonArray = new JSONArray(result);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject object = jsonArray.getJSONObject(i);
//                FactoryBean factoryBean = new FactoryBean();
//                factoryBean.setFactoryName(object.getString("zDataSourName"));
//                factoryBean.setType(object.getString("zDataSourDesc"));
//                factoryBean.setTownId(object.getString("zSuperIorId"));
//                factoryBeanList.add(factoryBean);
//            }
//        } catch (JSONException e) {
//            Log.i("TAG", "解析数据出现问题");
//            mHandler.sendEmptyMessage(0x30);
//        }
//        return factoryBeanList;
//    }

    //    private void handleData() {
//        if (thread == null) {//如果已创建就不再重新创建子线程了
//            Toast.makeText(MainActivity.this, "开始解析数据", Toast.LENGTH_SHORT).show();
//            thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    // 写子线程中的操作,解析省市区数据
//                    String townStr = getFromAssets("TownStreet.json");
//                    List<TownBean> townBeanList = getTownData(townStr);
//                    String factoryStr = getFromAssets("Factory.json");
//                    List<FactoryBean> factoryBeanList = getFactoryData(factoryStr);
//                    for (int i = 0; i < townBeanList.size(); i++) {
//                        List<FactoryBean> factoryBeanList1 = new ArrayList<>();
//                        for (int j = 0; j < factoryBeanList.size(); j++) {
//                            if (townBeanList.get(i).getTownId().equals(factoryBeanList.get(j).getTownId())) {
//                                factoryBeanList1.add(factoryBeanList.get(j));
//                            }
//                        }
//                        TownBean townBean = new TownBean();
//                        townBean.setTownName(townBeanList.get(i).getTownName());
//                        townBean.setList(factoryBeanList1);
//                        list.add(townBean);
//                    }
//                    mHandler.sendEmptyMessage(0x20);
//                }
//            });
//            thread.start();
//        }
//    }
}
