function post() {
    var content = $("#comment_content").val();
    var questionId = $("#question_id").val();
    $.ajax({
        type:"post",
        url:"/comment",
        contentType:'application/json',
        data:JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success:function (response) {
            if (response.code==200){
                $("#comment_section").hide();
            }else {
                alert(response.message);
            }
        },
        dataType:"JSON"
    });
}
