package com.mahnoosh.navigation

import androidx.navigation.NamedNavArgument

object NavigationDirections {
    object Auth {
        val splash = object : NavigationCommand {

            override val arguments = emptyList<NamedNavArgument>()

            override val destination = "splash"

        }
        val signUp = object : NavigationCommand {

            override val arguments = emptyList<NamedNavArgument>()

            override val destination = "signUp"

        }
        val signInWithEmailAndPassword = object : NavigationCommand {

            override val arguments = emptyList<NamedNavArgument>()

            override val destination = "signInWithEmailAndPassword"

        }
        val signInWithGoogle = object : NavigationCommand {

            override val arguments = emptyList<NamedNavArgument>()

            override val destination = "signInWithGoogle"

        }
    }

    object Dashboard {
        val dashboard = object : NavigationCommand {

            override val arguments = emptyList<NamedNavArgument>()

            override val destination = "dashboard"
        }
    }
}