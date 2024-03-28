$(function() {
  function getCurrentLanguage() {
      // 체크박스가 체크되어 있으면 'kr', 그렇지 않으면 'zh' 반환
      return $('.translate_btn input[type="checkbox"]').is(":checked") ? 'kr' : 'zh';
  }

  // 캐릭터에 마우스를 올렸을 때
  $('.character').mouseenter(function() {
      // 팝업 표시
      $('.popup').fadeIn();

      // introduce 클래스를 가진 요소들의 텍스트를 빈 문자열로 초기화
      $(".introduce").text("");

      var characterName = $(this).attr("character-name");
      var language = getCurrentLanguage(); 

      console.log("준비 완료");
      $.ajax({
          url: `http://15.164.230.127:8080/character/${characterName}/kr`, // 언어 변수를 동적으로 사용
          type: 'GET',
          dataType: 'json',
          success: function(response) {
              $(".introduce").each(function(index) {
                  if (response.storyContents && response.storyContents[index]) {
                      $(this).text(response.storyContents[index]);
                  }
              });
          },
          error: function(error) {
              console.error('Error fetching character info:', error);
              $("#characterInfo").html('<p>캐릭터 정보를 불러오는 데 실패했습니다.</p>');
          }
      });
  });

  // 캐릭터에서 마우스를 뗐을 때
  $('.character').mouseleave(function() {
      // 팝업 숨기기
      $('.popup').fadeOut();

      // introduce 클래스를 가진 요소들의 텍스트를 비움
      $(".introduce").text("");
  });
});