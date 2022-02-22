package com.otamurod.ussd.models

import java.io.Serializable

data class Packet(var name:String, var code:String, var info:String):Serializable