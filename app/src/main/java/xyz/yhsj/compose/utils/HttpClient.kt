package xyz.yhsj.compose.utils

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.util.*


//@KtorExperimentalAPI
val httpClient = HttpClient(Android) {
    //默认请求
//        defaultRequest {
//            host = "127.0.0.1"
//            port = 8080
//        }

    install(HttpCookies) {
        // Will keep an in-memory map with all the cookies from previous requests.
//            storage = AcceptAllCookiesStorage()

        // Will ignore Set-Cookie and will send the specified cookies.
        storage = ConstantCookiesStorage()
    }

    //字符集
    Charsets {
        // Allow to use `UTF_8`.
//            register(Charsets.UTF_8)

        // Allow to use `ISO_8859_1` with quality 0.1.
//            register(Charsets.ISO_8859_1, quality=0.1f)

        // Specify Charset to send request(if no charset in request headers).
//            sendCharset = Charsets.UTF_8

        // Specify Charset to receive response(if no charset in response headers).
//            responseCharsetFallback = Charsets.UTF_8
    }

    //重定向
//    followRedirects = true
//
//    install(HttpRedirect) {
//        allowHttpsDowngrade = true
//    }



    //json序列化
    install(JsonFeature) {
        serializer = GsonSerializer()
    }
    //日志
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
    }
    //超时
    install(HttpTimeout) {
        // timeout config
        requestTimeoutMillis = 1000
    }

    //响应验证
//        expectSuccess = false
//        HttpResponseValidator {
//            validateResponse { response ->
//                val statusCode = response.status.value
//                when (statusCode) {
//                    in 300..399 -> throw RedirectResponseException(response)
//                    in 400..499 -> throw ClientRequestException(response)
//                    in 500..599 -> throw ServerResponseException(response)
//                }
//
//                if (statusCode >= 600) {
//                    throw ResponseException(response)
//                }
//            }
//
//            handleResponseException { cause: Throwable ->
//                // ...
//            }
//        }

}


