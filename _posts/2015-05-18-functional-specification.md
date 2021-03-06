---
layout:     post
title:      기능명세서
subtitle:   
date:       2015-05-18
header-img: "public/img/post-bg.jpg"
---

###기능명세서  

![기능명세서 이미지](/Softcone/public/img/0518functional.png)  

* 로딩 화면을 지나면 로그인 화면이 나옵니다. 로그인 화면에서는 지점마다 부여된 공동 ID로 로그인할 수 있습니다. 만약 아이디가 없는 새로 개점한 편의점인 경우, 회원가입할 수 있습니다.
* 로그인을 하면 공지 팝업창이 나옵니다. 팝업창에는 유효한 공지의 내용이 표시됩니다. 공지 팝업창을 종료하면 메인 화면이 나옵니다.
* 메인 화면에는 유통기한이 남아있는(폐기되지 않은) 제품 리스트가 있습니다. 사용자는 하단바를 통해서 공지, 설정, 메인, 검색, 바코드 화면으로 자유롭게 이동할 수 있습니다.
    - 공지 화면에서는 근무자가 등록한 공지 리스트가 있습니다. 이 화면에서 사용자는 새로운 공지를 등록할 수 있고, 공지의 유효여부를 변경할 수 있습니다.
    - 설정 화면에서는 유통기한 알람에 대한 푸시 수신 여부, 진동 알람 여부, 알람 시간을 설정할 수 있습니다. 또한 현재 접속한 ID를 로그아웃할 수 있습니다.
    - 검색 화면에서는 사용자가 필요로 하는 특정 제품을 검색할 수 있습니다.
    - 바코드 화면에서는 제품의 바코드를 인실할 수 있습니다. 또한 인식된 제품을 등록할 수 있습니다.
* 제품 등록은 기존에 제품이 있는 경우와 신규 제품인 경우로 나눠집니다.
    - 기존 제품이 있는 경우, 해당 제품의 정보를 입력할 필요 없이 유통기한 시간만 입력합니다.
    - 신규 제품인 경우, 제품명과 사진 등 새로운 제품의 정보와 유통기한 시간 모두 입력합니다.  
