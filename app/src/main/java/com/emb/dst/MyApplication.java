package com.emb.dst;
import android.app.Application;

public class MyApplication extends Application
{
		public String MYNAME="MyApplication";
		private MyApplication This;
		@Override
		public void onCreate()
		{
				setTheme(R.style.AppUI);
				super.onCreate();
		}
		public MyApplication Init(){
				if(This == null) {
						This = new MyApplication();
				}
				return This;
		}
}
