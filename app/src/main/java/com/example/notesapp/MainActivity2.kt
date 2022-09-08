package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


//Main Activity which will contain all the fragments
class MainActivity2 : AppCompatActivity() {

    lateinit var navHost: FrameLayout
    lateinit var bnvMain: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        navHost = findViewById(R.id.flFragments)
        bnvMain = findViewById(R.id.bnvNav)

        val noteFragment = MainActivity()
        val todoFragment = TodoFragment()

        supportFragmentManager
            .beginTransaction().apply {
                replace(R.id.flFragments, noteFragment)
                    .addToBackStack(null)
                    .commit()
            }




        bnvMain.setOnItemSelectedListener {


            when (it.itemId) {

                R.id.item_note -> {

                    supportFragmentManager
                        .beginTransaction().apply {
                            replace(R.id.flFragments, noteFragment)
                                .addToBackStack(null)
                                .commit()
                        }

                    true
                }

                R.id.item_todo -> {


                    supportFragmentManager
                        .beginTransaction().apply {
                            replace(R.id.flFragments, todoFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    true
                }


                else -> {
                    false
                }
            }
        }


    }
}