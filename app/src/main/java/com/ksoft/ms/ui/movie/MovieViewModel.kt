package com.ksoft.ms.ui.movie

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MovieViewModel @Inject constructor() : ViewModel() {

    val movieAdapter = MovieAdapter()

    init {
        movieAdapter.submitList(
            listOf(
                MovieItem.Item(
                    title = "파리의 인어",
                    link = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=194486",
                    image = "https://ssl.pstatic.net/imgmovie/mdi/mit110/1944/194486_P00_172123.jpg",
                    subtitle = "<b>A</b> Mermaid in Paris",
                    pubDate = "2020",
                    director = "마티아스 말지우|",
                    actor = "마릴린 리마|니콜라스 뒤보셸|",
                    userRating = "6.49"
                ),
                MovieItem.Item(
                    title = "파리의 인어",
                    link = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=194486",
                    image = "https://ssl.pstatic.net/imgmovie/mdi/mit110/1641/164167_P01_131840.jpg",
                    subtitle = "<b>A</b> Mermaid in Paris",
                    pubDate = "2020",
                    director = "마티아스 말지우|",
                    actor = "마릴린 리마|니콜라스 뒤보셸|",
                    userRating = "6.49"
                ),
                MovieItem.Item(
                    title = "파리의 인어",
                    link = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=194486",
                    image = "https://ssl.pstatic.net/imgmovie/mdi/mit110/1943/194321_P01_112250.jpg",
                    subtitle = "<b>A</b> Mermaid in Paris",
                    pubDate = "2020",
                    director = "마티아스 말지우|",
                    actor = "마릴린 리마|니콜라스 뒤보셸|",
                    userRating = "6.49"
                ),
                MovieItem.Item(
                    title = "파리의 인어",
                    link = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=194486",
                    image = "https://ssl.pstatic.net/imgmovie/mdi/mit110/1893/189368_P08_160901.jpg",
                    subtitle = "<b>A</b> Mermaid in Paris",
                    pubDate = "2020",
                    director = "마티아스 말지우|",
                    actor = "마릴린 리마|니콜라스 뒤보셸|",
                    userRating = "6.49"
                )
            )
        )
    }

}