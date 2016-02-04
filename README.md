
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
	
3.在application里初始化

		AdapterHelper.setImagehandler(new Imagehandler()
        {
            @Override
            public void display(Context context, ImageView view, String imageUrl)
            {
                Picasso.with(view.getContext()).load(imageUrl).into(view);
            }
        });

4.使用方式：例子里有三种使用方式

        推荐方式3：这样一个adapter就设置完成了，就.行代码
         mAdapter2 = new QuickRecyclerAdapter<Item,ItemAdapterHelper>(context, R.layout.item_sale)
		{
			@Override
			public AdapterHelper getAdapterHelper(View view)
			{
				return new ItemAdapterHelper(view);
			}
		};

		mRecyclerView.setAdapter(mAdapter2);