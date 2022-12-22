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
|             |       |
v             v       v
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
















































