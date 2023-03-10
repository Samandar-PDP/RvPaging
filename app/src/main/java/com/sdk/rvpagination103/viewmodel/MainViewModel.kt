package com.sdk.rvpagination103.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sdk.rvpagination103.model.CharacterData
import com.sdk.rvpagination103.network.CharacterPagingSource
import com.sdk.rvpagination103.network.RetroInstance
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    private val apiService = RetroInstance.apiService()
    fun getCharacterList(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 40, maxSize = 200), pagingSourceFactory = {
            CharacterPagingSource(apiService)
        }).flow.cachedIn(viewModelScope)
    }
}