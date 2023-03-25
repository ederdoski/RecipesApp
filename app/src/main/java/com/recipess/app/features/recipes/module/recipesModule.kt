package com.recipess.app.features.recipes.module

import com.recipess.app.core.network.API_AUTH
import com.recipess.app.features.recipes.datasource.repository.RecipeRepository
import com.recipess.app.features.recipes.datasource.service.RecipeService
import com.recipess.app.features.recipes.usecase.RecipesUseCase
import com.recipess.app.features.recipes.viewmodel.RecipesVMDelegate
import com.recipess.app.features.recipes.viewmodel.RecipesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val recipesModule: Module =  module {

    //--- Inject viewModel
    viewModel {
        RecipesViewModel(
            recipesUseCase = get(),
            recipesVMDelegate = get()
        )
    }

    //--- Inject repository
    single<RecipeRepository> {
        RecipeRepository(
            recipeService = get()
        )
    }

    //--- Inject useCase

    single { providerCardUseCase(get()) }

    //--- Inject service

    single { providerRecipeService(get(named(API_AUTH))) }
    factory { providerRecipeVMDelegate() }
}

fun providerRecipeVMDelegate(): RecipesVMDelegate {
    return RecipesVMDelegate()
}

fun providerRecipeService(retrofit: Retrofit): RecipeService {
    return retrofit.create(RecipeService::class.java)
}

fun providerCardUseCase(locationRepository: RecipeRepository): RecipesUseCase {
    return RecipesUseCase(locationRepository)
}
