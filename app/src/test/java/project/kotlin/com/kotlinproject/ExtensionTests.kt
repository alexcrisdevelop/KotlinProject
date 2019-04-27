package project.kotlin.com.kotlinproject

import junit.framework.Assert.assertEquals
import org.junit.Test
import project.kotlin.com.kotlinproject.extensions.toDateString
import java.text.DateFormat

/**
 * Class to test ExtensionUtils
 * */

class ExtensionsTest {

    /**
     * Test default behaviour
     * */
    @Test
    fun `"longToDateString" returns valid value`() {
        assertEquals("19/out/2015", 1445275635000L.toDateString())
    }

    @Test
    fun `"longToDateString" with full format returns valid value`() {
        assertEquals("Segunda-feira, 19 de Outubro de 2015",
         1445275635000L.toDateString(DateFormat.FULL))
    }
}