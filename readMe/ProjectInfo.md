# 기능 소개

## [목차]


1. [모듈 괸리](#모듈-관리)
2. [테스트 환경](#테스트-환경)
3. [로그인 및 권한 체크](#로그인-및-권한-체크)
4. [인가 및 인증](#인가-및-인증)
5. [코딩 룸 게시판](#코딩-룸-게시판)
6. [코딩 테스트](#코딩-테스트)



<br/><br/><br/>

### 모듈 관리

---
 
 - core와 rest-api로 모듈 관리
    - core : 도메인 및 비즈니스 로직 관리
    - rest-api : view layer와 config 관리   
 
 
 <br/>

### 테스트 환경

---
 
 - Junit5 기반 단위 테스트, 통합 테스트 환경 구성


 <br/>

### 로그인 및 권한 체크

---

![image](https://user-images.githubusercontent.com/61372486/121284067-3117b480-c917-11eb-9b0e-4a7fe7ab06d6.png)

 - Spring Security, JWT 토큰 기반  
 
 - 접근 제한 Url은 authenticationProperties.json 으로 관리 
 
 ```java
{
  "/wcp/admin/**": [
    "ROLE_ADMIN"
  ],
  "/wcp/**": [
    "ROLE_MEMBER"
  ]
}
```
 
 
 - JWT Token 생성 시, 보안 강화 및 모바일 지원 위한 ip, uuid 값 추가
 
 - Token 인증 시, db 조회없이 즉시 Authentication에 권한 부여

```java
   private void setAuthentication(TokenDto dto) {
        if (!dto.getToken().isEmpty()) {
            JwtAuthentication authentication = new JwtAuthentication(dto);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
```
 
 <br/>

1. JwtTokenProvider 초기화시, DefaultSecretKeyProvider 통해 SecretKey 생성
2. 로그인이 되면 User PK, 권한, AES 암호화된 유저 IP, UUID v4 기반 생성하여 JWT 토큰과 UUID v4 Cookie 부여
3. 접근시, JwtAuthenticationFilter에서 토큰 검증 - 브라우저일 시, JWT Hash 및 IP 동일한지 검증 / 모바일일 시, 내부 UUID와 Cookie UUID가 일치하는지 검증
4. 권한에 따라 Authentication 생성 및 부여  







 <br/><br/>
 
### 페이징

---

![image](https://user-images.githubusercontent.com/61372486/121287555-bc477900-c91c-11eb-8704-c03f98e6d6b8.png)

 - 페이징은 재사용성을 고려해 PageCalculator와 PageService로 관리
 
 ```java
public interface PageService<T> {


    List<T> fetchByPage(int currentPage);
    List<T> fetchByPage(String currentPage);
    PageInfo fetchPageList(String currentPage);


}
```

- Service에 PageService 상속받은 후 PageCalculator 호출 및 JPA Paging 구현하면 페이징 완료
 
 



 <br/><br/>
 
### 코딩 룸 게시판

---
![image](https://user-images.githubusercontent.com/61372486/121285341-31b14a80-c919-11eb-8130-9845593da175.png)


 - Controller, Service, Repository layer 
 - JPA 활용한 방 개설, 삭제 변경 기능
 - 연관관계는 JPA Proxy 사용해 db connection 최적화
 
 ```java
   @Transactional
    public CodingRoom save(CodingRoom codingRoom, String userKey){
        if (StringUtils.isEmpty(userKey) || !StringUtils.isNumeric(userKey)) {
            throw new IllegalArgumentException("currentPage should not be empty or String. Please Check userKey : "+ userKey);
        }
        User user = userRepository.getOne(Long.valueOf(userKey));
        codingRoom.setUser(user);
        return save(codingRoom);
    }
```
 

<br/><br/>

### 코딩 테스트

---

![image](https://user-images.githubusercontent.com/61372486/121287458-8c987100-c91c-11eb-9d1d-3bd161f27917.png)

 - Controller, Service, Repository layer
 - 테스트 문제 생성, 수정, 채점 파일 등록, 삭제 기능 제공
 - 제출된 채점 zip 파일은 서버 특정 경로에 날짜별로 저장

```java
    @Override
    public CodeInputFile multiPartToEntity(MultipartFile file) throws IOException {
        String fileKey = UUID.randomUUID().toString();
        String fileName = fileKey + FilenameUtils.EXTENSION_SEPARATOR + FilenameUtils.getExtension(file.getOriginalFilename());
        File uploadFile = new File(FileUtils.resourceDirToday(Config.getProperty("com.wcp.parent.dir.path")),
                fileKey);
        if(!uploadFile.exists()){ uploadFile.mkdirs(); }
        uploadFile = new File(uploadFile, fileName);
        file.transferTo(uploadFile);

        return new CodeInputFile().setGivenName(file.getOriginalFilename())
                .setFileName(fileName)
                .setFileSize(file.getSize())
                .setPath(uploadFile.getParent());
    }
```

 - 제출 코드에 대한 채점은 Judge0 API 사용
 
 ```java
    @Override
    public JudegeResponseDto getSubmission(String token) throws Throwable {
        HttpResponse resp = HttpRequest.of()
                .post(getSubmissionUri(token))
                .addHeader(Judge.TOKEN_KEY, Judge.TOKEN_VALUE)
                .addHeader(Judge.HOST_KEY,  Judge.HOST_VALUE)
                .execute();

//        if(resp.getStatusLine().getStatusCode())
        //TODO. 상태코드에 따른 에러 분기 처리
        ResponseHandler<String> handler = new BasicResponseHandler();
        String result =  handler.handleResponse(resp);

        return gson.fromJson(result, JudegeResponseDto.class);
    }
```
 
 