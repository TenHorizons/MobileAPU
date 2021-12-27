package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class StudentHomepage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private val TAG = "StudentHomepage"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_homepage)

        var toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {}
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> home()
            R.id.nav_lectures -> lectures()
            R.id.nav_attendance -> attendance()
            R.id.logout -> logout()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun home() {
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        if (navigationView.menu.findItem(R.id.nav_home).isChecked) {
            drawer.closeDrawer(GravityCompat.START)
        } else{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment())
                .commit()
            drawer.closeDrawer(androidx.core.view.GravityCompat.START)
        }
    }

    private fun lectures() {
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        if (navigationView.menu.findItem(R.id.nav_lectures).isChecked) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LecturesFragment())
                .commit()
            drawer.closeDrawer(androidx.core.view.GravityCompat.START)
        }
    }

    private fun attendance() {
        var navigationView: NavigationView = findViewById(R.id.nav_view)
        if (navigationView.menu.findItem(R.id.nav_attendance).isChecked) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AttendanceFragment())
                .commit()
            drawer.closeDrawer(androidx.core.view.GravityCompat.START)
        }
    }

    private fun logout(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}