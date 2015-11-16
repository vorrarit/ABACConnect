package com.zicure.abacconnect.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.graduation.photo.GraduatePhotoGalleryFragment;
import com.zicure.abacconnect.profile.ProfileFragment;
import com.zicure.abacconnect.my.business.MyBusinessFragment;
import com.zicure.abacconnect.my.deal.MyDealsFragment;
import com.zicure.abacconnect.register.LoginActivity;
import com.zicure.abacconnect.transcript.TranscriptFragment;
import com.zicure.abacconnect.work.profile.WorkProfileFragment;

/**
 * Created by DUMP129 on 9/24/2015.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	private boolean doubleBackToExitPressedOnce = false;
	private NavigationView navigationView;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(savedInstanceState==null){
			checkMember();
		}

		toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		navigationView = (NavigationView) findViewById(R.id.navigation);
		navigationView.setNavigationItemSelectedListener(this);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
		drawerLayout.setDrawerListener(drawerToggle);

		ApplicationContext.getInstance().setContext(this);

		MainPage mainPage = new MainPage();
		getSupportFragmentManager().beginTransaction().replace(R.id.main, mainPage).commit();

		if (getIntent().getExtras() != null) {
			String parseData = getIntent().getExtras().getString("com.parse.Data");
			if (parseData != null) {
				Log.d("TestParse", parseData);
			}
		} else {
			Log.d("TestParse", "getExtras is null");
		}
	}

	private void checkMember() {
		SharedPreferences pref = getApplicationContext().getSharedPreferences("ABACLogin", Context.MODE_PRIVATE);
		String userid = pref.getString("userId", "null");
		if("null".equals(userid)){
			Intent intent = new Intent(MainActivity.this,MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {
		Fragment fragment = null;
		int id = menuItem.getItemId();
		switch (id) {
			case R.id.navItem0:
				fragment = new MainPage();
				break;

			case R.id.navItem1:
				fragment = new ProfileFragment();
				break;

			case R.id.navItem2:
				//fragment = new WorkProfileFragment();
				break;

			case R.id.navItem3:
				//fragment = new TranscriptFragment();
				break;

			case R.id.navItem4:
				//fragment = new GraduatePhotoGalleryFragment();
				break;

			case R.id.navItem5:
				//fragment = new MyBusinessFragment();
				break;

			case R.id.navItem6:
				//fragment = new MyDealsFragment();
				break;

			case R.id.navItem8:
				onLogOut();
				break;
		}

		if (fragment != null) {
			FragmentManager manager = getSupportFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.main, fragment);
			transaction.commit();

			drawerLayout.closeDrawers();

		} else {
		}

		return false;
	}

	private void onLogOut() {
		new AlertDialog.Builder(MainActivity.this)
				.setTitle("Log Out")
				.setMessage("You want Log Out")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						SharedPreferences pref = getApplicationContext().getSharedPreferences("ABACLogin", Context.MODE_PRIVATE);
						SharedPreferences.Editor prefEdit = pref.edit();
						prefEdit.putString("userId", "");
						prefEdit.putString("username", "");
						prefEdit.putString("firstName", "");
						prefEdit.putString("lastName", "");
						Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
						startActivity(intent);
						finish();
						dialog.cancel();

					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();

					}
				})
				.setIcon(android.R.drawable.ic_dialog_alert)
				.show();



	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}


	/*@Override
	public void onBackPressed() {
		if (doubleBackToExitPressedOnce) {
			super.onBackPressed();
			this.finish();
		}

		this.doubleBackToExitPressedOnce = true;
		Toast.makeText(this, "Please BACK again to exit ", Toast.LENGTH_SHORT).show();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				doubleBackToExitPressedOnce = false;
			}
		}, 2000);

	}*/
}