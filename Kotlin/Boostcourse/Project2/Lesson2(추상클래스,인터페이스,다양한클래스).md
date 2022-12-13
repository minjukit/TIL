## 추상클래스
- 구현 클래스에서 가져야할 명세를 정의한 클래스
- open 키워드 사용안해도 파생 클래스 작성 가능
- 주 생성자에 abstract 키워드 없으면 생성자안은 비추상 프로퍼티들
- 인스턴트 생성 불가
- 추상메서드는 반드시 하위 클래스에서 구현해야 하며, 구현내용을 가질 수 있음

 
object를 사용하면 하위클래스 생성하지 않고도 인스턴스로 객체 생성 가능(임시적 사용 시 매우 유용)

<br>

## 인터페이스

- 추상적인 작업들이 적힌 계약서같은 것
- 다중 상속가능
- 추상클래스와 다르게 강한 연관을 가지지 않음
- 인스턴스 생성 불가
- 프로퍼티에 값 저장 불가

### 코틀린의 인터페이스
- 코틀린은 기본적인 구현내용이 포함될 수 있음 (자바8부터 default 키워드를 통해 구현내용을 포함할 수 있음)
- 구현부를 가진 메서드는 추상메서드가 아닌 일반메서드가 됨
- 상속한 하위 클래스에서 override를 사용해 해당 메서드 구현
- 프로퍼티에 값을 저장할 수 없지만 val 선언 프로퍼티는 getter를 통해 필요 내용 구현이 가능함

### 인터페이스 위임

인터페이스 A,B가 있을 때,

```kotlin
class Delegated(a: A, b: B): A by a, B by b{
  fun function(){
     functionA()//A위임
     functionB()//B위임
  }
}
```
매개변수에 인터페이스 위임을 사용하면 참조바로가능

<br>

### DTO
- Data Transfer Object로, 자바에서는 POJO 라고 불림
- 데이터를 접근하는 게터/세터를 포함한 객체임
- toString(), equals() 등을 포함함

## data class


- DTO를 표현하기 좋은 클래스
- getter/setter/hashCode()/equals()/toString()/copy()/component1()/component2() 등 자동생성
- 주 생성자는 지정된 최소 하나 이상의 매개변수를 가져야 함
- 주 생성자의 매개변수는 val or var로 지정되어야 함
- abstract, open, sealed, inner 키워드 사용 불가
- init 초기화 블럭과 부생성자 사용 가능


### 객체 디스트럭처링

객체가 가지고 있는 프로퍼티를 개별 변수들로 분해하는 것

`val(name,email) = 객체`

특정 인자를 생략하고 가져오고 싶다면

`val (_, email) = 객체` 
객체의 첫번째 프로퍼티를 제외하고 할당함

- componentN()메서드 : 컴포넌트의 idx가 N에 해당하여 프로퍼티 하나를 가져옴
- for문 이용 : 반복문으로 모든 객체의 프로퍼티 분해
`for((name,email) in customers){} // customers는 객체의 리스트`

- 함수로부터 객체가 반환 될 경우
```kotlin
fun myFunc(): Customer {
   return Customer("d","dd@gmail.com")
}
val (name,email) = myFunc()
```

-람다식에서 사용하는 경우

```kotlin
val myLam = {
   (name,email):Customer ->
   println(name)
   println(email)
}
```
<br>
<br>

## 내부 클래스

- nested class
- inner class

이너 클래스는 꼭 inner 키워드 필요하며, 안쓰면 nested class가 되어 외부 클래스의 프로퍼티 및 메서드에 접근 불가
외부 클래스가 컴패니언 객체를 가지고 있으며 접근 가능

객체 생성없이 Nested 메서드와 프로퍼티 사용가능



<br>

## 지역 클래스

- 특정메서드의 블록이나 init 블록같이 블록 범위내 유효한 클래스




<br>


## Sealed 클래스

- 추상클래스와 비슷하여 객체를 만들 수 없음
- 같은 파일안에서 상속이 가능함
- 블록 안에 선언
- 조건을 제한할 수 있기 때문에 when문을 사용할 때 else가 필요없다(조건절에 유용)



<br>


## Enum 클래스

- 여러개 상수를 선언하고 열거된 값을 조건에 따라 선택할 수 있는 클래스
- 자료형이 동일한 상수를 나열할 수 있음
- 세미콜론으로 변수의 끝을 알리고 메서드를 포함할수도 있음

```kotlin
enum class DayOfWeek(val num:Int){
    MONDAY(1), TUESDAY(2), SUNDAY(7)
}
fun main(){
    val day = DayOfWeek.SUNDAY
    when(day.num){
        1,2,3,4,5 -> println("weekday")
        6,7-> println("weekend")
    }
}
```


<br>


## annotation 클래스

- 어노테이션: 코드에 부가정보를 추가하며, 컴파일러나 프로그램 실행에서 사전처리를 위해 사용함

@Target : 어노테이션을 사용할 종류 정의
@Retention: 컴파일된 파일에 저장할 것인지/런타임에 반영할 것인지
@MustBeDocumented: 어노테이션이 api의 일부분으로 문서화하기 위해 사용함
@Repeatable: 어노테이션을 같은 요소에 여러 번 사용가능하게 할지



<br>
<br>

## 연산자 오버로딩

- operator 키워드로 연산자에 다른작동을 부여함
- 특정 연산자의 역할을 함수를 정의함
- 객체사이의 연산이 가능함

`operator fun dec() = Point(--x,--y)`





- 수식연산자
|연산자|함수|
|---|---|
|+|plus|
|-|minus|
|*|times|
|/|div|
|%|rem|
|..|rangeTo|

할당연산자는 Assign붙이기 `a+=b`는 `a.plusAssign(b)`

- 단일 연산자

|연산자|함수|
|---|---|
|+a|a.unaryPlus()|
|-a|a.unaryMinus()|
|!a|a.not()|



`operator fun Point.unaryMinus() = Point(-x,-y)`
`println(-point)` 실행하면 부호가 바뀜


- 포함범위 연산자

|연산자|함수|
|---|---|
|a in b|b.contains(a)|
|a !in b|!b.contains(a)|

- 비교 연산자

|연산자|함수|
|---|---|
|a > b|a.compareTo(b) > 0|
|a < b|a.compareTo(b) < 0|


<br>

## 호출 연산자

invoke operator

- 함수의 호출을 돕는데 사용함

```kotlin
val sum = {x:Int,y:Int -> x+y}
sum.invoke(3,10)
sum(3,10) //같은 표현인데 생략된 표현 많이 씀
```

















