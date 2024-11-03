package com.example.educai.data.services

import com.example.educai.data.model.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @GET("classroom/{classroomId}/posts")
    suspend fun getPostsByClassroom(@Path("classroomId") classroomId: String): List<Post>
}

//async createPost(body: { title: string, description: string, datePosting: string, classroomId: string }, file: File): Promise<PostType> {
//    const formData = new FormData()
//    formData.append('title', body.title)
//    formData.append('description', body.description)
//    formData.append('datePosting', body.datePosting)
//    formData.append('classroomId', body.classroomId)
//    formData.append('file', file)
//
//    const response = await this.axios.post('/posts', formData)
//    const post: PostType = response.data
//            return  post
//}
//
//async getUrlArquivoPost(postId: string): Promise<string> {
//    return (await this.axios.get(`/posts/${postId}/download`)).data
//}
//
//async deletePost(postId: string): Promise<void> {
//    return (await this.axios.delete(`/posts/${postId}`))
//}
//
//async updatePost(postId: string, body: { title?: string, description?: string }): Promise<void> {
//    return (await this.axios.patch(`/posts/${postId}`, body))
//}
//
//async getPostsByClassroom(classroomId: string): Promise<PostType[]> {
//    return (await this.axios.get(`/classroom/${classroomId}/posts`)).data
//}
//
//async getClassworkById(classworkId: string): Promise<Classwork> {
//    return (await this.axios.get(`/classwork/${classworkId}`)).data
//}