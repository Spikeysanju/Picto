package www.spikeysanju.picto.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import www.spikeysanju.picto.data.api.db.PostDatabase

val databaseModule = module {
    single {
        PostDatabase(androidApplication())
    }
    single {
        get<PostDatabase>().getPostDao()
    }
}