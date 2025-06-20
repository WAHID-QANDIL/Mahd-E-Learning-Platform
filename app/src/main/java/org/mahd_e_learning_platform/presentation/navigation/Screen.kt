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
    ERROR,
    ONBOARDING,
}

typealias routs = RoutingDestinations

@JvmInline
value class Destination(val rout: String)

sealed class Screen(val destination: Destination) {
    data object Welcome : Screen(destination = Destination(rout = routs.WELCOME.name))
    data object ForgetPassword : Screen(Destination(rout = routs.FORGET_PASSWORD.name))
    data object Home : Screen(Destination(rout = routs.HOME.name))
    data object Details : Screen(Destination(rout = routs.DETAILS.name)){
        fun passCourseId(courseId: String) = "${routs.DETAILS.name}/$courseId"
    }
    data object Profile : Screen(Destination(rout = routs.PROFILE.name))
    data object Payment : Screen(Destination(rout = routs.PAYMENT.name))
    data object CourseContent : Screen(Destination(rout = routs.COURSE_CONTENT.name))
    data object MyCourses : Screen(Destination(rout = routs.MY_COURSES.name))
    data object OtpVerification : Screen(Destination(rout = routs.OTP_VER.name))
    data object Search : Screen(Destination(rout = routs.SEARCH.name))
    data object Error : Screen(Destination(rout = routs.ERROR.name))
    data object OnBoarding : Screen(Destination(rout = routs.ONBOARDING.name))
}