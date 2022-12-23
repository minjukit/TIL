# 동시성 프로그래밍

## 동기적 Synchronous 

- 순서대로 작업을 수행
- UI와 data download 를 동시에 대응하는 경우 등 다중실행환경에서 제약 발생

## 비동기적 Asynchronous 

- 다양한 기능을 동시에 수행
- 스레드 이용 or RxJava,Reactive같은 서드파티 라이브러리에서 제공
- 코틀린에서는 코루틴 기본제공

## 코루틴 Coroutines

- co-루틴
- 넌블로킹 또는 비동기 코드를 마치 일반적인 동기코드처럼 쉽게 작성하면서 비동기 효과를 낼 수 있음

### 블로킹

- 요청작업을 기다리거나 다른 Task한테 선점당하여 blocking 당하는 상태

### 넌블로킹

- 요청한 작업의 응답이 올 때까지 blocking 하지 않고, 다른 일을 할 수 있도록 함
- 요청한 작업이 완료되면 콜백함수를 부르고, 다른 Task와 제어를 병행하며 동시성 처리를 함

#### Task

태스크는 프로세스나 스레드임
프로세스는 실행되는 메모리, 스택, 열린 파일 등 모두 포함하여 프로세스 간 문맥교환의 비용이 큼
스레드는 자신만의 스택이 있고 나머지는 공유하므로 프로그래밍에서 많이 사용


*문맥교환 context-switching : 하나의 프로세스나 스레드가 CPU를 사용하고 있는 상태에서 다른 프로세스나 스레드가 CPU를 사용하도록 하기위해, 이전의 프로세스 상태를 보관하고 새로운 프로세스의 상태를 적재하는 것

 


### Thread 생성 방법
1. Thread클래스에서 상속받아 구현
2. Runnable인터페이스 상속받아 run()구현, 스레드 객체만들고 runnable 객체넣기
3. 익명객체 object로 Thread()상속받아 생성(클래스의 객체 만들지 않고도 실행)
4. 람다식 Thread{}으로 생성


#### Thread Pool
`Executors.newFixedThreadPool()`
자주 재사용되는 스레드를 이용하기 위해 미리 생성된 스레드풀에서 스레드 이용


<br><br>

## 코루틴

- 복잡성을 줄이고 손쉽게 일시중단하거나 다시 시작하는 루틴 생성
- 문맥교환없이 해당 루틴 일시중단(suspended)을 통해 제어
- Stackless


### kotlinx.coroutines

- common 패키지
launch/async : 코루틴빌더
Job/Deferred : cancellation
Dispatchers : Default는 백그라운드 코루틴, Main은 Android나 Swing,JavaFx를 위해 사용, IO등등
delay/yield : 상위 레벨 지연 함수
Channel/Mutex :  통신과 동기화를 위한 기능
coroutineScope/superviserScope : 범위 빌더
select : 표현식 지원

- core 패키지
CommonPool : 코루틴 컨텍스트
produce/actor :  코루틴 빌더



<br>

#### 코루틴 빌더


launch
- 메인 프로그램과 독립되어 실행
- 즉시실행 후 블록 내 결과 반환X
- 넌블로킹관리를 위한 Job객체 즉시 반환
- join으로 상위코드가 종료되지 않고 완료를 기다리게 할 수 있음

async
- 비동기 호출을 위해 만든 코루틴
- 결과나 예외를 반환함
- 결과는 Deffered<T>객체를 반환하며 await로 받음
- await는 작업이 완료될 때까지 기다리게 됨

#### 일시중단 함수 

delay()
- 넌블로킹 일시중단함수
- 반드시 코루틴 블록안에서 실행됨

suspend 키워드
- 사용자 함수에 붙어서 컴파일러가 Continuation 클래스로부터 분리된 루틴을 생성
- 코루틴 빌더인 launch와 async에서 이용가능

#### Job 객체

