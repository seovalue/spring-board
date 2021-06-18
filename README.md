# 게시판 만들기

## @SpringBootApplication
스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정한다.
특히 `@SpringBootApplication`이 있는 위치부터 설정들을 읽어가기 때문에 이 클래스는 항상
프로젝트의 최상단에 위치해야한다. `main`메소드의 `SpringApplication.run`으로 인해 내장 WAS를 실행한다.
> 스프링 부트의 내장 WAS는 톰캣으로 이루어져있다.

* 추가 학습해야할 것: WAS란? 톰캣이란?

