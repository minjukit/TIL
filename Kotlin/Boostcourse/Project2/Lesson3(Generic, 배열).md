# 제네릭
데이터 타입을 일반화 함

## 제네릭의 사용
자료형의 객체들을 메서드나 클래스에서 컴파일 시간에 자료형을 검사해 적당한 자료형을 선택할 수 있도록 하기 위해 사용.

자료형의 결정은 함수가 호출될 때 컴파일러가 자료형을 유추할 수 있으며 이 자료형은 반환 자료형과 매개변수 자료형에도 사용가능.


- <>사이에 형식 매개변수 T를 사용해 선언.(추론가능한 경우 생략가능)
- ctrl + shift + p로 추론된 타입 보기

#### 형식 매개변수 이름

- E: 요소
- K: 키
- N: 숫자
- T: 형식(type)
- V: 값

형식매개변수는 null이 가능하기 때문에 null을 허용하지 않으려면 `<T: Any>`같이 `<T: Number>` 이런식으로 제한해줌
JAVA에서는 extends나 super로 자료형을 제한했었음

형식 매개변수는 상위클래스객체에 하위클래스 할당 시에도 선언이 불가함

형식 매개변수는 생성자없이 클래스 내부에 프로퍼티 사용 불가함

<br>

## 제네릭 함수

- 메서드 앞쪽에 <T>를 지정
- 자료형의 결정은 함수가 호출될때 컴파일러가 자료형을 추론함

### 제네릭과 람다식

형식 매개변수로 선언된 함수의 매개변수를 연산할 경우 자료형을 결정지을 수 없으면 오류가 생김

- 람다식을 사용하여 연산식 작성가능

```kotlin
fun <T: Number> add(a:T,b:T,op:(T,T)->T):T{
   return op(a,b)
}

fun main(){
   val result = add(2,3, {a,b->a+b})
   println(result)
}
```


#### reified 자료형
- 형식 매개변수 T를 지정하면 실행시간에 접근 가능
- reified 자료형은 inline함수에서만 사용 가능(컴파일러가 복사해서 넣을 때 실제 자료형을 알 수 있기 때문에 실행시간에도 사용할 수 있게 됨)
`inline fun <reified T> myGenericFunc()`

<br>

### Class<T>와 KClass

Class<T>
- .class 형태로 반환 받는 객체
- 원본 클래스에 대한 meta-data를 가짐

Object::class
- 코틀린의 KClass를 나타내는 표현

`Object::class` //KClass
`Object::class.java` //Class
- 자바의 클래스를 가져오려면 .java 붙임


<br><br>

# 코틀린 배열

배열은 객체로 생성되어 heap메모리에 저장됨

## 배열 생성

1. arrayOf() 헬퍼함수 이용
2. Array() 생성자 이용

다양한 자료형의 혼합이 가능함 `arrayOf(1,6,"jk",false)` 

- 특정 자료형으로 제한하기
1. 제네릭을 이용하여 arrayOf<자료형>
2. 자료형+ArrayOf() `charArrayOf(), uintArrayOf()`



### 표현식을 통한 배열의 생성

`val|var 변수명 = Array(요소개수, 초기값)`

이 초기값안에 람다식함수가 정의될 수 있음.
`val arr = Array(5, {i->i*2})`


많은 양의 배열의 생성해보자

`var a = Array(1000,{0})` 0으로 채울 수 있고,
`var a = arrayOfNulls<Int> (1000)` 처럼 null로 채울 수도있음

특정 클래스 객체로 배열을 생성할 수도 있음

`var a = Array(1000, {i->myClass(i)})`


- 요소 접근 방법
1. `arr.get(idx)`, `arr.set(idx)`
2. `arr[idx]`

- 배열 출력
1. `Arrays.toString(배열명)`
2. `Arrays.deepToString(배열명)` 다차원 배열

코틀린 배열1.4부터 코틀린 컬렉션 자체에서 contentToString 함수를 지원함.

`배열명.contentToString()`
`배열명.contentDeepToString()` 으로 zhxm사용가능.

- 배열 추가
`arr.plus(element)` 
- 범위 잘라내기
`arr.sliceArray(0..2)` 
- 인덱스 출력
`arr.indexOf(idx)`
- 배열의 평균
`arr.average()`
- 요소 개수
`arr.count()`
- 요소 존재여부 확인
`arr.contains(element)`
`element in arr`

## 배열 순환

1. for
`for(e in arr)`
2. 순환 메서드
`arr.forEach{ e -> println("$element")}` it으로 받을 수 있음
`arr.forEachIndexed({i,e -> println("arr[$i] = $e")})` 인덱스와 요소
3. Iterator
```kotlin
val iter:Iterator<Int> = arr.iterator()
while(iter.hasNext()){
   val e = iter.next()
}
```

정수형만을 다룰 때는 1번, list등 콜렉션은 2번 사용함.

 
## 배열의 정렬

- 정렬된배열반환함수
`sortedArray()`
`sortedArrayDescending()`
- 원본배열 정렬 함수
`sort()`
`sortDescending()`

`arr.sort(1,3)` 처럼 사용하면 원하는 범위 `1..2` 정렬이 가능함.

- 원본 리스트 반환 함수
`sorted()`
`sortedDescending()`

- 특정 표현식에 따른 정렬
`배열명.sortBy{it -> it.length}` 길이가 짧은 순으로 정렬

- 두 객체 비교
`sortWith` 두개이상 정보를 가지고 정렬이 가능하여 유연함.

## 배열 필터링

- filter
`arr.filter{e->e>0}.forEach{e->println(e)}` it으로 대체가능

- 체이닝으로 필터링
```kotlin
arr.filter{it.startsWith("a")}
   .sortedBy{it}
   .map{it.toUpperCase()}
   .forEach{println(it)}
```

- when 문으로 요소 확인
`when{ "apple" in fruits -> println("exists")}`

- max/min
`배열명.minByOrNull{it.price}`
`배열명.maxByOrNull{it.price}`

- 배열 평탄화
`배열명.flatten()` 다차원 -> 일차원

<br>



## 문자열

문자열의 immutable하게 생성되어, 참조되고 있는 메모리가 변경될 수 없음.
var로 선언된 문자열을 재할당하면 기존 문자열은 GC에 의해 제거되고, 새로운 메모리 공간이 생성됨.


### 문자열 추출

`.substring(범위)` String 반환
`.subSequence(3..s.length-1)` CharSequence 반환


### 문자열 비교

`a.compareTo(b)` 같으면 0, a < b 양수
`a.compareTo(b,true)` 대소문자 무시


### StringBuilder

문자열이 초기화된 값보다 더 크게 일정 버퍼공간을 사용함

- 간단한 요소변경이 있을 때
-  문자열이 자주 변경되는 경우

`.append("")`
`.insert(10,"")`
`.delete(5,10)`


### 기타 문자열 함수

`toLowerCase()` -> `lowercase()`
`toUpperCase()` -> `.uppercase()`
`split(" ")` List로 분리된 내용 반환
`trim()` 앞뒤 공백 제거


### Escape 문자

`\t` tab
`\b` backspace
`\n` newline
`\r` 리턴
`\uHHHH` 유니코드문자
`\'` 작은따옴표
`\"` 큰따옴표
`\\` 슬래시
`\$` 달러기호


### format() 형식문자

`.format()`
%b boolean
%d 부호있는 정수
%f 10진 실수
%h 해시코드
%o 8진 정수
%t 날짜/시간
%c 문자
%e E표기법 실수
%g 10진 실수
%n 줄 구분
%s 문자열
%x 16진 정수



