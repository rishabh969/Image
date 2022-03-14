package com.example.imageapp.helper

import java.io.Serializable

data class Response(
	val response: List<ResponseItem?>? = null
) :Serializable

data class ResponseItem(
	val date: String? = null,
	val copyright: String? = null,
	val mediaType: String? = null,
	val hdurl: String? = null,
	val serviceVersion: String? = null,
	val explanation: String? = null,
	val title: String? = null,
	val url: String? = null
):Serializable

