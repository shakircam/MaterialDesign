package com.example.materialdesign

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(),ItemListener {
    private val cameraRequest = 1888
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val camera = findViewById<ImageView>(R.id.camera)
        val call = findViewById<ImageView>(R.id.call)
        val email = findViewById<ImageView>(R.id.email)
        val share = findViewById<ImageView>(R.id.share)



        showImage()
        camera.setOnClickListener { capturePhoto() }
        call.setOnClickListener {makeCall()}
        email.setOnClickListener { makeEmail()}
        share.setOnClickListener { shareText() }
        addDataToRecyclerView()






    }

   private fun capturePhoto() {

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, cameraRequest)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
           // mainImage.setImageBitmap(photo)
        }
    }


    private fun makeCall(){
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + "01917380832")
            startActivity(dialIntent)
    }
    private fun makeEmail(){
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto: shakircam@gmail.com")
        intent.putExtra(Intent.EXTRA_EMAIL, "test")
        intent.putExtra(Intent.EXTRA_SUBJECT, "Good Evening")

        startActivity(intent)
    }
    private fun shareText(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Hi Shajid!!!!.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }


    private fun showImage(){
        val url = "https://source.unsplash.com/random"
        val mainImage = findViewById<ImageView>(R.id.mainImage)
        Glide.with(this)
                .load(url)
                .override(300,200)
                .centerCrop()
                .placeholder(R.drawable.ic_image_place_holder)
                .error(R.drawable.ic_broken_image)
                .fallback(R.drawable.ic_no_image)
                .into(mainImage)
    }

    @SuppressLint("WrongConstant")
    fun addDataToRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_View)
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL,false)

        //crating an arraylist to store users using the data class user
        val users = ArrayList<User>()

        //adding some dummy data to the list
        users.add(User("Ibrahim Khalil","Comilla","01917380832"))
        users.add(User("Shajid Anam","Comilla","01632158468"))
        users.add(User("Fahim Rahman","Jessor","01534896824"))
        users.add(User("Abdul Hamid","Chittagong","01917215648"))
        users.add(User("Fazlur Rahman","Dhaka","01852014563"))
        users.add(User("Khalid Khan","Narshindi","01875236950"))
        users.add(User("Jhon Dho","Rongpur","01874425968"))
        users.add(User("Arif Hasan","Khulna","01917333222"))
        users.add(User("Abdul Motalab","Dhaka","01912356874"))
        users.add(User("Hanif Mia","Comilla","01745621850"))

        //creating my adapter
        val adapter = CustomAdapter(users,this)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
    }



    override fun onClicked(name: String) {
        Toast.makeText(this, "Name: $name", Toast.LENGTH_SHORT).show()
    }
}