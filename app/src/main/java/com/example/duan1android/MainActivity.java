package com.example.duan1android;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.duan1android.DAO.StoryDao;
import com.example.duan1android.DAO.TypeDAO;
import com.example.duan1android.Model.Story;
import com.example.duan1android.Model.Type;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static int width;
    public static int height;
    private AppBarConfiguration mAppBarConfiguration;
    TypeDAO typeDAO;
    StoryDao storyDao;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resources = getResources();
        typeDAO = new TypeDAO(this);
        storyDao = new StoryDao(this);
        if (typeDAO.getAllType().size() == 0 && storyDao.getAllStory().size() == 0) {
            loadTypeData();
            loadStory("Vova");
            loadStory("Thế Giới");
            loadStory("Việt Nam");
            loadStory("dân gian");
            loadStory("hiện đại");
            loadStory("Tổng hợp");
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_categorystory, R.id.nav_story, R.id.nav_favourite, R.id.nav_currentstory, R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        Log.e("data", "" + width + " " + height);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void loadTypeData() {
        typeDAO.insertType(new Type(0, "Truyện cười Vova", 10, R.drawable.vova));
        typeDAO.insertType(new Type(0, "Truyện cười Thế Giới", 10, R.drawable.thegioi));
        typeDAO.insertType(new Type(0, "Truyện cười Việt Nam", 10, R.drawable.vietnam));
        typeDAO.insertType(new Type(0, "Truyện cười dân gian", 10, R.drawable.truyencuoidangian));
        typeDAO.insertType(new Type(0, "Truyện cười hiện đại", 10, R.drawable.hiendai));
        typeDAO.insertType(new Type(0, "Truyện cười Tổng hợp", 10, R.drawable.tonghop));
    }


    public void loadStory(String name) {
        String name2 = name;
//        String title[] = resources.getStringArray(resources.getIdentifier("listTile"+name, "string", getPackageName()));
//        String content[] = resources.getStringArray(resources.getIdentifier("listContent"+name, "string", getPackageName()));
        switch (name) {
            case "Thế Giới":
                name2 = "TheGioi";
                break;
            case "Việt Nam":
                name2 = "VietNam";
                break;
            case "dân gian":
                name2 = "DanGian";
                break;
            case "hiện đại":
                name2 = "HienDai";
                break;
            case "Tổng hợp":
                name2 = "TongHop";
                break;
        }
        String title[] = resources.getStringArray(resources.getIdentifier("listTitle" + name2, "array", getPackageName()));
        String content[] = resources.getStringArray(resources.getIdentifier("listContent" + name2, "array", getPackageName()));
        for (int i = 0; i < title.length; i++) {
            storyDao.insertStory(new Story(1, "Truyện cười " + name, title[i], content[i], 0));
        }
    }
}