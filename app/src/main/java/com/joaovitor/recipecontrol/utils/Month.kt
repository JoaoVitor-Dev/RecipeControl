package com.joaovitor.recipecontrol.utils

import java.time.LocalDate

class Month {
    companion object {
        // Retorna o mês atual (int)
        fun getCurrentMonth(): Int {
            return LocalDate.now().monthValue
        }

        // Retorna o nome do mês atual como string
        fun getCurrentMonthName(): String {
            return when (LocalDate.now().monthValue) {
                1 -> "Janeiro"
                2 -> "Fevereiro"
                3 -> "Março"
                4 -> "Abril"
                5 -> "Maio"
                6 -> "Junho"
                7 -> "Julho"
                8 -> "Agosto"
                9 -> "Setembro"
                10 -> "Outubro"
                11 -> "Novembro"
                12 -> "Dezembro"
                else -> "Mês inválido" // Caso improvável, mas para segurança
            }
        }
    }
}