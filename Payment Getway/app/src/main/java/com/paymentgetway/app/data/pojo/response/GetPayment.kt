package com.paymentgetway.app.data.pojo.response


import com.google.gson.annotations.SerializedName

data class GetPayment(
    @SerializedName("amount")
    val amount: Int? = null,
    @SerializedName("amount_capturable")
    val amountCapturable: Int? = null,
    @SerializedName("amount_details")
    val amountDetails: AmountDetails? = null,
    @SerializedName("amount_received")
    val amountReceived: Int? = null,
    @SerializedName("application")
    val application: Any? = null,
    @SerializedName("application_fee_amount")
    val applicationFeeAmount: Any? = null,
    @SerializedName("automatic_payment_methods")
    val automaticPaymentMethods: AutomaticPaymentMethods? = null,
    @SerializedName("canceled_at")
    val canceledAt: Any? = null,
    @SerializedName("cancellation_reason")
    val cancellationReason: Any? = null,
    @SerializedName("capture_method")
    val captureMethod: String? = null,
    @SerializedName("client_secret")
    val clientSecret: String? = null,
    @SerializedName("confirmation_method")
    val confirmationMethod: String? = null,
    @SerializedName("created")
    val created: Int? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("customer")
    val customer: String? = null,
    @SerializedName("description")
    val description: Any? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("invoice")
    val invoice: Any? = null,
    @SerializedName("last_payment_error")
    val lastPaymentError: Any? = null,
    @SerializedName("latest_charge")
    val latestCharge: Any? = null,
    @SerializedName("livemode")
    val livemode: Boolean? = null,
    @SerializedName("metadata")
    val metadata: Metadata? = null,
    @SerializedName("next_action")
    val nextAction: Any? = null,
    @SerializedName("object")
    val objectX: String? = null,
    @SerializedName("on_behalf_of")
    val onBehalfOf: Any? = null,
    @SerializedName("payment_method")
    val paymentMethod: Any? = null,
    @SerializedName("payment_method_configuration_details")
    val paymentMethodConfigurationDetails: PaymentMethodConfigurationDetails? = null,
    @SerializedName("payment_method_options")
    val paymentMethodOptions: PaymentMethodOptions? = null,
    @SerializedName("payment_method_types")
    val paymentMethodTypes: List<String?>? = null,
    @SerializedName("processing")
    val processing: Any? = null,
    @SerializedName("receipt_email")
    val receiptEmail: Any? = null,
    @SerializedName("review")
    val review: Any? = null,
    @SerializedName("setup_future_usage")
    val setupFutureUsage: Any? = null,
    @SerializedName("shipping")
    val shipping: Any? = null,
    @SerializedName("source")
    val source: Any? = null,
    @SerializedName("statement_descriptor")
    val statementDescriptor: Any? = null,
    @SerializedName("statement_descriptor_suffix")
    val statementDescriptorSuffix: Any? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("transfer_data")
    val transferData: Any? = null,
    @SerializedName("transfer_group")
    val transferGroup: Any? = null
) {
    data class AmountDetails(
        @SerializedName("tip")
        val tip: Tip? = null
    ) {
        class Tip
    }

    data class AutomaticPaymentMethods(
        @SerializedName("allow_redirects")
        val allowRedirects: String? = null,
        @SerializedName("enabled")
        val enabled: Boolean? = null
    )

    class Metadata

    data class PaymentMethodConfigurationDetails(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("parent")
        val parent: Any? = null
    )

    data class PaymentMethodOptions(
        @SerializedName("card")
        val card: Card? = null
    ) {
        data class Card(
            @SerializedName("installments")
            val installments: Any? = null,
            @SerializedName("mandate_options")
            val mandateOptions: Any? = null,
            @SerializedName("network")
            val network: Any? = null,
            @SerializedName("request_three_d_secure")
            val requestThreeDSecure: String? = null
        )
    }
}