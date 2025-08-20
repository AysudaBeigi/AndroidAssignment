package ir.miare.androidcodechallenge.data.api

import ir.logicbase.mockfit.Mock
import ir.miare.androidcodechallenge.data.model.FakeData
import retrofit2.http.GET

interface RankingApiService {
    @Mock("data.json")
    @GET("list")
    fun getData(): List<FakeData>
}