package com.example.android.weeklytips

sealed class ClothingItem(val clothingType: ClothingType) {

    abstract var price:Double

    enum class ClothingType {
        SHIRT, PANTS
    }
}

data class Shirt(override var price:Double):
        ClothingItem(ClothingType.SHIRT)

data class Pants(override var price:Double):
        ClothingItem(ClothingType.PANTS)

