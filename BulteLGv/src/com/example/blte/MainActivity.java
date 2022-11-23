package com.example.blte;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
 
 


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
 
 
 


import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View; 
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
 
 
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blte.DeviceListActivity;
 
 

 
public class MainActivity extends Activity 
{
		Button butblte=null;//定义一个按键控件,控制蓝牙搜索的
		Button Button1=null;//定义按键发送
		Button Button2=null;//定义按键发送
		Button Button3=null;//定义按键发送
		Button Button4=null;//定义按键发送
		Button Button5=null;//定义按键发送
		Button Button6=null;//定义按键发送
		Button Button7=null;//定义按键发送
		Button Button8=null;//定义按键发送
		Button Button9=null;//定义按键发送
	
		TextView View1=null;//显示 
		TextView View2=null;//显示
		TextView View3=null;//显示
		TextView View4=null;//显示
		TextView View5=null;//显示
		TextView View6=null;//显示
		TextView View7=null;//显示
		TextView View8=null;//显示

		private EditText EditText1; 
		private EditText EditText2;
		private EditText EditText3;
		private EditText EditText4;
		private EditText EditText5;
		private EditText EditText6;
		private EditText EditText7;
		private EditText EditText8;

		boolean enable=false;
		boolean blecon=true;
		boolean bThread=false;
		boolean LEDstauts=false;
		
		private List<String> mBuffer;//定义一个阻塞队列
		String showstr="";
		boolean led1data=false,led2data=false;
		private InputStream inputStream;    //输入流，用来接收蓝牙数据
		BluetoothSocket socket = null; // 蓝牙通信socket
		BluetoothDevice device = null;
		BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();//获取蓝牙
		private final static int REQUEST_CONNECT_DEVICE = 1;    //宏定义查询设备句柄
		private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
		
		MediaPlayer mp;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{


			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			butblte=(Button)findViewById(R.id.BUTBLTE);//蓝牙控制按键
 
			Button1=(Button)findViewById(R.id.Button1Xml);//发送控制按钮
			Button2=(Button)findViewById(R.id.Button2Xml);//发送控制按钮
			Button3=(Button)findViewById(R.id.Button3Xml);//发送控制按钮
			

			
			
			View1=(TextView)findViewById(R.id.View1Xml);//显示数据

		
			EditText1 = (EditText)findViewById(R.id.EditText1Xml);	
	
			
			butblte.setOnClickListener(new setclick());//定义按钮事件
			Button1.setOnClickListener(new Button1click());
			Button2.setOnClickListener(new Button2click());	 	
			Button3.setOnClickListener(new Button3click());

			mp = MediaPlayer.create(this,R.drawable.alarm1);	
			
			if(adapter.isEnabled())//蓝牙可用
			{
				enable=true;//表明蓝牙开启
			}
		}		
 
