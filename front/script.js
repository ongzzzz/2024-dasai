$(function(){ 

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