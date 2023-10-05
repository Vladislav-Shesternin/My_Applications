package com.veldan.lbjt.util

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.veldan.lbjt.MainActivity

class BillingUtil(val activity: MainActivity) {

    private val purchasesUpdatedListener = PurchasesUpdatedListener { billingResult, purchases ->
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                log("PurchasesUpdatedListener SUCCESS: $purchases")
                purchases?.let { it.onEach { purchase -> consumePurchase(purchase) } }
            }
            BillingClient.BillingResponseCode.USER_CANCELED -> log("PurchasesUpdatedListener CANCELED")
            else                                            -> log("PurchasesUpdatedListener ERROR")
        }
    }

    private var billingClient = BillingClient.newBuilder(activity)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    private var handlePurchaseBlock: (Purchase) -> Unit = {}


    fun startConnection(successBlock: () -> Unit = {}) {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    log("onBillingSetupFinished: SUCCESS")
                    successBlock()
                }
            }
            override fun onBillingServiceDisconnected() {
                log("onBillingServiceDisconnected")
                startConnection()
            }
        })
    }

    fun getProductDetails(product: Product, block: (BillingResult, List<ProductDetails>) -> Unit = {_,_->}) {
        val productList = listOf(QueryProductDetailsParams.Product.newBuilder()
            .setProductId(product.id)
            .setProductType(BillingClient.ProductType.INAPP)
            .build()
        )

        val queryProductDetailsParams = QueryProductDetailsParams.newBuilder()
            .setProductList(productList)
            .build()

        billingClient.queryProductDetailsAsync(queryProductDetailsParams) { billingResult, productDetailsList ->
            log("queryProductDetailsAsync: $billingResult, $productDetailsList")
            block(billingResult, productDetailsList)
        }
    }

    fun launchBillingFlow(productDetails: ProductDetails, handlePurchaseBlock: (Purchase) -> Unit = {}) {
        this.handlePurchaseBlock = handlePurchaseBlock

        val productDetailsParamsList = listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                .setProductDetails(productDetails)
                .build()
        )

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()

        billingClient.launchBillingFlow(activity, billingFlowParams)
    }

    private fun consumePurchase(purchase: Purchase) {
        val consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()
        billingClient.consumeAsync(consumeParams) { billingResult, purchaseToken ->
            log("consumeAsync: $billingResult, $purchaseToken")
            handlePurchaseBlock(purchase)
        }
    }



    enum class Product(val id: String) {
        GIFT_1_DOLLAR("gift_1_usd"),
        GIFT_100_DOLLAR("gift_100_usd"),
    }

}