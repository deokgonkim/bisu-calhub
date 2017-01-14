= calhub =

== 구조 및 설명 ==

 * maven 소스 디렉토리 구조를 사용합니다.
  * src : 소스 디렉토리의 최상위 경로입니다.
  * src/main : 실제 배포될 소스 경로입니다.
  * src/main/java : 실제 배포될 소스 중 java 파일의 경로입니다.
  * src/main/resources : 실제 배포될 소스 중 properties, xml 파일 등의 경로입니다.
  * src/main/webapp : 실제 배포될 java web module 소스 경로입니다. html, js, css 등과 WEB-INF 경로를 포함합니다.
  * src/test : 테스트 코드의 소스 경로입니다.
  * src/test/java : java 테스트 코드의 경로입니다.
  * src/test/resources : 테스트시 사용될 properties, xml 파일 등의 경로입니다.

 * spring webmvc 3.0을 사용하여, MVC구조를 가지는 애플리케이션입니다.
 * extjs 4.0을 화면 UI용 라이브러리로 사용합니다.
 * ical4j 라이브러리를 사용하여 ics 캘린더 처리를 합니다.

== 빌드 및 배포 ==

 * maven을 통해서 빌드합니다.
  * mvn clean : 빌드를 제거합니다.
  * mvn package : 빌드합니다.

 * maven tomcat plugin을 통해서 tomcat에 배포합니다.
  * mvn tomcat:deploy : 배포명령입니다.
