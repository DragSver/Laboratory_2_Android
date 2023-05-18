package com.example.laboratory2

sealed class Card {
    data class BigImageCard(
        val image: String,
        val header: String,
        val text: String) : Card()
    data class BigTextCard(
        val image: String,
        val header: String,
        val text: String,
        val colorBag: String) : Card()
    data class MiddleCard(
        val image: String,
        val header: String,
        val subhead: String) : Card()
    data class SmallCard(
        val header: String,
        val subhead: String) : Card()

    object CardFactory {
        fun create(cardInfo: CardInfo): Card{
            return when {
                cardInfo.img == null -> {
                    SmallCard(cardInfo.title, cardInfo.subtitle)
                }
                cardInfo.isCircle == true -> {
                    MiddleCard(cardInfo.img, cardInfo.title, cardInfo.subtitle)
                }
                cardInfo.hasBag?.isNotEmpty() == true -> {
                    BigTextCard(cardInfo.img, cardInfo.title, cardInfo.subtitle, cardInfo.hasBag)
                }
                else -> {
                    BigImageCard(cardInfo.img, cardInfo.title, cardInfo.subtitle)
                }
            }
        }
    }
}



