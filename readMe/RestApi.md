# API 



1. [Auth](#Auth)
2. [User](#User)
3. [CodingRoom](#CodingRoom)
4. [CodingTest](#CodingTest)
5. [CodeSubmit](#CodeSubmit)
6. [Judge](#Judge)



<br/><br/><br/>
 
 ### Auth
 
 ---

 - 로그인 
   - ```POST``` :  /wcp/auth   
 - 로그아웃
   - ```GET``` :  /wcp/auth
 - 로그인 여부
   - ```GET``` :  /wcp/auth/check  

<br/>
  
 
 ### User
 
 ---
 
  - 회원가입
    - ```POST``` :  /wcp/signup
    
  - 회원탈퇴
      - ```DELETE``` :  /wcp/user  
       
  - 아이디 찾기
       - ```GET``` :  /wcp/user/id 
       
  - 비밀번호 수정
       - ```PATCH``` :  /wcp/user/pw
       
  - 회원정보 조회
       - ```GET``` :  /wcp/user
 
 <br/>
   
  
  ### CodingRoom
  
  ---

 - 전체 조회
      - ```GET``` :  /wcp/coding/room
       
 - 방 등록
      - ```POST``` :  /wcp/coding/room
       
 - 특정 방 조회
      - ```GET``` :  /wcp/coding/room/{postId}
       
 - 방 페이지 조회
      - ```GET``` :  /wcp/coding/room/page/{pageNm}
       
 - 방 페이지 범위 조회
      - ```GET``` :  /wcp/coding/room/range/{pageNm}
       
 - 특정 방 정보 수정
      - ```PUT``` :  /wcp/coding/room/{postId}
       
 - 특정 방 삭제
      - ```DELETE``` :  /wcp/coding/room/{postId}
       
 <br/>
   
  
### CodingTest
  
---
 - 코딩 테스트 전체 조회
   - ```GET``` :  /wcp/coding/test
   
 - 코딩 테스트 등록
   - ```POST``` :  /wcp/coding/test
  
 - 코딩 테스트 조회
   - ```GET``` :  /wcp/coding/test/{postId}
  
 - 코딩 테스트 페이징 조회
   - ```GET``` :  /wcp/coding/test/page/{pageNm}
  
 - 코딩 테스트 페이징 범위 조회
   - ```GET``` :  /wcp/coding/test/range/{pageNm}
  
 - 코딩 테스트 수정
   - ```PUT``` :  /wcp/coding/test/{postId}
  
 - 코딩 테스트 삭제
   - ```DELETE``` :  /wcp/coding/test/{postId}   
  
 <br/>
   
  
  ### CodeSubmit
  
  ---
  
 - 제출 기록 등록
   - ```POST``` :  /wcp/coding/submit/{postId}   
  
 <br/>
     
    
 ### Judge
    
 ---
 
 - 토큰 생성 요청
   - ```POST``` :  /wcp/coding/api/{postId}
   
 - 토큰 정보 요청
   - ```GET``` :  /wcp/coding/api/{token}/{postId}