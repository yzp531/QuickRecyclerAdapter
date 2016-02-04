package com.jcodecraeer.sample;

import android.view.View;
import android.widget.Toast;

import com.arthas.recycler.adapter.QuickAdaptHelper;

/**
 * Created by Administrator on 2016/2/4.
 */
public class ItemAdapterHelper extends QuickAdaptHelper<Item>
{
    public ItemAdapterHelper(View itemView)
    {
        super(itemView);
        setOnClickListener(R.id.tip, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getContext(), "click tip jt=" + obj.jt, 0).show();
            }
        });
    }

    @Override
    public void render(Item obj, int position)
    {
        setImageUrl(R.id.img, obj.img);
        setText(R.id.info, obj.img);
        setTextColor(R.id.info, 0xfff03838);
    }
}
