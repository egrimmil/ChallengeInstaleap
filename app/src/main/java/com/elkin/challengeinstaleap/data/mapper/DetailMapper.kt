package com.elkin.challengeinstaleap.data.mapper

import com.elkin.challengeinstaleap.core.util.Const
import com.elkin.challengeinstaleap.data.local.entities.DetailMediaEntity
import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaMovieDto
import com.elkin.challengeinstaleap.data.remote.dto.DetailMediaTvDto
import com.elkin.challengeinstaleap.data.remote.dto.MediaGenreDto
import com.elkin.challengeinstaleap.data.remote.dto.SeasonMediaTvDto
import com.elkin.challengeinstaleap.domain.model.DetailMedia
import com.elkin.challengeinstaleap.domain.model.GenresMedia
import com.elkin.challengeinstaleap.domain.model.SeasonsMedia
import com.google.gson.Gson

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

fun DetailMediaMovieDto.toDetailMediaEntity(): DetailMediaEntity = DetailMediaEntity(
    name = title,
    overview = overview,
    imgDetail = poster_path,
    isMovie = true,
    dateRelease = release_date,
    genre_ids = Gson().toJson(genres),
    votes = vote_average,
    runtime = runtime,
    seasons = "",
)

fun DetailMediaTvDto.toDetailMediaEntity(): DetailMediaEntity = DetailMediaEntity(
    name = name,
    overview = overview,
    imgDetail = poster_path,
    isMovie = true,
    dateRelease = first_air_date,
    genre_ids = Gson().toJson(genres),
    votes = vote_average,
    runtime = 0,
    seasons = Gson().toJson(seasons),
)

fun DetailMediaEntity.toDetailMedia(): DetailMedia = DetailMedia(
    id,
    name,
    overview,
    imgDetail,
    isMovie,
    dateRelease,
    Gson().fromJson(genre_ids, Array<GenresMedia>::class.java).toList(),
    votes,
    runtime,
    Gson().fromJson(seasons, Array<SeasonsMedia>::class.java).toList(),
)