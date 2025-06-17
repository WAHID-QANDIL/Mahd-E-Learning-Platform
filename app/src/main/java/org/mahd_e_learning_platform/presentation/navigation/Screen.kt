package org.mahd_e_learning_platform.presentation.navigation

enum class RoutingDestinations {
    HOME,
    FORGET_PASSWORD,
    WELCOME,
    DETAILS,
    PROFILE,
    PAYMENT,
    COURSE_CONTENT,
    MY_COURSES,
    OTP_VER,
    SEARCH,
}

typealias routs = RoutingDestinations

@JvmInline
value class Destination(val rout: String)

sealed class Screen(val destination: Destination) {
    data object Welcome : Screen(destination = Destination(rout = routs.WELCOME.name))
    data object ForgetPassword : Screen(Destination(rout = routs.FORGET_PASSWORD.name))
    data object Home : Screen(Destination(rout = routs.HOME.name))
}