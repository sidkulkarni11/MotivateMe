package com.sid.motivateme.quotenetwork

open class QuoteNetworkEntity {
    var _id: String? = null
    var content: String? = null
    var author: String? = null
    var tags: List<String>? = null
    var authorSlug: String? = null
    var length: Int? = null
    var dateAdded: String? = null
    var dateModified: String? = null
}