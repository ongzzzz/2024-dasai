### 메인화면
# 캐릭터 선택창 ("url:/character/cn)
# @GetMapping ("/character/{characterType}/cn or kr") return List<String> content JSON;

### 스토리 화면
# 스토리 화면 @GetMapping ("/url: /story/{storyId}/cn or kr") return 

### 퀴즈 화면 
# 퀴즈화면 닉네임 INPUT 창, 프론트에서 닉네임을 저장하여 사용자가 시작하기 버튼을 눌렀을때 프론트에서 검증을 한 후 백엔드에 요청을보냄
# 중간에 번역 x
# @GetMapping ("/quiz/start/{nickname}/cn or kr) return
   List<QuizDto> [ { 
   quizContent :, 
   Character :, 
   List<String> options [{

   }], 
   answer:, 
   answerDescription:, 
   userId: 
   }]





