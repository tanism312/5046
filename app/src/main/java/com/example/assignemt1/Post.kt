package com.example.assignemt1

import android.net.Uri
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

@IgnoreExtraProperties
data class Post(var userName : String, var postId : Int, var text : String, var imageUri : String,  var comments : Int, var likes : Int)
{
    companion object {
        private var lastPostId: Int = 0

        // Function to generate the next post ID
        private fun getNextPostId() = ++lastPostId
    }

    constructor() : this (
        userName = "",
        postId = getNextPostId(),
        text = "",
        imageUri = "",
        comments = 0,
        likes = 0
    )

    constructor(userName: String, text: String, imageUri: String, likes: Int) : this(
        userName = userName,
        postId = getNextPostId(),
        text = text,
        imageUri = imageUri,
        comments = 0,
        likes = likes
    )


    constructor(userName: String, text: String, likes: Int) : this(
        userName = userName,
        postId = getNextPostId(),
        text = text,
        imageUri = "",
        comments = 0,
        likes = likes
    )


    fun addLike(){
        this.likes++
    }

    fun removeLike(){
        this.likes--
    }
}

lateinit var storageRef : StorageReference
lateinit var firebaseRef : DatabaseReference

fun upload(post: Post, uri: Uri)
{
    storageRef = FirebaseStorage.getInstance().getReference()
    firebaseRef = FirebaseDatabase.getInstance().getReference()
    storageRef.child("images/${post.userName}/${uri}").putFile(uri)
        .addOnSuccessListener { task ->
            task.metadata!!.reference!!.downloadUrl
                .addOnSuccessListener { url ->
                    val imgUrl = url.toString()
                    post.imageUri = imgUrl
                    firebaseRef.child("posts").child(post.userName).child(post.postId.toString())
                        .setValue(post)
                }
        }  //firebaseRef.child("posts").child(post.userName).child(post.postId.toString()).setValue("try")
}

fun upload2(post: Post)
{
    firebaseRef = FirebaseDatabase.getInstance().getReference()
    firebaseRef.child("posts").child(post.userName).child(post.postId.toString()).setValue("try")
}