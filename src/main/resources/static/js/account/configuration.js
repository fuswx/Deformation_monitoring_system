$(()=>{

    $('#leftContent,#rightContent').css("display",'none')
    $('#leftContent').transition('slide left',800)
    $('#rightContent').transition('slide right',800)

    //提交表单
    $("#formSubmit").click(()=>{
        if (confirm("确定要提交此表单吗？")) {
            let updateFlag=0;
            $(".contentLines input").each(function (i,e){
                updateFlag+=$(e).val()===null||$(e).val()===''?0:1;
            })
            if (updateFlag>0){
                $(".contentLines input").each(function (i,e){
                    if ($(e).val()===''){
                        $(e).val($(e).prop('placeholder'))
                    }
                })
                var datas=$(".contentLines input").serializeArray()
                var dataObj={};
                $.each(datas,function (index,data){
                    dataObj[data.name]=data.value
                })
                $.ajax({
                    url: "/monitorSystem/account/setReflectData.do",
                    dataType: 'JSON',
                    method: "post",
                    contentType: "application/json",
                    data: {
                        "reflectData": JSON.stringify(dataObj)
                    },
                    success:function (res) {
                        if (res===200){
                            alert("提交成功！")
                            window.location=window.location.href
                        }else {
                            alert("提交失败，请重试")
                        }
                    },
                    fail:()=>{
                        alert("提交失败，请重试")
                    }
                })
            }else {
                alert("您未作任何修改！")
            }
        }
    })

    $("#reflectHistoryLook").click(()=>{
        window.location.href="/account/reflectHistory"
    })

    $("#fixHistoryLook").click(()=>{
        window.location.href="/account/fixHistory"
    })

    $("#returnLast").click(()=>{
        window.history.back()
    })
})