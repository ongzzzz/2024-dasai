$(function(){ 

  //번역 버튼 부분
  $(".switch input[type='checkbox']").change(function() {
    if($(this).is(":checked")) {
      $(".korean").css({"color": "#f86551", "font-weight": "bold"});
      $(".chinese").css({"color": "#000000", "font-weight": "normal"});
    } else {
      $(".chinese").css({"color": "#f86551", "font-weight": "bold"});
      $(".korean").css({"color": "#000000", "font-weight": "normal"});
    }
  });


  //캐릭터 선택시
  $(".character").click(function(){
     $(".character").removeClass("clicked").css({"transform": "", "z-index": ""});
    
    $(this).addClass("clicked").css({"transform": "scale(1.2)", "z-index": 1});
    $(".character").not(this).addClass("blurred");
    $(".go_btn").hide();
    $("#popup").show();
    return false;
  })

  $("#popup").click(function(){
    $(".character.clicked").css({"transform": "", "z-index": ""}).removeClass("clicked");
    $(".character").removeClass("blurred");
    $(".go_btn").show();
    $("#popup").hide();
  })

  
})