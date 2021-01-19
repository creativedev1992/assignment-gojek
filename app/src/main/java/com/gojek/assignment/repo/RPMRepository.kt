package com.gojek.assignment.repo

import com.gojek.assignment.util.Resource

interface RPMRepository {
    fun getRPM(): kotlinx.coroutines.flow.Flow<Resource<Any>>
}