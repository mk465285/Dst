package com.emb.dst;

import com.emb.dst.app.Activity;
import com.emb.dst.R;
import android.os.Bundle;
import com.dst.read.*;
import com.dst.read.Error.*;
import com.dst.read.Listener.*;
import android.widget.*;

public class MainActivity extends Activity 
{
   @Override
   protected int CreateView_Res()
   {
     return R.layout.main;
   }
      @Override
   protected void Init()
   {
	 super.Init();
	 
	 
	 try
	 {
	   DST d =new DST.Open("").Read();
	 }
	 catch (DSTexception e)
	 {
	   ShowAlert(e.getMessage()+e.getErrCode()).show();
	 }

	 
	 
	 
	 final LinearLayout main = (LinearLayout) findViewById(R.id.mainLinearLayout1);
	 DST.Open open = new DST.Open();
	 
	 try
	 {
	   DST dst =new DST
	   .Open("/storage/emulated/0/2.dst")
		 .setOnReadBodyListener(new OnReadBodyListener(){

		   @Override
		   public void Read(int num, int type, int X, int Y, int Color_Index, boolean Jump, boolean Slice)
		   {
			 TextView t = new TextView(MainActivity.this);
			 t.setText("第"+num+"针 X:"+X+" Y:"+Y+ "第"+Color_Index+"色  类型 "+type+"  "+checktype(type));
			 main.addView(t);
		   }

		   private String checktype(int type)
		   {
			 switch (type) {
			   case DST.Data_Type.NORMAL: return "下针";
			   case DST.Data_Type.CHANGECOLOR: return "换色";
			   case DST.Data_Type.DOWN_SLICE_HEADE: return "下片头";
			   case DST.Data_Type.JUMP: return "跳针";
			   case DST.Data_Type.SLICE: return "下珠片";
			   case DST.Data_Type.UP_SLICE_HEADER: return "升片头";
			 }
			 return "未知";
		   }
		 })
		 
	   .Read();
	   ShowAlert(dst.getHeader().toString()).show();
	   ShowAlert(dst.getBody().size()+"").show();
	   ShowAlert(dst.getHeader().getMY()+"").show();
	 }
	 catch (DSTexception e)
	 {
	   ShowAlert(e.getMessage()).show();
	 }
   }
}
