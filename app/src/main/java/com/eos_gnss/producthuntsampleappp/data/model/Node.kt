package com.eos_gnss.producthuntsampleappp.data.model

import java.io.Serializable


data class Node(
    var name: String,
    var thumbnail: Thumbnail,
    var tagline: String,
    var votesCount: Int,
    var makers: List<Maker>
): Serializable
