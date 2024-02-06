package com.apps.hw2.list.model

class DateMessage(dateCreated: Long) : BaseMessage(dateCreated.toInt(), MessageType.DATE, dateCreated)