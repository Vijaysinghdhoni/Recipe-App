package com.example.recipeapp.di

import android.content.Context
import androidx.compose.ui.unit.Constraints
import androidx.room.Room
import com.example.recipeapp.data.setting.UserPreferences
import com.example.recipeapp.data.local.MealLocalDb
import com.example.recipeapp.data.local.UserDao
import com.example.recipeapp.data.remote.MealsApiService
import com.example.recipeapp.data.repository.AuthRepoImpl
import com.example.recipeapp.data.repository.MealsRepositoryImpl
import com.example.recipeapp.data.repository.UserRepoImpl
import com.example.recipeapp.domain.repo.AuthenticationRepository
import com.example.recipeapp.domain.repo.MealsRepository
import com.example.recipeapp.domain.repo.UserRepository
import com.example.recipeapp.domain.usecases.AuthUseCases
import com.example.recipeapp.domain.usecases.GetAllCategoriesUsecase
import com.example.recipeapp.domain.usecases.GetRandomMealUseCase
import com.example.recipeapp.domain.usecases.GetUserByIdUseCase
import com.example.recipeapp.domain.usecases.InsertUserUseCase
import com.example.recipeapp.domain.usecases.LoginUserWithEmailUseCase
import com.example.recipeapp.domain.usecases.MealsUseCases
import com.example.recipeapp.domain.usecases.RegisterUserWithEmailUseCase
import com.example.recipeapp.util.Constants
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRoomDb(@ApplicationContext context: Context): MealLocalDb {
        return Room.databaseBuilder(context, MealLocalDb::class.java, "Meal_Db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesUserDao(db: MealLocalDb): UserDao {
        return db.userDao
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesMealsApiService(retrofit: Retrofit): MealsApiService {
        return retrofit.create(MealsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesAuthRepo(firebaseAuth: FirebaseAuth): AuthenticationRepository {
        return AuthRepoImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesUserRepoImpl(userDao: UserDao): UserRepository {
        return UserRepoImpl(userDao)
    }

    @Provides
    @Singleton
    fun providesAuthUseCases(
        authenticationRepository: AuthenticationRepository,
        userRepository: UserRepository
    ): AuthUseCases {
        return AuthUseCases(
            getUserByIdUseCase = GetUserByIdUseCase(userRepository),
            insertUserUseCase = InsertUserUseCase(userRepository),
            loginUserWithEmailUseCase = LoginUserWithEmailUseCase(authenticationRepository),
            registerUserWithEmailUseCase = RegisterUserWithEmailUseCase(authenticationRepository)
        )
    }

    @Provides
    @Singleton
    fun dataStorePreferences(@ApplicationContext context: Context): UserPreferences =
        UserPreferences(context = context, name = "settings")

    @Provides
    @Singleton
    fun providesMealUseCases(
        getAllCategoriesUsecase: GetAllCategoriesUsecase,
        getRandomMealUseCase: GetRandomMealUseCase
    ): MealsUseCases {
        return MealsUseCases(
            getAllCategoriesUsecase,
            getRandomMealUseCase
        )
    }

    @Provides
    @Singleton
    fun providesMealRepositroy(
        mealsApiService: MealsApiService
    ): MealsRepository {
        return MealsRepositoryImpl(mealsApiService)
    }
}