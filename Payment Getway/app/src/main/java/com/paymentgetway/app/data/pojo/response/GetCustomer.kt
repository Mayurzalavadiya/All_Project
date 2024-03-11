package com.paymentgetway.app.data.pojo.response


import com.google.gson.annotations.SerializedName

data class GetCustomer(
    @SerializedName("address")
    val address: Any? = null,
    @SerializedName("balance")
    val balance: Int? = null,
    @SerializedName("created")
    val created: Int? = null,
    @SerializedName("currency")
    val currency: Any? = null,
    @SerializedName("default_source")
    val defaultSource: Any? = null,
    @SerializedName("delinquent")
    val delinquent: Boolean? = null,
    @SerializedName("description")
    val description: Any? = null,
    @SerializedName("discount")
    val discount: Any? = null,
    @SerializedName("email")
    val email: Any? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("invoice_prefix")
    val invoicePrefix: String? = null,
    @SerializedName("invoice_settings")
    val invoiceSettings: InvoiceSettings? = null,
    @SerializedName("livemode")
    val livemode: Boolean? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("name")
    val name: Any? = null,
    @SerializedName("next_invoice_sequence")
    val nextInvoiceSequence: Int? = null,
    @SerializedName("object")
    val objectX: String? = null,
    @SerializedName("phone")
    val phone: Any? = null,
    @SerializedName("preferred_locales")
    val preferredLocales: List<Any?>? = null,
    @SerializedName("shipping")
    val shipping: Any? = null,
    @SerializedName("tax_exempt")
    val taxExempt: String? = null,
    @SerializedName("test_clock")
    val testClock: Any? = null
) {
    data class InvoiceSettings(
        @SerializedName("custom_fields")
        val customFields: Any? = null,
        @SerializedName("default_payment_method")
        val defaultPaymentMethod: Any? = null,
        @SerializedName("footer")
        val footer: Any? = null,
        @SerializedName("rendering_options")
        val renderingOptions: Any? = null
    )

    class Metadata
}