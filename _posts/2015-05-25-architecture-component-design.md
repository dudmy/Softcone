---
layout:     post
title:      상세설계서 - 아키텍쳐 & 컴포넌트 설계
subtitle:   
date:       2015-05-25
header-img: "public/img/post-bg.jpg"
---

###아키텍쳐 설계  

'편해? 편앱!(App)'의 Active System Component로는 User와 App이 있다. Backend Service인 Parse는 Active System Component인 Server와 Passive System Component인 Files Storage의 역할을 담당한다. User는 App과 양방향 통신을 할 수 있다. 또한 App은 Parse에 Request 혹은 Response 하고, Parse를 통해 제품의 정보를 Read 한다.  

![아키텍쳐 이미지](/Softcone/public/img/0525architecture.png)  


---


###컴포넌트 설계  

'User'는 '공지', '메인', '검색', '바코드' 컴포넌트들을 실현할 수 있다. '공지사항 리스트', '메인 리스트', '검색 리스트', '제품등록'' 컴포넌트들은 각각의 상위 컴포넌트(인터페이스를 실현하는 컴포넌트)들과의 인터페이스를 통하여 컴포넌트의 기능이 이용된다. 각각의 리스트 컴포넌트는 'Server'에서 실현 가능하다. 또한 '푸시 서비스' 컴포넌트는 '편해? 편앱!(App)'이 설치되면 백그라운드에서 자동으로 실행된다.  

![컴포넌트 이미지](/Softcone/public/img/0525component.png)
