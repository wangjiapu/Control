package com.example.com.control.Fragments;

import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.com.control.Adapters.MlistViewAdapter;
import com.example.com.control.Adapters.MyRecyclerViewAdapter;
import com.example.com.control.R;
import com.example.com.control.bean.Goods;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蒲家旺 on 2017/4/11.
 *
 */
public class MarketFrament extends Fragment{

    private ListView mlistView;
    private List<String> mlist;//存放主要的类别菜单
    private MlistViewAdapter madapter;//listview 适配器

    private RecyclerView marketRecycerView;
    private List<Goods> goodsinfo;//商品信息列表
    private MyRecyclerViewAdapter radapter;//商品信息适配器；

    private LinearLayout screening;

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment contextMenuDialogFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.marketlayout, container, false);

        initview(view);//初始化布局
        initData();//初始化数据
        initEvent();//初始化事件
        return view;
    }


    private void initData() {
        mlist = new ArrayList<>();
        /**
         * 获取物品的分类
         *   暂时使用假数据
         */
        mlist.add("热门推荐");
        mlist.add("床铺类");mlist.add("桌椅类");
        mlist.add("文件类");mlist.add("点缀类");
        mlist.add("建材类");mlist.add("电器类");
        mlist.add("洁具类");mlist.add("贴纸类");
        mlist.add("贴纸类");mlist.add("贴纸类");
        mlist.add("贴纸类");mlist.add("贴纸类");
        mlist.add("贴纸类");mlist.add("贴纸类");
        mlist.add("贴纸类");mlist.add("贴纸类");
        mlist.add("贴纸类");mlist.add("贴纸类");
        mlist.add("贴纸类");mlist.add("贴纸类");

        madapter = new MlistViewAdapter(getContext(), mlist);
        mlistView.setAdapter(madapter);

        goodsinfo = new ArrayList<>();
        /**
         * 获取物品的信息
         * 暂时使用假数据
         */

        for (int i = 0; i < 31; i++) {
            Goods temp = new Goods();
            temp.setGoodsimage(getResources().getDrawable(R.drawable.markat_back));
            temp.setGoodsname("程序员，如何从平庸走向理想！！！");
            temp.setGoodsvalue("13.14");
            goodsinfo.add(temp);
        }

        GridLayoutManager gm = new GridLayoutManager(getContext(), 4);//一行有4个目录
        gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {

                if (position < 1) {
                    return 4;
                } else if (position > 2 && position < 7) {
                    return 1;
                } else {
                    return 2;
                }
            }
        });
        marketRecycerView.setLayoutManager(gm);
        radapter = new MyRecyclerViewAdapter(getContext(), goodsinfo);
        marketRecycerView.setAdapter(radapter);

        fragmentManager=getActivity().getSupportFragmentManager();

        initScreeningMenu();

    }

    /**
     * 初始化筛选菜单
     */
    private void initScreeningMenu() {

        MenuParams menu=new MenuParams();
        menu.setActionBarSize((int)getResources().getDimension(R.dimen.menusize));//弹出菜单的宽度
        menu.setMenuObjects(getMenuObjects());
        menu.setClosableOutside(false);
        menu.setClipToPadding(false);
        contextMenuDialogFragment=ContextMenuDialogFragment.newInstance(menu);

        contextMenuDialogFragment.setItemClickListener(new MyMenuOnclick());
    }

    private void initEvent() {

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), mlist.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        radapter.setRecyclerViewOnItemClickListener(new MyRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                /**
                 * recycler view的点击事件
                 * 执行以后是物品的详细信息
                 */
                Toast.makeText(getContext(), pos + "", Toast.LENGTH_SHORT).show();
            }
        });
        screening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    contextMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
            }
        });

    }

    private void initview(View view) {
        mlistView = (ListView) view.findViewById(R.id.listview);
        marketRecycerView = (RecyclerView) view.findViewById(R.id.marketrecycer);
        screening=(LinearLayout)view.findViewById(R.id.screen);
    }

    /**
     * 创造弹出菜单的item
      * @return
     */
    public List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects=new ArrayList<>();

        MenuObject colse=new MenuObject();
        colse.setResource(R.drawable.colse2);//关闭菜单
        colse.setScaleType(ImageView.ScaleType.CENTER);
        colse.setDividerColor(R.color.colormenu_select);


        MenuObject aa=new MenuObject();
        aa.setResource(R.drawable.zonghe);
        aa.setTitle("Comprehensive sequencing");//综合排序
        aa.setDividerColor(R.color.colormenu_select);
        MenuObject aa1=new MenuObject();
        aa1.setResource(R.drawable.xingyong2);
        aa1.setDividerColor(R.color.colormenu_select);
        aa1.setTitle("Credit");
        MenuObject aa2=new MenuObject();
        aa2.setResource(R.drawable.up);
        aa2.setDividerColor(R.color.colormenu_select);
        aa2.setTitle("Prices are low to high");

        MenuObject aa3=new MenuObject();
        aa3.setResource(R.drawable.down);
        aa3.setDividerColor(R.color.colormenu_select);
        aa3.setTitle("Prices are high to low");
        MenuObject aa4=new MenuObject();
        aa4.setResource(R.drawable.baoyou2);
        aa4.setDividerColor(R.color.colormenu_select);
        aa4.setTitle("Package mail");
        MenuObject aa5=new MenuObject();
        aa5.setResource(R.drawable.zhekou);
        aa5.setDividerColor(R.color.colormenu_select);
        aa5.setTitle("At a discount");

        menuObjects.add(colse);
        menuObjects.add(aa);
        menuObjects.add(aa1);
        menuObjects.add(aa2);
        menuObjects.add(aa3);
        menuObjects.add(aa4);
        menuObjects.add(aa5);
        return menuObjects;
    }



    class MyMenuOnclick implements OnMenuItemClickListener{
        public MyMenuOnclick(){

        }
        @Override
        public void onMenuItemClick(View clickedView, int position) {
            Toast.makeText(getContext(), "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
        }
    }
}