		public class setclick implements OnClickListener
		{
					@Override
					public void onClick(View arg0) 
					{
								// TODO Auto-generated method stub
								if(adapter!=null)
								{
									if(!adapter.isEnabled())
									{
											Toast.makeText(MainActivity.this,"Open Bluetooth"	, 0).show();	
											adapter.enable();//将把蓝牙打开
									} 
								}	
								else
								{
										Toast.makeText(MainActivity.this,"No new device found"	, 0).show();	
								}
								if(!enable)//如果蓝牙失败，需要重新连接
									blecon=true;
								new BLEThread().start();//开启蓝牙现成  		
					}
		}

	
		public class Button1click implements OnClickListener 
		{
					@Override
					public void onClick(View arg0) 
					{
							// TODO Auto-generated method stub
							if(socket!=null)
							{
									try 
									{
										OutputStream outputstream = socket.getOutputStream();
										String msgText =   EditText1.getText().toString(); 
										byte[] buf = msgText.getBytes();
										outputstream.write(buf);
										outputstream.flush();//清空发送数据*/
										Toast.makeText(MainActivity.this,"Data sent successfully!"	, 0).show();	
									} 
									catch (IOException e) 
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}   //蓝牙连接输出流
							}
							else
								Toast.makeText(MainActivity.this,"Error!"	, 0).show();	
					}	 
		}

		public class Button2click implements OnClickListener 
		{
					@Override
					public void onClick(View arg0) 
					{
							// TODO Auto-generated method stub
							if(socket!=null)
							{
									try 
									{
										OutputStream outputstream = socket.getOutputStream();
										String msgText = "101040100"; 
										byte[] buf = msgText.getBytes();
										outputstream.write(buf);
										outputstream.flush();//清空发送数据*/
										Toast.makeText(MainActivity.this,"Data sent successfully!"	, 0).show();	
									} 
									catch (IOException e) 
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}   //蓝牙连接输出流
							}
							else
								Toast.makeText(MainActivity.this,"Error!"	, 0).show();	
					}	 
		}
		
		public class Button3click implements OnClickListener 
		{
					@Override
					public void onClick(View arg0) 
					{
							// TODO Auto-generated method stub
							if(socket!=null)
							{
									try 
									{
										OutputStream outputstream = socket.getOutputStream();
										String msgText = "101210101"; 
										byte[] buf = msgText.getBytes();
										outputstream.write(buf);
										outputstream.flush();//清空发送数据*/
										Toast.makeText(MainActivity.this,"Data sent successfully!"	, 0).show();	
									} 
									catch (IOException e) 
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}   //蓝牙连接输出流
							}
							else
								Toast.makeText(MainActivity.this,"Error!"	, 0).show();	
					}	 
		}
		
		
    
		Handler mHandler = new Handler()    //等待接收蓝牙返回的一些数据消息
		{ 
			@Override  
			public void handleMessage(Message msg) 
			{  
					super.handleMessage(msg);  
					String a,LED1status;
					switch(msg.what)
					{
							case 1:
								  new BLEInput().start();//开启蓝牙接收线程
								  break;
							case 2:
								String result = msg.getData().get("msg").toString();
								showstr=showstr+result;
								if(showstr.length()>=2)//接收到的数据大于8个，表明数据正常
								{
										a=showstr.substring(0, 1);
										if(a.equals("w"))//java字符串从0开始,如果第一个字符是w，表明接收到的数据是正确的
										{										
													if(showstr.substring(1, 2).equals("1"))
													{
														View1.setText("It will rain today, take your umbrella!");

														
													}
													else
													{
														View1.setText("                     ");
													}										
										}
		
										showstr="";
								}
								break; 
								
								default:break; 
					} 
				
			}
		};
 
		//开辟一个线程 ,线程不允许更新UI  
		public class BLEThread extends Thread
		{
				public void run()
				{
						while(blecon)
						{
								if(adapter.isEnabled())//蓝牙可用，
								{
										enable=true;
										blecon=false;//只进行检测一次
								}
								if(enable)//蓝牙被正常开启了
								{
										Intent serverIntent = new Intent(MainActivity.this, DeviceListActivity.class); // 跳转程序设置
										startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE); // 设置返回宏定义 
										enable=false; 
								}
						}
				}
		}
			
			/*
			 * (non-Javadoc)
			 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
			 */
			// 接收活动结果，响应startActivityForResult() 安卓回调函数
			public void onActivityResult(int requestCode, int resultCode, Intent data) 
			{		
						switch(requestCode)
					  {
								case REQUEST_CONNECT_DEVICE:
								if (resultCode == Activity.RESULT_OK) //搜索到蓝牙，将进行配对
								{
											  // MAC地址，由DeviceListActivity设置返回
											String address = data.getExtras()
																 .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
											// 得到蓝牙设备句柄      
											device = adapter.getRemoteDevice(address);//远端的
											try 
											{
																socket= device.createRfcommSocketToServiceRecord(UUID
																.fromString(SPP_UUID));//通过socket测试是否连接成功
												
														} 
														catch (IOException e) 
														{							
																Toast.makeText(this, "Connection fails", Toast.LENGTH_SHORT).show();
																// TODO Auto-generated catch block
																e.printStackTrace();	
														}
														
											try 
											{
																socket.connect();
																butblte.setText("Successfully Connect");	
																//Rssi=device.getBLERSSI();
																Toast.makeText(this, "Connect"+device.getName()+"successfully!", Toast.LENGTH_SHORT).show();
														} 
														catch (IOException e) 
														{
																Toast.makeText(this, "Connection fails!", Toast.LENGTH_SHORT).show();
																// TODO Auto-generated catch block
																e.printStackTrace();
														}
											
												try 
												{
																inputStream  = socket.getInputStream();
															  new BLEInput().start();//开启蓝牙接收线程
														}
														catch (IOException e) 
														{
																// TODO Auto-generated catch block
																e.printStackTrace();
														}   //得到蓝牙数据输入流  
								
								}
								break;
								default:break;
					  }
			}
			
			/*
			 * 开辟线程来做数据接收使用
			 */
			//开辟一个线程 ,线程不允许更新UI  
			public class    BLEInput extends Thread
			{
						 String str ;
						//得到一个消息对象，Message类是有Android操作系统提供
					   int num;
						 public void run()
						 { 
									while(true)
									{	
										
											byte buffer[]=new byte[1024];//定义1024个字节
												try 
												{
														num=inputStream.read(buffer);
														str = new String(buffer,0,num);
														Message msg = new Message();
														msg.what = 2;
													  Bundle data = new Bundle();
														data.putString("msg", str);
														msg.setData(data);
														mHandler.sendMessage(msg);//发送数据给handler，让其进行数据更新*/
												} 
												catch (IOException e) 
												{
														// TODO Auto-generated catch block
														e.printStackTrace();
												}				
									}    
						 }
			 }
 
			/*
			 * (non-Javadoc)
			 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
			 */
			@Override
			public boolean onCreateOptionsMenu(Menu menu) 
			{
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}
    
}
 
 
  