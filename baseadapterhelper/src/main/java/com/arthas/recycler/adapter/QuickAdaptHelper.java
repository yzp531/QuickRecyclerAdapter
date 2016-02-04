package com.arthas.recycler.adapter;

import android.view.View;


/**
 * Created by jianghejie on 15/8/8.
 */
public abstract class QuickAdaptHelper<E> extends AdapterHelper
{
    public E obj;

    public QuickAdaptHelper(View itemView)
    {
        super(itemView);
    }

    public abstract void render(E obj, int position);


    public void renderWithSave(E obj, int position)
    {
        this.obj = obj;
        render(obj, position);

    }
}