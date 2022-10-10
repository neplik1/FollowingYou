package com.example.followingyou.domain.model

data class NewsItem(
    val name: String,
    val type: Int,
    val source: Boolean,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
