package com.eos_gnss.producthuntsampleappp.data.model

import java.io.Serializable

data class Post(
    var totalCount: Int,
    var edges: List<Node>
): Serializable