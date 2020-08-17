package com.nikolam.menu.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.core.model.MenuItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

class FirebaseDataSource (private val firebaseFirestore: FirebaseFirestore) : NetworkDataSource{

    @ExperimentalCoroutinesApi
    override fun fetchMenuItems(): Flow<MenuItem> = callbackFlow{
        firebaseFirestore.collection("menu_items")
                .get()
                .addOnSuccessListener { documents ->
                    try {
                        for (document in documents) {
                            val doc = document.toObject(MenuItem::class.java)
                            doc.itemID = document.id
                            offer(doc)
                        }
                    } catch (e: Exception) {
                        Timber.d("Error getting documents:  ${e.localizedMessage}")
                    }
                }
                .addOnFailureListener { exception ->
                    Timber.d("Failure getting documents:  ${exception.localizedMessage}")
                    close(exception)
                }
            .addOnCompleteListener {
                close()
            }
        awaitClose { Timber.d("Closed") }
    }

    @ExperimentalCoroutinesApi
    override fun fetchMenuItem(itemID: String) : Flow<MenuItem> = callbackFlow {
        val docRef =  firebaseFirestore.collection("menu_items").document(itemID)
        docRef.get()
            .addOnSuccessListener { document ->
                val doc = document.toObject(MenuItem::class.java)
                if (doc != null) {
                    doc?.itemID = document.id
                    offer(doc!!)
                } else {
                    cancel()
                }
            }
            .addOnFailureListener { exception ->
                Timber.d("get failed with $exception")
                cancel()
            }.addOnCompleteListener {
                cancel()
            }

        awaitClose{ Timber.d("Closed") }
    }

}


