## MVC 패턴

### MVC 구성

- Model: 데이터를 조작(ex.데이터베이스)하기위한 구성요소로 컨트롤러와 뷰에서 사용함
- View: 눈에 보여지는 UI의 로직을 담당하는 구성요소
- Controller: 모델이 어떻게 처리할지 알려주며 화면의 로직처리 부분

Controller는 Model과 View가 무엇을 해야할 지를 알고 있음. Model과 View가 직접 연결되지 못하게 함.

### MVC 단점

다수의 View를 가지고 있을 수 있고, Model은 Controller를 통해 View와 연결됨.
Controller를 통해 하나의 View에 연결될 수 있는 Model도 여러 개가 될 수 있어 View와 Model이 서로 의존성을 띄게 됨.
복잡한 화면 + 복잡한 데이터의 구성이면 Controller에 다수의 Model과 View가 복잡하게 연결됨.
액티비티가 커지게 됨.

MVP는 View와 Presenter가 1:1동작하기 때문에 의존성이 강해진다는 단점.

<br>

## MVVM 패턴

### MVVM 구성

- Model
- View
- ViewModel: View를 표현하기 위한 모델

Command패턴과 Data Binding을 사용하면서 View와 View Model의 의존성을 줄여줌.

커맨드 패턴은 하나의 객체를 통해 여러객체들에 명령을 해야할 때 사용되는 패턴임. 요청을 캡슐화해서 커맨드 객체가 명령을 해야하는 객체들에 대한 의존성을 느슨하게 만듦.

Binding은 ViewModel의 속성과 특정 View의 속성을 바인딩시켜줌으로써 ViewModel의 속성이 변경될 때 마다 View를 업데이트 시켜줄 수 있음.


### 안드로이드 MVVM

View(Activity/Fragment)와 모델(Repository)가 분리되어 있고, 이 분리된 로직 사이에서
뷰의 이벤트에 따라 모델이 데이터를 반환/저장하도록 통신하는 뷰모델이 존재함.


|View|ViewModel|Model|
|---|---|---|
|event 발생시켜 data 요청||
||해당 데이터를 불러오는 모델의 메소드를 호출||
|||뷰모델에서 요청하는 값을 반환|
||모델로부터 전달받은 값ㅇ르 LiveData에 저장||
|LiveData를 감지해 저장된 값을 View에 출력|||

