package com.jintoga.animekyabin.injection.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jintoga.animekyabin.ViewModelFactory
import com.jintoga.animekyabin.ViewModelKey
import com.jintoga.animekyabin.ui.animes.AnimesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AnimesViewModel::class)
    internal abstract fun postAnimesViewModel(viewModel: AnimesViewModel): ViewModel


    //Add ViewModels here

}
