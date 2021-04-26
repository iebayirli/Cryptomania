package com.iebayirli.cryptomania.service.listeners

import com.iebayirli.cryptomania.model.TimeInterval

interface ITimeIntervalSelectListener {
    fun timeIntervalChanged(timeInterval: TimeInterval)
}