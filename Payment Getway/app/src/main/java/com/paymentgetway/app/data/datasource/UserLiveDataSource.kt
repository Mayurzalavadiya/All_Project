package com.paymentgetway.app.data.datasource

import com.paymentgetway.app.data.pojo.DataWrapper
import com.paymentgetway.app.data.pojo.User
import com.paymentgetway.app.data.pojo.request.EphemeralKeysRequest
import com.paymentgetway.app.data.pojo.request.LoginRequest
import com.paymentgetway.app.data.pojo.request.PaymentIntentRequest
import com.paymentgetway.app.data.pojo.response.GetCustomer
import com.paymentgetway.app.data.pojo.response.GetEphemeralKeys
import com.paymentgetway.app.data.pojo.response.GetPayment
import com.paymentgetway.app.data.repository.UserRepository
import com.paymentgetway.app.data.service.AuthenticationService
import com.paymentgetway.app.data.service.StripeService
import com.stripe.net.ApiResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val stripeService: StripeService
) : BaseDataSource(), UserRepository {

    override suspend fun login(request: LoginRequest): DataWrapper<User> {
        return execute { authenticationService.login(request) }
    }

    override suspend fun getCustomer(): GetCustomer {
        return stripeService.getCustomer()
    }

    override suspend fun getEphemeralKeys(request: String): GetEphemeralKeys {
        return stripeService.getEphemeralKeys(request)
    }

    override suspend fun getPaymentIntents(request: String): GetPayment {
        return stripeService.getPaymentIntents(request)
    }

}
