package com.globant.imdb.model.entity

import com.globant.imdb.database.entities.User

data class AuthState(
    var isLogged: Boolean = false,
    var user: User
)