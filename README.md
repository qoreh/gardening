# [Gardening]

### 식물 관리 서비스
식물 관리에 필요한 일정들을 관리해주는 서비스


### [개발 기간]
2024.01.17 ~ 진행중(5주)

---

### [회원 가입 & 로그인, 로그아웃]

- `회원 가입`
- `정보 수정`
- `로그인`
- `로그아웃`
- `탈퇴`

### [이벤트]
 물 주기 / 분갈이 / 영양제 투여 / 가지치기 / 특별한 스케줄 등 사전에 등록되어있는 이벤트를 리스트로 상기시켜주는 기능
- `조회`  
      - 하루 / 일주일 / 1개월 단위로 스케줄 조회 가능(최대 현재 +3개월까지 조회)
- `알림`  
      - 전날 체크가 안 된 항목들이 있을 시에 알림을 줌
- `수행 완료 체크`  
      - 완료한 이벤트에 대해 체크 기능을 넣어 완료 / 미완료 구분하게 함
- `미루기`  
      - 해당 이벤트를 지정되어 있던 날짜에 실행하지 않을 경우 다른 날짜로 옮기도록 함
- `등록`  
      - 급수를 제외한 이벤트를 등록할 수 있는 기능으로 날짜와 해당되는 식물을 선택하여 원하는 이벤트를 등록함
- `수정`   
      - 이벤트의 내용 혹은 실행일을 변경하고 싶은 경우 수정할 수 있도록 함
- `삭제`   
      - 해당 이벤트가 실행되는 것을 원하지 않을 경우 삭제하도록 함
 

### [my 식물]
: 사용자가 관리를 원하는 식물을 등록 & 등록되어있는 식물에 대한 리스트업
- `리스트 조회`  
      - 등록되어있는 식물 리스트 반환
- `식물 등록`  
      - 대표사진(1장, 없으면 기본 이미지) / 들여온 날짜(선택옵션) / 종류(필수, 검색을 통해 선택) / 이름(별도의 수정이 없다면 종 이름으로 지정) / 급수 주기 / 기타 설명
- `식물 수정`  
      - 급수 주기, 이름, 사진, 기타 설명에 대한 항목을 수정
- `식물 삭제`  
      - 더이상 관리를 원하지 않는 식물을 삭제
- `상세 보기`  
      - 각 식물에 대한 상세


### [식물 도감]
: 검색을 통해 식물에 대한 정보를 얻을 수 있게 한다  
농사로(www.nongsaro.go.kr)에서 제공하는 실내정원용 식물 Open API 사용(식물 도감)

- `랭킹`  
        - 도감 페이지의 기본 값으로 많이 키우는 순위를 리스트업(Redis Cache의 sorted set사용)
- `검색`  
      - 원하는 식물에 대한 정보를 이름 값으로 검색
- `상세 정보`  
      - 검색 결과 중 원하는 항목에 대한 상세 정보를 확인. 

---

### [사용기술]
- Java
- Gradle
- Springboot
- Spring Security,JWT(회원가입, 로그인)
- MySQL
- Redis Cache
- Docker
- SSE(알림)
- Amazon s3(이미지)

---

### [Open API]
농사로(www.nongsaro.go.kr)에서 제공하는 실내정원용 식물 Open API 


---
### [ERD]
![gardening](https://github.com/qoreh/gardening/assets/143871233/1b595517-9c94-455f-bdd9-e643278b4843)



