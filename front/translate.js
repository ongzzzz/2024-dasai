$(document).ready(function() {
  function updateLanguageAndStyle() {
    if($('.translate_btn input[type="checkbox"]').is(":checked")) {
      // 체크박스가 체크되어 있으면 한국어로 설정
      $(".korean").css({"color": "#f86551", "font-weight": "bold"});
      $(".chinese").css({"color": "#000000", "font-weight": "normal"});
      $('[data-ko]').each(function() { $(this).text($(this).data('ko')); });
    } else {
      // 체크박스가 해제되어 있으면 중국어로 설정
      $(".chinese").css({"color": "#f86551", "font-weight": "bold"});
      $(".korean").css({"color": "#000000", "font-weight": "normal"});
      $('[data-zh]').each(function() { $(this).text($(this).data('zh')); });
    }
  }

  // 페이지 로드 시 localStorage에서 언어 설정을 확인하고 적용
  var preferredLanguage = localStorage.getItem('preferredLanguage');
  if(preferredLanguage === 'ko') {
    $('.translate_btn input[type="checkbox"]').prop('checked', true);
  } else {
    $('.translate_btn input[type="checkbox"]').prop('checked', false);
  }

  // 초기 언어와 스타일 적용
  updateLanguageAndStyle();

  $('.translate_btn input[type="checkbox"]').change(function() {
    updateLanguageAndStyle(); // 체크박스 상태 변경 시 언어와 스타일 업데이트
    
    // 변경된 언어 설정을 localStorage에 저장
    if($(this).is(":checked")) {
      localStorage.setItem('preferredLanguage', 'ko');
    } else {
      localStorage.setItem('preferredLanguage', 'zh');
    }
  });

  // 페이지가 완전히 닫힐 때 언어 설정을 초기화
  $(window).on('beforeunload', function() {
    // 현재 페이지가 완전히 닫힌 경우에만 localStorage에서 언어 설정을 제거
    if (performance.navigation.type === 1) {
      localStorage.removeItem('preferredLanguage');
    }
  });
});

