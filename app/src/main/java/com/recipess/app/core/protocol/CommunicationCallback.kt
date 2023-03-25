package com.recipess.app.core.protocol

import com.recipess.app.core.protocol.ProtocolAction

interface CommunicationCallback {
    fun onFragmentEvent(action: ProtocolAction)
}