package com.apps.hw2.chat.elm

import vivid.money.elmslie.core.ActorCompat

interface IActor: ActorCompat<Command, Event.Internal>