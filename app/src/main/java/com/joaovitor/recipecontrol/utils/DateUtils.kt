package com.joaovitor.recipecontrol.utils

import java.time.LocalDate
import java.time.LocalDateTime

class DateUtils {
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

        fun getDay(): Int {
            return LocalDate.now().dayOfMonth
        }

        fun getYear(): Int {
            return LocalDate.now().year
        }

        fun getHour(): Int {
            return LocalDateTime.now().hour
        }

        fun getMinute(): Int {
            return LocalDateTime.now().minute
        }

        fun getTimestamp(): Long {
            return LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC).toEpochMilli()
        }
    }
}