package com.fronties.socialeventchat

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

class Extensions<T> {

    fun Mockito.whenever(
        methodCall: OngoingStubbing<T>
    ): OngoingStubbing<OngoingStubbing<T>>? {
        return Mockito.`when`(methodCall)
    }
}
