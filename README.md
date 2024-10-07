# Cat-ch :: 생성형 AI 동화 제작 어플리케이션


## 프로젝트 개요
* <목적> Cat-ch의 목적은 사용자가 간단한 입력만으로 창의적이고 재미있는 이야기를 쉽게 제작할 수 있도록 돕는 것입니다. 특히나, AI 기반 스토리텔링을 통해 어린이와 어른 모두를 위한 맞춤형 이야기를 손쉽게 생성하며, 사용자에게 다양한 장르와 스타일을 선택할 수 있는 기능을 제공합니다. 이를 통해 동화 제작의 복잡한 과정을 간소화하고, 높은 비용 없이도 누구나 창의적인 이야기를 만들어낼 수 있도록 돕는 것이 목표입니다.

* <기대 효과> Cat-ch를 통해 사용자들은 동화책 구매 부담을 덜고, 누구나 무료로 간편하게 창의적인 이야기를 제작할 수 있습니다. 부모는 자녀에게 맞춤형 동화를 제공할 수 있으며, 어른들은 자신의 경험을 바탕으로 이야기를 만들어 추억을 공유할 수 있습니다. 또한, 모바일 친화적인 인터페이스 덕분에 언제 어디서나 손쉽게 이야기 제작이 가능하여 사용자 편의성을 극대화합니다. 현재 웹 기반의 생성형 AI 스토리 제작 서비스는 활발히 제공되고 있지만, 모바일 시장에서는 이와 같은 서비스를 제대로 제공하는 애플리케이션이 거의 없는 상황입니다. Cat-ch는 이러한 모바일 시장의 틈새를 공략함과 동시에 꾸준한 버전 업데이트를 진행해 서비스를 진행할 것입니다. 이를 통해 Cat-ch는 단순한 이야기 제작 도구를 넘어, 사용자들이 개성 있고 독창적인 스토리를 손쉽게 창작하고 공유할 수 있는 플랫폼으로 자리매김할 것입니다.

* 참고자료
    * https://storyclass.ai/
    * https://www.canva.com/ko_kr/create/story-generator/
    * 신동광, 이용상, and 노원준. "생성형 AI 를 활용한 영어 스토리북 제작이 초등학생의 학습동기에 미치는 영향." 영어교과교육 22.4 (2023): 177-196.

### 주요 기능
* 이야기 제작 기능 : 사용자가 미리 제공된 프롬프트에 답을 입력하고, 원하는 이미지를 업로드하거나 제작한 뒤 다양한 장르 중 하나를 선택해 이야기를 제작하는 기능

### 부가 기능
* 이야기 보따리 : 사용자가 제작한 이야기들을 날짜 별로 모아놓는 저장소 기능
* 이야기 다운로드 : pdf 파일의 형태로 사용자의 이야기를 다운 받을 수 있으며 공유할 수 있는 기능
* 책 만들기 : 사용자가 제작한 여러 개의 이야기들을 책 형태로 묶어서 보여주는 서비스를 제공하는 기능

### 사용 시나리오
## **Step 1: 로그인 화면**

1-1. **아이디와 비밀번호 입력**

- 화면에는 아이디와 비밀번호를 입력할 수 있는 입력 칸이 제공됩니다.
- 사용자는 입력칸에 로그인 정보를 입력하고 로그인 버튼을 누릅니다.

1-2. **회원가입 및 아이디/비밀번호 찾기**

- 로그인 버튼 하단에는 회원가입 버튼과 "아이디/비밀번호 찾기" 링크가 있습니다.
- 필요한 경우 해당 링크를 통해 계정을 생성하거나 비밀번호를 찾을 수 있습니다.

1-3. **로그인 성공 시 메인화면으로 이동**

- 로그인에 성공하면 자동으로 메인화면으로 이동합니다.

---

## **Step 2: 메인화면**

2-1. **이야기 제작 기능**

- 메인화면에 두 가지 주요 기능이 표시됩니다.
- 첫 번째 기능은 이야기를 제작할 수 있는 제작소입니다. 이 "입장하기" 버튼을 눌러 이야기 제작을 시작할 수 있습니다.

