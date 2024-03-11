package com.paymentgetway.app.utils

import android.content.Context
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.math.BigDecimal
import java.math.RoundingMode

object UtilsPaymentClass {
    val CENTS = BigDecimal(100)
    const val COUNTRY_CODE = "US"
    const val CURRENCY_CODE = "USD"
    val SUPPORTED_NETWORKS = listOf(
        "AMEX",
        "DISCOVER",
        "JCB",
        "MASTERCARD",
        "VISA"
    )
    val SUPPORTED_METHODS = listOf(
        "PAN_ONLY",
        "CRYPTOGRAM_3DS"
    )
    private const val PAYMENT_GATEWAY_TOKENIZATION_NAME = "your gateway name"
    val PAYMENT_GATEWAY_TOKENIZATION_PARAMETERS = mapOf(
        "gateway" to PAYMENT_GATEWAY_TOKENIZATION_NAME,
        "gatewayMerchantId" to "Gateway Merchant Id"
    )

    // if you are using gateway integration like stripe you need to add
// "Gateway" to "stirpe"
// "gatewayMerchantId" to "ADYJ$%$A&%DABDAH44fd4" //example stripe key
    private val baseRequest = JSONObject().apply {
        put("apiVersion", 2)
        put("apiVersionMinor", 0)
    }

    private fun gatewayTokenizationSpecification(): JSONObject {
        return JSONObject().apply {
// if you use direct integration you need to add "DIRECT" in "type"
// parameter
            put("type", "DIRECT")
            put("parameters", JSONObject(DIRECT_TOKENIZATION_PARAMETERS))
        }
    }

    private val allowedCardNetworks = JSONArray(SUPPORTED_NETWORKS)
    private val allowedCardAuthMethods = JSONArray(SUPPORTED_METHODS)
    private fun baseCardPaymentMethod(): JSONObject {
        return JSONObject().apply {
            val parameters = JSONObject().apply {
                put("allowedAuthMethods", allowedCardAuthMethods)
                put("allowedCardNetworks", allowedCardNetworks)
                put("billingAddressRequired", true)
                put("billingAddressParameters", JSONObject().apply {
                    put("format", "FULL")
                })
            }
            put("type", "CARD")
            put("parameters", parameters)
        }
    }

    private fun cardPaymentMethod(): JSONObject {
        val cardPaymentMethod = baseCardPaymentMethod()
        cardPaymentMethod.put("tokenizationSpecification", gatewayTokenizationSpecification())
        return cardPaymentMethod
    }

    fun isReadyToPayRequest(): JSONObject? {
        return try {
            baseRequest.apply {
                put("allowedPaymentMethods", JSONArray().put(baseCardPaymentMethod()))
            }
        } catch (e: JSONException) {
            null
        }
    }

    private val merchantInfo: JSONObject =
        JSONObject().apply {
            put("merchantName", "Mayur Zalavadiya")
            put("merchantId", "BCR2DN4T7XR4VJ2P")
        }

    fun createPaymentsClient(context: Context): PaymentsClient {
        val walletOptions = Wallet.WalletOptions.Builder()
            .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
            .build()
        return Wallet.getPaymentsClient(context, walletOptions)
    }

    @Throws(JSONException::class)
    private fun getTransactionInfo(price: String): JSONObject {
        return JSONObject().apply {
            put("totalPrice", price)
            put("totalPriceStatus", "FINAL")
            put("countryCode", COUNTRY_CODE)
            put("currencyCode", CURRENCY_CODE)
        }
    }

    fun getPaymentDataRequest(priceCemts: Long): JSONObject {
        return baseRequest.apply {
            put("allowedPaymentMethods", JSONArray().put(cardPaymentMethod()))
            put("transactionInfo", getTransactionInfo(priceCemts.centsToString()))
            put("merchantInfo", merchantInfo)
// An optional shipping address requirement is a top-level property of the
// PaymentDataRequest JSON object.
            val shippingAddressParameters = JSONObject().apply {
                put("phoneNumberRequired", false)
                put("allowedCountryCodes", JSONArray(listOf("US", "GB", "PT")))
            }
            put("shippingAddressParameters", shippingAddressParameters)
            put("shippingAddressRequired", true)
        }
    }

    const val DIRECT_TOKENIZATION_PUBLIC_KEY =
        "BAuWjOEcrAedJs+PhH6WW4XomLzrq87WGB0/8nUc57gUw+m58jw0cB0vw6Nj5YCc5mQOaMII4H3iIIoQveerU2M="

    /**
     * Parameters required for `DIRECT` tokenization.
     * Only used for `DIRECT` tokenization. Can be removed when using `PAYMENT_GATEWAY`
     * tokenization.
     *
     * @value #DIRECT_TOKENIZATION_PARAMETERS
     */
    val DIRECT_TOKENIZATION_PARAMETERS = mapOf(
        "protocolVersion" to "ECv2",
        "publicKey" to DIRECT_TOKENIZATION_PUBLIC_KEY,
    )

    fun Long.centsToString() = BigDecimal(this)
        .divide(CENTS)
        .setScale(2, RoundingMode.HALF_EVEN)
        .toString()
}