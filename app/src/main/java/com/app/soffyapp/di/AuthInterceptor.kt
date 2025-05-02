package com.app.soffyapp.di

import com.app.soffyapp.data.localy.TokenDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Interceptor that adds authentication headers to outgoing requests.
 *
 * This interceptor adds an Authorization header with a bearer token to all requests.
 * In a real application, you would retrieve this token from a secure storage.
 */
@Singleton
class AuthInterceptor
    @Inject
    constructor(
        private val tokenDataStore: TokenDataStore,
    ) : Interceptor {
        /**
         * Intercepts the request and adds authorization headers.
         *
         * @param chain The interceptor chain
         * @return The response from the chain
         */
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            // Get the token from your secure storage
            val token =
                runBlocking {
                    tokenDataStore.getToken().firstOrNull()
                }
            val modifiedRequest =
                if (!token.isNullOrEmpty()) {
                    originalRequest
                        .newBuilder()
                        .header("Authorization", "Bearer $token")
                        .build()
                } else {
                    originalRequest
                }

            return chain.proceed(modifiedRequest)
        }

        /**
         * Gets the authentication token from secure storage.
         *
         * In a real application, this would retrieve the token from
         * encrypted preferences, a keystore, or other secure storage.
         *
         * @return The authentication token or an empty string if not found
         */
        private fun getAuthToken(): String {
            // TODO: Implement actual token retrieval from secure storage
            return "" // Replace with actual implementation
        }
    }
