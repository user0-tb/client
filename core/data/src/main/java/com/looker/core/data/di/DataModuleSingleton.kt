package com.looker.core.data.di

import com.looker.core.data.fdroid.sync.IndexDownloader
import com.looker.core.data.fdroid.sync.IndexManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.fdroid.index.IndexConverter

@Module
@InstallIn(SingletonComponent::class)
object DataModuleSingleton {

	@Provides
	fun provideIndexManager(
		downloader: IndexDownloader
	): IndexManager = IndexManager(
		indexDownloader = downloader,
		converter = IndexConverter()
	)

}