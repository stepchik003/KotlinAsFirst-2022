@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val d = age % 10
    return when {
        d == 1 && age != 11 && age != 111 -> "$age год"
        d in 2..4 && age !in 12..14 && age !in 112..114 -> "$age года"
        else -> "$age лет"
    }
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = s1 + s2 + s3
    val hW = s / 2
    if (hW < s1) {
        return (hW / v1)
    } else if (hW in s1..(s1 + s2)) {
        return (t1 + (hW - s1) / v2)
    } else if (hW in (s1 + s2)..s) {
        return (t1 + t2 + (hW - s1 - s2) / v3)
    } else {
        return 0.0
    }
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    val firstTest = (rookX1 == kingX)
    val secondTest = (rookY1 == kingY)
    val thirdTest = (rookX2 == kingX)
    val fourthTest = (rookY2 == kingY)
    if (firstTest || secondTest) {
        return if (thirdTest || fourthTest) 3 else 1
    } else {
        return if (thirdTest || fourthTest) 2 else 0
    }
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val fT = (abs(bishopX - kingX) == abs(bishopY - kingY))
    val sT = (rookX == kingX)
    val tT = (rookY == kingY)
    if (sT || tT) {
        return if (fT) 3 else 1
    } else {
        return if (fT) 2 else 0
    }
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val maxSide = max(max(a, b), max(b, c))
    val minSide = min(min(a, b), min(b, c))
    val midSide = a + b + c - maxSide - minSide
    val maxSqr = sqr(maxSide)
    val midSqr = sqr(midSide)
    val minSqr = sqr(minSide)
    return when {
        minSide + midSide <= maxSide -> -1
        minSqr + midSqr > maxSqr -> 0
        minSqr + midSqr == maxSqr -> 1
        minSqr + midSqr < maxSqr -> 2
        else -> -2
    }
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        (c > b) && (c > a) && (d > a) && (d > b) -> -1
        (a > d) && (a > c) && (b > c) && (b > d) -> -1
        ((b == c) && (d > a)) || ((a == d) && (b > c)) -> 0
        (c >= a) && (d >= a) && (b >= c) && (b >= d) -> (d - c)
        (a >= c) && (b >= c) && (d >= a) && (d >= b) -> (b - a)
        (c >= a) && (d >= a) && (b >= c) -> (b - c)
        (a >= c) && (b >= c) && (d >= a) -> (d - a)
        else -> -2
    }
}
