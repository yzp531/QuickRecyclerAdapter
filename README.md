
1.Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

2.Add the dependency

	dependencies {
	        compile 'com.github.Aarthas:QuickRecyclerAdapter:1.1'
	}
	
3.��application���ʼ��

		AdapterHelper.setImagehandler(new Imagehandler()
        {
            @Override
            public void display(Context context, ImageView view, String imageUrl)
            {
                Picasso.with(view.getContext()).load(imageUrl).into(view);
            }
        });

4.ʹ�÷�ʽ��������������ʹ�÷�ʽ

        �Ƽ���ʽ3������һ��adapter����������ˣ���.�д���
         mAdapter2 = new QuickRecyclerAdapter<Item,ItemAdapterHelper>(context, R.layout.item_sale)
		{
			@Override
			public AdapterHelper getAdapterHelper(View view)
			{
				return new ItemAdapterHelper(view);
			}
		};

		mRecyclerView.setAdapter(mAdapter2);