/**
 * Created by gssflyaway on 16/1/12.
 */
// 基于准备好的dom，初始化echarts图表

$(document).ready(function () {
    $.getJSON("/api/single/costcount?cname=京东白条" , function (result) {
        var myChart = echarts.init(document.getElementById('costcountDiv'));

        var provinces = new Array();
        var data = new Array();
        var ddd = new Array();
        //alert(result);
        $.each(result, function(i, field){
            //$("#testp").append(field.pName + " ");
            provinces[i] = field.hour;
            data[i] = field.count;
            var d= new Object();
            d.name=field.hour;
            d.value=field.cost;
            ddd[field.hour]=field.count;
        });
        provinces.sort(function (a,b) {
            return a>b?1:-1
        })
        var idx = 1;
        var option = {
            title : {
                text: '时间分布',
                //subtext: '纯属虚构'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['销售量']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data :  provinces
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'销售量',
                    type:'bar',
                    data:ddd,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };




// 为echarts对象加载数据
        myChart.setOption(option);
    });


});

