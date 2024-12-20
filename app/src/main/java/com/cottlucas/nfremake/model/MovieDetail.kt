package com.cottlucas.nfremake.model

data class MovieDetail(
    val movie: Movie,
    val similars: List<Movie>
)
