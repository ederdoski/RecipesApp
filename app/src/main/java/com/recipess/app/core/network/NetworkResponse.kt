package com.recipess.app.core.network

import com.salmo23.bibliaydevocional.core.network.model.NetworkData
import retrofit2.Response

class NetworkResponse<T>(responseNetwork: Response<T>) {
    var network: NetworkData = NetworkData(responseNetwork.code(), responseNetwork.message())
    var data: T? = responseNetwork.body()
}