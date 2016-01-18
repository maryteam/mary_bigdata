/**
 * Created by gssflyaway on 16/1/17.
 */

$(document).ready(function () {
    var myChart = echarts.init(document.getElementById('morris-area-chart1'));

    option = {
        color : [
            '#FBB367','#80B1D2','#FB8070','#CC99FF','#B0D961',
            '#99CCCC','#BEBBD8','#FFCC99','#8DD3C8','#FF9999',
            '#CCEAC4','#BB81BC','#FBCCEC','#CCFF66','#99CC66',
            '#66CC66','#FF6666','#FFED6F','#ff7f50','#87cefa',
        ],
        title : {
            text : '公司用户关联关系',
            //subtext: '数据来自财新网',
            //sublink: 'http://international.caixin.com/2013-09-06/100579154.html',
            x:'right',
            y:'bottom'
        },
        toolbox: {
            show : true,
            feature : {
                restore : {show: true},
                magicType: {show: true, type: ['force', 'chord']},
                saveAsImage : {show: true}
            }
        },
        tooltip : {
            trigger: 'item',
            formatter : function (params) {
                if (params.name && params.name.indexOf('-') != -1) {
                    return params.name.replace('-', ' ' + params.seriesName + ' ')
                }
                else {
                    return params.name ? params.name : params.data.id
                }
            }
        },
        legend : {
            data : [
                '京东',
                '神州租车',
                '腾讯',
                '名品屋',
                '艺龙',
                'e代驾',
                '滴滴',
                '联动优势',
                '支付宝',
                '淘宝',

                '',
                '关联'
            ],
            orient : 'vertical',
            x : 'left'
        },
        series : [
            {
                "name": "关联",
                "type": "chord",
                "showScaleText": false,
                "clockWise": false,
                "data": [
                    {"name": "京东"},
                    {"name": "腾讯"},
                    {"name": "神州租车"},
                    {"name": "名品屋"},
                    {"name": "艺龙"},
                    {"name": "e代驾"},
                    {"name": "滴滴"},
                    {"name": "联动优势"},
                    {"name": "支付宝"},
                    {"name": "淘宝"}
                ],
                "matrix": [
                    [0,45029,25876,1080,1421,1050,0,237,1558,1331],
                    [45029,0,11266,1149,1153,0,0,253,728,893],
                    [25876,11266,0,2473,2805,3169,1224,340,750,716],
                    [1080,1149,2473,0,0,0,0,0,52,95],
                    [1421,1153,2805,0,0,0,0,67,1609,146],
                    [1050,0,3169,0,0,0,0,11,170,26],
                    [0,0,1224,0,0,0,0,6,17312,19],
                    [237,253,340,0,67,11,6,0,248,7],
                    [1558,728,750,52,1609,170,17132,248,0,242],
                    [1331,893,716,95,146,26,19,7,242,0]
                ]
            }
        ]
    };


    myChart.setOption(option);

})