package com.jcodecraeer.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arthas.recycler.adapter.AdapterHelper;
import com.arthas.recycler.adapter.Imagehandler;
import com.arthas.recycler.adapter.QuickAdaptHelper;
import com.arthas.recycler.adapter.QuickRecyclerAdapter;
import com.arthas.recycler.adapter.RecyclerAdapter;
import com.arthas.utils.AssetHelp;
import com.arthas.utils.DeviceInfo;
import com.arthas.utils.log.LogUtil;
import com.squareup.picasso.Picasso;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private RecyclerAdapter<Item> mAdapter;
    private MainActivity context;
    private QuickRecyclerAdapter<Item, ItemAdapterHelper> mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);

        DeviceInfo.app = getApplication();
        AdapterHelper.setImagehandler(new Imagehandler()
        {
            @Override
            public void display(Context context, ImageView view, String imageUrl)
            {
                Picasso.with(view.getContext()).load(imageUrl).into(view);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //方式1：性能稍低,写法简单
//        initRecycler1();
        //方式2：性能比方式1稍好，不会重复创建onclicklistener
//        initRecycler2();
        //方式3：性能好，最简洁，推荐方式3，耦合度低
        initRecycler3();
    }



    private void initRecycler3()
    {
        mAdapter2 = new QuickRecyclerAdapter<Item,ItemAdapterHelper>(context, R.layout.item_sale)
        {
            @NonNull
            @Override
            public AdapterHelper getAdapterHelper(View view)
            {
                return new ItemAdapterHelper(view);
            }
        };

        mRecyclerView.setAdapter(mAdapter2);


        AssetHelp.getInstance().getBeana("sales.json", ItemsResp.class).subscribe(new Action1<ItemsResp>()
        {
            @Override
            public void call(ItemsResp itemsResp)
            {
                LogUtil.d(Thread.currentThread().toString());
                LogUtil.d(itemsResp.data.size());
                mAdapter2.setData(itemsResp.data);
            }
        });
    }


    private void initRecycler1()
    {
        mAdapter = new RecyclerAdapter<Item>(context, R.layout.item_sale)
        {
            @Override
            protected void convert(AdapterHelper helper, final Item item, int position)
            {
                helper.setImageUrl(R.id.img, item.img);
                helper.setText(R.id.info, item.img);
                helper.setTextColor(R.id.info, 0xfff03838);
            }
        };
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                final Item item = mAdapter.getItem(position);
                Toast.makeText(context, "click " + position + "    " + item.jt, 0).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);


        AssetHelp.getInstance().getBeana("sales.json", ItemsResp.class).subscribe(new Action1<ItemsResp>()
        {
            @Override
            public void call(ItemsResp itemsResp)
            {
                LogUtil.d(Thread.currentThread().toString());
                LogUtil.d(itemsResp.data.size());
                mAdapter.setData(itemsResp.data);
            }
        });
    }

    private void initRecycler2()
    {
        mAdapter = new RecyclerAdapter<Item>(context, R.layout.item_sale)
        {
            @NonNull
            @Override
            public AdapterHelper getAdapterHelper(View view)
            {
                final AdapterHelper adapterHelper = super.getAdapterHelper(view);
                adapterHelper.setOnClickListener(R.id.tip, new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        final Item item = mAdapter.getItem(adapterHelper);
                        Toast.makeText(context, "click tip jt="+item.jt, 0).show();
                    }
                });
                return adapterHelper;
            }

            @Override
            protected void convert(AdapterHelper helper, final Item item, int position)
            {
                helper.setImageUrl(R.id.img, item.img);
                helper.setText(R.id.info, item.img);
                helper.setTextColor(R.id.info, 0xfff03838);

            }
        };
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                final Item item = mAdapter.getItem(position);
                Toast.makeText(context, "click " + position + "    " + item.jt, 0).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);


        AssetHelp.getInstance().getBeana("sales.json", ItemsResp.class).subscribe(new Action1<ItemsResp>()
        {
            @Override
            public void call(ItemsResp itemsResp)
            {
                LogUtil.d(Thread.currentThread().toString());
                LogUtil.d(itemsResp.data.size());
                mAdapter.setData(itemsResp.data);
            }
        });
    }


}
