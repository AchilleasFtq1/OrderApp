package cy.com.gcc.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodOrderApplication

fun main(args: Array<String>) {
    runApplication<FoodOrderApplication>(*args)
}
