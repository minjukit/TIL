# Collection

## Collecton Interface

Iterable로부터 확장
불변형으로 set,list는 읽기전용 컬렉션

`.size`
`.isEmpty()`
`.contains(e)`
`.containsAll(컬렉션)`

## MutableIterable, MutableCollection Interface

- 가변형 컬렉션을 지원하기 위한 인터페이스

`.add(e)`
`.remove(e)`
`.addAll(컬렉션)`
`.removeAll(컬렉션)`
`.retainAll(컬렉션)`
`.clear()`


## List

`.listOf(e)` 어떤 자료형이든 가능
`.mutableListOf()`
`.emptyList()`
`listOfNotNull()` null 제외한 요소만 반환한 리스트 생성


### 컬렉션 접근

`for (e in fruits)`

`for (idx in fruits.indices)`


### 리스트 메서드

`.get(idx)`
`.indexOf(e)` 요소가 첫번째로 나타나는 인덱스 반환
`.lastIndexOf(e)` 요소가 마지막으로 나타나는 인덱스 반환
`.listIterator()` iterator 반환
`.subList(from,to)` from~to-1범위내 요소 목록 반환



### 가변형 List 생성

`arrayListOf()` 반환자료형이 ArrayList
`mutableListOf()` 반환자료형이 MutableList
`.toMutableList()` List를 MutableList로

어레이리스트는 add와 remove,
뮤터블리스트는 add와 removeAt으로 추가삭제



## Set

- 순서가 없는 요소들의 집합
- 동일한 요소를 중복해서 가질 수 없음

`setOf` 불변형
`mutableSetOf()` 가변형
`hashSetOf()` 검색속도 O(1)
`sortedSetOf()` 정렬된 TreeSet 반환
`linkedSetOf()` LinkedHashSet 반환




## Map

- 키와 값으로 구성된 요소를 저장
- 키는 중복x, 값은 중복 가능


`mapOf(키 to 값,)` 불변형
`mutableMapOf()` 가변형
`hashMapOf(키 to 값,)` 해시맵 반환
`sortedMapOf(키 to 값,)` 키 오름차순 맵 반환
`linkedMapOf(키 to 값,)` LinkedHashMap 반환


### map의 멤버

`size`
`keys` 모든 키 반환
`values` 모든 값 반환
`isEmpty()` 맵이 비어있는지
`containsKey(키)` t/f반환
`containsValue(값)` t/f반환
`get(키)` 없으면 null 반환


### mutableMap의 추가멤버

`put(키,값)` 
`remove(키)`
`putAll(맵)`
`clear()`



## 컬렉션의 확장함수

- operators 연산

```kotlin
println(list1+"four")
println(list2 - 5) //요소5 뺌
println(list2 + listOf(7,8))
println(map + Pair(7,8))
println(map - listOf("hi","hello")) //일치하는 키찾아서 맵에서 제거
```

- aggregators 집계

```kotlin

list.forEach{println($it)} //요소를 람다식으로 처리
list.forEachIndexed{index,value->println("$index : $value")}
val list2 = list.onEach{println(it)} // 요소를 람다식으로 처리 후 컬렉션으로 반환

```kotlin
println(list.fold(4) {total, next -> total  * next}) // 처음->마지막 요소까지 적용해 값 반환
println(list.reduce {total,next->total*next}) // fold와 같지만 초기값이 없음
println(list.foldRight(4) {total, next -> total * next }) // 요소의 마지막->처음 순서로 적용하여 값 반환
println(list.reduceRight {total, next -> total * next }) 


```

- checks
- filtering
- transformers


요소 처리와 검색

`list.elementAt(1)` 인덱스의 요소 반환 
`list.elementAtOrElse(10, {2 * it})` 인덱스를 벗어나는 경우엔 식의 결과 반환
`list.elementAtOrNull(10, {2 * it})` 인덱스를 벗어나는 경우엔 null 반환


요소 순서와 정렬

`reversed()`
`sorted()`
`sortedDescending()`
`sortedBy {it % 3}`
`sortedByDescending {it % 3}`


### 매핑 관련 연산 : 새로운 컬렉션 반환

- map()

`list.map{it*2}` 
`list.mapIndexed{idx,it->idx*it}` 
`list.mapNotNull{it?.times(2)}` null 제외하고 새로운 컬렉션 반환

- flatMap()

`listOf("abc","12").flatMap{it.toList()}` [a,b,c,1,2] 반환
 
- groupBy()

`list.groupBy{if(it%2 ==0) "even" else "odd"}` 주어진 식으로 요소를 그룹화하고 Map으로 반환 // {odd=[1,3], even = [2,4]}


### 요소 값 생성

```kotlin
val squares = generateSequence(1) {it + 1}.map {it * it}
println(squares.take(10).toList()) // [1,4,9, ... , 100]

val oddSquares = squares.filter{it%2 != 0}
println(oddSquares.take(5).toList())// [1,9,25,49,81]
```

메서드 체이닝을 연속해서 쓰면 하나의 구문이 끝날 때 마다 중간 결과로 새로운 List를 만들어 냄




중간 연산 결과 없이 한 번에 끝까지 연산 후 결과를 반환 하려면

```kotlin
val list1 = listOf(1,2,3,4,5)
val listSeq = list1.asSequence()
	.map{println("map($it)"); it * it}
	.filter { println("filter($it)"); it % 2 ==0}
	.toList()
println(listSeq) // [4, 16]
```































