package com.example.petscoffee.model.pets

class Cat(
    hp: Int,
    hunger: Int,
    cleanliness: Int,
    mood: Int,
    loveliness: Int,
    sp: Int,
    name: String
) : Pets(hp, hunger, cleanliness, mood, loveliness, sp, name, 1)