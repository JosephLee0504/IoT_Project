package com.example.blte;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {
	private ImageView loginImage;
	private TextView regist;
	private TextView topText;
	private TextPaint tp;
	private Button loginbtn;
	private Button jinru;
	private EditText username, tel, qq;
	private EditText password;
	private Drawable mIconPerson;
	private Drawable mIconLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);// ������Ļ��ת
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_login);
		
		username = (EditText) findViewById(R.id.username);
		username.setCompoundDrawables(mIconPerson, null, null, null);
		password = (EditText) findViewById(R.id.pass);
		password.setCompoundDrawables(mIconLock, null, null, null);
		loginbtn = (Button) findViewById(R.id.loginbtn);
		init();

	}

	@SuppressWarnings("deprecation")
	public void init() {
		loginbtn = (Button) findViewById(R.id.loginbtn);
		loginbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(username.getText().toString().equals("app")&&password.getText().toString().equals("123")){
					Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
					startActivity(new Intent(LoginActivity.this,MainActivity.class));
				}else{
					Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	
}
