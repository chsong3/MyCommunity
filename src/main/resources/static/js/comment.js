function post() {
    var content = $("#comment_content").val();
    var questionId = $("#question_id").val();
    if (!content){
        alert("输入内容不能为空~~~~");
        return;
    }
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
            alert("进入");
            if (response.code===200){
                alert("asfd");
                window.location.reload();
            }else if (response.code===2003){
                var isAccepted = confirm(response.message);
                if (isAccepted){
                    window.open("http://github.com/login/oauth/authorize?client_id=Iv1.17c11c00c1e1f800&redirect_uri=http://localhost:8088/callback&scope=user&state=1");
                    window.localStorage.setItem("closable",true);
                }
            }else {
                confirm(response.message);
            }
        },
        dataType:"JSON"
    });
}
