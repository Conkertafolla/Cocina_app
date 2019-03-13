package com.example.conke.cocina;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conke.cocina.Entities.Store;
import com.example.conke.cocina.Fragments.ListOrdersFragment;
import com.example.conke.cocina.Fragments.OrdersFragment;
import com.example.conke.cocina.Fragments.ProductFragment;
import com.example.conke.cocina.Fragments.SupplierFragment;
import com.example.conke.cocina.SQL.SQLQueries;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SupplierFragment.OnFragmentInteractionListener,
        ProductFragment.OnFragmentInteractionListener,
        OrdersFragment.OnFragmentInteractionListener,
        ListOrdersFragment.OnFragmentInteractionListener{
    Menu menu;
    String mailUser;
    private int storeId;
    private int supplierActive;
    private int orderActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.add_order);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Get mail user to query and fill the nav bar
         mailUser=getIntent().getExtras().getString("userMail");

    }


    public interface ComunicacionInterfaz{
        void loadProducts(int supplierId);
        int  getSupplierId();
        int getStoreId();
        int getOrderId();
        void loadOrdersProduct(int order);

    }


    private ComunicacionInterfaz interfaz = new ComunicacionInterfaz(){

        @Override
        public void loadProducts(int supplierId) {
            supplierActive=supplierId;
            ProductFragment productFragment = new ProductFragment();
            productFragment.setInterfaz(interfaz);
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_home,productFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


        }

        @Override
        public int getSupplierId() {
            return supplierActive;
        }

        @Override
        public int getStoreId() {
            return storeId;
        }

        @Override
        public int getOrderId() {
            return orderActive;
        }

        @Override
        public void loadOrdersProduct(int order) {
            orderActive=order;
            OrdersFragment ordersFragment = new OrdersFragment();
            ordersFragment.setInterfaz(interfaz);
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_home,ordersFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }


    };



    @Override
    public void onFragmentInteraction(Uri uri) {

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Store storeName = new Store();
        SQLQueries queries = new SQLQueries(this);
        storeName=queries.userStore(mailUser);
        TextView mailNav = (TextView) findViewById(R.id.mail_nav);
        TextView storeNav = (TextView) findViewById(R.id.store_nav);
        mailNav.setText(mailUser);
        storeNav.setText(storeName.getStoreName().toString());
        this.storeId = storeName.getStoreId();
        this.menu = menu;
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
        boolean selectedFragment = false;
        if (id == R.id.nav_camera) {
            SupplierFragment supplierfragment = new SupplierFragment();
            supplierfragment.setInterfaz(interfaz);
            fragment=supplierfragment;
            selectedFragment = true;
        } else if (id == R.id.nav_gallery) {
            ListOrdersFragment listOrdersFragment = new ListOrdersFragment();
            listOrdersFragment.setInterfaz(interfaz);
            fragment = listOrdersFragment;
            selectedFragment=true;

        } else if (id == R.id.nav_share) {

        }

        if(selectedFragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_home,fragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
