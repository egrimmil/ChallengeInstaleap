package com.elkin.challengeinstaleap.data.mapper

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaMovieDto
import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaTvDto
import com.elkin.challengeinstaleap.data.remote.dto.MediaGenreDto
import com.elkin.challengeinstaleap.data.remote.dto.SeasonMediaTvDto
import com.elkin.challengeinstaleap.domain.model.DetailMedia
import com.elkin.challengeinstaleap.domain.model.GenresMedia
import com.elkin.challengeinstaleap.domain.model.SeasonsMedia

fun DetailMediaMovieDto.toDetailMedia(): DetailMedia = DetailMedia(
    id = id,
    name = title,
    overview = overview,
    imgDetail = Const.URL_IMG + if (backdrop_path.isNullOrEmpty()) poster_path else backdrop_path,
    isMovie = true,
    dateRelease = release_date,
    genre_ids = genres.map { it.toGenresMedia() }.toList(),
    votes = vote_average,
    runtime = runtime,
    seasons = emptyList()
)

fun DetailMediaTvDto.toDetailMedia(): DetailMedia = DetailMedia(
    id = id,
    name = name,
    overview = overview,
    imgDetail = Const.URL_IMG + if (backdrop_path.isNullOrEmpty()) poster_path else backdrop_path,
    isMovie = false,
    dateRelease = first_air_date,
    genre_ids = genres.map { it.toGenresMedia() }.toList(),
    votes = vote_average,
    runtime = 0,
    seasons = seasons.map { it.toSeasonMedia() }.toList()
)

fun MediaGenreDto.toGenresMedia(): GenresMedia = GenresMedia(
    id,
    name
)

fun SeasonMediaTvDto.toSeasonMedia(): SeasonsMedia = SeasonsMedia(
    id,
    name,
    overview,
    season_number,
    episode_count,
    poster_path ?: "",
)