Job
- 코루틴의 생명주기를 관리함
- 생성된 코루틴 작업들은 여러개면 부모-자식과 같은 관계를 가짐
- 부모가 취소되거나 실행실패하면 하위자식들은 모두 취소
- 자식의 실패는 부모에게 전달되고 부모 또한 실패(다른 모든 자식도 취소)

SupervisorJob
- 자식의 실패가 그 부모나 다른 자식에게 전달되지 않고 실행 유지

```kotlin
    val job = GlobalScope.launch { //background에서 새 코루틴 실행
        delay(1000L) // 1초의 넌블로킹 지연
        println("hi2") //지연 후 출력

    }
    println("hi") // main스레드가 코루틴이 지연되는 동안 계속 실행
    println("job: ${job.isActive}") // job: StandaloneCoroutine{Active}@1517365b
    println("job: ${job.isActive}, ${job.isCompleted}") //true, false
    // 아래 문장을 쓰지 않으면 바로 종료되어 hi2 출력 안됨
    Thread.sleep(2000L) //main스레드가 JVM에서 바로 종료되지않게 2초기다림
    println("job: $job") // job: StandaloneCoroutine{Completed}@1517365b
    println("job: ${job.isActive}, ${job.isCompleted}") //false, true

}
```



join 
- 모든 작업의 완료를 기다림
- 코루틴 내 실행되어야 하므로 runBlocking {}내 사용


#### Job의 상태

생성     -> Active -> Completing -> Completed

|ㅡㅡㅡㅡㅡ|ㅡㅡㅡㅡㅡㅡ |
vㅡㅡㅡㅡㅡvㅡㅡㅡㅡㅡ v
cancelled <- Cancelling


### 코루틴 중단과 취소

중단
- delay(시간값) : 일정시간 지연 후 중단
- yield() : 특정 값 산출하기 위해 중단
- Thread.sleep(시간값) : 블록킹하게 중단

취소(코루틴 외부에서)
- Job.cancel() : 지정된 코루틴 작업 즉시 취소
- Job.cancelAndJoin() : 지정된 코루틴 작업을 완료까지 기다리고 취소
- 부모 블록이 취소되면 모든 자식 코루틴 취소


<br>


#### async 코루틴 빌더 생성

- launch와 다르게 Deferred객체로 결과를 반환
- 지연된 결과 받기위해 await()사용
- 비동기적으로 뱅행실행



<br>


### Coroutine Context 코루틴 문맥

- 코루틴을 실행하기 위한 다양한 설정값을 가진 관리정보
- 디스패처는 코루틴 문맥을 보고 어떤 스레드에서 실행되고 있는지 식별
- 코루틴 문맥은 + 연산으로 조합 가능
- CoroutineName(), Job(), SupervisorJob(),CoroutineDispatcher, CoroutineExceptionHandler 등이 있음



#### 코루틴 스코프


GlobalScope
- 독립형 코루틴 구성
- 생명주기는 프로그램전체, main의 생명주기가 끝나면 같이 종료됨
- Dispatchers.Unconfined와 함께 작업이 서로 무관한 전역 범위 실행
- launch, async 사용이 권장되지 않음(자원낭비)

CoroutineScope
- 특정 목적의 디스패처를 지정한 범위를 블록으로 구성
- 모든 코루틴 빌더는 CoroutineScope의 인스턴스를 가짐
- `launch{..}` 처럼 인자가 없으면 상위의 문맥이 상속되어 결정
- `launch(Dispatchers.옵션){..}` 이면 디스패처의 스케줄러를 지정 가능
  - Dispatchers.Default는 GlobalScope에서 실행되는 문맥과 동일하게 사용


<br>



#### 스레드풀 thread pool
- 하나의 스레드에 여러 개의 코루틴 동작가능
- 이미 초기화된 스레드 중 하나이상이 선택되며 초기화해 스레드를 생성하는 오버헤드가 없어 빠름



### 빌더의 특정 속성 지정

