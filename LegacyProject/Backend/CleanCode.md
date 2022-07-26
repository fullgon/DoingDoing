## 1장 깨끗한 코드

### PPP

SPR, OCP, DIP

* SRP : 단일 책임 원칙

한 클래스는 하나의 책임만 가져야 한다.

* OCP

개방 폐쇄 원칙

* DIP

의존 관계 역전 원칙

* 추가 SOLID

객체 지향 5대 원칙 : 응집도는 높이고 결합도는 낮추자

SPR(Single Responsibility Principle) : 단일 책임 원칙

OCP(Open Closed Principle) : 개방 폐쇄 원칙

LSP(Liskov Substitution Principle) : 리스코프 치환 원칙

ISP(Interface Segregation Principle) : 인터페이스 분리 원칙

DIP(Dependency Inversion Principle) : 의존 역전 원칙

참고 자료

https://sjh836.tistory.com/159

### 캠프장은 처음 왔을 때 보다 더 깨끗하게 하고 떠나라.

한 번에 다 개선해야지 X

내가 이 기능 쓰기 전보단 깔끔하게 짜야지

이렇게 조금씩 개선하다보면 프로젝트가 꾸준히 좋은 코드로 발전해 나갈 거 임

## 2장 의미 있는 이름

### 의도를 분명히 밝혀라

이름을 보고

변수, 함수, 클래스의 존재 이유는?

수행 기능은? 사용 방법은?

이런 질문이 떠오르지 않도록 이름에 의도를 담고 있어야함

AS-IS-1

```
public List<int[]> getThem(){
    List<int[]> list1 = new ArrayList<int[]>();
    
    for (int[] x: theList)
        if(x[0] == 4)
            list1.add(x):
    return list1;
}
```

AS-IS-2

```
public List<int[]> getFlaggedCells(){
    List<int[]> flaggedCells = new ArrayList<int[]>();
    for (int[] cell : getBoard)
        if (cells[STATUS_VALUE] == FLAGGED)
            flaggedCells.add(cell);
        return flaggedCells;
}
```

TO-BE

```
public List<Cell> getFlaggedCells(){
    List<Cell> flaggedCells = new ArrayList<Cell>();
    for (Cell cell:getBoard)
        if (cell.isFlagged())
            flaggedCells.add(cell);
        return flaggedCells;
}
```

### 그릇된 정보를 피해라

코드의 의미를 흐리는 그릇된 단서를 코드에 남기지 말아야 함

* 아무리 유명한 약어라 해도 약어는 적합하지 않음

빗변(hypotenuse) 를 hp 로 하면 그릇된 정보를 제공할 수 있다.

* 서로 흡사한 이름 사용하지 않도록 주의

XYZControllerForEfficientHandlingOfStrings 와 XYZControllerForEfficientStorageOfStrings 이름만 보고 구분 가능?

* 유사한 개념은 유사한 표기법 사용

일관성이 떨어지는 표기법은 그릇된 정보

+유사한 코드는 유사한 이름으로, 명확하게 구분 된다면 자동 완성이 굉장히 유용해짐

끔찍한 예제 - 알파벳 L 과 O 를 조심하자.

```
int a = 1;
if ( 0 == l)
    a = Ol;
else
    l = 01;
```

### 의미있게 구분하라

### 발음하기 쉬운 이름을 사용하라

### 검색하기 쉬운 이름을 사용하라

### 인코딩을 피하라

### 자신의 기억력을 자랑하지 마라

### 클래스 이름

### 메서드 이름

### 기발한 이름을 피하라

### 한 개념에 한 단어를 사용하라

### 말 장난 하지 마라

### 해법 영역에서 가져온 이름을 사용하라

### 문제 영역에서 가져온 이름을 사용하라

### 의미 있는 맥락을 추가하라

### 불필요하나 맥락을 없애라

