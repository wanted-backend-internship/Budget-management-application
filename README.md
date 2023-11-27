
<br>
<br>

<div align="center">
    <img src="https://github.com/wanted-backend-internship/budget-management-application/assets/119668620/5a2fd1cd-5e9b-4b6b-8e6a-6a75e8bb1ab3" width=350/>   
</div>

<br>
<br>
<br>
<br>

# 예산 관리 프로그램
## 서비스 개요
본 서비스는 사용자들이 개인 재무를 관리하고 지출을 추적하는 데 도움을 주는 애플리케이션입니다. 이 앱은 사용자들이 예산을 설정하고 지출을 모니터링하며 재무 목표를 달성하는 데 도움이 됩니다. 

<br>

## 서비스 설명
- 유저는 본 사이트에 들어와 회원가입을 통해 서비스를 이용합니다.
- 예산 설정 및 설계 서비스
    - 월별 총 예산을 설정합니다.
    - 카테고리 별 예산을 설계(=추천)하여 사용자의 과다 지출을 방지합니다.
- 사용자는 지출을 금액, 카테고리 등을 지정하여 등록 합니다. 이는 언제든지 수정 및 삭제 할 수 있습니다.
- 지출 컨설팅
    - 월별 설정한 예산을 기준으로 오늘 소비 가능한 지출 을 알려줍니다.
    - 매일 발생한 지출 을 카테고리 별로 안내받습니다.
- 지출 통계
    - 지난 달 대비, 지난 요일 대비, 다른 유저 대비 등 여러 기준 카테고리 별 지출 통계를 확인 할 수 있습니다.

<br>

## 개발 기간
- 23.11.18 ~ 23.11.21 수행
- 개발 기간은 `4` 일이 주어졌습니다.

<br>

## 사용 기술
### Backend
<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white" height="25px">  <img src="https://img.shields.io/badge/Mysql-4479A1?style=flat&logo=mysql&logoColor=white" height="25px"> <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=flat&logo=Gradle&logoColor=white" height="25px"> 
    <img src="https://img.shields.io/badge/Hibernate-59666C?style=flat&logo=Hibernate&logoColor=white" height="25px"> <img src="https://img.shields.io/badge/JWT Webtoken-black?style=flat&logo=JSON%20web%20tokens" height="25px"> <img src="https://img.shields.io/badge/IntelliJ IDEA-000000.svg?style=flat&logo=intellij-idea&logoColor=white" height="25px"> <br>
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=springBoot&logoColor=white" height="25px"> 
    <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=flat&logo=springSecurity&logoColor=white" height="25px"> <img src="https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=Postman&logoColor=white" height="25px">
  <img src="https://img.shields.io/badge/Redis-DC382D?style=flat&logo=Redis&logoColor=white" height="25px">
  
### Frontend
<img src="https://img.shields.io/badge/Vue.js-4FC08D?style=flat&logo=vuedotjs&logoColor=white" height="25px"> <img src="https://img.shields.io/badge/TypeScript-3178C6?style=flat&logo=typescript&logoColor=white" height="25px"> <img src="https://img.shields.io/badge/Prettier-F7B93E?style=flat&logo=prettier&logoColor=white" height="25px"> <img src="https://img.shields.io/badge/Sass-CC6699?style=flat&logo=sass&logoColor=white" height="25px"> <img src="https://img.shields.io/badge/ESLint-4B32C3?style=flat&logo=eslint&logoColor=white" height="25px"> 

<br>

## 구현 기능 시연
### 유저 기능
https://github.com/wanted-backend-internship/budget-management-application/assets/119668620/a65ee133-f111-4c60-91ff-0f69980a9984

https://github.com/wanted-backend-internship/budget-management-application/assets/119668620/d3692255-63f5-4bab-b8c2-83e4854cd502

### 예산 설정 및 설계
🔗 https://sieunnnn.oopy.io/0d1b5d82f5c24779b834ff641c92d4f0?pvs=4
### 지출 CRUD
🔗 https://sieunnnn.oopy.io/0d1b5d82f5c24779b834ff641c92d4f0?pvs=4
### 지출 조회
🔗 https://sieunnnn.oopy.io/0d1b5d82f5c24779b834ff641c92d4f0?pvs=4
- 조회별 데이터 형식 :
  🔗 https://sieunnnn.oopy.io/21fac8a7382e4b99b346b3f09444c8f4?pvs=4
### 지출 레포트
https://github.com/wanted-backend-internship/budget-management-application/assets/119668620/f5b27103-da9d-4053-93a4-355424007a0f

- 저번 달과 이번 달(현재 날짜까지)의 카테고리별 총 지출을 비교 합니다.
  - 카테고리의 경우 **이번 달**에 존재하는 것을 기준으로 계산합니다.
  - 결과가 양/음 인 경우 색을 달리하여 구분 하였습니다.
- 저번 주 해당 요일과 오늘의 총 지출을 비교 합니다.
  - 결과가 음인 경우 아래와 같은 화면을 출력합니다.
    
    <img width="376" alt="스크린샷 2023-11-26 오후 4 00 27" src="https://github.com/wanted-backend-internship/budget-management-application/assets/119668620/aa3fe352-eef8-4f0a-b8de-2a74e09cde83">


<br>

## 프로젝트 노션 링크
🔗 https://sieunnnn.oopy.io/1d48854cc69a4565bd1d9f55fc1cb400?pvs=4
- 다이어그램과 시연 영상을 확인할 수 있어요.
- 프로젝트를 진행하면서 한 고찰을 확인할 수 있어요.
- 프로젝트를 마치며 회고한 내용을 볼 수 있어요.

<br>

