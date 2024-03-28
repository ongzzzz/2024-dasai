$(document).ready(function() {
    // 저장된 닉네임 로드
    var nickname = localStorage.getItem('currentNickname');
    // 닉네임을 기반으로 퀴즈 데이터 로드
    var quizData = JSON.parse(localStorage.getItem('quizData' + nickname));
    var currentQuizIndex = 0;
    var score = 0;
    var correctCharacterTypes = []; // 맞춘 문제의 캐릭터 유형을 저장할 배열

    function displayQuiz() {
        if (currentQuizIndex < quizData.length) {
            var currentQuiz = quizData[currentQuizIndex];

            // 문제와 옵션 업데이트
            $(".number").text("Q" + (currentQuizIndex + 1));
            $(".question").text(currentQuiz.quizContent);
            $('.answer div').each(function(index) {
                $(this).find('input[type="radio"]').prop('checked', false);
                $(this).find('.radio_label').text(currentQuiz.options[index]);
            });

            // 답변 확인 섹션 숨기기 및 문제 섹션 표시
            $(".an").hide();
            $(".main").show();
        } else {
            // 모든 퀴즈 완료 시 결과 표시
            displayResults();
        }
    }

    function checkAnswer() {
        var selectedAnswer = $('input[name="answer_1"]:checked').val();
        var correctAnswer = quizData[currentQuizIndex].answer.toString();

        if (selectedAnswer === correctAnswer) {
            score++;
            $(".ox").text("O");
            correctCharacterTypes.push(quizData[currentQuizIndex].characterType);
        } else {
            $(".ox").text("X");
        }
        $(".content p").html(quizData[currentQuizIndex].answerDescription);

        // 답변 확인 섹션 표시 및 문제 섹션 숨기기
        $(".main").hide();
        $(".an").show();
    }

    function displayResults() {
        $(".container").html(`<div class="score-display">퀴즈 완료! 당신의 점수는 ${score}/${quizData.length}입니다.</div><div>맞춘 캐릭터 유형: ${[...new Set(correctCharacterTypes)].join(', ')}</div>`);
    }

    // 옵션 선택 시 정답 확인
    $('.answer input[type="radio"]').on('change', function() {
        checkAnswer();
    });

    // "다음 문제" 버튼 클릭 이벤트
    $("#next_btn").on('click', function() {
        currentQuizIndex++;
        displayQuiz();
    });

    // 첫 번째 문제 표시
    displayQuiz();
});