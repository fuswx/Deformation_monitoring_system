$(()=>{
    // // 观察者的选项(要观察哪些突变)
    // var config = { attributes: false, childList: true, subtree: true };
    // //为select的div设置监控者，监控div内容变化
    // var observer=new MutationObserver((mutationsList)=>{
    //     mutationsList.forEach((item,index)=>{
    //         if (item.type == 'childList') {
    //             console.log('有节点发生改变，当前节点的内容是：');
    //             console.log(item.target.innerHTML);
    //         } else if (item.type == 'attributes') {
    //             console.log('修改了'+item.attributeName+'属性');
    //         }
    //     })
    // })
    // observer.observe(document.getElementById("select-field"),config)


    $("#invokeSelect").change(()=>{
            console.log("sss")
            // $.ajax({
            //     url:"/monitorSystem/account/invokeUserByUserId.do",
            //     dataType: 'JSON',
            //     method: 'post',
            //     contentType: "application/json",
            //     data: {
            //         id: $(this).redata,
            //         limits: $(this).val()
            //     },
            //     success: function (res){
            //         console.log("ssss")
            //     }
            // })
    })
})
