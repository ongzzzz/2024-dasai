$('#go_quiz').click(function(){
  var num = $('#id').val()
  if(num == '') {
    alert('닉네임을 입력하세요.')
  }
  else if(num.length >= 12) {
    alert('닉네임은 12글자 이하여야 합니다.')
  }
})