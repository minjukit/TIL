package Lesson3.Chapter4

data class Product(val name: String, val price: Double)

fun main() {
    val products = arrayOf(
        Product("snowball", 870.0),
        Product("tree", 2000.0),
        Product("tree", 30.30),
        Product("candcanelane", 1000.0)

    )

    //가격 낮은 순 정렬
    /*
    products.sortBy { it.price }
    products.forEach { println(it) }
 */

    // 두 객체 비교
    products.sortWith(
        Comparator{ p1, p2 ->
            when {
                p1.price > p2.price -> 1
                p1.price == p2.price -> 0
                else -> -1
            }
        }
    )

    // 이름 정렬 후, 가격 정렬
    products.sortWith(compareBy({it.name},{it.price}))

    products.forEach {
        println(it)
    }

    println(products.maxByOrNull{it.price})

}