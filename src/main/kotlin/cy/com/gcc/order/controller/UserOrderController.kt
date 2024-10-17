package cy.com.gcc.order.controller

import cy.com.gcc.order.model.Order
import cy.com.gcc.order.model.OrderStatus
import cy.com.gcc.order.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/orders")
@Tag(name = "User Order Controller", description = "APIs for regular users to manage their orders")
class UserOrderController(private val orderService: OrderService) {

    @Operation(summary = "Get all orders for the current user", description = "Fetch all orders for the logged-in user")
    @GetMapping
    fun getAllOrders(): ResponseEntity<List<Order>> {
        val orders = orderService.getAllOrdersForCurrentUser() // Should fetch only the current user's orders
        return if (orders.isNotEmpty()) {
            ResponseEntity.ok(orders)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    @Operation(summary = "Get order by ID for the current user", description = "Fetch a specific order by its ID")
    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: UUID): ResponseEntity<Order> {
        val order = orderService.getOrderByIdForCurrentUser(id)
        return order?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @Operation(summary = "Create a new order", description = "Allows users to create a new order")
    @PostMapping
    fun createOrder(@RequestBody order: Order): ResponseEntity<Order> {
        val newOrder = orderService.createOrder(order)
        return ResponseEntity.ok(newOrder)
    }
}
