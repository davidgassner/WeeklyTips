package com.example.android.weeklytips

data class ClothingItem(val clothingType: ClothingType,
                        var price:Double = 0.0) {

    enum class ClothingType {
        SHIRT, PANTS
    }
}