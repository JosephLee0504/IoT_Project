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
		Button butblte=null;//????????????????,??????????????
		Button Button1=null;//????????????
		Button Button2=null;//????????????
		Button Button3=null;//????????????
		Button Button4=null;//????????????
		Button Button5=null;//????????????
		Button Button6=null;//????????????
		Button Button7=null;//????????????
		Button Button8=null;//????????????
		Button Button9=null;//????????????
	
		TextView View1=null;//???? 
		TextView View2=null;//????
		TextView View3=null;//????
		TextView View4=null;//????
		TextView View5=null;//????
		TextView View6=null;//????
		TextView View7=null;//????
		TextView View8=null;//????

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
		
		private List<String> mBuffer;//????????????????
		String showstr="";
		boolean led1data=false,led2data=false;
		private InputStream inputStream;    //????????????????????????
		BluetoothSocket socket = null; // ????????socket
		BluetoothDevice device = null;
		BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();//????????
		private final static int REQUEST_CONNECT_DEVICE = 1;    //??????????????????
		private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
		
		MediaPlayer mp;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{


			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			butblte=(Button)findViewById(R.id.BUTBLTE);//????????????
 
			Button1=(Button)findViewById(R.id.Button1Xml);//????????????
			Button2=(Button)findViewById(R.id.Button2Xml);//????????????
			Button3=(Button)findViewById(R.id.Button3Xml);//????????????
			

			
			
			View1=(TextView)findViewById(R.id.View1Xml);//????????

		
			EditText1 = (EditText)findViewById(R.id.EditText1Xml);	
	
			
			butblte.setOnClickListener(new setclick());//????????????
			Button1.setOnClickListener(new Button1click());
			Button2.setOnClickListener(new Button2click());	 	
			Button3.setOnClickListener(new Button3click());

			mp = MediaPlayer.create(this,R.drawable.alarm1);	
			
			if(adapter.isEnabled())//????????
			{
				enable=true;//????????????
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
											adapter.enable();//????????????
									} 
								}	
								else
								{
										Toast.makeText(MainActivity.this,"No new device found"	, 0).show();	
								}
								if(!enable)//??????????????????????????
									blecon=true;
								new BLEThread().start();//????????????  		
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
										outputstream.flush();//????????????*/
										Toast.makeText(MainActivity.this,"Data sent successfully!"	, 0).show();	
									} 
									catch (IOException e) 
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}   //??????????????
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
										outputstream.flush();//????????????*/
										Toast.makeText(MainActivity.this,"Data sent successfully!"	, 0).show();	
									} 
									catch (IOException e) 
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}   //??????????????
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
										outputstream.flush();//????????????*/
										Toast.makeText(MainActivity.this,"Data sent successfully!"	, 0).show();	
									} 
									catch (IOException e) 
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}   //??????????????
							}
							else
								Toast.makeText(MainActivity.this,"Error!"	, 0).show();	
					}	 
		}
		
		
    
		Handler mHandler = new Handler()    //??????????????????????????????
		{ 
			@Override  
			public void handleMessage(Message msg) 
			{  
					super.handleMessage(msg);  
					String a,LED1status;
					switch(msg.what)
					{
							case 1:
								  new BLEInput().start();//????????????????
								  break;
							case 2:
								String result = msg.getData().get("msg").toString();
								showstr=showstr+result;
								if(showstr.length()>=2)//????????????????8????????????????
								{
										a=showstr.substring(0, 1);
										if(a.equals("w"))//java????????0????,????????????????w??????????????????????????
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
 
		//???????????? ,??????????????UI  
		public class BLEThread extends Thread
		{
				public void run()
				{
						while(blecon)
						{
								if(adapter.isEnabled())//??????????
								{
										enable=true;
										blecon=false;//??????????????
								}
								if(enable)//????????????????
								{
										Intent serverIntent = new Intent(MainActivity.this, DeviceListActivity.class); // ????????????
										startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE); // ?????????????? 
										enable=false; 
								}
						}
				}
		}
			
			/*
			 * (non-Javadoc)
			 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
			 */
			// ??????????????????startActivityForResult() ????????????
			public void onActivityResult(int requestCode, int resultCode, Intent data) 
			{		
						switch(requestCode)
					  {
								case REQUEST_CONNECT_DEVICE:
								if (resultCode == Activity.RESULT_OK) //??????????????????????
								{
											  // MAC????????DeviceListActivity????????
											String address = data.getExtras()
																 .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
											// ????????????????      
											device = adapter.getRemoteDevice(address);//??????
											try 
											{
																socket= device.createRfcommSocketToServiceRecord(UUID
																.fromString(SPP_UUID));//????socket????????????????
												
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
															  new BLEInput().start();//????????????????
														}
														catch (IOException e) 
														{
																// TODO Auto-generated catch block
																e.printStackTrace();
														}   //??????????????????  
								
								}
								break;
								default:break;
					  }
			}
			
			/*
			 * ????????????????????????
			 */
			//???????????? ,??????????????UI  
			public class    BLEInput extends Thread
			{
						 String str ;
						//??????????????????Message??????Android????????????
					   int num;
						 public void run()
						 { 
									while(true)
									{	
										
											byte buffer[]=new byte[1024];//????1024??????
												try 
												{
														num=inputStream.read(buffer);
														str = new String(buffer,0,num);
														Message msg = new Message();
														msg.what = 2;
													  Bundle data = new Bundle();
														data.putString("msg", str);
														msg.setData(data);
														mHandler.sendMessage(msg);//??????????handler??????????????????*/
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
 
 
  