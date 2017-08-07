package com.example.android.weeklytips.model

data class Person(private var _name:String, val age:Int) {
    var name: String
        get() {
            return _name.toUpperCase()
        }
        set(value) {
            _name = value
        }
}

