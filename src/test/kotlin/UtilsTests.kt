import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class UtilsTests {

    @ParameterizedTest
    @CsvSource(value = ["one  :1", "two  :2", "three:3", "four :4", "five :5", "six  :6", "seven:7", "eight:8", "nine :9"], delimiter = ':')
    fun spelledDigitsToDigits(input: String, output: String) {
        assertThat(input.spelledDigitsToDigits()).startsWith(output)
    }
}