2-2. **이야기 저장소 기능**

- 두 번째 기능은 사용자가 이전에 제작한 동화를 확인할 수 있는 이야기 보따리입니다.
- 이 버튼을 누르면 사용자가 저장한 이야기 목록이 갤러리의 형식으로로 표시됩니다.

---

## **Step 3: 이야기 만들기 화면**

3-1. **이야기 주제 입력**

- "이야기 주제를 선택하세요" 라는 질문이 표시되며, '판타지'. '과학 소설', '스릴러', '드라마', '모험' 등의 주제 중 하나를 선택 할 수 있습니다.
- 목록에 원하는 주제가 없을 시 사용자가 직접 선택할 수 있습니다.

3-2. **등장인물 입력**

- "등장인물을 설정해주세요" 라는 질문이 나타나며, 등장인물과 그 등장인물의 역할을 작성할 수 있습니다.
- "등장인물 추가하기" 버튼을 누르면 등장인물을 추가로 등록할 수 있습니다.
    - **김태완 -> 컴공 트러블메이커**
    - **이석호 -> 컴공 말썽쟁이**
    - **윤경민 -> 컴공 개구쟁이**
    - **권경원 -> 점잖고 예의바르고 성실한 대학생**
- 모든 등장인물의 역할을 입력한 후 "다음" 버튼을 누릅니다.

3-3. **플롯 작성**

- "떠오르는 스토리를 자유롭게 작성해 주세요" 라는 질문이 표시되고, 사용자가 생각하는 이야기의 줄거리를 대략적으로 작성할 수 있습니다..
- **EX) 태완, 경민, 석호는 항상 사고를 친다.**
- 줄거리 작성칸 하단에는 이전 단계에 작성해 두었던 등장인물들과 역할들이 표시되어 있습니다.
- 줄거리를 입력한 후 "다음" 버튼을 누릅니다.

3-4. **배경 설정**

- 사용자는 '분위기 설정', '기후 및 환경 설정', '시간 설정', '장소 설정' 의 탭에 이야기의 배경을 설정해서 작성할 수 있습니다.
- **어둡고 음침한 분위기**
- **극한의 한겨울**
- **고대 이집트**
- **피라미드 앞**
- 배경을 모두 설정한 뒤 "다음" 버튼을 누릅니다. 

3-5. **이야기 생성**

- "이야기 생성하기" 버튼을 클릭합니다.
- "이야기가 생성되고 있는 중입니다. 잠시만 기다려주세요."라는 알림 문구가 화면에 나타납니다.
- 생성된 이야기를 확인할 수 있고, 이야기가 마음에 들지 않을 경우 재생성이 가능합니다.
- 이야기가 마음에 들었을 경우 "다음" 버튼을 누릅니다.

3-6. **이미지 생성**

- 이야기에 관련된 이미지를 생성할 수 있으며 모델, 너비, 높이, 로고 유무 등을 선택할 수 있습니다.
- "이미지 생성" 버튼을 누릅니다.

3-7. **이야기 확인 및 저장**

- 생성된 이야기를 확인할 수 있으며, 이야기의 최하단에 "저장하기" 버튼이 표시됩니다.
- 저장하기 버튼을 누르면 메인화면으로 돌아갑니다.
---

## **Step 4: 이야기 보따리 화면**

4-1. **제작한 이야기 목록 확인**

- 사용자가 제작한 이야기들이 날짜별로 정리되어 표시됩니다.
- 사용자는 저장된 이야기 목록에서 원하는 이야기를 선택하여 다시 확인할 수 있습니다.

  
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
![KakaoTalk_20241001_124106810_02](https://github.com/user-attachments/assets/35f12db9-e98c-4304-90dc-cd76c0effe42)

![KakaoTalk_20241001_124106810_10](https://github.com/user-attachments/assets/5fd423e1-c763-41a5-a393-02a7bf76a00e)


## 개발 계획

