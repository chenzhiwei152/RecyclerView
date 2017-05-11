package com.example.chenzhiwei.recycleview;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager mLayoutManager; //LayoutManager
    private RVAdapter recyclerAdapter;    //RecyclerView对应的Adapter
    private ArrayList<String> mContentList; //内容list这里只是使用了字符串，当然也可以替换成其他的JavaBean类
    private Random mRandom = new Random(); //用于产生随机字符串
    private int mSum = 50; //初始化子项数目
    private LinearLayout root_layout;
    private RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root_layout = (LinearLayout) findViewById(R.id.root_layout);
        rcv = (RecyclerView) findViewById(R.id.rcv);

        mContentList = new ArrayList<String>();
        for (int i = 0; i < mSum; i++) {
            mContentList.add(getRandomString());
        } //随机生成数据
        recyclerAdapter = new RVAdapter(mContentList);


        mLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL); //3列纵向

        rcv.setAdapter(recyclerAdapter);
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator()); //设置动画效果，可以看下上面的效果图，动画效果还是比较明显的


        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerAdapter.addData(0); //加一个
                makeSnackBar(root_layout, "添加一个 :)", null, null);//显示一个Snackbar
            }
        });
        findViewById(R.id.fab_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerAdapter.removeData(0); //去掉一个
                makeSnackBar(root_layout, "删除一个 :(", null, null); //显示一个Snackbar
            }
        });
    }


    //用于产生随机字符串的方法
    public String getRandomString() {
        String src = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder dst = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            dst.append(src.charAt(mRandom.nextInt(62)));
        }
        return dst.toString();
    }

    //构建一个Snackbar并显示出来
    private void makeSnackBar(View view, String message, String buttonText, View.OnClickListener onClickListener) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction(buttonText, onClickListener)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //填充选项菜单（读取XML文件、解析、加载到Menu组件上）
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                //GridLayoutManager
                mLayoutManager = new GridLayoutManager(this, 3);//3列
                rcv.addItemDecoration(new DividerItemDecoration(this, StaggeredGridLayoutManager.VERTICAL));
                rcv.setLayoutManager(mLayoutManager);
                rcv.getAdapter().notifyDataSetChanged();
                break;
            case R.id.item2:
                //LinearLayoutManager
                mLayoutManager = new LinearLayoutManager(this);
                rcv.addItemDecoration(new DividerItemDecoration(this, StaggeredGridLayoutManager.VERTICAL));
                rcv.setLayoutManager(mLayoutManager);
                rcv.getAdapter().notifyDataSetChanged();
                break;
            case R.id.item3:
                //StaggeredGridLayoutManager_VERTICAL
                mLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL); //3列纵向
                rcv.setLayoutManager(mLayoutManager);
                rcv.getAdapter().notifyDataSetChanged();
                break;
            case R.id.item4:
                //StaggeredGridLayoutManager_HORIZONTAL
                mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
                rcv.setLayoutManager(mLayoutManager);
                rcv.getAdapter().notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
