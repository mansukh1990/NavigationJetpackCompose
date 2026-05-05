package com.example.typesafenavigation.authenticationWithFirebase

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthenticationManager(
        app: Application,
        auth: FirebaseAuth
    ): AuthenticationManager = AuthenticationManager(app, auth)
}


