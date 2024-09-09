# Cat-ch :: 나만의 서명 만들기 어플리케이션


## 프로젝트 개요
* 전자 문서의 사용이 전 세계적으로 급증하면서 디지털 서명에 대한 수요가 빠르게 증가하고 있습니다. 2023년 디지털 서명 시장은 53억 4천만 달러로 평가되었으며, 2032년까지 연평균 성장률 41.0%로 시장 규모가 1,188억 8천만 달러에 이를 것으로 예상됩니다. 디지털 서명은 전자 문서와 거래에서 개인의 신원 및 메시지 출처를 인증하는 중요한 수단이 되었습니다. 특히, 코로나19 팬데믹 이후 원격 근무와 온라인 거래가 늘어나면서 디지털 서명은 일상 속 필수 요소로 자리잡고 있습니다. 이에 더해, 정부 차원의 규제 완화 및 지원이 디지털 서명 시장의 성장을 더욱 가속화하고 있습니다.
* 한국에서도 전자문서 산업은 비대면화와 디지털 전환으로 인해 매년 성장하고 있는데, 2020년 국내 전자문서 공급시장은 9조 6천851억 원에 달했습니다. 또한 전자문서 관리와 교환, 생성 등의 분야에서의 매출 증가와 함께, 전자 서명에 대한 수요 또한 꾸준히 확대되고 있습니다. 이러한 흐름 속에서 개인들이 자신만의 독특하고 아름다운 디지털 서명을 만들고자 하는 욕구도 증가하고 있습니다.
* 그래서 저희는 사람들이 타이핑한 글자 혹은 자신의 서명을 이용해 아름다운 캘리그래피 서명을 만들 수 있는 앱을 기획했습니다. 이 앱은 다양한 서체와 스타일을 인공지능을 활용하여 제공해 사용자들이 자신만의 개성 넘치는 서명을 생성할 수 있게 합니다. 또한, 이렇게 만들어진 서명은 물건에 이니셜로 새길 수 있거나, 명함에 적용할 수 있도록 활용 범위를 확장할 계획입니다. 이로써 개인의 개성을 더욱 돋보이게 하고, 디지털 서명이 단순한 인증 도구를 넘어 스타일과 브랜딩의 수단이 될 수 있도록 돕고자 합니다.
* 참고자료
    * Source: https://www.fortunebusinessinsights.com/ko/industry-reports/digital-signature-market-100356
    * https://www.etnews.com/20240722000074
    * https://m.blog.naver.com/ucert/221995084245
      
### 컨셉
* 타이핑을 한 글자나 사용자의 서명을 사용자만의 개성이 넘치는 캘리그래피 서명으로 만들어 주는 어플리케이션

### 주요 기능
* 사용자의 글씨를 폰트로 만드는 기능
* 글씨체 연습 기능
* 글씨 교정 기능
* 푸쉬 알림 기능

### 부가 기능
* 시각적인 그래프 : 사용자의 학습량을 시각적으로 표현함
* 사용자가 연습한 글씨 공유 : pdf나 이미지 파일 형식으로 사용자가 연습한 글씨를 다운 받아 인스타 같은 SNS에 공유
* 편지 쓰기 : 사용자가 연습한 글씨체를 사용해서 연인, 가족 등에게 편지를 쓸 수 있는 기능
* 글씨체 선택 기능 : 사용자가 연습하길 원하는 글씨체 선택 가능
* 사용자 맞춤 문장 업로드 : 사용자가 연습하길 원하는 문장을 업로드하여 필기 연습
  


## 팀원
|<img src="https://avatars.githubusercontent.com/u/85469656?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/74997175?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/115124830?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/100402802?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|
|김태완<br/>프로젝트 메니저<br/>[@kimtaewan22](https://github.com/kimtaewan22)|권경원<br/>프론트엔드<br/>[@Nikellodian](https://github.com/Nikellodian)|이석호<br/>벡엔드<br/>[@LLagoon3](https://github.com/LLagoon3)|윤경민<br/>인공지능<br/>[@YoonTree](https://github.com/YoonTree)|


## 기술 스택 & 프레임워크
<div align="center">

![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) 
![Kotlin](https://img.shields.io/badge/Kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white) 
![Python](https://img.shields.io/badge/Python-%233776AB.svg?style=for-the-badge&logo=python&logoColor=white) 
![Django](https://img.shields.io/badge/Django-%23092E20.svg?style=for-the-badge&logo=django&logoColor=white) 
![PyTorch](https://img.shields.io/badge/PyTorch-%23EE4C2C.svg?style=for-the-badge&logo=pytorch&logoColor=white) 
![Docker](https://img.shields.io/badge/Docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) 
![MySQL](https://img.shields.io/badge/MySQL-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white) 
![Figma](https://img.shields.io/badge/Figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white) 
</div>


## 아키텍쳐
### 모델
> Few-shot Font Generation with Multiple Localized Experts
- MX-Font (Park, Song, et al. ICCV 2021) [[pdf]](https://arxiv.org/abs/2104.00887) [[github]](https://github.com/clovaai/mxfont): generating fonts by employing multiple experts where each expert focuses on different local concepts.

## 동작 화면


## 개발 계획
- 저작권 문제가 없는 선정된 폰트 50가지를 토대로 mxfont 모델 재학습, 이미지와 타겟 폰트가 주어지면 모델은 모델은 likelihood 기반으로 모델이 학습한 폰트와 얼마나 유사한지 probability값을 출력하도록 함
- 글씨 교정 관련 기능 삭제 및 폰트체 학습과 캘리그래피 기능에 초점
