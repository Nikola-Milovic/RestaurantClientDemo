package com.nikolam.order.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.order.data.model.Order
import timber.log.Timber

class FirebaseDataSource (private val firebaseFirestore: FirebaseFirestore) : NetworkDataSource {

    override fun placeOrder(order: Order) {
        val orderToSave = hashMapOf(
            "tableNumber" to order.tableNumber,
            "orderItems" to order.orderItems
        )

        firebaseFirestore.collection("orders").add(orderToSave)
            .addOnSuccessListener { Timber.d("DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Timber.d("Error writing document ${e.localizedMessage}") }
    }
}