- start()/await()가 호출될 떄 실제로 시작
- launch나 async는 즉시 실행되지만 start 옵션에 따라 실행시점 지정가능

```kotlin
val someJob = launch(start = CoroutineStart.LAZY){
}//start호출을 해야만 시작함
...
button.setOnClickListener{
	someJob.start() // 버튼을 눌러야지만 코루틴 시작
}
```



### runBlocking

- 완료를 기다리기 위한 블로킹
- 새로운 코루틴을 실행하고 완료되기 전까지는 현재 스레드를 블로킹함(delay()안해도 코루틴 기다림)
- 코루틴 빌더와 마찬가지로 CoroutineScope의 인스턴스를 가짐
- main에 달아주거나 그냥 런블로킹 블럭을 생성하거나 클래스 내의 멤버 메서드에 사용함
- 특정 피스패처 옵션줄 수 있음 `runBlocking(Dispatchers.IO)`

### withContext()

1. 특정문맥과 함께 실행

- 인자로 코루틴 문맥 지정
- 해당 코드블럭은 다른 스레드에서 수행되고 결과를 반환함
- 부모 스레드는 blocking하지 않음
`result = withContext(Dispatchers.IO){fun()}`


2. 완료보장

`withContext{try{}finally{}}` finally 블럭의 실행보장


### Scope Builder 스코프 빌더 

coroutineScope 빌더

- 자신만의 코루틴 스코프를 선언하고 생성
- 자식이 완료되기전까지 종료되지 않음
- runBlocking과 유사하지만 runBlocking은 단순함수로 현재 스레드를 blocking하고, coroutineScope는 단순 suspend 함수 형태로 non-blocking함
- 자식코루틴이 실패하면 이 스코프도 실패하고 남은 모든 자식 취소됨 (외부에 의해 작업 취소 시 CancellationException 발생)

supervisorScope 빌더

- 코루틴 스코프를 생성함
- SupervisorJob 함께 생성하여 기존 문맥의 Job을 override함
  - launch로 생성한 작업의 실패는 CoroutineExceptionHandler를 통해 핸들링
  - async로 생성한 작업의 실패는 Deferred.await의 결과에 따라 핸들링
  - parent로 부모작업이 지정되면 자식작업이 되며, 이때 부모에 따라 취소여부를 결정함
- 자식이 실패해도 스코프에 영향을 받지 않음


### 병렬 분해

Parallel decomposition 

```kotlin
suspend fun loadAndCombine(name: String, name2: String): Image = coroutineScope{
   val deferred1 = async{loadImage(name)}
   val deferred2 = async{loadImage(name2)}
  combineImages(deferred1.await(), deferred2.await())
// 합치는 거라 하나가 실패하면 의미가 없기때문에
// 코루틴 문맥에서 실행하여 자식 코루틴으로 구성한다면 예외를 부모에게 전달하고 모든 자식 코루틴을 취소할 수 있음
}



### 스코프의 취소와 예외처리

- cancel(), cancelChildren()

```kotlin
val scope = CoroutineScope
val routine1 = scope.launch{}
val routine2 = scope2.async{}
scope.cancel() // 스코프 내 r1,r2 모두 취소
// 또는
scope.cancelChildren() // 하위 자식들 모두 취소
```

- 예외처리

```kotlin
try{
}catch(e:CancellationException){
}
```

### 코루틴 실행시간 지정

- 실행시간 제한
`withTimeout(시간값){}` 특정시간값 동안만 수행하고 블럭을 끝냄(시간되면 TimeoutCancellationException 예외 발생)
`withTimeoutOrNull(시간값){}` 동작은 withTimeout과 동일한데 시간되도 예외발생하지 않고 null을 반환함


```kotlin
val result = withTimeoutOrNull(1300L){
        repeat(1000){
            println("I'm sleeping $it")
            delay(500L)
        }
        "Done" //코루틴 블럭이 완료되면 이 값이 result에 반환됨
    }
    println("Result is $result") // null
```



























