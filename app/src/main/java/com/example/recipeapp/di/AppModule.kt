package com.example.recipeapp.di

import android.content.Context
import androidx.room.Room
import com.example.recipeapp.data.local.BookMarkMealDao
import com.example.recipeapp.data.local.MealLocalDb
import com.example.recipeapp.data.local.UserDao
import com.example.recipeapp.data.remote.MealsApiService
import com.example.recipeapp.data.repository.AuthRepoImpl
import com.example.recipeapp.data.repository.BookMarkRepoImpl
import com.example.recipeapp.data.repository.MealsRepositoryImpl
import com.example.recipeapp.data.repository.UserRepoImpl
import com.example.recipeapp.data.setting.UserPreferences
import com.example.recipeapp.domain.repo.AuthenticationRepository
import com.example.recipeapp.domain.repo.BookMarkRepository
import com.example.recipeapp.domain.repo.MealsRepository
import com.example.recipeapp.domain.repo.UserRepository
import com.example.recipeapp.domain.usecases.AuthUseCases
import com.example.recipeapp.domain.usecases.DeleteBookMarkMealUseCase
import com.example.recipeapp.domain.usecases.GetAllBookMarkMealUseCase
import com.example.recipeapp.domain.usecases.GetAllCategoriesUsecase
import com.example.recipeapp.domain.usecases.GetCategoryMealsUseCase
import com.example.recipeapp.domain.usecases.GetMealDetailByIdUseCase
import com.example.recipeapp.domain.usecases.GetRandomMealUseCase
import com.example.recipeapp.domain.usecases.GetUserByIdUseCase
import com.example.recipeapp.domain.usecases.InsertBookMarkMealUseCase
import com.example.recipeapp.domain.usecases.InsertUserUseCase
import com.example.recipeapp.domain.usecases.LoginUserWithEmailUseCase
import com.example.recipeapp.domain.usecases.LogoutUserUseCase
import com.example.recipeapp.domain.usecases.MealsUseCases
import com.example.recipeapp.domain.usecases.RegisterUserWithEmailUseCase
import com.example.recipeapp.domain.usecases.ResetPasswordWithEmailUseCase
import com.example.recipeapp.domain.usecases.UserLocalUseCase
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
    fun providesBookMarkMealDao(db: MealLocalDb): BookMarkMealDao {
        return db.bookMarkMealDao
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
    fun providesUserRepo(userDao: UserDao, firebaseAuth: FirebaseAuth): UserRepository {
        return UserRepoImpl(userDao, firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesAuthUseCases(
        authenticationRepository: AuthenticationRepository,
    ): AuthUseCases {
        return AuthUseCases(
            loginUserWithEmailUseCase = LoginUserWithEmailUseCase(authenticationRepository),
            registerUserWithEmailUseCase = RegisterUserWithEmailUseCase(authenticationRepository),
            logoutUserUseCase = LogoutUserUseCase(authenticationRepository),
            resetPasswordWithEmailUseCase = ResetPasswordWithEmailUseCase(authenticationRepository)
        )
    }

    @Provides
    @Singleton
    fun providesUserLocalUseCases(
        userRepository: UserRepository
    ): UserLocalUseCase {
        return UserLocalUseCase(
            insertUserUseCase = InsertUserUseCase(userRepository),
            getUserByIdUseCase = GetUserByIdUseCase(userRepository)
        )
    }

    @Provides
    @Singleton
    fun dataStorePreferences(@ApplicationContext context: Context): UserPreferences =
        UserPreferences(context = context, name = "settings")

    @Provides
    @Singleton
    fun providesMealUseCases(
        mealsRepository: MealsRepository,
        bookMarkRepository: BookMarkRepository
    ): MealsUseCases {
        return MealsUseCases(
            getAllCategoriesUsecase = GetAllCategoriesUsecase(mealsRepository),
            getAllBookMarkMealUseCase = GetAllBookMarkMealUseCase(bookMarkRepository),
            getCategoryMealsUseCase = GetCategoryMealsUseCase(mealsRepository),
            getMealDetailByIdUseCase = GetMealDetailByIdUseCase(mealsRepository),
            getRandomMealUseCase = GetRandomMealUseCase(mealsRepository),
            insertBookMarkMealUseCase = InsertBookMarkMealUseCase(bookMarkRepository),
            deleteBookMarkMealUseCase = DeleteBookMarkMealUseCase(bookMarkRepository)
        )
    }

    @Provides
    @Singleton
    fun providesMealRepositroy(
        mealsApiService: MealsApiService
    ): MealsRepository {
        return MealsRepositoryImpl(mealsApiService)
    }

    @Provides
    @Singleton
    fun providesBookMarkRepo(
        bookMarkMealDao: BookMarkMealDao,
        firebaseAuth: FirebaseAuth
    ): BookMarkRepository {
        return BookMarkRepoImpl(bookMarkMealDao, firebaseAuth)
    }
}