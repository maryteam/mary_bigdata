/**
 * Created by gssflyaway on 16/1/12.
 */
// 基于准备好的dom，初始化echarts图表

$(document).ready(function () {
        $.getJSON("/api/single/cityuser?cname=京东白条" , function (result) {
            var myChart1 = echarts.init(document.getElementById('morris-area-chart1'));

            var provinces1 = new Array();
            var data1 = new Array();
            var ddd1 = new Array();
            //alert(result);
            $.each(result, function(i, field){
                //$("#testp").append(field.pName + " ");
                provinces1[i] = field.pName;
                data1[i] = field.num;
                var d= new Object();
                d.name=field.pName;
                d.value=field.num;
                ddd1[i]=d;
            });
            option1 = {
                title : {
                    text: '省份销量',
                    //subtext: '纯属虚构',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    x:'left',
                    data:['销量']
                },
                dataRange: {
                    min: 3000,
                    max: 30000,
                    x: 'left',
                    y: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    x: 'right',
                    y: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                roamController: {
                    show: true,
                    x: 'right',
                    mapTypeControl: {
                        'china': true
                    }
                },
                series : [
                    {
                        name: '销量',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        itemStyle:{
                            normal:{label:{show:true}},
                            emphasis:{label:{show:true}}
                        },
                        data:ddd1

                    }
                ]
            };


// 为echarts对象加载数据
            myChart1.setOption(option1);
        });


});

