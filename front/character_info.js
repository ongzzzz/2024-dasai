$(document).ready(function() {
    function getCurrentLanguage() {
        // 체크박스가 체크되어 있으면 'kr', 그렇지 않으면 'zh' 반환
        return $('.translate_btn input[type="checkbox"]').is(":checked") ? 'kr' : 'zh';
    }

    $(".character_select img").mouseenter(function() {
        var characterName = $(this).attr("character-name");
        var language = getCurrentLanguage(); 
        
        console.log("준비 완료");
        $.ajax({
            url: `http://15.164.230.127:8080/character/${characterName}/kr`,
            type: 'GET',
            dataType: 'json',
            success: function(response) {
                // 응답 데이터 처리: storyContents
                // 'introduce' 클래스를 가진 요소들을 찾아 순회하면서 데이터 바인딩
                $(".introduce").each(function(index) {
                    if (response.storyContents && response.storyContents[index]) {
                        $(this).text(response.storyContents[index]);
                    }
                });
            },
            error: function(error) {
                // 에러 처리
                console.error('Error fetching character info:', error);
                $("#characterInfo").html('<p>캐릭터 정보를 불러오는 데 실패했습니다.</p>');
            }
        });
    });
});