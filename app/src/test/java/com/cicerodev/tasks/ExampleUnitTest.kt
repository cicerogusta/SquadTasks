package com.cicerodev.tasks

import org.junit.Test

import org.junit.Assert.*
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class Copo(var qtdGin: Int = 0, val mlCopo: Int = 80)


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val copo = Copo(qtdGin = Random.nextInt(0, 100))

        if (copo.qtdGin > copo.mlCopo) {

           assertEquals(true, true)
       }
        if (copo.qtdGin == copo.mlCopo) {
            assertEquals(false, false)
        }

        if (copo.qtdGin < copo.mlCopo) {
            assertEquals(false, false)

        }
    }
}