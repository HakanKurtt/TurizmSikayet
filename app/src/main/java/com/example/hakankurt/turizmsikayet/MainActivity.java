package com.example.hakankurt.turizmsikayet;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hakankurt.turizmsikayet.fragment.Bildirim_fragment;
import com.example.hakankurt.turizmsikayet.fragment.Profil_fragment;
import com.example.hakankurt.turizmsikayet.fragment.Search_fragment;
import com.example.hakankurt.turizmsikayet.fragment.Sikayetlerim_fragment;
import com.example.hakankurt.turizmsikayet.fragment.Sikayetyaz_fragment;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setting toolbar color
        toolbar.setBackgroundColor(Color.parseColor("#9fe025"));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_search_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.icons_pencil_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.icons_bell_24);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_person_black_24dp);

        //setting tablayout color
        tabLayout.setBackgroundColor(Color.parseColor("#9fe025"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        int countTabItem;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position)
            {
                case 0:
                    Search_fragment tab1=new Search_fragment();
                    return tab1;
                case 1:
                    Sikayetyaz_fragment tab2=new Sikayetyaz_fragment();
                    return tab2;
                case 2:
                    Sikayetlerim_fragment tab3=new Sikayetlerim_fragment();
                    return tab3;
                case 3:
                    Bildirim_fragment tab4=new Bildirim_fragment();
                    return tab4;
                case 4:
                    Profil_fragment tab5=new Profil_fragment();
                    return tab5;
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    return "ARAMA";
                case 1:
                    return "SİKAYETYAZ";
                case 2:
                    return "SİKAYETLERİM";
                case 3:
                    return "BİLDİRİM";
                case 4:
                    return "PROFİL";
            }
            return null;
        }

    }
}
