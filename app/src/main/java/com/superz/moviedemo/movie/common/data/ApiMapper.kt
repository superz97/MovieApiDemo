package com.superz.moviedemo.movie.common.data

interface ApiMapper<Domain, Entity> {
    fun mapToDomain(apiDto: Entity): Domain
    // fun mapFromDomain(domain: Domain): Entity
}