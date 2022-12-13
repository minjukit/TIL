# OOP 
- 절차적 프로그래밍의 한계를 극복하고자 나온 언어의 한가지 방법론
- 객체와 관계를 표현하고 확장과 재사용이 용이한 프로그래밍 방식

클래스 = 프로퍼티+메서드
생성자,부생성자는 객체 생성시 자동 호출

<br>

### 추상화 
- 속성과 동작을 정의하는 과정 (클래스 만들 때)
### 인스턴스
- 객체를 생성하는 것

### 메시지 전송
- 객체들 간 주고받는 메시지


<br>


## 상속
- 부모클래스 내용을 자식클래스가 물려받는 것


부모클래스에 open키워드로 상속가능하도록 만들기

코틀린의 모든 클래스는 묵시적으로 Any로부터 상속

<br>

## 다형성
- 하나의 이름으로 다양한 처리를 하는 것

### 정적 다형성

- compile time에 결정되는 다형성
- ex. method overloading


`fun add(x:Int,y:Int):Int = x+y`
`fun add(x:Double,y:Double):Double = x+y`
* 오버로딩: 기능은 같지만 인자를 다르게하여 여러경우처리


### 동적 다형성
- run time 다형성
- ex. method overriding

* 오버라이딩: 기능을 다르게 바꾸어 재설계하는 것
함수를 오버라이드하려면 open키워드
오버라이딩을 금지하려면 부모클래스의 메소드에 final키워드.

<br>

상위클래스는 super키워드로 현 클래스는 this키워드로 참조가능
super()는 상위클래스의 생성자를 참조한다
this()는 현재클래스의 생성자를 참조한다

이너 클래스에서 바깥 클래스 접근하려면 `super@클래스명`
상속을 여러개 받았을때 이름중복문제를 해결하려면 앵글브라켓을 이용한다.`super<클래스/인터페이스명>.함수()`




<br>

## 캡슐화
- 클래스 외부에서 숨겨야하는 내용을 숨기고 필요한 부분만 사용하는 것

### 가시성 지시자 visibility modifiers
- private(-) : 클래스 내부(패키지에서는 파일내부)에서만
- public(+) : 어디서든 접근가능
- protected(#) : 하위 상속 요소에서 접근가능
- internal(~) : 같은 모듈내에서만 접근가능

<br>

## 연관
: 클래스들의 관계

- 연관 (DoctorㅡPatient)
- 의존 (DoctorA-->Patient)
- 집합 (Pond◇ㅡDuck)
- 구성 (Car◆ㅡEngine)





<br>

## 게터와 세터

val은 게터만 설정가능
부모가 val인 경우, 자식변수가 var로 변경하고 setter 설정 가능
부모가 var인 경우, 자식변수 val은 변경 불가

value: 세터의 매개변수로 외부로부터 값을 가져옴(value말고 다른이름이어도 OK)

field: 프로퍼티를 참조하는 변수로 backing field라 불림(프로퍼티를 대신하는 임시필드,프로퍼티 사용시 게터나 세터가 무한호출재귀에 빠짐)


<br>


## 지연 초기화
: 클래스의 프로터피 자료형들은 null을 가질 수 없으니 객체가 나중에 나타나는 경우에 초기화하는 방법
 
- lateinit 
 - var의 늦은 초기화 시
  - 의존성이 있는 초기화 시
 - unit test코드 작성 시
  - 프로퍼티에 대한 게터와 세터 사용 불가
- by lazy
 - val의 늦은 초기화 시 = 불변
 - 일종의 람다식으로 {블록} 부분의 초기화 진행 
- lazy
  - 늦은 초기화 위임변수
  - `val personDelegate = lazy{Person("min",24)}`처럼 할당

<br>


### by
: 한 클래스가 다른 클래스에게 위임하도록 선언

```kotlin
interface Animal{
   fun eat(){}
}
class Cat : Animal{}
val cat = Cat()
class Robot = Animal by cat //Animal의 정의된 Cat의 모든멤버를 로봇에 위임
```

참조없이 각 인터페이스 구현 클래스의 함수나 변수에 접근 가능하다.

코틀린 기본 라이브러리는 open되지 않은 클래스이다.
상속이나 직접 클래스의 기능 확장을 하기 어렵기 때문에 위임을 사용하면 확장을 구현할 수 있다.

<br>

- observable : 프로퍼티를 감시하여 로직변경이 일어날때 호출


```kotlin
class User{
  var name: String by Delegates.observable("NONAME"){
     prop, old, new->
     println("$old->$new") // 값이 변경될때마다 출력
  }
}
```

- vetoable : 반환값에 따라 프로퍼티변경을 허용하거나 취소

```kotlin
fun main() {
  var max:Int by Delegates.vetoable(0) { //초기값 0으로 설정
       prop, old, new ->
    new > old // 더 커야 max 할당
  }
}
```



<br>


## companion object



동적으로 객체를 생성하는데 정적으로 고정하는 방법은?
java에서는 static 변수/객체
kotlin에서는 컴패니언 객체


컴패니언 객체는 실제 객체의 싱글톤으로 정의됨

자바코드로 이후에 컴패니언 객체에 접근가능하도록 하려면 `@JvmStatic` 어노테이션 사용.

<br>


### top-level function

자바의 static final선언 함수와 같음

패키지 레벨 함수라고도 불리며, 객체생성없이도 어디에서든 실행
자바와 연동 시 내부적으로 클래스를 자동생성하며, `코틀린파일.함수` 로 실행
`@file:JvmName("클래스명")` 사용 시 `클래스명.함수` 로 자바에서 사용가능

<br>


## object 선언

- 상속할 수 없는 클래스에 내용이 변경된 객체를 생성할 때 
- 주/부생성자 사용 불가

자바에서는 `오브젝트명.INSTANCE.함수()`로 호출해야 함

## object 표현식
- 선언 시 이름이 없으며 싱글턴 
- 표현식 사용될 때 마다 new 인스턴스 생성
- 익명내부클래스를 object 표현식으로 만들 수 있음


