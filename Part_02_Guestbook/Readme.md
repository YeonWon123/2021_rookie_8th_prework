# guestbook (방명록) 사이트 구현하기

## 주요 기능  
	목록 : 목록 화면 표시, 페이징/검색 기능 지원  
	등록 : 입력 화면 표시, 등록 처리   
	조회 : 조회 화면 표시    
	수정 : 수정/삭제 가능 화면 표시, 수정 처리  
	삭제 : 삭제 처리    

## 진행 과정
	1. 프로젝트 와이어프레임 구성
	2. 자동으로 처리되는 날짜/시간 설정
	3. 엔티티 클래스와 Querydsl 설정
	4. 서비스 계층과 DTO 구성
	5. 목록 처리 구현
	6. 컨트롤러와 화면에서의 목록 처리 구현
	7. 등록 페이지와 등록 처리 구현
	8. 방명록의 조회 처리 구현
	9. 방명록의 수정/삭제 처리 구현
	10. 검색 처리 구현

## 목록 페이지
1) 기본적인 목록 페이지, 1페이지   
localhost:8080/guestbook/list

2) 다음 페이지를 보려는 경우  
localhost:8080/guestbook/list?page={숫자}

3) 검색하는 경우  
localhost:8080/guestbook/list?page={숫자}&type={t|c|w|tc|tcw}&keyword={숫자} 

## 등록 페이지
localhost:8080/guestbook/register  

## 수정 페이지
localhost:8080/guestbook/modify?gno={숫자}&page={숫자} 
주의: {숫자} 에는 적절한 숫자가 들어가야 합니다.
