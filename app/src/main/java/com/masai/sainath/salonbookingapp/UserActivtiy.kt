package com.masai.sainath.salonbookingapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.salonbookingapp.databinding.ActivityUserActivtiyBinding
import io.grpc.InternalChannelz.id
import java.lang.Exception

class UserActivtiy : AppCompatActivity() ,OnItemClickListener{

    lateinit var binding: ActivityUserActivtiyBinding
    lateinit var database: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserActivtiyBinding.inflate(layoutInflater)
        setContentView(binding.root)




        database= FirebaseFirestore.getInstance()


        binding.btnmenu.setOnClickListener {
            if (binding.drawerlayout.isDrawerOpen(Gravity.LEFT)){
                binding.drawerlayout.closeDrawer(Gravity.LEFT)
            }else
            {
                binding.drawerlayout.openDrawer(Gravity.LEFT)
            }
        }
        binding.navigationView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.share->{
                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "\nInstall this application for poetry\n\n"
                        shareMessage =
                            """
                            ${shareMessage}https://play.google.com/store/apps/details?id=$packageName
                            
                            
                            """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {
                        //e.toString();
                    }
                    true
                }
                R.id.more->{
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                    }
                    true
                }
                R.id.rate->{
                    val uri = Uri.parse("market://details?id=$packageName")
                    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(myAppLinkToMarket)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show()
                    }
                    true
                }
                else->{
                    false
                }
            }
        }


        database.collection("Admindata").addSnapshotListener { value, error ->
            val listOfCategories = arrayListOf<AdminModel>()
            val data = value?.toObjects(AdminModel::class.java)
            listOfCategories.addAll(data!!)
            binding.revUser.layoutManager = LinearLayoutManager(this)
            binding.revUser.adapter = UserAdapter1(listOfCategories,this)

        }


    }

    override fun onItemClicked(mallItem: AdminModel) {
        val intent=Intent(this,SlotActivty::class.java)
        intent.putExtra("imgurl",mallItem.imgurl)
        intent.putExtra("salonname",mallItem.salonname)
        intent.putExtra("barbername",mallItem.barbarname)
        intent.putExtra("location",mallItem.location)
        startActivity(intent)

    }

    override fun onDirectionsClicked() {
    //    val location=intent.getStringExtra("location")
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode("barber shops bengalore"))

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (this.let {
                mapIntent.resolveActivity(it.packageManager) } != null) {
            startActivity(mapIntent)
        }
    }
    override fun onBackPressed() {
        if (binding.drawerlayout.isDrawerOpen(Gravity.LEFT)){
            binding.drawerlayout.closeDrawer(Gravity.LEFT)
        }else
        {
            super.onBackPressed()

        }
    }
}