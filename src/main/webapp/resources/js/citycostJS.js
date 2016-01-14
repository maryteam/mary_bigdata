/**
 * Created by gssflyaway on 16/1/12.
 */
// 基于准备好的dom，初始化echarts图表

$(document).ready(function () {
    $.getJSON("/api/single/citycost?cname=京东白条" , function (result) {
        var myChart = echarts.init(document.getElementById('morris-area-chart2'));

        var provinces = new Array();
        var data = new Array();
        var ddd = new Array();
        //alert(result);
        $.each(result, function(i, field){
            //$("#testp").append(field.pName + " ");
            provinces[i] = field.pName;
            data[i] = field.cost;
            var d= new Object();
            d.name=field.pName;
            d.value=field.cost;
            ddd[i]=d;
        });
        var idx = 1;
        var option = {
            timeline : {
                data : [
                    //'2013-01-01', '2013-02-01', '2013-03-01', '2013-04-01', '2013-05-01',
                    { name:'2015-07-01', symbol:'emptyStar6', symbolSize:8 },
                ],
                label : {
                    formatter : function(s) {
                        return s.slice(0, 7);
                    }
                }
            },
            options : [
                {
                    title : {
                        text: '销售额分布变化',
                        //subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x:'center',
                        data:['销售额']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel'],
                                option: {
                                    funnel: {
                                        x: '25%',
                                        width: '50%',
                                        funnelAlign: 'left',
                                        max: 24000000
                                    }
                                }
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    series : [
                        {
                            name:['销量'],
                            type:'pie',
                            center: ['50%', '45%'],
                            radius: '50%',
                            data: (function(){

                                var res = [];
                                var len = 0;
                                for(var i=0,size=data.length;i<size;i++) {
                                    res.push({
                                        name: provinces[i],
                                        value: data[i]
                                    });
                                }
                                return res;
                            })()
                        }
                    ]
                }

            ]
        };



// 为echarts对象加载数据
        myChart.setOption(option);
    });


});

