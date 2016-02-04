package com.jcodecraeer.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arthas.recycler.adapter.AdapterHelper;
import com.arthas.recycler.adapter.Imagehandler;
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
        mAdapter = new RecyclerAdapter<Item>(context, R.layout.item_sale)
        {
            @Override
            protected void convert(AdapterHelper helper, Item item, int position)
            {
                helper.setImageUrl(R.id.img, item.img);
            }
        };
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                final Item item = mAdapter.getItem(position);
                Toast.makeText(context, "click " + position +"    "+ item.jt, 0).show();
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
