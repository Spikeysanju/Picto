package www.spikeysanju.picto.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import www.spikeysanju.picto.ui.viewmodel.PostViewModel


val viewModelModule = module {
    viewModel {
        PostViewModel(get(), get())
    }
}