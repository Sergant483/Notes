package io.mobilation.bike.tracker.mobile.presentation.fragment.history.util

abstract class SwipeControllerActions {
    open fun onLeftClicked(position: Int) {}
    open fun onRightClicked(position: Int) {}
}