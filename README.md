# 프로젝트를 통한 기본 개념 정리
1. 스프링 프레임워크 특징 또는 장점
- 스프링 프레임워크란?
자바 엔터프라이즈 개발을 편하게 해주는 경량화된 솔루션
즉 대규모 데이터 처리와 트랜잭션이 동시에 여러 사용자로 부터 행해지는 매우 큰 규모의 환경을 개발합니다.
스프링 프레임워크는 객체의 생성과 소멸 그리고 라이프사이클을 관리하며 언제든지 스프링 컨테이너로 부터 팰요한 객체를 가져와 사용할 수 있다.

- 스프링은 IOC기반이다.
IOC는 Inversion of Control의 약자로 제어의 역전이다.
일반적으로 프로그램을 개발할 때, 객체의 결정 및 생성 -> 의존성 객체 생성 -> 객체 내 메소드 호출을 반복한다.
이는 개발자가 객체를 구성하고 직접적으로 참여한다. 즉 모든 작업을 제어하는 구조이다.

하지만 IOC는 이 흐름의 구조를 바꾼다. 자신이 어디서 만들어지고 어떻게 사용되는지 모른다. 자신의 모든 권한을 다른 대상에 위임한다.
제어권한을 위임받은 특별한 객체에 의해 결정되고 만들어진다.
즉, 제어의 흐름을 사용자가 컨트롤 하지 않고 위임한 특별한 객체에 모든 것을 맡기는 것이다.

