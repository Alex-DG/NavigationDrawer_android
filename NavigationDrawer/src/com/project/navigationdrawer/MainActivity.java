package com.project.navigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.navigationdrawer.fragments.BreakingBadFragment;
import com.project.navigationdrawer.fragments.DexterFragment;
import com.project.navigationdrawer.fragments.GameOfThronesFragment;
import com.project.navigationdrawer.fragments.SpartacusFragment;
import com.project.navigationdrawer.fragments.TheBigBangTheoryFragment;
import com.project.navigationdrawer.fragments.TheWalkingDeadFragment;
import com.project.navigationdrawer.fragments.tvShowsHomeFragment;

public class MainActivity extends Activity {
	
	private String[] tvShowsTitle;
	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private ActionBarDrawerToggle drawerToggle;
	
	// nav drawer title
	private CharSequence mDrawerTitle;
	// used to store app title
	private CharSequence mTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTitle = mDrawerTitle = getTitle();
		
		tvShowsTitle = getResources().getStringArray(R.array.tvShow_array);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerListView = (ListView) findViewById(R.id.left_drawer);
		
		drawerListView.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_items, tvShowsTitle));
		
		drawerListView.setOnItemClickListener(new DrawerItemClickListener());
		
		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	
		drawerToggle = new ActionBarDrawerToggle(
				this, 
				drawerLayout,
				R.drawable.ic_drawer, 
				R.string.drawer_open,
				R.string.drawer_close 
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}
	
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);
		
		if (savedInstanceState == null) {
			// on first time display view for first nav item (home fragment)
			displayView(0);
		}
	}
	
	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = drawerLayout.isDrawerOpen(drawerListView);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	displayView(position);
        }
    }
    
    /*
     * Update the main content by replacing fragments
     */
    private void displayView(int position) {    	
    	Fragment fragment = null;
    	
    	switch (position) {
		case 0:	
			//Go to main activity
			fragment = new tvShowsHomeFragment();
			break;
		case 1:	
			fragment = new BreakingBadFragment();
			break;
		case 2:
			fragment = new DexterFragment();
			break;
		case 3:
			fragment = new TheBigBangTheoryFragment();
			break;
		case 4:
			fragment = new GameOfThronesFragment();
			break;
		case 5:
			fragment = new TheWalkingDeadFragment();
			break;
		case 6:
			fragment = new SpartacusFragment();
			break;
		default:
			break;
		}
    	
    	if(fragment != null){
    		FragmentManager fragmentManager = getFragmentManager();
    		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    		
    		drawerListView.setItemChecked(position, true);
    		setTitle(tvShowsTitle[position]);
    		drawerLayout.closeDrawer(drawerListView);
    	} else {
    		Log.e("MainActivity", "Error in creating fragment");
    	}
	}
}
