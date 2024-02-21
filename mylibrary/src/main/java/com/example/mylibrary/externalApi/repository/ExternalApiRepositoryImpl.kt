package com.example.mylibrary.externalApi.repository

import com.example.mylibrary.commons.BaseException
import com.example.mylibrary.commons.Constants
import com.example.mylibrary.commons.ExceptionMock
import com.example.mylibrary.commons.network.NetworkUtils
import com.example.mylibrary.externalApi.repository.models.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExternalApiRepositoryImpl : BaseException(), ExternalApiRepository {

    /*private fun fakeApiCallStringJson(): List<PostsResponseModel> {
        val json = "[\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"qui est esse\",\n" +
                "    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"userId\": 1,\n" +
                "    \"id\": 3,\n" +
                "    \"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",\n" +
                "    \"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"\n" +
                "  }\n" +
                "]"


        val collectionType: Type =
            object : TypeToken<List<PostsResponseModel?>?>() {}.type

        return Gson().fromJson(json, collectionType)
    }*/

    override suspend fun getPosts(): ExternalApiRepositoryState.GetPosts {

        val api = NetworkUtils(Constants.Path.API1)
        val posts: MutableList<PostModel> = mutableListOf()

        return withContext(Dispatchers.IO) {
            try {
                val res = api.getPosts()

                if (res.isSuccessful) {
                    for (i in res.body().orEmpty()) {
                        val item = PostModel(i.userId, i.id, i.title, i.body)
                        posts.add(item)
                    }
                    ExternalApiRepositoryState.GetPosts.Success(posts)
                } else {
                    ExternalApiRepositoryState.GetPosts.ErrorApi(api.getPosts().errorBody())
                }
            } catch (e: ExceptionMock) {
                ExternalApiRepositoryState.GetPosts.Error(analyseBackEndException(e))
            }
        }



        /*val response = fakeApiCallStringJson()
        return withContext(Dispatchers.IO) {
            try {
                val posts: MutableList<PostModel> = mutableListOf()

                for (i in response) {
                    val item = PostModel(i.userId, i.id, i.title, i.body)
                    posts.add(item)
                }

                ExternalApiRepositoryState.GetPosts.Success(posts)
            } catch (e: ExceptionMock) {
                ExternalApiRepositoryState.GetPosts.Error(analyseBackEndException(e))
            }
        }*/
    }
}