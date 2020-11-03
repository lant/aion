import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Tests {
    @Test
    fun testNumberRegex() {
        assertFalse("a".isOnlyNumbers())
        assertTrue("1".isOnlyNumbers())
        assertTrue("1234".isOnlyNumbers())
        assertTrue("-1234".isOnlyNumbers())
    }
}